package reinfect.datalab.tour.utilities;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class Weather {

    @Value("${application.token.portal}")
    private String dataPortalKey;

    private final Common common;

    public String weatherData() throws Exception {
        String requestUrl = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst" +
                            "?serviceKey=" + dataPortalKey +
                            "&pageNo=1&numOfRows=300&dataType=JSON" +
                            "&base_date=" + nowDate() + "&base_time=" + nowHour() + "&nx=60&ny=127";

        return common.getRestApi(requestUrl, 8000, "", "");
    }

    private String nowDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return now.format(formatter);
    }

    private String nowHour() {
        LocalDateTime now = LocalDateTime.now().minusHours(1L);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        return now.format(formatter) + "00";
    }

}
