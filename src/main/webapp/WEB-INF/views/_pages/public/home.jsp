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
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-4">
                <div class="p-3">
                    <strong>날씨정보</strong>
                </div>
                <div class="row">
                    <div class="col">
                        <section class="now-weather" aria-label="now-weather"></section>
                    </div>
                </div>
                <div class="row">
                    <section class="future-weather" aria-label="future-weather"></section>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../../_layouts/public/footer.jsp"/>
    <jsp:include page="../../_layouts/public/scripts.jsp"/>
    <script src="<c:url value="/assets/scripts/weather.js"/>"></script>
    <script src="<c:url value="/vendors/chartjs/chartjs.min.js"/>"></script>
    <script>
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