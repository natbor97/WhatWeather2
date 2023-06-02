package pl.nataliamichalowska.model;

import java.util.ArrayList;

public class Weather {
    private CityData cityData;
    private WeatherData weatherData;
    private ArrayList<ForecastData> forecastData;
    public Weather(CityData cityData, WeatherData weatherData, ArrayList<ForecastData> forecastData) {
        this.cityData = cityData;
        this.weatherData = weatherData;
        this.forecastData = forecastData;
    }

    public CityData getCityData() {
        return cityData;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public ArrayList<ForecastData> getForecastData() {
        return forecastData;
    }
}
