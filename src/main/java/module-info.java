module pl.nataliamichalowska {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports pl.nataliamichalowska;
    opens pl.nataliamichalowska to javafx.fxml, javafx.controls, com.google.gson;

    exports pl.nataliamichalowska.controller;
    opens pl.nataliamichalowska.controller to javafx.fxml, javafx.controls, com.google.gson;

    exports pl.nataliamichalowska.view;
    opens pl.nataliamichalowska.view to javafx.fxml, javafx.controls, com.google.gson;

    exports pl.nataliamichalowska.model;
    opens pl.nataliamichalowska.model to javafx.fxml, javafx.controls, com.google.gson;
}