<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="ko">
<head>
    <jsp:include page="../../../_common/meta.jsp"/>
    <jsp:include page="../../../_layouts/public/links.jsp"/>
    <title><spring:message code="website.name"/></title>
</head>
<body class="d-flex flex-column">
    <jsp:include page="../../../_layouts/public/header.jsp"/>
    <hr class="my-0"/>
    <div class="container">
        <header class="page-subject mb-4 py-5">
            <h3 class="m-0">
                <spring:message code="subject.food"/>
            </h3>
            <p class="mb-0 mt-1 text-secondary">
                <spring:message code="subject.food.description"/>
            </p>
        </header>
        <main class="page-content">
            <form:form method="get" action="/place">
                <div class="row">
                    <div class="col-12 col-md-3">
                        <label for="t" class="form-label d-md-none">
                            <spring:message code="search.subject.type"/>
                        </label>
                        <select name="t" id="t" class="form-select">
                            <option value=""><spring:message code="search.select"/></option>
                            <option value="name" <c:if test="${type eq 'name'}"> selected="selected" </c:if>><spring:message code="search.name"/></option>
                            <option value="local" <c:if test="${type eq 'local'}"> selected="selected" </c:if>><spring:message code="search.location"/></option>
                        </select>
                    </div>
                    <div class="col-12 col-md-8">
                        <label for="s" class="form-label d-md-none"><spring:message code="search.subject.word"/></label>
                        <input type="text" name="s" id="s" class="form-control" value="${search}">
                    </div>
                    <div class="col-12 col-md-1">
                        <button type="submit" class="btn btn-dark w-100">
                            <spring:message code="search.subject.button"/>
                        </button>
                    </div>
                </div>
            </form:form>
            <hr class="my-4"/>
            <div class="row">
                <div class="col">
                    <nav class="items-sorter">
                        <ul class="nav">
                            <li class="nav-item">
                                <a href="<c:url value="/place?t=${type}&s=${search}&a=&p=${currentPage}"/>">
                                    <spring:message code="filter.align.default"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/place?t=${type}&s=${search}&a=rating&p=${currentPage}"/>">
                                    <spring:message code="filter.align.score"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/place?t=${type}&s=${search}&a=review&p=${currentPage}"/>">
                                    <spring:message code="filter.align.review"/>
                                </a>
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
                                    <th>
                                        <spring:message code="page.tour.table.name"/>
                                    </th>
                                    <th>
                                        <spring:message code="page.tour.table.address"/>
                                    </th>
                                    <th>
                                        <spring:message code="page.tour.table.telephone"/>
                                    </th>
                                </tr>
                            </thead>
                            <c:choose>
                                <c:when test="${not empty items.content}">

                                </c:when>
                                <c:otherwise>
                                <tbody>
                                    <tr>
                                        <td colspan="3">
                                            <spring:message code="error.no_data"/>
                                        </td>
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
                            <ul class="pagination justify-content-center">
                                <c:if test="${currentPage gt 1}">
                                    <li class="page-item">
                                        <a href="<c:url value="/food?p=${currentPage - 1}&s=${search}&t=${type}&a=${sort}"/>" class="page-link">이전</a>
                                    </li>
                                </c:if>
                                <c:forEach var="page" begin="${start}" end="${end}">
                                    <li class="page-item">
                                        <a href="<c:url value="/food?p=${page}&s=${search}&t=${type}&a=${sort}"/>" class="page-link <c:if test="${page eq currentPage}"> active </c:if>">${page}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${currentPage lt totalPages}">
                                    <li class="page-item">
                                        <a href="<c:url value="/food?p=${currentPage + 1}&s=${search}&t=${type}&a=${sort}"/>" class="page-link">다음</a>
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