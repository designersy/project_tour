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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cf2ee77b85e83b5d204b042088fcf0c3"></script>
	<script src="<c:url value="/assets/scripts/modules.js"/>"></script>

</head>
<body>

<div>
	<h1>${place.name}</h1>
	<hr>
	
	<div>
		<p>주소 ${place.address} / ${place.newAddress}</p>
		<p>장애인 편의시설 ${place.handicap}</p>
		<p>오시는길 ${place.access}</p>
		<p>운영요일 ${place.businessDay}</p>
		<p>휴무일 ${place.breakDate}</p>
		<p>이용 시간: ${place.businessTime}</p>
		<p>홈페이지: ${place.website}</p>
		<p>${place.postUrl}</p>
		<p># ${place.tags}</p>
		<p>전화번호 ${place.telephone}</p>
		<p>생성일 ${place.created}</p>
		<p>수정일 ${place.updated}</p>
	</div>
	
	<a href="/place/update?id=${place.id}">수정</a> <a href="/place/delete?id=${place.id}">삭제</a>
</div>

<div>
	<input type="hidden" id="name" value="${place.name}">
	<input type="hidden" id="address" value="${place.address}">
	<input type="hidden" id="access" value="${place.access}">
	<input type="hidden" id="telephone" value="${place.telephone}">
	<input type="hidden" id="position" value="${place.position}">
</div>

<hr>

<div id="map" style="width:1000px;height:800px;"></div>

<script type="text/javascript">

	// 지도 생성 코드
	const mapContainer = document.getElementById('map'), // 지도를 표시할 div
			mapOption = {
				center: new kakao.maps.LatLng(37.5788482938018, 126.968398565118), // 지도의 중심좌표
				level: 10 // 지도의 확대 레벨
			};
	const map = new kakao.maps.Map(mapContainer, mapOption);

	const name = document.getElementById('name').value;
	const address = document.getElementById('address').value;
	const access = document.getElementById('access').value;
	const telephone = document.getElementById('telephone').value;
	const position = document.getElementById('position').value;

	createMarker(map, position, name, address, access, telephone);
</script>


<script type="text/javascript">

</script>

</body>
</html>