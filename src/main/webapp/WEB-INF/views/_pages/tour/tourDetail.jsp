<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<div>
	<h1>${tourEntity.POSTSJ}</h1>
	<h1>${tourEntity.POSTSN}</h1>
	
	<h3>별점 ${tourEntity.rating} </h3>
	<hr>
	
	<div>
		<p>주소 ${tourEntity.ADDRESS} / ${tourEntity.NEWADDRESS}</p>
		<p>장애인 편의시설 ${tourEntity.BFDESC}</p>
		<p>오시는길 ${tourEntity.SUBWAYINFO}</p>
		<p>운영요일 ${tourEntity.CMMNBSNDE}</p>
		<p>휴무일 ${tourEntity.CMMNRSTDE}</p>
		<p>이용 시간: ${tourEntity.CMMNUSETIME}</p>
		<p>홈페이지: ${tourEntity.CMMNHMPGURL}</p>
		<p>팩스번호: ${tourEntity.CMMNFAX}</p>
		<p>${tourEntity.POSTURL}</p>
		<p># ${tourEntity.TAG}</p>
		<p>전화번호 ${tourEntity.CMMNTELNO}</p>
		
		<p>생성일 ${tourEntity.createDate}</p>
		<p>수정일 ${tourEntity.updateDate}</p>
	</div>
</div>

</body>
</html>