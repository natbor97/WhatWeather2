package pl.nataliamichalowska.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.nataliamichalowska.controller.BaseController;
import pl.nataliamichalowska.controller.MainPageController;
import pl.nataliamichalowska.controller.WeatherDisplayController;
import pl.nataliamichalowska.controller.ForecastDisplayController;
import pl.nataliamichalowska.model.ForecastData;
import pl.nataliamichalowska.model.Weather;

import java.io.IOException;

public class ViewFactory {
    public void showMainPage(){
        BaseController controller = new MainPageController (this, "/MainPageView.fxml");
        initializeStage(controller);
    }

    public void showWeatherDisplay(Weather myCityWeather, Weather purposeCityWeather){
        BaseController controller = new WeatherDisplayController(this, "/WeatherDisplayView.fxml", myCityWeather, purposeCityWeather);
        initializeStage(controller);
    }

    public Parent showForecastDisplay(ForecastData forecastData) throws IOException {
        ForecastDisplayController controller = new ForecastDisplayController(null, "/ForecastDisplayView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        controller.setForecastData(forecastData);
        fxmlLoader.setController(controller);

        Parent parent;
        parent = fxmlLoader.load();
        return parent;
    }

    private static void initializeStage(BaseController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(ViewFactory.class.getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);

        Parent parent;
        try {
            parent = fxmlLoader.load();
        }catch(IOException e){
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
