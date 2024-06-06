package com.example.javafx_matdis_calculator_logika;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ConversionCalculatorApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LogicCalculatorApplication.class.getResource("conversionCalculator-view.fxml"));
            Font algerianFont = Font.loadFont(getClass().getResourceAsStream("algerian.ttf"), 20);

            Scene scene = new Scene(fxmlLoader.load());
//
//            if (getClass().getResource("style.css") != null) {
//                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
//            } else System.out.println("lol");

            stage.setTitle("Logic Calculator");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}