package pl.nataliamichalowska.model;

import java.io.IOException;
public interface WeatherClient {
    Weather getWeather(String cityName) throws IOException;
}
