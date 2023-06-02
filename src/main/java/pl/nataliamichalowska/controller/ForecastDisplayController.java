package pl.nataliamichalowska.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import pl.nataliamichalowska.model.ForecastData;
import pl.nataliamichalowska.model.RealWeatherClient;
import pl.nataliamichalowska.view.ViewFactory;

import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ForecastDisplayController extends BaseController implements Initializable {
    private ForecastData forecastData;
    @FXML
    private Label forecastDate;
    @FXML
    private ImageView imageWeather;
    @FXML
    private Label pressureForecast;
    @FXML
    private Label tempForecast;

    public ForecastDisplayController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    public void setForecastData(ForecastData forecastData) {
        this.forecastData = forecastData;
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        setForecastDate();
        setNumericalWeatherData();
    }

    private void setNumericalWeatherData() {
        double temp = (Math.round((forecastData.temp-272.15)*100))/100.0;
        tempForecast.setText("Temperatura: "+temp +" °C");
        pressureForecast.setText("Ciśnienie: "+ forecastData.pressure +" hPa");
        String imageUrl = RealWeatherClient.setWeatherIcon(temp);
        imageWeather.setImage(new Image(imageUrl));
    }

    private void setForecastDate() {
        Date date = new Date(forecastData.dt*1000);
        Format format = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss");
        String formattedDate = format.format(date);
        forecastDate.setText(formattedDate);
    }
}
