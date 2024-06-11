<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cf2ee77b85e83b5d204b042088fcf0c3"></script>
<script src="<c:url value="/assets/scripts/modules.js"/>"></script>


</head>
<body>

<form method="post">
	<select id="select_language">
		<option value="ko">Korean</option>
		<option value="en">English</option>
		<option value="zh-TW">zh-TW</option>
		<option value="zh-CN">zh-CN</option>
		<option value="ja">Japanese</option>
	</select>
	<button type="button" id="select_lang_btn"> 언어선택 </button>
</form>

<div id="map" style="width:1000px;height:800px;"></div>


<script type="text/javascript">

	// 지도 생성 코드
	const mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
		center: new kakao.maps.LatLng(37.5788482938018, 126.968398565118), // 지도의 중심좌표
		level: 2 // 지도의 확대 레벨
	};
	
	const map = new kakao.maps.Map(mapContainer, mapOption);

	const btn = document.querySelector("#select_lang_btn");

	let marker_list = []
	
	btn.onclick = function(){
		
		const selectLang = document.getElementById('select_language');
		const selectedIndex = selectLang.selectedIndex;
		const selectedValue = selectLang.options[selectedIndex].value;
		
		// 마커 지우는 코드
		for (var i = 0; i < marker_list.length; i++){
			marker_list[i].setMap(null)
		}
		
		marker_list = [];
		
		//

		// api에서 응답을 받아 함수로 전달하는 코드
		$.ajax({
			type: 'GET',
			url: 'http://openapi.seoul.go.kr:8088/724c59555163686135397278534771/xml/TbVwAttractions/1500/1968/',
			data: {},
			success: function(response) {
				
				const responseJson = xmlToJson(response);

				for(let i = 0; i < responseJson["TbVwAttractions"]["row"].length; i++){
					
					let address = responseJson["TbVwAttractions"]["row"][i]["NEW_ADDRESS"];
					let lang = responseJson["TbVwAttractions"]["row"][i]["LANG_CODE_ID"];
					
					if (lang === "ko") {
						if (address.indexOf("서울") == 6) {
							let dict = responseJson["TbVwAttractions"]["row"][i];
							addressSlice = address.slice(6, isBracket(address));	
							createMarker(map, addressSlice, dict, marker_list);
						} else if (address.indexOf("서울") == 8) {
							let dict = responseJson["TbVwAttractions"]["row"][i];
							addressSlice = address.slice(8, isBracket(address));
							createMarker(map, addressSlice, dict, marker_list);
						} else if (address.indexOf("서울") == 0) {
							let dict = responseJson["TbVwAttractions"]["row"][i];
							addressSlice = address.slice(8, isBracket(address));
							createMarker(map, addressSlice, dict, marker_list);
						}
					}
					
				}
			}
		})
	}



</script>

</body>
</html>