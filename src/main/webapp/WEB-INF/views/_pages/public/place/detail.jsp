<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="ko">
<head>
	<jsp:include page="../../../_common/meta.jsp"/>
	<jsp:include page="../../../_layouts/public/links.jsp"/>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cf2ee77b85e83b5d204b042088fcf0c3"></script>
	<title><spring:message code="website.name"/></title>
	<script src="<c:url value="/assets/scripts/modules.js"/>"></script>
</head>
<body class="d-flex flex-column">
<jsp:include page="../../../_layouts/public/header.jsp"/>
<hr class="my-0"/>
<div class="container">
	<header class="page-subject mb-4 py-5">
		<h3 class="m-0">
			<spring:message code="subject.tour"/>
		</h3>
		<p class="mb-0 mt-1 text-secondary">
			<spring:message code="subject.tour.description"/>
		</p>
	</header>
	<main class="page-content">

		<div>
	<h1>${place.name}</h1>
	<hr>
	
	<div>
		<ul>
			<li>
				<strong class="d-block">
					<spring:message code="page.place.detail.address"/>
				</strong>
				<p class="d-block">
					${place.newAddress}
					<c:if test="${not empty place.address}">
						<span>(${place.address})</span>
					</c:if>
				</p>
			</li>
			<li>
				<strong class="d-block">
					<spring:message code="page.place.detail.access"/>
				</strong>
				<p class="d-block">
					${place.access}
				</p>
			</li>
			<li>
				<strong class="d-block">
					<spring:message code="page.place.detail.business"/>
				</strong>
				<p class="d-block">
					${place.businessDay}
				</p>
			</li>
			<li>
				<strong class="d-block">
					<spring:message code="page.place.detail.rest"/>
				</strong>
				<p class="d-block">
					${place.breakDate}
				</p>
			</li>
			<li>
				<strong class="d-block">
					<spring:message code="page.place.detail.hour"/>
				</strong>
				<p class="d-block">
					${place.businessTime}
				</p>
			</li>
			<li>
				<strong class="d-block">
					<spring:message code="page.place.detail.handicap"/>
				</strong>
				<p class="d-block">
					${place.handicap}
				</p>
			</li>
			<li>
				<strong class="d-block">
					<spring:message code="page.tour.table.telephone"/>
				</strong>
				<p class="d-block">
					${place.telephone}
				</p>
			</li>
		</ul>
		<iframe src="${place.postUrl}" style="width:100%;height:64vh;border:1px solid #eaeaea;"></iframe>
		<p># ${place.tags}</p>
	</div>
	
	<a href="/place/update?id=${place.id}">수정</a> <a href="/place/delete?id=${place.id}">삭제</a>
</div>

		<div>
	<form:form action="/placeReview/insert" method="post">
		<input type="hidden" name="id" value="${place.id}">
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
	</form:form>
</div>

		<div>
			<input type="hidden" id="name" value="${place.name}">
			<input type="hidden" id="address" value="${place.address}">
			<input type="hidden" id="access" value="${place.access}">
			<input type="hidden" id="telephone" value="${place.telephone}">
			<input type="hidden" id="position" value="${place.position}">
		</div>

		<c:choose>
			<c:when test="${not empty place.placeReviews}">
				<c:forEach items="${place.placeReviews}" var="review">
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
<jsp:include page="../../../_layouts/public/footer.jsp"/>
<jsp:include page="../../../_layouts/public/scripts.jsp"/>
</body>
</html>