<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
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
</body>
</html>