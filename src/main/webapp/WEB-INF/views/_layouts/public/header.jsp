<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <a class="nav-link text-dark" href="<c:url value="/"/>">처음으로</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/place"/>">명소정보</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/food"/>">맛집정보</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="<c:url value="/faq"/>">자주묻는질문</a>
                </li>
            </ul>
        </section>
    </div>
</header>