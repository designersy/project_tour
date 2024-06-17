<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="website.name"/></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<h1>업데이트 페이지</h1>
	
	<form:form action="/place/update" method="post">
	
		<input type="hidden" name="id" value="${place.id}"> <br>
		관광지명: <input name="name" value="${place.name}"> <br>
		언어: <input name="language" value="${place.language}"> <br>
		URL: <input name="postUrl" value="${place.postUrl}"> <br>
		주소: <input name="address" value="${place.address}"> <br>
		신주소: <input name="newAddress" value="${place.newAddress}"> <br>
		전화번호: <input name="telephone" value="${place.telephone}"> <br>
		웹사이트: <input name="website" value="${place.website}"> <br>
		운영 시간: <input name="businessTime" value="${place.businessTime}"> <br>
		운영 요일: <input name="businessDay" value="${place.businessDay}"> <br>
		휴무일: <input name="breakDate" value="${place.breakDate}"> <br>
		교통정보: <input name="access" value="${place.access}"> <br>
		태그: <input name="tags" value="${place.tags}"> <br>
		장애인 편의시설: <input name="handicap" value="${place.handicap}"> <br>
		
		<button>수정 완료</button>
	</form:form>
	
</body>
</html>