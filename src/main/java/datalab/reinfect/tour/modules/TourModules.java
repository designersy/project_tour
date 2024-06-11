package datalab.reinfect.tour.modules;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io.BufferedReader;

public class TourModules {
	// 관광지 api를 startPage ~ endPage까지 읽은 후,
	// 각 줄을 List<String> 자료형인 response에 저장해 반환한다.
    public List<String> tourApi(int startPage, int endPage) {
        try {
            String apiURL = """
            		http://openapi.seoul.go.kr:8088/724c59555163686135397278534771/xml/TbVwAttractions/%d/%d
            		""".formatted(startPage, endPage);
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            List<String> response = new ArrayList<String>();
            String tempInputLine = "";
            while ((inputLine = br.readLine()) != null) {
            	if ((inputLine.indexOf("</") != -1) || (inputLine.indexOf("/>") != -1)) {
            		inputLine = tempInputLine + inputLine;
            		response.add(inputLine);
            		tempInputLine = "";
            	} else {
            		tempInputLine = tempInputLine + inputLine;
            	}

            }
            br.close();
            return response;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }
    
    // 한국어 데이터만을 골라서 dataList에 저장하여 반환하는 함수
    public List<List<String>> dataKo(List<String> data){
    	
    	List<List<String>> data_list = new ArrayList<List<String>>();
    	
    	for (int i = 0; i + 14 < data.size(); i++) {
    		if (data.get(i).indexOf("LANG_CODE_ID>ko") != -1) {
    			List<String> tempData = new ArrayList<String>();
    			
    			String POST_SN = cutData(data.get(i-1), "POST_SN");
    			tempData.add(POST_SN);
    			String POST_SJ = cutData(data.get(i+1), "POST_SJ");
    			tempData.add(POST_SJ);
    			String POST_URL = cutData(data.get(i+2), "POST_URL");
    			tempData.add(POST_URL);
    			String ADDRESS = cutData(data.get(i+3), "ADDRESS");
    			tempData.add(ADDRESS);
    			String NEW_ADDRESS = cutData(data.get(i+4), "NEW_ADDRESS");
    			tempData.add(NEW_ADDRESS);
    			String CMMN_TELNO = cutData(data.get(i+5), "CMMN_TELNO");
    			tempData.add(CMMN_TELNO);
    			String CMMN_FAX = cutData(data.get(i+6), "CMMN_FAX");
    			tempData.add(CMMN_FAX);
    			String CMMN_HMPG_URL = cutData(data.get(i+7), "CMMN_HMPG_URL");
    			tempData.add(CMMN_HMPG_URL);
    			String CMMN_USE_TIME = cutData(data.get(i+8), "CMMN_USE_TIME");
    			tempData.add(CMMN_USE_TIME);
    			String CMMN_BSNDE = cutData(data.get(i+9), "CMMN_BSNDE");
    			tempData.add(CMMN_BSNDE);
    			String CMMN_RSTDE = cutData(data.get(i+10), "CMMN_RSTDE");
    			tempData.add(CMMN_RSTDE);
    			String SUBWAY_INFO = cutData(data.get(i+11), "SUBWAY_INFO");
    			tempData.add(SUBWAY_INFO);
    			String TAG = cutData(data.get(i+12), "TAG");
    			tempData.add(TAG);
    			String BF_DESC = cutData(data.get(i+13), "BF_DESC");
    			tempData.add(BF_DESC);
    			
    			Date d = new Date();
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    			String now = sdf.format(d);
    			
    			tempData.add(now);
    			tempData.add(now);
    			
    			data_list.add(tempData);
    		}
    	}
    	
    	return data_list;
    }
    
    // cutWord 기준으로 입력된 data를 다듬어주는 함수
    // data: 가공할 String
    // cutWord: 가공 기준으로 삼을 단어 (컬럼명)
    public String cutData(String data, String cutWord) {
    	
    	if (data.indexOf("</"+cutWord) != -1) {
    		int wordLength = cutWord.length()+1;
    		data = data.substring(data.indexOf(cutWord+">")+wordLength, data.lastIndexOf("</"+cutWord));
    	} else {

    		data = "데이터 없음";
    	}
    	return data;
    }
}
