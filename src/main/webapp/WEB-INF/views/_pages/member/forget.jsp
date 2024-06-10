<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html lang="ko">
<head>
    <jsp:include page="../../_common/configuration.jsp"/>
    <title>서울유람 - 비밀번호 재발급</title>
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
    <form:form method="post" action="/member/forget" modelAttribute="forget">
    	<div class="container">
    		<div class="row">
                <div class="col-12 my-3">
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
            	<div class="col-12 my-3">
                    <div>
                        개인정보 처리방침 내용
                    </div>
                    <div class="form-check">
                        <form:checkbox path="privacy"/>
                        <form:label path="privacy" cssClass="form-check-label">
                            개인정보 처리방침에 동의합니다.
                        </form:label>
                        <form:errors path="privacy" cssClass="invalid-feedback d-block"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12 mt-5 mb-3">
                    <button type="submit">비밀번호 재발급</button>
                </div>
            </div>
    	</div>
    </form:form>
	<jsp:include page="../../_common/scripts.jsp"/>
</body>
</html>