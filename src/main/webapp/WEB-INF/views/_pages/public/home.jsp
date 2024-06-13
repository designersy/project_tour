<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
    <jsp:include page="../../_common/meta.jsp"/>
    <jsp:include page="../../_layouts/public/links.jsp"/>
    <title>서울유람</title>
</head>
<body class="d-flex flex-column">
    <jsp:include page="../../_layouts/public/header.jsp"/>
    <jsp:include page="../../_elements/public/carousel.jsp"/>
    <jsp:include page="../../_layouts/public/footer.jsp"/>
    <jsp:include page="../../_layouts/public/scripts.jsp"/>
    <script>
        let weatherData = `${weather}`;
        console.log(weatherData);
    </script>
</body>
</html>