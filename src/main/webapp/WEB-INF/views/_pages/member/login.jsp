<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html lang="ko">
<head>
    <jsp:include page="../../_common/configuration.jsp"/>
    <title>서울유람 - 로그인</title>
</head>
<body>
    <header class="container">
        <section class="row">
            <div class="col-12">
                <h2>로그인</h2>
                <p>${sessionScope.message}</p>
                <c:remove var="message" scope="session"/>
            </div>
        </section>
    </header>
    <hr/>
    <form:form method="post" action="/member/login">
    <main class="container">
        <div class="row">
            <div class="col-12 col-md-6 my-3">
                <label for="username" class="form-label">아이디</label>
                <input type="text" name="username" id="username" class="form-control">
            </div>
            <div class="col-12 col-md-6 my-3">
                <label for="password" class="form-label">비밀번호</label>
                <input type="password" name="password" id="password" class="form-control">
            </div>
        </div>
        <div class="row">
            <div class="col-12 mt-5 mb-3">
                <button type="submit">로그인</button>
            </div>
        </div>
    </main>
    </form:form>
    <jsp:include page="../../_common/scripts.jsp"/>
</body>
</html>