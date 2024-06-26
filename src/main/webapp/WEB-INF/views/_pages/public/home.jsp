<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html lang="ko">
<head>
    <jsp:include page="../../_common/meta.jsp"/>
    <jsp:include page="../../_layouts/public/links.jsp"/>
    <title>
        <spring:message code="website.name"/>
    </title>
</head>
<body class="d-flex flex-column h-100">
    <jsp:include page="../../_layouts/public/header.jsp"/>
    <jsp:include page="../../_elements/public/carousel.jsp">
        <jsp:param name="banners" value="${banners}"/>
    </jsp:include>
    <div class="container py-4">
        <div class="row">
            <div class="col-12 col-md-8">
                <section class="main--latest-place my-3">
                    <c:choose>
                        <c:when test="${not empty places or not empty foods}">
                            <div class="row my-2" data-masonry='{"percentPosition": true }'>
                                <c:forEach var="place" items="${places}">
                                    <div class="col-6 col-md-6 col-xl-4 my-2">
                                        <div class="card">
                                            <div class="card-header">
                                                <spring:message code="subject.tour"/>
                                            </div>
                                            <div class="card-body">
                                                <h5 class="card-title">${place.name}</h5>
                                                <p class="card-text">${place.tags}</p>
                                                <a href="/place/detail/${place.id}" class="btn btn-sm btn-outline-secondary">More +</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                <c:forEach var="food" items="${foods}">
                                    <div class="col-6 col-md-6 col-xl-4 my-2">
                                        <div class="card">
                                            <div class="card-header">
                                                <spring:message code="subject.food"/>
                                            </div>
                                            <div class="card-body">
                                                <h5 class="card-title">${food.name}</h5>
                                                <p class="card-text">${food.mainFood}</p>
                                                <a href="/food/detail?id=${food.id}" class="btn btn-sm btn-outline-secondary">More +</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="error.no_data"/>
                        </c:otherwise>
                    </c:choose>
                </section>
            </div>
            <div class="col-12 col-md-4">
                <div class="row">
                    <div class="col my-3 position-sticky sticky-top">
                        <div class="py-3">
                            <strong>
                                <spring:message code="main.subject.weather"/>
                            </strong>
                        </div>
                        <div class="row">
                            <div class="col">
                                <section class="now-weather" aria-label="now-weather"></section>
                            </div>
                        </div>
                        <div class="row">
                            <section class="future-weather border-top border-bottom border-1" aria-label="future-weather"></section>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../../_layouts/public/footer.jsp"/>
    <jsp:include page="../../_layouts/public/scripts.jsp"/>
    <script src="<c:url value="/assets/scripts/weather.js"/>"></script>
    <script src="<c:url value="/vendors/chartjs/chartjs.min.js"/>"></script>
    <script src="<c:url value="/assets/scripts/masonry.pkgd.min.js"/>"></script>
    <script>
        const weatherText = {
            clear: '<spring:message code="weather.clear"/>',
            lot_cloudy: '<spring:message code="weather.lot_cloudy"/>',
            cloudy: '<spring:message code="weather.cloudy"/>',
            rain: '<spring:message code="weather.rain"/>',
            rain_snow: '<spring:message code="weather.rain_snow"/>',
            snow: '<spring:message code="weather.snow"/>',
            scurry: '<spring:message code="weather.scurry"/>'
        };

        const nowWeatherInformation = document.querySelector('section.now-weather[aria-label="now-weather"]');
        const futureWeatherInformation = document.querySelector('section.future-weather[aria-label="future-weather"]');

        let weatherData = `${weather}`;
        let nowData = {
            pty: getWeatherDetails(getWeatherInformation(weatherData), '강수형태')[0],
            sky: getWeatherDetails(getWeatherInformation(weatherData), '하늘')[0],
            tmp: getWeatherDetails(getWeatherInformation(weatherData), '기온')[0]
        }

        let nowWeatherHtml = weatherHtmlBuilderSky(nowData.sky.value, nowData.pty.value) +
                             weatherHtmlBuilderInformation(nowData.tmp.value, nowData.sky.value, nowData.pty.value);

        nowWeatherInformation.innerHTML = nowWeatherHtml;
        futureWeatherInformation.innerHTML = futureWeatherHtmlBuilder(getWeatherInformation(weatherData));
    </script>
</body>
</html>