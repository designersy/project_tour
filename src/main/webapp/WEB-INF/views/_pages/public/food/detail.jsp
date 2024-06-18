<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="ko">
<head>
	<jsp:include page="../../../_common/meta.jsp"/>
	<jsp:include page="../../../_layouts/public/links.jsp"/>
	<title><spring:message code="website.name"/></title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../../../_layouts/public/header.jsp"/>
<hr class="my-0"/>
<div class="container">
	<header class="page-subject mb-4 py-5">
		<h3 class="m-0">
			<spring:message code="subject.food"/>
		</h3>
		<p class="mb-0 mt-1 text-secondary">
			<spring:message code="subject.food.description"/>
		</p>
	</header>
	<main class="page-content">
<div>
	<h1>${food.name}</h1>
	<hr>
	
	<div>
		<p>주소 ${food.address} / ${food.newAddress}</p>
		<p>오시는길 ${food.access}</p>
		<p>이용 시간: ${food.businessTime}</p>
		<p>웹사이트: ${food.website}</p>
		<p>대표 메뉴: ${food.mainFood}</p>
		<p>${food.contentUrl}</p>
		<p>전화번호 ${food.telephone}</p>
		<p>생성일 ${food.created}</p>
		<p>수정일 ${food.updated}</p>
	</div>
	
	<a href="/food/update?id=${food.id}">수정</a> <a href="/food/delete?id=${food.id}">삭제</a>
</div>

<div>
	<form action="/food/insert" method="post">
		<input type="hidden" name="id" value="${food.id}">
		<textarea name="content" rows="2" cols="10">
		</textarea>
		<select name="score">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>
		<button>리뷰 작성</button>
	</form>
</div>

<div>
	<input type="hidden" id="name" value="${food.name}">
	<input type="hidden" id="address" value="${food.address}">
	<input type="hidden" id="access" value="${food.access}">
	<input type="hidden" id="telephone" value="${food.telephone}">
	<input type="hidden" id="position" value="${food.position}">
</div>

<c:choose>
	<c:when test="${not empty food.foodReviews}">
		<c:forEach items="${food.foodReviews}" var="review">
			<p>${review.content}</p>
		</c:forEach>
	</c:when>

	<c:otherwise>
		<p>데이터 없음</p>
	</c:otherwise>
</c:choose>


<hr>

	<div id="map" style="width:1000px;height:800px;"></div>
	</main>
</div>
<script type="text/javascript">

	const name = document.getElementById('name').value;
	const address = document.getElementById('address').value;
	const access = document.getElementById('access').value;
	const telephone = document.getElementById('telephone').value;
	const position = document.getElementById('position').value;

	const lonlat = latlon(position);

	// 지도 생성 코드
	const mapContainer = document.getElementById('map'), // 지도를 표시할 div
			mapOption = {
				center: new kakao.maps.LatLng(lonlat[1], lonlat[0]), // 지도의 중심좌표
				level: 3 // 지도의 확대 레벨
			};
	const map = new kakao.maps.Map(mapContainer, mapOption);


	createMarker(map, position, name, address, access, telephone);
</script>


<script type="text/javascript">

</script>

</body>
</html>