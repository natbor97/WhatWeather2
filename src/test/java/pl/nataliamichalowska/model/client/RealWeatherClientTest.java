package pl.nataliamichalowska.model.client;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import pl.nataliamichalowska.model.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class RealWeatherClientTest {
    @Spy
    private WeatherClient weatherClient = spy(WeatherClient.class);

    @Test
    public void shouldReturnWeatherForKnownCity() throws IOException {
        //given
        when(weatherClient.getWeather("Londyn")).thenReturn(expectedWeather);

        //when
        Weather result = weatherClient.getWeather("Londyn");

        //then
        assertThat(result).isEqualTo(expectedWeather);
    }

    @Test
    public void shouldNotReturnWeatherForUnknownCity() throws IOException {
        //given
        when(weatherClient.getWeather("City")).thenReturn(expectedWeather);

        //when
        Weather result = weatherClient.getWeather("Warszawa");

        //then
        assertThat(result).isNotEqualTo(expectedWeather);
    }

    @Test
    public void shouldNotReturnCorrectWeatherForOtherClient() throws IOException {
        //given
        WeatherClient weatherClient1 = new RealWeatherClient();
        when(weatherClient.getWeather("Londyn")).thenReturn(expectedWeather);

        //when
        Weather result = weatherClient1.getWeather("Londyn");

        //then
        assertThat(result).isNotEqualTo(expectedWeather);
    }

    private CityData cityData = new CityData("Londyn", 51.5073219, -0.1276474, "GB","England");
    private WeatherData weatherData = new WeatherData(32, 994, "000");
    private ArrayList<ForecastData> forecastData = createForecastData();

    private ArrayList<ForecastData> createForecastData() {
        ArrayList<ForecastData> forecastDataArray = new ArrayList<ForecastData>();
        ForecastData forecastData1 = new ForecastData(1662314400,32, 994, "000");
        for (int i = 0; i<40; i++){
            forecastDataArray.add(forecastData1);
        }
        return forecastDataArray;
    }

    Weather expectedWeather = new Weather(cityData, weatherData, forecastData);
}
