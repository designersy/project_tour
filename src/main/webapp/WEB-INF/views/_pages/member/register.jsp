<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html lang="ko">
<head>
    <jsp:include page="../../_common/configuration.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/stylesheets/member.css"/>">
    <title>서울유람 - 회원가입</title>
</head>
<body class="bg-light d-flex flex-column py-5">
    <div class="register-container member-container mx-auto my-auto">
        <header>
            <section class="brand mb-5">
                <h1>
                    <a href="<c:url value="/"/>" class="logo-default">서울유람</a>
                </h1>
            </section>
            <section class="bg-white p-5 pb-3 section-subject">
                <h2>회원 가입</h2>
                <p>${error}</p>
            </section>
        </header>
        <main class="bg-white p-5 pt-0">
            <form:form method="post" action="/member/register" modelAttribute="member" cssClass="mb-0">
                <div class="row">
                    <div class="col-12 col-md-12 my-3">
                        <label class="form-label">아이디</label>
                        <form:input path="username" cssClass="form-control"/>
                        <form:errors path="username" cssClass="invalid-feedback d-block"/>
                        <div class="form-text">
                            6~30자의 영문, 숫자로 입력해 주십시오.
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-md-6 my-3">
                        <label class="form-label">비밀번호</label>
                        <form:password path="password" cssClass="form-control"/>
                        <form:errors path="password" cssClass="invalid-feedback d-block"/>
                        <div class="form-text">
                            6~30자의 영문, 숫자로 입력해 주십시오.
                        </div>
                    </div>
                    <div class="col-12 col-md-6 my-3">
                        <label class="form-label">비밀번호 확인</label>
                        <form:password path="confirm" cssClass="form-control"/>
                        <form:errors path="confirm" cssClass="invalid-feedback d-block"/>
                        <div class="form-text">
                            비밀번호를 똑같이 다시 한 번 입력해 주십시오.
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-md-12 my-3">
                        <label class="form-label">이메일</label>
                        <form:input path="email" cssClass="form-control"/>
                        <form:errors path="email" cssClass="invalid-feedback d-block"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-md-6 my-3">
                        <label class="form-label">별명</label>
                        <form:input path="nickname" cssClass="form-control"/>
                        <form:errors path="nickname" cssClass="invalid-feedback d-block"/>
                        <div class="form-text">
                            2~10자의 한글, 영문, 숫자로 입력해 주십시오.
                        </div>
                    </div>
                    <div class="col-12 col-md-6 my-3">
                        <label class="form-label">국적</label>
                        <form:select path="national" cssClass="form-control">
                            <form:option value="kor">대한민국</form:option>
                            <form:option value="jpn">일본</form:option>
                            <form:option value="chn">중국</form:option>
                            <form:option value="eng">영국</form:option>
                            <form:option value="eur">유럽연합</form:option>
                            <form:option value="usa">미국</form:option>
                            <form:option value="etc">기타</form:option>
                        </form:select>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-12 mt-3 mb-5 agreement">
                        <strong>이용약관 동의</strong>
                        <div class="content mt-2 mb-3 p-4">
                            약관 내용
                        </div>
                        <div class="form-check">
                            <form:checkbox path="terms" cssClass="form-check-input"/>
                            <form:label path="terms" cssClass="form-check-label">
                                이용약관에 동의합니다.
                            </form:label>
                        </div>
                        <form:errors path="terms" cssClass="ps-0 invalid-feedback d-block"/>
                    </div>
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
                        <button type="submit" class="w-100 py-3 btn btn-lg btn-dark rounded-0">회원가입</button>
                    </div>
                </div>
            </form:form>
        </main>
    </div>
    <jsp:include page="../../_common/scripts.jsp"/>
</body>
</html>