package pl.nataliamichalowska.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.nataliamichalowska.model.RealWeatherClient;
import pl.nataliamichalowska.model.Weather;
import pl.nataliamichalowska.model.WeatherService;
import pl.nataliamichalowska.view.Messages;
import pl.nataliamichalowska.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController extends BaseController implements Initializable {
    @FXML
    private TextField purposeCityText;
    @FXML
    private TextField myCityText;
    @FXML
    private Label errorLabel;

    Messages messages = new Messages();

    public MainPageController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    private final WeatherService weatherService = new WeatherService(new RealWeatherClient());

    @FXML
    void checkButton() throws IOException {
        if((myCityText.getText().isEmpty()) && (purposeCityText.getText().isEmpty())){
            errorLabel.setText(messages.getEmptyLocations());
        } else if (myCityText.getText().isEmpty()) {
            errorLabel.setText(messages.getEmptyYourLocation());
        } else if (purposeCityText.getText().isEmpty()) {
            errorLabel.setText(messages.getEmptyPurposeLocation());
        }
        else {
            getWeatherForCities();
        }
    }

    private void getWeatherForCities() throws IOException {
        String myCityName = myCityText.getText();
        String purposeCityName = purposeCityText.getText();

        Weather myCityWeather = weatherService.getWeather(myCityName);
        Weather purposeCityWeather = weatherService.getWeather(purposeCityName);

        if ((myCityWeather.getCityData().name == null)&&(purposeCityWeather.getCityData().name == null)||
                (myCityWeather.getCityData().name == null) ||
                (purposeCityWeather.getCityData().name == null)) {
            errorLabel.setText(messages.getNoCityInDatabase());
        }
        else {
            displayWeather(myCityWeather, purposeCityWeather);
        }
    }

    private void displayWeather(Weather myCityWeather, Weather purposeCityWeather){
        viewFactory.showWeatherDisplay(myCityWeather, purposeCityWeather);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myCityText.setText("Kraków");
        purposeCityText.setText("Rzym");
    }
}