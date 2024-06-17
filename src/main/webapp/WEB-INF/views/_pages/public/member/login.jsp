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
            <spring:message code="member.sign_in"/>
        </h2>
        <c:if test="${not empty information}">
            <p class="invalid-feedback d-block mt-2 mb-0">${information}</p>
        </c:if>
        <hr/>
        <form:form method="post" action="/member/login">
            <div class="row">
                <div class="col-12 my-2">
                    <label for="username" class="form-label">
                        <spring:message code="field.member.username"/>
                    </label>
                    <input type="text" id="username" name="username" class="form-control form-control-lg"/>
                </div>
            </div>
            <div class="row">
                <div class="col-12 my-2">
                    <label for="password" class="form-label">
                        <spring:message code="field.member.password"/>
                    </label>
                    <input type="password" id="password" name="password" class="form-control form-control-lg"/>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-12 mt-4">
                    <button type="submit" class="btn btn-dark btn-lg w-100">
                        <spring:message code="member.sign_in"/>
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