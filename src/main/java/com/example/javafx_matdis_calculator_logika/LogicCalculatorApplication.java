package com.example.javafx_matdis_calculator_logika;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LogicCalculatorApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LogicCalculatorApplication.class.getResource("logicCalculator-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            if (getClass().getResource("style.css") != null) {
                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            } else System.out.println("lol");

            stage.setTitle("Logic Calculator");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}