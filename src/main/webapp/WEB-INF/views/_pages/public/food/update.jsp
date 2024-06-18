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
<h1>업데이트 페이지</h1>
	
	<form:form action="/food/update" method="post">
	
		<input type="hidden" name="id" value="${food.id}"> <br>
		관광지명: <input name="name" value="${food.name}"> <br>
		언어: <input name="language" value="${food.language}"> <br>
		URL: <input name="postUrl" value="${food.contentUrl}"> <br>
		주소: <input name="address" value="${food.address}"> <br>
		신주소: <input name="newAddress" value="${food.newAddress}"> <br>
		전화번호: <input name="telephone" value="${food.telephone}"> <br>
		웹사이트: <input name="website" value="${food.website}"> <br>
		운영 시간: <input name="businessTime" value="${food.businessTime}"> <br>
		교통정보: <input name="access" value="${food.access}"> <br>
		메인 메뉴: <input name="mainFood" value="${food.mainFood}"> <br>
		
		<button>수정 완료</button>
	</form:form>
	
</body>
</html>