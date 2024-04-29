package com.example.javafx_matdis_calculator_logika;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class LogicCalculatorController {

    String Input = "";
    @FXML
    private TextField UserInput;

    @FXML
    private TextField output;

    @FXML
    private TextArea table;

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
                    case "1" -> Input = Input.concat("T");
                    case "0" -> Input = Input.concat("F");
                    case "n" -> Input = Input.concat("¬");
                    case "o" -> Input = Input.concat("V");
                    case "a" -> Input = Input.concat("Λ");
                    case "i" -> Input = Input.concat("→");
                    case "b" -> Input = Input.concat("↔");
                    case "(" -> Input = Input.concat("(");
                    case ")" -> Input = Input.concat(")");
                    case "c" -> Input = "";
                }
            }
            case "=" -> {
                int errorCode = InfixToPostfixAlgorithm.checkForErrors(Input.replace(" ", ""));
                switch (errorCode) {
                    case 1 -> {
                        output.setText("Isi input terlebih dahulu!");
                        table.setText("");
                    }
                    case 2 -> {
                        output.setText("Error! Jumlah open bracket dan close bracket tidak sama!");
                        table.setText("");
                    }
                    case 3 -> {
                        output.setText("Error! Setelah operator harus ada variabel!");
                        table.setText("");
                    }
                    case 4 -> {
                        output.setText("Error! Setelah not tidak boleh ada not lagi tanpa ada kurung (Ex: nnP, seharusnya n(np) atau n(n(P)))");
                        table.setText("");
                    }
                    case 5 -> {
                        output.setText("Error! Setelah variabel tidak boleh ada variabel lain!");
                        table.setText("");
                    }
                    //case 6 -> output.setText("Error! Length input minimal 30!");
                    default -> {
                        String[][] truthTable = TruthTable.truthTable(Input.replace(" ", ""));
                        String truthTableString = "";
                        String[][] truthTableRotatedClockwise = new String[truthTable[0].length][truthTable.length];
                        for (int i = 0; i < truthTable.length; i++) {
                            for (int j = 0; j < truthTable[i].length; j++) {
                                truthTableRotatedClockwise[j][i] = truthTable[i][j];
                            }
                        }
                        int gapCount = truthTable[truthTable.length-1][0].length()/2;
                        for (int i = 0; i < truthTableRotatedClockwise.length; i++) {
                            for (int j = 0; j < truthTableRotatedClockwise[i].length; j++) {
                                if (i != 0 && j == truthTableRotatedClockwise[i].length-1) truthTableString = truthTableString.concat("  ".repeat(gapCount) + truthTableRotatedClockwise[i][j] + "\t\t");
                                else truthTableString = truthTableString.concat(truthTableRotatedClockwise[i][j] + "\t\t");
                            }
                            truthTableString = truthTableString.concat("\n");
                        }
                        boolean allTrue = false;
                        boolean allFalse = false;
                        try {
                            for (int i = 1; i < truthTable.length+1; i++) {
                                if (truthTable[truthTable.length-1][i].equals("1")) {
                                    allTrue = true;
                                } else {
                                    allTrue = false;
                                    break;
                                }
                            }
                            for (int i = 1; i < truthTable.length+1; i++) {
                                if (truthTable[truthTable.length-1][i].equals("0")) {
                                    allFalse = true;
                                } else {
                                    allFalse = false;
                                    break;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            output.setText(truthTable[0][0].equals("1") ? "Tautology" : "Contradiction");
                            table.setText("");
                            return;
                        }

                        if (allTrue && !allFalse) {
                            output.setText("Tautology");
                        } else if (!allTrue && allFalse) {
                            output.setText("Contradiction");
                        } else {
                            output.setText("Contingency");
                        }
                        table.setText(truthTableString);
//                        for (String[] row : truthTable) {
//                            for (String item : row) {
//                                System.out.print(item + "\t");
//                            }
//                            System.out.println();
//                        }
                    }
                }
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
                switch (input) {
                    case "9" -> pressButton("(");
                    case "0" -> pressButton(")");
                    case "p" -> pressButton("P");
                    case "q" -> pressButton("Q");
                    case "t" -> pressButton("T");
                    case "f" -> pressButton("F");
                    case "c" -> pressButton("C");
                    case "6" -> pressButton("A");
                    case "v" -> pressButton("O");
                }
            }
        } else if (input.matches("[pqtfnaoibc10]")) {
            pressButton(input);
        } else if (input.matches("[10v]")) {
            switch (input) {
                case "1" -> pressButton("t");
                case "0" -> pressButton("f");
                case "v" -> pressButton("o");
            }
        } else if (event.getCode() == KeyCode.BACK_SPACE) {
            pressButtonBackslash();
        } else if (event.getCode() == KeyCode.ENTER) {
            pressButton("=");
        }
    }
}
