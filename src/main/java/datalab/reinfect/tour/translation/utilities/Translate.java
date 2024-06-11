package reinfect.datalab.translation.utilities;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Component
public class Translate {

	public void work() {
		String apiKey = "AIzaSyCL6EL26mN9NIMxiqdJhktQQRtP7IUexW0"; 
        String url = "https://translation.googleapis.com/language/translate/v2?key=" + apiKey;

        // 번역할 텍스트
        String text = "안녕하세요, 반갑습니다!";

        // 요청 JSON 생성
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("q", text);
        json.put("source", "ko");
        json.put("target", "en");
        json.put("format", "text");

        try {
            // 클라이언트 생성
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(url);

            // POST 요청 보내기
            Response response = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(json.toString(), MediaType.APPLICATION_JSON));

            if (response.getStatus() == 200) {
                // 응답 처리
                String responseBody = response.readEntity(String.class);
                JsonNode responseJson = mapper.readTree(responseBody);
                String translatedText = responseJson.path("data").path("translations").get(0).path("translatedText").asText();
                System.out.println("Translated Text: " + translatedText);
            } else {
                System.out.println("Error: " + response.getStatus());
            }

            // 클라이언트 종료
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
