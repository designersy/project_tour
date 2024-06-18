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
<h1>데이터 추가 페이지</h1>
	<form:form action="/place/insert" method="post">

		언어: <input name="language" value="기본 데이터"> <br>
		관광지명: <input name="name" value="기본 데이터"> <br>
		URL: <input name="postUrl" value="기본 데이터"> <br>
		주소: <input name="address" value="기본 데이터"> <br>
		신주소: <input name="newAddress" value="기본 데이터"> <br>
		전화번호: <input name="telephone" value="기본 데이터"> <br>
		웹사이트: <input name="website" value="기본 데이터"> <br>
		운영 시간: <input name="businessTime" value="기본 데이터"> <br>
		운영 요일: <input name="businessDay" value="기본 데이터"> <br>
		휴무일: <input name="breakDate" value="기본 데이터"> <br>
		교통정보: <input name="access" value="기본 데이터"> <br>
		태그: <input name="tags" value="기본 데이터"> <br>
		태그: <input name="hadicap" value="기본 데이터"> <br>

		<button>데이터 추가</button>
	</form:form>
	</main>
</div>
</body>
</html>