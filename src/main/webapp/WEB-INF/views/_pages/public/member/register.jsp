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
            <spring:message code="member.register"/>
        </h2>
        <c:if test="${not empty error}">
            <p class="invalid-feedback d-block mt-2 mb-0">${error}</p>
        </c:if>
        <hr/>
        <form:form method="post" action="/member/register" modelAttribute="member">
            <div class="row">
                <div class="col-12 my-2">
                    <form:label path="username" cssClass="form-label">
                        <spring:message code="field.member.username"/>
                    </form:label>
                    <form:input path="username" cssClass="form-control form-control-lg"/>
                    <div class="form-text">
                        <spring:message code="help.member.username"/>
                    </div>
                    <form:errors path="username" cssClass="invalid-feedback d-block"/>
                </div>
            </div>
            <div class="row">
                <div class="col-12 col-sm-6 my-2">
                    <form:label path="password" cssClass="form-label">
                        <spring:message code="field.member.password"/>
                    </form:label>
                    <form:password path="password" cssClass="form-control form-control-lg"/>
                    <div class="form-text">
                        <spring:message code="help.member.password"/>
                    </div>
                    <form:errors path="password" cssClass="invalid-feedback d-block"/>
                </div>
                <div class="col-12 col-sm-6 my-2">
                    <form:label path="confirm" cssClass="form-label">
                        <spring:message code="field.member.confirm"/>
                    </form:label>
                    <form:password path="confirm" cssClass="form-control form-control-lg"/>
                    <div class="form-text">
                        <spring:message code="help.member.confirm"/>
                    </div>
                    <form:errors path="confirm" cssClass="invalid-feedback d-block"/>
                </div>
            </div>
            <div class="row">
                <div class="col-12 my-2">
                    <form:label path="nickname" cssClass="form-label">
                        <spring:message code="field.member.nickname"/>
                    </form:label>
                    <form:input path="nickname" cssClass="form-control form-control-lg"/>
                    <div class="form-text">
                        <spring:message code="help.member.nickname"/>
                    </div>
                    <form:errors path="nickname" cssClass="invalid-feedback d-block"/>
                </div>
            </div>
            <div class="row">
                <div class="col-12 my-2">
                    <form:label path="email" cssClass="form-label">
                        <spring:message code="field.member.email"/>
                    </form:label>
                    <form:input path="email" cssClass="form-control form-control-lg"/>
                    <div class="form-text">
                        <spring:message code="help.member.email"/>
                    </div>
                    <form:errors path="email" cssClass="invalid-feedback d-block"/>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-12">
                    <div class="form-check">
                        <form:checkbox path="terms" cssClass="form-check-input"/>
                        <form:label path="terms" cssClass="form-check-label">
                            <a href="#"><spring:message code="field.member.link.agreement"/></a>
                            <spring:message code="field.member.text.terms.agreement"/>
                        </form:label>
                        <form:errors path="terms" cssClass="invalid-feedback d-block"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="form-check">
                        <form:checkbox path="privacy" cssClass="form-check-input"/>
                        <form:label path="privacy" cssClass="form-check-label">
                            <a href="#"><spring:message code="field.member.link.agreement"/></a>
                            <spring:message code="field.member.text.privacy.agreement"/>
                        </form:label>
                        <form:errors path="privacy" cssClass="invalid-feedback d-block"/>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-12">
                    <button type="submit" class="btn w-100 btn-lg btn-dark">
                        <spring:message code="member.register"/>
                    </button>
                </div>
            </div>
        </form:form>
    </main>
    <jsp:include page="../../../_layouts/public/scripts.jsp"/>
</body>
</html>