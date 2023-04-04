package pl.nataliamichalowska;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.nataliamichalowska.view.ViewFactory;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
       ViewFactory viewFactory = new ViewFactory();
       viewFactory.showMainPage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}