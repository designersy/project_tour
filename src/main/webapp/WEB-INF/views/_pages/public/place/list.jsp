<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="ko">
<head>
    <jsp:include page="../../../_common/meta.jsp"/>
    <jsp:include page="../../../_layouts/public/links.jsp"/>
    <title>서울유람 - 맛집정보</title>
</head>
<body class="d-flex flex-column">
    <jsp:include page="../../../_layouts/public/header.jsp"/>
    <hr class="my-0"/>
    <div class="container">
        <header class="page-subject mb-4 py-5">
            <h3 class="m-0">관광명소 정보</h3>
            <p class="mb-0 mt-1 text-secondary">서울시와 서울유람 그리고 관광객 분들께서 추천하는 맛집을 소개합니다.</p>
        </header>
        <main class="page-content">
            <form:form method="get" action="/food">
                <div class="row">
                    <div class="col-12 col-md-3">
                        <label for="t" class="form-label d-md-none">검색 유형</label>
                        <select name="t" id="t" class="form-select">
                            <option selected>검색 유형</option>
                            <option value="name" <c:if test="${type eq 'name'}"> selected="selected" </c:if>>상호(명칭)</option>
                            <option value="local" <c:if test="${type eq 'local'}"> selected="selected" </c:if>>주소(지역)</option>
                        </select>
                    </div>
                    <div class="col-12 col-md-8">
                        <label for="s" class="form-label d-md-none">검색어</label>
                        <input type="text" name="s" id="s" class="form-control" value="${search}">
                    </div>
                    <div class="col-12 col-md-1">
                        <button type="submit" class="btn btn-dark w-100">검색</button>
                    </div>
                </div>
            </form:form>
            <hr class="my-4"/>
            <div class="row">
                <div class="col">
                    <nav class="items-sorter">
                        <ul class="nav">
                            <li class="nav-item">
                                <a href="<c:url value="/place?t=${type}&s=${search}&a=&p=${currentPage}"/>">기본 정렬</a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/place?t=${type}&s=${search}&a=rating&p=${currentPage}"/>">별점 높은순 정렬</a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/place?t=${type}&s=${search}&a=review&p=${currentPage}"/>">리뷰 많은순 정렬</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="row">
                <div class="col mt-5">
                    <div class="table-responsive">
                        <table class="table">
                            <thead class="table-dark">
                                <tr>
                                    <th>번호</th>
                                    <th>명칭</th>
                                    <th>주소</th>
                                    <th>전화번호</th>
                                </tr>
                            </thead>
                            <c:choose>
                                <c:when test="${not empty items.content}">

                                </c:when>
                                <c:otherwise>
                                <tbody>
                                    <tr>
                                        <td colspan="4">등록된 정보가 없습니다.</td>
                                    </tr>
                                </tbody>
                                </c:otherwise>
                            </c:choose>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col my-4">
                    <c:if test="${not empty items}">
                        <div>
                            <ul>
                                <c:if test="${currentPage gt 1}">
                                    <li>
                                        <a href="<c:url value="/place?p=${currentPage - 1}&s=${search}&t=${type}&a=${sort}"/>">이전</a>
                                    </li>
                                </c:if>
                                <c:forEach var="page" begin="${start}" end="${end}">
                                    <li>
                                        <a href="<c:url value="/place?p=${page}&s=${search}&t=${type}&a=${sort}"/>">${page}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${currentPage lt totalPages}">
                                    <li>
                                        <a href="<c:url value="/place?p=${currentPage + 1}&s=${search}&t=${type}&a=${sort}"/>">다음</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </c:if>
                </div>
            </div>
        </main>
    </div>
    <jsp:include page="../../../_layouts/public/footer.jsp"/>
    <jsp:include page="../../../_layouts/public/scripts.jsp"/>
</body>
</html>