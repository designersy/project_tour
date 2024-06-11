<%@page import="org.springframework.data.domain.Page"%>
<%@page import="datalab.reinfect.tour.entities.TourEntity"%>
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

	<!-- 리스트 파트 시작 -->
	
	<h1>검색</h1>
	<form action="/tour/search" method="get">
		<input name="keyword">
		<select name="criteria">
			<option>고유번호</option>
			<option>관광지명</option>
			<option>주소</option>
			<option>신주소</option>
			<option>태그</option>
			<option>장애인 편의시설</option>
		</select>
		<button>검색</button>
	</form>
	
	<hr>
	
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
			<c:forEach items="${paging.content}" var="TourEntity">
				<tr>
					<td>${TourEntity.POSTSJ}</td>
					<td>${TourEntity.ADDRESS }</td>
					<td>${TourEntity.SUBWAYINFO}</td>
					<td>${TourEntity.CMMNTELNO }</td>
					<td>${TourEntity.rating}</td>
					<td>${TourEntity.id }</td>
					<td><a href="/tour/detail?id=${TourEntity.id}">자세히 보기</a></td>
					<td>사진 들어갈 부분</td>
				</tr>
	
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 리스트 파트 끝 -->
	
	
	
	<!-- 페이징 파트 시작 -->
	<%
		Page<TourEntity> paging = (Page<TourEntity>) request.getAttribute("paging");

		int perLine = 5;

		// 페이지를 0이 아닌 1부터 숫자를 부여하기 위한 작업
		int number = paging.getNumber() + 1;

		int beginPageNum = ((number - 1) / perLine) * perLine + 1;

		int stopPageNum = beginPageNum + (perLine - 1);
		if (stopPageNum > paging.getTotalPages()) {
			stopPageNum = paging.getTotalPages();
		}

		pageContext.setAttribute("beginPageNum", beginPageNum);
		pageContext.setAttribute("stopPageNum", stopPageNum);
	%>

	<a
		href="/tour/list?page=${paging.hasPrevious() ? paging.number : paging.number+1}">
		← </a>

	<c:forEach begin="${beginPageNum }" end="${stopPageNum}" var="number">

		<a class="${number != paging.number+1 ? '' : 'currentPage'}"
			href="/tour/list?page=${number}">${number }</a>

	</c:forEach>

	<a
		href="/tour/list?page=${paging.hasNext() ? paging.number+2 : paging.number+1}">
		→ </a>
		
	<form action="/tour/list" method="get">
		<input type="number" name="page">
		<button>페이지로 이동</button>
	</form>
		
	<!-- 페이징 파트 끝 -->
</body>
</html>