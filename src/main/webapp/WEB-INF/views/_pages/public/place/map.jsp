<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title><spring:message code="website.name"/></title>
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

<input type="number" id="length" value="${length}">

<div id="map" style="width:1000px;height:800px;"></div>

<script type="text/javascript">
	let makerList = []

	// 지도 생성 코드
	const mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
		center: new kakao.maps.LatLng(37.5788482938018, 126.968398565118), // 지도의 중심좌표
		level: 10 // 지도의 확대 레벨
	};
	const map = new kakao.maps.Map(mapContainer, mapOption);
	
	const btn = document.querySelector("#select_lang_btn");
	btn.onclick = function() {
		const selectLang = document.getElementById('select_language');
		const selectedIndex = selectLang.selectedIndex;
		const selectedValue = selectLang.options[selectedIndex].value;
		
		// 마커 지우는 코드
		for (var i = 0; i < makerList.length; i++){
			makerList[i].setMap(null)
		}

		makerList = [];

		let length = document.getElementById('length').value;
		console.log(length);
		for(var id = 1; id < length; id++) {
			$.ajax({
				type: 'Get',
				url: '/place/mapProcess',
				data: {
					"id":id,
					"language":selectLang
				},
				success: function(response) {
					if(response != null){
						// 매개변수들을 입력받아 마커를 만들어 makerList에 저장하는 함수
						createMarker(map, response["position"], response["name"], response["address"], response["access"], response["telephone"], makerList)
					} else {
						createMarker(map, "0 0", "데이터없음", "데이터없음", "데이터없음", "데이터없음", makerList)
					}
				}
			})
		}


		
		
	}

</script>


</body>
</html>