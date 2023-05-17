package pl.nataliamichalowska.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherServiceTest {

    @Mock
    private WeatherClient weatherClient = mock(WeatherClient.class);

    @Test
    public void shouldReturnCorrectWeather() throws IOException {
        //given
        when(weatherService.getWeather("Londyn")).thenReturn(expectedCurrentWeatherAndForecast);

        //when
        Weather result = weatherService.getWeather("Londyn");

        //then
        assertThat(result).isEqualTo(expectedCurrentWeatherAndForecast);
    }

    @Test
    public void shouldNotReturnWeatherForWeatherServiceAndUnknownCity() throws IOException {
        //given
        when(weatherService.getWeather("City")).thenReturn(expectedCurrentWeatherAndForecast);

        //when
        Weather result = weatherService.getWeather("Warszawa");

        //then
        assertThat(result).isNotEqualTo(expectedCurrentWeatherAndForecast);
    }

    private CityData cityData = new CityData("Londyn", 51.5073219, -0.1276474, "GB","England");
    private WeatherData weatherData = new WeatherData(25, 994, "000");
    private ArrayList<ForecastData> forecastData = createForecastData();

    private ArrayList<ForecastData> createForecastData() {
        ArrayList<ForecastData> forecastDataList = new ArrayList<ForecastData>();
        ForecastData exampleForecastData = new ForecastData(1662314400, 25, 994, "000");
        for (int i = 0; i<40; i++){
            forecastDataList.add(exampleForecastData);
        }
        return forecastDataList;
    }

    Weather expectedCurrentWeatherAndForecast = new Weather(cityData, weatherData, forecastData);
    WeatherService weatherService = new WeatherService(weatherClient);
}
