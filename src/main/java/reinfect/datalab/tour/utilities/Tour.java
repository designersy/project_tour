package reinfect.datalab.tour.utilities;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reinfect.datalab.tour.entities.Place;
import reinfect.datalab.tour.http.forms.PlaceForm;
import reinfect.datalab.tour.services.PlaceService;

import java.net.URLEncoder;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Tour {

    private final PlaceService service;

    @Value("${application.token.seoul}")
    private String seoulApiToken;

    private final Common common;

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
                    placeEntity.setPositionX(position.get(0));
                    placeEntity.setPositionY(position.get(1));

                    if (!position.isEmpty()){
                        try{
                            service.register(placeEntity);
                        } catch ( Exception e ) {
                        }

                    }
                } catch ( Exception e ){
                    System.out.println(slicedNewAddress);
                }
            }
        }
    }



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

}
