<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="ko">
<head>
    <jsp:include page="../../../_common/meta.jsp"/>
    <jsp:include page="../../../_layouts/management/links.jsp"/>
    <title>서울유람 - 배너관리</title>
</head>
<body class="d-flex flex-column">
    <jsp:include page="../../../_layouts/management/header.jsp"/>
    <div class="container">
        <header class="page-subject mb-4 py-5">
            <h3 class="m-0">배너 관리</h3>
            <p class="mb-0 mt-1 text-secondary">메인페이지 배너 정보를 관리합니다.</p>
        </header>
        <c:choose>
            <c:when test="${not empty items.content}">
                <div class="row">
                    <c:forEach var="item" items="${items.content}">
                        <div class="col-12 col-md-6">
                            <div class="card">
                                <img src="${item.image}" class="card-img-top" alt="배너 이미지">
                                <div class="card-body">
                                    <h5 class="card-title">${item.subject}</h5>
                                    <p class="card-text">${item.comment}</p>
                                    <a href="<c:url value="/management/banner/${item.id}"/>" class="btn btn-sm btn-outline-secondary">수정/상세</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="d-flex flex-column justify-content-center align-items-center py-5 no-data">
                    <img src="<c:url value="/assets/images/no_data.jpg" />" alt="데이터가 없습니다."/>
                    <p>등록된 배너가 없습니다.</p>
                </div>
            </c:otherwise>
        </c:choose>
        <hr/>
        <a href="<c:url value="/management/banner/add"/>" class="mb-5 btn btn-sm btn-primary">배너 신규 등록</a>
    </div>
    <jsp:include page="../../../_layouts/management/footer.jsp"/>
    <jsp:include page="../../../_layouts/management/scripts.jsp"/>
</body>
</html>