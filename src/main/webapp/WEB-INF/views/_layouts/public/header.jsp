<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<header class="primary-header w-100 py-4">
    <div class="container d-flex justify-content-between">
        <section class="brand">
            <h1 class="brand brand-default">
                <a href="<c:url value="/"/>">서울유람</a>
            </h1>
        </section>
        <section class="navigation">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/"/>"><spring:message code="menu.home" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/place"/>"><spring:message code="menu.place" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/food"/>"><spring:message code="menu.food" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/faq"/>"><spring:message code="menu.faq" /></a>
                </li>
            </ul>
        </section>
    </div>
</header>