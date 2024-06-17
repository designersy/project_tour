<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<header class="login-status py-1 secondary bg-dark w-100">
    <div class="container">
        <div class="row">
            <sec:authorize access="isAnonymous()">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link text-white opacity-75" href="<c:url value="/member/login"/>">
                            <spring:message code="header.information.login"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white opacity-75" href="<c:url value="/member/register"/>">
                            <spring:message code="header.information.register"/>
                        </a>
                    </li>
                </ul>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link text-white opacity-75" href="<c:url value="/member/logout"/>">
                            <spring:message code="header.information.logout"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white opacity-75" href="<c:url value="/member/update"/>">
                            <spring:message code="header.information.update"/>
                        </a>
                    </li>
                </ul>
            </sec:authorize>
        </div>
    </div>
</header>
<header class="primary-header w-100 py-4 border border-bottom">
    <div class="container d-flex justify-content-between">
        <section class="brand">
            <h1 class="brand brand-<spring:message code="website.logo.type"/>">
                <a href="<c:url value="/"/>">
                    <spring:message code="website.name"/>
                </a>
            </h1>
        </section>
        <section class="navigation">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/"/>">
                        <spring:message code="menu.home" />
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/place"/>">
                        <spring:message code="menu.place" />
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/food"/>">
                        <spring:message code="menu.food" />
                    </a>
                </li>
            </ul>
        </section>
    </div>
</header>