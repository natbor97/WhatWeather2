package pl.nataliamichalowska.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pl.nataliamichalowska.model.ForecastData;
import pl.nataliamichalowska.model.RealWeatherClient;
import pl.nataliamichalowska.model.Weather;
import pl.nataliamichalowska.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class WeatherDisplayController extends BaseController implements Initializable {
    @FXML
    private Label dateToday;
    @FXML
    private Label purposeCityNameExtended;
    @FXML
    private Label myCityNameExtended;
    @FXML
    private ImageView imagePurposeCityToday;
    @FXML
    private ImageView imageMyCityToday;
    @FXML
    private Label pressurePurposeCityToday;
    @FXML
    private Label pressureMyCityToday;
    @FXML
    private Label tempPurposeCityToday;
    @FXML
    private Label tempMyCityToday;
    @FXML
    private VBox myCityForecastBox;
    @FXML
    private VBox purposeCityForecastBox;
    private final Weather myCityWeather;
    private final Weather purposeCityWeather;

    public WeatherDisplayController(ViewFactory viewFactory, String fxmlName, Weather myCityWeather, Weather purposeCityWeather) {
        super(viewFactory, fxmlName);
        this.myCityWeather = myCityWeather;
        this.purposeCityWeather = purposeCityWeather;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLocalDate();
        setCityNames(myCityWeather, purposeCityWeather);
        setWeatherNumericalData();

        for(int i=Settings.FIRST_FORECAST_AFTER_ONE_DAY; i<Settings.TOTAL_NUMBER_OF_FORECAST_DATA; i++)
        {
            ForecastData myCityForecastData = myCityWeather.getForecastData().get(i);
            ForecastData purposeCityForecastData = purposeCityWeather.getForecastData().get(i);
            try {
                populateMyCityForecastBox(myCityForecastData);
                populatePurposeCityForecastBox(purposeCityForecastData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i+=Settings.FORECAST_DATA_PER_DAY;
        }
    }

    private void setLocalDate() {
        LocalDate dateForToday = LocalDate.now();

        String formattedDate = dateForToday.format(Settings.DATE_FORMAT);
        dateToday.setText("DZISIAJ: "+formattedDate);
    }

    private void setCityNames(Weather myCityWeather, Weather purposeCityWeather) {
        String myCity = myCityWeather.getCityData().name + ", "+ myCityWeather.getCityData().country;
        String purposeCity = purposeCityWeather.getCityData().name+ ", "+ purposeCityWeather.getCityData().country;
        myCityNameExtended.setText(myCity);
        purposeCityNameExtended.setText(purposeCity);
    }

    private void setWeatherNumericalData() {
        double temp1 = (Math.round((this.myCityWeather.getWeatherData().temp-272.15)*100))/100.0;
        tempMyCityToday.setText("Temperatura: "+ temp1 +" °C");
        pressureMyCityToday.setText("Ciśnienie: "+ this.myCityWeather.getWeatherData().pressure +" hPa");
        String imageUrl1 = RealWeatherClient.setWeatherIcon(temp1);
        imageMyCityToday.setImage(new Image(imageUrl1));

        double temp2 = (Math.round((this.purposeCityWeather.getWeatherData().temp-272.15)*100))/100.0;
        tempPurposeCityToday.setText("Temperatura: "+ temp2 +" °C");
        pressurePurposeCityToday.setText("Ciśnienie: "+ this.purposeCityWeather.getWeatherData().pressure +" hPa");
        String imageUrl2 = RealWeatherClient.setWeatherIcon(temp2);
        imagePurposeCityToday.setImage(new Image(imageUrl2));
    }

    private void populatePurposeCityForecastBox(ForecastData forecastData) throws IOException {
        Parent parent1 = viewFactory.showForecastDisplay(forecastData);
        purposeCityForecastBox.getChildren().add(parent1);
    }

    private void populateMyCityForecastBox(ForecastData forecastData) throws IOException {
        Parent parent2 = viewFactory.showForecastDisplay(forecastData);
        myCityForecastBox.getChildren().add(parent2);
    }

}
