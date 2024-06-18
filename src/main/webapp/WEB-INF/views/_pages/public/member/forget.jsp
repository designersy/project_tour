<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../../../_common/meta.jsp"/>
    <jsp:include page="../../../_layouts/public/links.jsp"/>
    <title><spring:message code="website.name"/></title>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
    <main class="register-form mx-auto">
        <h2>
            <spring:message code="member.forget"/>
        </h2>
        <c:if test="${not empty error}">
            <p class="invalid-feedback d-block mt-2 mb-0">${error}</p>
        </c:if>
        <hr/>
        <form:form method="post" action="/member/forget" modelAttribute="forget">
            <div class="row">
                <div class="col-12 my-2">
                    <form:label path="nickname" class="form-label">
                        <spring:message code="field.member.username"/>
                    </form:label>
                    <form:input path="nickname" cssClass="form-control form-control-lg"/>
                </div>
            </div>
            <div class="row">
                <div class="col-12 my-2">
                    <form:label path="email" class="form-label">
                        <spring:message code="field.member.email"/>
                    </form:label>
                    <form:input path="email" cssClass="form-control form-control-lg"/>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-12 mt-4">
                    <button type="submit" class="btn btn-dark w-100">
                        <spring:message code="member.forget"/>
                    </button>
                </div>
            </div>
            <div class="row">
                <div class="col-12 mt-4">
                    <p class="my-2">
                        <span>
                            <spring:message code="member.login.forget"/>
                        </span>
                        <a href="<c:url value="/member/forget"/>">
                            <spring:message code="member.forget"/>
                        </a>
                    </p>
                    <p class="my-2">
                        <span>
                            <spring:message code="member.login.register"/>
                        </span>
                        <a href="<c:url value="/member/register"/>">
                            <spring:message code="member.register"/>
                        </a>
                    </p>
                </div>
            </div>
        </form:form>
    </main>
<jsp:include page="../../../_layouts/public/scripts.jsp"/>
</body>
</html>