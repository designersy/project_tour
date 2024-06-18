package reinfect.datalab.tour.utilities;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reinfect.datalab.tour.http.forms.FoodForm;
import reinfect.datalab.tour.http.forms.PlaceForm;
import reinfect.datalab.tour.services.FoodService;
import reinfect.datalab.tour.services.PlaceService;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Tour {

    private final PlaceService service;
    private final FoodService foodService;

    @Value("${application.token.seoul}")
    private String seoulApiToken;

    private final Common common;

    @Value("${application.token.position}")
    private String positionApiToken;

    // 주소를 이용하여 좌표값을 얻어내는 함수
    // api키 분리 필요
    public List<String> getPositionApiData(String address) throws Exception {
        String requestUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + URLEncoder.encode(address);
        Map<String, Object> data = common.convertJsonData(common.getRestApi(requestUrl, 8000, "Authorization", "KakaoAK " + positionApiToken));
        List<Map<String, String>> documentList = (List<Map<String, String>>) data.get("documents");
        Map<String, String> positionMap = documentList.get(0);

        // 중첩된 Map 및 List에 접근
        List<String> position = new ArrayList<>();
        position.add(positionMap.get("y"));
        position.add(positionMap.get("x"));

        return position;
    }

    @Value("${application.token.google}")
    private String translateApiToken;

    // 언어는 en, ja, zh-CN 셋 중 하나를 선택해야한다.
    // 그렇지 않으면 오류 발생
    public String changeText(String text, String language) throws Exception {

        Translate translate = TranslateOptions.newBuilder().setApiKey(translateApiToken).build().getService();

        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage("ko"),
                        Translate.TranslateOption.targetLanguage(language),
                        Translate.TranslateOption.model("nmt")
                );

        String translated = translation.getTranslatedText();

        return translated;
    }

    // 관광지 api를 start 페이지 ~ end 페이지까지 읽어 데이터베이스에 곧바로 저장한다.
    public void getSeoulApiData(int start, int end) throws Exception {
        String requestUrl = "http://openapi.seoul.go.kr:8088/" + seoulApiToken + "/json/TbVwAttractions/" + start + "/" + end + "/";
        Map<String, Object> data = common.convertJsonData(common.getRestApi(requestUrl, 8000, "", ""));

        // 중첩된 Map 및 List에 접근
        Map<String, Object> objects = (Map<String, Object>) data.get("TbVwAttractions");
        List<Map<String, String>> places = (List<Map<String, String>>) objects.get("row");

        for (Map<String, String> place : places) {
            if (place.get("LANG_CODE_ID").equals("ko")){
                PlaceForm placeEntity = new PlaceForm();
                PlaceForm placeEntityEn = new PlaceForm();
                PlaceForm placeEntityZhCN = new PlaceForm();
                PlaceForm placeEntityJa = new PlaceForm();

                setAttributes(place, placeEntity, "ko");
                setAttributes(place, placeEntityEn, "en");
                setAttributes(place, placeEntityZhCN, "zh-CN");
                setAttributes(place, placeEntityJa, "ja");

                String newAddress = placeEntity.getNewAddress();
                String slicedNewAddress = "";

                if (newAddress.indexOf("서울") != -1){
                    if (newAddress.indexOf("(") == -1){
                        slicedNewAddress = newAddress.substring(newAddress.indexOf("서울"), newAddress.length());
                    } else {
                        slicedNewAddress = newAddress.substring(newAddress.indexOf("서울"), newAddress.indexOf("("));
                    }
                }

                // 좌표 정보
                try{
                    List<String> position = getPositionApiData(slicedNewAddress);
                    setPosition(placeEntity, position);
                    setPosition(placeEntityEn, position);
                    setPosition(placeEntityZhCN, position);
                    setPosition(placeEntityJa, position);

                    if (!position.isEmpty()){
                        try{
                            service.register(placeEntity);
                            service.register(placeEntityEn);
                            service.register(placeEntityZhCN);
                            service.register(placeEntityJa);
                        } catch ( Exception e ) {
                        }

                    }
                } catch ( Exception e ){
                    System.out.println(slicedNewAddress);
                }
            }
        }
    }

    public PlaceForm setAttributes(Map<String, String> place,
                    PlaceForm placeEntity,
                    String lang) throws Exception {
        if (lang.equals("ko")){
            placeEntity.setLanguage("ko");
            placeEntity.setName(common.isEmpty(place.get("POST_SJ")));
            placeEntity.setPostUrl(common.isEmpty(place.get("POST_URL")));
            placeEntity.setAddress(common.isEmpty(place.get("ADDRESS")));
            placeEntity.setNewAddress(common.isEmpty(place.get("NEW_ADDRESS")));
            placeEntity.setTelephone(common.isEmpty(place.get("CMMN_TELNO")));
            placeEntity.setWebsite(common.isEmpty(place.get("CMMN_HMPGURL")));
            placeEntity.setBusinessTime(common.isEmpty(place.get("CMMN_USETIME")));
            placeEntity.setBusinessDay(common.isEmpty(place.get("CMMN_BSNDE")));
            placeEntity.setBreakDate(common.isEmpty(place.get("CMMN_RSTDE")));
            placeEntity.setAccess(common.isEmpty(place.get("SUBWAY_INFO")));
            placeEntity.setTags(common.isEmpty(place.get("TAG")));
            placeEntity.setHandicap(common.isEmpty(place.get("BF_DESC")));
        } else {
            placeEntity.setLanguage(lang);
            placeEntity.setName(changeText(common.isEmpty(place.get("POST_SJ")), lang));
            placeEntity.setPostUrl(common.isEmpty(place.get("POST_URL")));
            placeEntity.setAddress(changeText(common.isEmpty(place.get("ADDRESS")), lang));
            placeEntity.setNewAddress(changeText(common.isEmpty(place.get("NEW_ADDRESS")), lang));
            placeEntity.setTelephone(common.isEmpty(place.get("CMMN_TELNO")));
            placeEntity.setWebsite(common.isEmpty(place.get("CMMN_HMPGURL")));
            placeEntity.setBusinessTime(changeText(common.isEmpty(place.get("CMMN_USETIME")), lang));
            placeEntity.setBusinessDay(changeText(common.isEmpty(place.get("CMMN_BSNDE")), lang));
            placeEntity.setBreakDate(changeText(common.isEmpty(place.get("CMMN_RSTDE")), lang));
            placeEntity.setAccess(changeText(common.isEmpty(place.get("SUBWAY_INFO")), lang));
            placeEntity.setTags(changeText(common.isEmpty(place.get("TAG")), lang));
            placeEntity.setHandicap(changeText(common.isEmpty(place.get("BF_DESC")), lang));
        }

        return placeEntity;
    }


    // 맛집 api를 start 페이지 ~ end 페이지까지 읽어 데이터베이스에 곧바로 저장한다.
    public void getMatApiData(int start, int end) throws Exception {
        String requestUrl = "http://openapi.seoul.go.kr:8088/" + seoulApiToken + "/json/TbVwRestaurants/" + start + "/" + end + "/";
        Map<String, Object> data = common.convertJsonData(common.getRestApi(requestUrl, 8000, "", ""));

        // 중첩된 Map 및 List에 접근
        Map<String, Object> objects = (Map<String, Object>) data.get("TbVwRestaurants");
        List<Map<String, String>> places = (List<Map<String, String>>) objects.get("row");

        for (Map<String, String> place : places) {
            if (place.get("LANG_CODE_ID").equals("ko")){
                FoodForm foodEntity = new FoodForm();
                FoodForm foodEntityEn = new FoodForm();
                FoodForm foodEntityZhCN = new FoodForm();
                FoodForm foodEntityJa = new FoodForm();

                setAttributesMat(place, foodEntity, "ko");
                setAttributesMat(place, foodEntityEn, "en");
                setAttributesMat(place, foodEntityZhCN, "zh-CN");
                setAttributesMat(place, foodEntityJa, "ja");

                String newAddress = foodEntity.getNewAddress();
                String slicedNewAddress = "";

                if (newAddress.indexOf("서울") != -1){
                    if (newAddress.indexOf("(") == -1){
                        slicedNewAddress = newAddress.substring(newAddress.indexOf("서울"), newAddress.length());
                    } else {
                        slicedNewAddress = newAddress.substring(newAddress.indexOf("서울"), newAddress.indexOf("("));
                    }
                }

                // 좌표 정보
                try{
                    List<String> position = getPositionApiData(slicedNewAddress);
                    setPosition(foodEntity, position);
                    setPosition(foodEntityEn, position);
                    setPosition(foodEntityZhCN, position);
                    setPosition(foodEntityJa, position);

                    if (!position.isEmpty()){
                        try{
                            foodService.register(foodEntity);
                            foodService.register(foodEntityEn);
                            foodService.register(foodEntityZhCN);
                            foodService.register(foodEntityJa);
                        } catch ( Exception e ) {
                        }

                    }
                } catch ( Exception e ){
                    System.out.println(slicedNewAddress);
                }
            }
        }
    }


    public FoodForm setAttributesMat(Map<String, String> place,
                                 FoodForm foodEntity,
                                   String lang) throws Exception {
        if (lang.equals("ko")){
            foodEntity.setLanguage("ko");
            foodEntity.setName(common.isEmpty(place.get("POST_SJ")));
            foodEntity.setContentUrl(common.isEmpty(place.get("POST_URL")));
            foodEntity.setAddress(common.isEmpty(place.get("ADDRESS")));
            foodEntity.setNewAddress(common.isEmpty(place.get("NEW_ADDRESS")));
            foodEntity.setTelephone(common.isEmpty(place.get("CMMN_TELNO")));
            foodEntity.setWebsite(common.isEmpty(place.get("CMMN_HMPGURL")));
            foodEntity.setBusinessTime(common.isEmpty(place.get("CMMN_USETIME")));
            foodEntity.setAccess(common.isEmpty(place.get("SUBWAY_INFO")));
            foodEntity.setMainFood(common.isEmpty(place.get("FD_REPRSNT_MENU")));
        } else {
            foodEntity.setLanguage(lang);
            foodEntity.setName(changeText(common.isEmpty(place.get("POST_SJ")), lang));
            foodEntity.setContentUrl(common.isEmpty(place.get("POST_URL")));
            foodEntity.setAddress(changeText(common.isEmpty(place.get("ADDRESS")), lang));
            foodEntity.setNewAddress(changeText(common.isEmpty(place.get("NEW_ADDRESS")), lang));
            foodEntity.setTelephone(common.isEmpty(place.get("CMMN_TELNO")));
            foodEntity.setWebsite(common.isEmpty(place.get("CMMN_HMPGURL")));
            foodEntity.setBusinessTime(changeText(common.isEmpty(place.get("CMMN_USETIME")), lang));
            foodEntity.setAccess(changeText(common.isEmpty(place.get("SUBWAY_INFO")), lang));
            foodEntity.setMainFood(changeText(common.isEmpty(place.get("FD_REPRSNT_MENU")), lang));
        }

        return foodEntity;
    }

    public PlaceForm setPosition(PlaceForm placeEntity,
                                 List<String> position) throws Exception {
        placeEntity.setPositionX(position.get(0));
        placeEntity.setPositionY(position.get(1));

        return placeEntity;
    }

    public FoodForm setPosition(FoodForm foodEntity,
                                 List<String> position) throws Exception {
        foodEntity.setPositionX(position.get(0));
        foodEntity.setPositionY(position.get(1));

        return foodEntity;
    }
}
