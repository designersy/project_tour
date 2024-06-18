<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="management-primary py-3">
    <div class="container d-flex justify-content-between">
        <section class="brand">
            <h1 class="brand brand-white">
                <a href="<c:url value="/management"/>">서울유람 관리자 페이지</a>
            </h1>
        </section>
        <section class="menu d-none d-lg-block">
            <jsp:include page="../../_elements/management/menu.jsp"/>
        </section>
        <section class="d-block d-lg-none">
            <a class="btn bg-transparent" data-bs-toggle="offcanvas" href="#menu" role="button" aria-controls="menu">
                <span class="material-icons text-white">menu</span>
            </a>
        </section>
    </div>
</header>
<div class="offcanvas offcanvas-end" tabindex="-1" id="menu" aria-labelledby="menuLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasExampleLabel">
            <strong class="brand brand-white set-brand-position">
                <a href="<c:url value="/management"/>">서울유람 관리자 페이지</a>
            </strong>
        </h5>
        <button type="button" class="menu-close" data-bs-dismiss="offcanvas" aria-label="Close">
            ×
        </button>
    </div>
    <div class="offcanvas-body offcanvas-menu bg-light-subtle">
        <jsp:include page="../../_elements/management/menu.jsp"/>
    </div>
</div>