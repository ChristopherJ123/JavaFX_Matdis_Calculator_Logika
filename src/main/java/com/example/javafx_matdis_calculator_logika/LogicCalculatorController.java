package com.example.javafx_matdis_calculator_logika;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class LogicCalculatorController {

    String Input = "";
    @FXML
    private TextField UserInput;

    @FXML
    void initialize() {
        UserInput.setEditable(false);
    }
    void updateScreen() {
        this.UserInput.setText((Input));
    }
    void pressButton(String button){
        String low = button.toLowerCase();
        switch (low) {
            case "p", "q", "t", "f" -> Input = Input.concat(low.toUpperCase());
            case "n", "o", "c", "a", "i", "b", "(", ")", "1", "0" -> {
                switch (low) {
                    case "1":Input=Input.concat("T"); break;
                    case "0":Input=Input.concat("F"); break;
                    case "n":
                        Input = Input.concat("~");
                        break;
                    case "o":
                        Input = Input.concat("V");
                        break;
                    case "a":
                        Input = Input.concat("Λ");
                        break;

                    case "i":
                        Input = Input.concat("→");
                        break;
                    case "b":
                        Input = Input.concat("↔");
                        break;
                    case "(":
                        Input = Input.concat("(");
                        break;
                    case ")":
                        Input = Input.concat(")");
                        break;
                    case "c":
                        Input = "";
                        break;
                }
            }
            case "=" -> {
            }
        }
        updateScreen();
    }
    void pressButtonBackslash() {
        if (!Input.isEmpty()) {
            try {
                Input=Input.substring(0,Input.length()-1);
            } catch (Exception ignored) {}
        }
        updateScreen();
    }
    @FXML
    void pressButtonP(ActionEvent event){
        pressButton(((Button) event.getSource()).getText());
    }
    @FXML
    void pressButtonQ(ActionEvent event){
        pressButton(((Button) event.getSource()).getText());
    }
    @FXML
    void pressButtonA(ActionEvent event){
        pressButton(((Button) event.getSource()).getText());
    }
    @FXML
    void pressButtonO(ActionEvent event){
        pressButton(((Button) event.getSource()).getText());
    }
    @FXML
    void pressButtonBack(){
        pressButtonBackslash();
    }
    @FXML
    void pressButtonI(ActionEvent event){
        pressButton(((Button) event.getSource()).getText());
    }
    @FXML
    void pressButtonB(ActionEvent event){
        pressButton(((Button) event.getSource()).getText());
    }
    @FXML
    void pressButtonN(ActionEvent event){
        pressButton(((Button) event.getSource()).getText());
    }
    @FXML
    void pressButtonEquals(){
        pressButton("=");
    }
    @FXML
    void pressButtonC(){
        pressButton("C");
    }
    @FXML
    void pressButtonT(){
        pressButton("T");
    }
    @FXML
    void pressButtonF(){
        pressButton("F");
    }
    @FXML
    void pressButtonOpen(ActionEvent event){
        pressButton(((Button) event.getSource()).getText());
    }
    @FXML
    void pressButtonClose(ActionEvent event){
        pressButton(((Button) event.getSource()).getText());
    }
    @FXML
    public void KeyListener(KeyEvent event) {
        String input = event.getText();
        if (event.isShiftDown()) {
            if (input.matches("[90pqtfc6v]")) {
                if (input.equals("9")) pressButton("(");
                else if (input.equals("0")) pressButton(")");
                else if (input.equals("p")) pressButton("P");
                else if (input.equals("q")) pressButton("Q");
                else if (input.equals("t")) pressButton("T");
                else if (input.equals("f")) pressButton("F");
                else if (input.equals("c")) pressButton("C");
                else if (input.equals("6")) pressButton("A");
                else if (input.equals("v")) pressButton("O");
            }
        } else if (input.matches("[pqtfnaoibc10]")) {
            pressButton(input);
        } else if (input.matches("[10v]")) {
            if (input.equals("1")) pressButton("t");
            else if (input.equals("0")) pressButton("f");
            else if (input.equals("v")) pressButton("o");
        } else if (event.getCode() == KeyCode.BACK_SPACE) {
            pressButtonBackslash();
        } else if (event.getCode() == KeyCode.ENTER) {
            pressButton("=");
        }
    }
}
