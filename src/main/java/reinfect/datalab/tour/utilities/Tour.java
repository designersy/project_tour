package reinfect.datalab.tour.utilities;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Tour {

    @Value("${application.token.seoul}")
    private String seoulApiToken;

    private final Common common;

    public void getSeoulApiData(int start, int end) throws Exception {
        String requestUrl = "http://openapi.seoul.go.kr:8088/" + seoulApiToken + "/json/TbVwAttractions/" + start + "/" + end + "/";

        Map<String, Object> data = common.convertJsonData(common.getRestApi(requestUrl, 8000));


        // 중첩된 Map 및 List에 접근
        Map<String, Object> address = (Map<String, Object>) data.get("address");
        System.out.println("City: " + address.get("city"));

        List<Map<String, Object>> phones = (List<Map<String, Object>>) data.get("phones");
        for (Map<String, Object> phone : phones) {
            System.out.println("Phone type: " + phone.get("type") + ", number: " + phone.get("number"));
        }
    }

}
