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
            <c:if test="${not empty error}">
                <br/><span>${error}</span>
            </c:if>
        </header>
        <form:form method="post" action="/management/banner" modelAttribute="banner" enctype="multipart/form-data">
            <div class="row">
                <div class="col my-3">
                    <form:label path="subject" cssClass="form-label">제목</form:label>
                    <form:input path="subject" cssClass="form-control form-control-lg"/>
                    <form:errors path="subject" cssClass="invalid-feedback d-block"/>
                </div>
                <div class="col my-3">
                    <form:label path="comment" cssClass="form-label">내용</form:label>
                    <form:input path="comment" cssClass="form-control form-control-lg"/>
                    <form:errors path="comment" cssClass="invalid-feedback d-block"/>
                </div>
                <div class="col my-3">
                    <form:label path="link" cssClass="form-label">연결 링크</form:label>
                    <form:input path="link" cssClass="form-control form-control-lg"/>
                    <form:errors path="link" cssClass="invalid-feedback d-block"/>
                </div>
                <div class="col my-3">
                    <label for="image" class="form-label">이미지</label>
                    <input type="file" id="image" name="image" class="form-control form-control-lg"/>
                </div>
            </div>
            <hr/>
            <button type="submit" class="btn btn-primary">등록</button>
        </form:form>
    </div>
    <jsp:include page="../../../_layouts/management/footer.jsp"/>
    <jsp:include page="../../../_layouts/management/scripts.jsp"/>
</body>
</html>