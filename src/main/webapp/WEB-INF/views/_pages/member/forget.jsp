<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html lang="ko">
<head>
    <jsp:include page="../../_common/configuration.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/stylesheets/member.css"/>">
    <title>서울유람 - 비밀번호 재발급</title>
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
                <h2>로그인 정보 확인</h2>
                <p class="mb-1">아이디와 비밀번호를 찾아 드립니다.</p>
                <p class="mt-0 invalid-feedback d-block">${error}</p>
            </section>
        </header>
        <main class="bg-white p-5 pt-0">
            <form:form method="post" action="/member/forget" modelAttribute="forget" cssClass="mb-0">
                <div class="row">
                    <div class="col-12">
                        <label class="form-label">이메일</label>
                        <form:input path="email" cssClass="form-control"/>
                        <form:errors path="email" cssClass="invalid-feedback d-block"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 my-3">
                        <label class="form-label">별명</label>
                        <form:input path="nickname" cssClass="form-control"/>
                        <form:errors path="nickname" cssClass="invalid-feedback d-block"/>
                        <div class="form-text">
                            2~10자의 한글, 영문, 숫자로 입력해 주십시오.
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 mt-3 mb-5 agreement">
                        <strong>개인정보 처리방침 동의</strong>
                        <div class="content mt-2 mb-3 p-4">
                            개인정보 처리방침 내용
                        </div>
                        <div class="form-check">
                            <form:checkbox path="privacy" cssClass="form-check-input"/>
                            <form:label path="privacy" cssClass="form-check-label">
                                개인정보 처리방침에 동의합니다.
                            </form:label>
                        </div>
                        <form:errors path="privacy" cssClass="ps-0 invalid-feedback d-block"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 mt-5 mb-3">
                        <button type="submit" class="w-100 py-3 btn btn-lg btn-dark rounded-0">로그인 정보 확인</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <ul class="mb-0 mt-3 p-0">
                            <li>
                                <span>로그인 정보를 기억해내셨나요?</span>
                                <a href="<c:url value="/member/login"/>">로그인</a>
                            </li>
                            <li>
                                <span>아직 서울유람 회원이 아니셨나요?</span>
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