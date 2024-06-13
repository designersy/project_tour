const getWeatherInformation = (weatherData) => {
    let weatherObject = JSON.parse(weatherData);
    let weatherObject_items = weatherObject.response.body.items.item;
    let weatherStatus = [];
    const weatherDataSet = ['PTY', 'SKY', 'T1H'];

    for (let i = 0; i < weatherObject_items.length; i++) {

        if (weatherDataSet.includes(weatherObject_items[i].category)) {

            let weatherCategoryType;

            switch (weatherObject_items[i].category) {
                case 'PTY': weatherCategoryType = '강수형태'; break;
                case 'SKY': weatherCategoryType = '하늘'; break;
                case 'T1H': weatherCategoryType = '기온'; break;
            }

            weatherStatus.push({
                'type': weatherCategoryType,
                'time': weatherObject_items[i].fcstTime,
                'value': weatherObject_items[i].fcstValue
            });
        }
    }

    return weatherStatus;
};

const getWeatherDetails = (weatherInformation, korCategory) => {
    let result = [];

    for (let i = 0; i < weatherInformation.length; i++) {
        if (weatherInformation[i].type === korCategory) {
            result.push(weatherInformation[i]);
        }
    }

    return result;
};

const weatherHtmlBuilderSky = (sky, pty) => {
    let iconPath = '';
    let iconAlt = '';

    if (pty === '0') {
        switch (sky) {
            case '1':
                iconAlt = '맑음';
                iconPath = '/assets/images/weather/weather_clear.jpg';
                break;

            case '3':
                iconAlt = '구름많음';
                iconPath = '/assets/images/weather/weather_lot_cloud.jpg';
                break;

            case '4':
                iconAlt = '흐림';
                iconPath = '/assets/images/weather/weather_cloudy.jpg';
                break;
        }
    } else {
        switch (pty) {
            case '1':
                iconAlt = '비';
                iconPath = '/assets/images/weather/weather_rain.jpg';
                break;

            case '2':
                iconAlt = '비/눈'
                iconPath = '/assets/images/weather/weather_rain_snow.jpg';
                break;

            case '3':
                iconAlt = '눈'
                iconPath = '/assets/images/weather/weather_snow.jpg';
                break;

            case '4':
                iconAlt = '소나기'
                iconPath = '/assets/images/weather/weather_rain.jpg';
                break;
        }
    }

    return `
        <div class='weather--sky-icon'>
            <img src='${iconPath}' alt='${iconAlt}'/>
        </div>
    `;
};

const weatherHtmlBuilderInformation = (tmp, sky, pty) => {
    let skyInformation = convertSkyToText(sky);
    let ptyInformation = convertPtyToText(pty);

    return `
        <div class='weather--sky-information'>
            <strong>${tmp}℃</strong>
            <p class="m-0">${skyInformation} ${ptyInformation}</p>
        </div>
    `;
};

const futureWeatherHtmlBuilder = (data) => {
    let resultHtml = '';

    let futureData = {
        pty: getWeatherDetails(getWeatherInformation(weatherData), '강수형태'),
        sky: getWeatherDetails(getWeatherInformation(weatherData), '하늘'),
        tmp: getWeatherDetails(getWeatherInformation(weatherData), '기온'),
    };

    for (let a = 0; a < futureData.pty.length; a++) {
        let time = futureData.tmp[a].time;
        time = time.substring(0, 2) + ':' + time.substring(2, 4);
        resultHtml += '<div class="col-6 col-md-6 col-xl-4 my-2 text-center future-information">';
        resultHtml += '<span>' + time + '</span>';
        resultHtml += weatherHtmlBuilderInformation(futureData.tmp[a].value, futureData.sky[a].value, futureData.pty[a].value);
        resultHtml += '</div>';
    }

    return '<div class="p-3 mx-2 mt-4 bg-light border border-1"><div class="row">' + resultHtml + '</div></div>';
};

const convertSkyToText = (sky) => {
    let skyInformation;

    switch(sky) {
        case '1': skyInformation = '맑음'; break;
        case '3': skyInformation = '구름많음'; break;
        case '4': skyInformation = '구름'; break;
    }

    return skyInformation;
}

const convertPtyToText = (pty) => {
    let ptyInformation;

    switch (pty) {
        case '0': ptyInformation = ''; break;
        case '1': ptyInformation = '비'; break;
        case '2': ptyInformation = '비/눈'; break;
        case '3': ptyInformation = '눈'; break;
        case '4': ptyInformation = '소나기'; break;
    }

    return ptyInformation;
};