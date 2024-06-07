<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html lang="ko">
<head>
    <jsp:include page="../../_common/configuration.jsp"/>
    <title>서울유람 - 회원가입</title>
</head>
<body>
    <header class="container">
        <section class="row">
            <div class="col-12">
                <h2>회원가입</h2>
                <p>${error}</p>
            </div>
        </section>
    </header>
    <hr/>
    <form:form method="post" action="/member/register" modelAttribute="member">
        <main class="container">
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
            <div class="row">
                <div class="col-12 my-3">
                    <div>
                        약관 내용
                    </div>
                    <div class="form-check">
                        <form:checkbox path="terms"/>
                        <form:label path="terms" cssClass="form-check-label">
                            이용약관에 동의합니다.
                        </form:label>
                        <form:errors path="terms" cssClass="invalid-feedback d-block"/>
                    </div>
                </div>
                <div class="col-12 my-3">
                    <div>
                        개인정보 처리방침 내용
                    </div>
                    <div class="form-check">
                        <form:checkbox path="privacy"/>
                        <form:label path="privacy" cssClass="form-check-label">
                            이용약관에 동의합니다.
                        </form:label>
                        <form:errors path="privacy" cssClass="invalid-feedback d-block"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12 mt-5 mb-3">
                    <button type="submit">회원가입</button>
                </div>
            </div>
        </main>
    </form:form>
    <jsp:include page="../../_common/scripts.jsp"/>
</body>
</html>