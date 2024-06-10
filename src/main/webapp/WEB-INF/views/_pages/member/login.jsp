<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html lang="ko">
<head>
    <jsp:include page="../../_common/configuration.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/stylesheets/member.css"/>">
    <title>서울유람 - 로그인</title>
</head>
<body class="bg-light d-flex flex-column py-5">
    <div class="login-container member-container mx-auto my-auto">
        <header>
            <section class="brand mb-5">
                <h1>
                    <a href="<c:url value="/"/>" class="logo-default">서울유람</a>
                </h1>
            </section>
            <section class="bg-white p-5 pb-3 section-subject">
                <h2>회원 로그인</h2>
                <p class="mb-1">서울유람에 오신 것을 환영합니다!</p>
                <p class="mt-0 invalid-feedback d-block">${sessionScope.message}</p>
                <c:remove var="message" scope="session"/>
            </section>
        </header>
        <main class="bg-white p-5 pt-0">
            <form:form method="post" action="/member/login" cssClass="mb-0">
                <div class="row">
                    <div class="col-12">
                        <label for="username" class="form-label">아이디</label>
                        <input type="text" name="username" id="username" class="form-control form-control-lg">
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 mt-4">
                        <label for="password" class="form-label">비밀번호</label>
                        <input type="password" name="password" id="password" class="form-control form-control-lg">
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 mt-5 mb-3">
                        <button type="submit" class="w-100 py-3 btn btn-lg btn-dark rounded-0">로그인</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <ul class="mb-0 mt-3 p-0">
                            <li>
                                <span>아이디 또는 비밀번호를 잊으셨나요?</span>
                                <a href="<c:url value="/member/forget"/>">비밀번호 찾기</a>
                            </li>
                            <li>
                                <span>아직 서울유람 회원이 아니세요?</span>
                                <a href="<c:url value="/member/register"/>">회원가입</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </form:form>
        </main>
    </div>
    <jsp:include page="../../_common/scripts.jsp"/>
</body>
</html>