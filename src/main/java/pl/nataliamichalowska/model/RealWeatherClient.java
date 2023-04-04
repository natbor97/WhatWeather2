package pl.nataliamichalowska.model;

import com.google.gson.Gson;
import pl.nataliamichalowska.view.Messages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
public class RealWeatherClient implements WeatherClient{
    private final Gson gson = new Gson();
    Messages messages = new Messages();
    @Override
    public Weather getWeather(String cityName) throws IOException {
        String webPageForCity = Config.WEB_PAGE_BASE+ "geo/1.0/direct?q="+cityName+"&limit=1&appid="+Config.API_KEY;
        URL url = new URL(webPageForCity);

        CityData cityData = getCityCoordinates(url, cityName);

        double cityLat = cityData.lat;
        double cityLon = cityData.lon;

        String webPageForWeather = Config.WEB_PAGE_BASE+ "data/2.5/weather?lat="+cityLat+"&lon="+cityLon+"&appid="+Config.API_KEY;
        URL url1 = new URL(webPageForWeather);
        WeatherData weatherData = getWeatherData(url1);

        String webPageForForecast = Config.WEB_PAGE_BASE+"data/2.5/forecast?lat="+cityLat+"&lon="+cityLon+"&appid="+Config.API_KEY;
        URL url2 = new URL(webPageForForecast);

        ArrayList<ForecastData> forecastData = getForecastData(url2);

        return new Weather(cityData, weatherData, forecastData);
    }

    private ArrayList<ForecastData> getForecastData(URL url) throws IOException {
        int response = getResponse(url);

        if (response==200){
            String inline = getStringForGson(url);

            if (inline.isEmpty()) {
                return null;
            }
            else {
                ArrayList<ForecastData> forecastDataList= new ArrayList<ForecastData>();
                AdditionalData additionalData = gson.fromJson(inline, AdditionalData.class);

                for(int i = 0; i<40; i++){
                    String listItem = gson.toJson(additionalData.list.get(i));

                    AdditionalData additionalData1 = gson.fromJson(listItem, AdditionalData.class);
                    String dt = gson.toJson(additionalData1.dt);
                    String weather = gson.toJson(additionalData1.weather);
                    String mainData = gson.toJson(additionalData1.main);

                    String finalString = "{\"dt\":" + dt + "," + mainData.substring(1, mainData.length() - 1) + "," + weather.substring(2, weather.length() - 1);

                    ForecastData forecastData = gson.fromJson(finalString, ForecastData.class);

                    forecastDataList.add(forecastData);
                }
                return forecastDataList;
            }
        }
        else {
            System.out.println(messages.getApiError());
            return null;
        }
    }

    private WeatherData getWeatherData(URL url) throws IOException {
        int response = getResponse(url);

        if (response==200){
            String inline = getStringForGson(url);

            if (inline.isEmpty()) {
                return null;
            }
            else {
                AdditionalData additionalData = gson.fromJson(inline, AdditionalData.class);

                String mainData = gson.toJson(additionalData.main);
                mainData=mainData.substring(0,mainData.length()-1);

                String mainData2 = gson.toJson(additionalData.weather);
                mainData2 = mainData2.substring(2, mainData2.length()-1);

                mainData+=", "+ mainData2;

                return gson.fromJson(mainData, WeatherData.class);
            }
        }
        else {
            System.out.println(messages.getApiError());
            return null;
        }
    }

    private CityData getCityCoordinates(URL url, String cityName) throws IOException {
        int response = getResponse(url);

        if (response==200){
            String jsonString = getJsonStringFromUrl(url);

            if (jsonString.isEmpty()) {
                return new CityData();
            }
            else {
                return gson.fromJson(jsonString, CityData.class);
            }
        }
        else {
            System.out.println(messages.getApiError());
            return null;
        }
    }

    private String getJsonStringFromUrl(URL url) throws IOException {
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();

        String jsonString = inline.substring(1, inline.length() - 1);
        System.out.println(jsonString);
        return jsonString;
    }

    private int getResponse(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int response = conn.getResponseCode();
        return response;
    }
    private String getStringForGson(URL url) throws IOException {
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();

        return inline;
    }

    public static String setWeatherIcon(double temp) {
        String imageUrl = "/unknown.png";

        if(temp <= -15){
            imageUrl = "/cold.png";
        }
        else if(temp < -5 && temp > -15){
            imageUrl = "/minus.png";
        }
        else if(temp >= -5 && temp <= 5){
            imageUrl = "/zero.png";
        }
        else if(temp > 5 && temp < 20){
            imageUrl = "/plus.png";
        }
        else if(temp >=20) {
            imageUrl = "/hot.png";
        }
        return imageUrl;
    }
}

class AdditionalData {
    public  Object main;
    public  Object weather;
    public ArrayList<Object> list;
    public  Integer dt;
}