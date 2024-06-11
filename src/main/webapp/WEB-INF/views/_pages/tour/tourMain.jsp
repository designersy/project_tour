<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Main page</title>
</head>
<body>
	<!-- 리스트 파트 시작 -->
	
	<table border=1>
		<thead>
			<tr>
				<th>관광지명</th>
				<th>주소</th>
				<th>주변 지하철</th>
				<th>전화번호</th>
				<th>별점</th>
				<th>댓글 수(아이디 값으로 임시 설정)</th>
				<th>자세히 보기</th>
				<th>비고(사진)</th>
			</tr>
		</thead>
	
		<tbody>
			<c:forEach var="tourEntity" items="${paging.content}">
				<tr>
					<td>${tourEntity.POSTSJ}</td>
					<td>${tourEntity.ADDRESS }</td>
					<td>${tourEntity.SUBWAYINFO}</td>
					<td>${tourEntity.CMMNTELNO }</td>
					<td>${tourEntity.rating}</td>
					<td>${tourEntity.id }</td>
					<td><a href="/tour/detail?id=${tourEntity.id}">자세히 보기</a></td>
					<td>사진 들어갈 부분</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>