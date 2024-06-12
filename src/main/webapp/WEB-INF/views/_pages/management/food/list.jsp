<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="ko">
<head>
    <jsp:include page="../../../_common/meta.jsp"/>
    <jsp:include page="../../../_layouts/management/links.jsp"/>
    <title>서울유람 - 맛집정보</title>
</head>
<body class="d-flex flex-column">
    <jsp:include page="../../../_layouts/management/header.jsp"/>
    <div class="container">
        <header class="page-subject mb-4 py-5">
            <h3 class="m-0">맛집 정보</h3>
            <p class="mb-0 mt-1 text-secondary">맛집 정보를 관리합니다.</p>
        </header>
        <div class="row">
            <div class="col">
                <form:form method="get" action="/food">
                    <div class="row">
                        <div class="col-12 col-md-3">
                            <label for="t" class="form-label d-md-none">검색 유형</label>
                            <select name="t" id="t" class="form-select">
                                <option selected>검색 유형</option>
                                <option value="name" <c:if test="${type eq 'name'}"> selected="selected" </c:if>>상호(명칭)</option>
                                <option value="local" <c:if test="${type eq 'local'}"> selected="selected" </c:if>>주소(지역)</option>
                                <option value="food" <c:if test="${type eq 'food'}"> selected="selected" </c:if>>대표메뉴</option>
                            </select>
                        </div>
                        <div class="col-12 col-md-7">
                            <label for="s" class="form-label d-md-none">검색어</label>
                            <input type="text" name="s" id="s" class="form-control" value="${search}">
                        </div>
                        <div class="col-12 col-md-2 mt-3 m-md-0">
                            <button type="submit" class="btn btn-dark w-100">검색</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <hr/>
        <div class="table-responsive">
            <table class="table">
                <thead class="table-dark">
                    <tr>
                        <th>번호</th>
                        <th>명칭</th>
                        <th>전화번호</th>
                        <th>등록일시</th>
                        <th>작업</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <jsp:include page="../../../_layouts/management/footer.jsp"/>
    <jsp:include page="../../../_layouts/management/scripts.jsp"/>
</body>
</html>