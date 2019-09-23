$(function () {
    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            Console.log("Geolocation is not supported by this browser.");
        }
    }

    function showPosition(position) {
        getWeatherByLocation(position.coords.latitude,position.coords.longitude);
    }

    function getWeatherByLocation(lat,lon){
        let url = "https://api.openweathermap.org/data/2.5/weather";
        $.get(url,{
                "lat": lat,
                "lon": lon,
                "appid": "a08612ed2fa31be034e5cb20bccd6929"
        }).done(function(data){
            showWeather(data);
        }).fail(function(error){
            console.error(error);
        })

    }

    function getWeatherByName(cityName){
        
        let url = "https://api.openweathermap.org/data/2.5/weather?";
        $.get(url,{
                "q": cityName,
                "appid": "a08612ed2fa31be034e5cb20bccd6929"
        }).done(function(data){
            showWeather(data);
        }).fail(function(error){
            console.error(error);
        })
    }

    function showWeather(weatherData){
        window.myWidgetParam ? window.myWidgetParam : window.myWidgetParam = []; 
        window.myWidgetParam.push({id: 4,cityid: weatherData.id,appid: 'a08612ed2fa31be034e5cb20bccd6929',units: 'metric',containerid: 'openweathermap-widget-4',  });  
        let script = document.createElement('script');
        script.async = true;script.charset = "utf-8";
        script.src = "//openweathermap.org/themes/openweathermap/assets/vendor/owm/js/weather-widget-generator.js";
        let s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(script, s);
    }

    $("#weatherCurrent").on("click", function () {
        getLocation();
    });

    $("#weatherCity").change(function(){
        getWeatherByName($(this).val());
    });
});