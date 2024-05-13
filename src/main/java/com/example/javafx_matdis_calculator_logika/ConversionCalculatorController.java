package com.example.javafx_matdis_calculator_logika;

import com.example.javafx_matdis_calculator_logika.Testing.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ConversionCalculatorController {

    String infixInputString = "";
    String prefixInputString = "";
    String postfixInputString = "";

    boolean infixIsClicked = true;
    boolean prefixIsClicked = false;
    boolean postfixIsClicked = false;

    @FXML
    private TextField infixInput;

    @FXML
    private TextField output;

    @FXML
    private TextField postfixInput;

    @FXML
    private TextField prefixInput;

    @FXML
    private TextArea table;

    @FXML
    public void textBoxClick(MouseEvent event) {
        if (event.getSource().toString().equals("TextField[id=infixInput, styleClass=text-input text-field]")) {
            infixIsClicked = true;
            prefixIsClicked = false;
            postfixIsClicked = false;
        } else if (event.getSource().toString().equals("TextField[id=prefixInput, styleClass=text-input text-field]")) {
            infixIsClicked = false;
            prefixIsClicked = true;
            postfixIsClicked = false;
        } else if (event.getSource().toString().equals("TextField[id=postfixInput, styleClass=text-input text-field]")) {
            infixIsClicked = false;
            prefixIsClicked = false;
            postfixIsClicked = true;
        }
    }

    @FXML
    void initialize() {
        infixInput.setEditable(false);
        prefixInput.setEditable(false);
        postfixInput.setEditable(false);
    }

    void updateScreen() {
        if (infixIsClicked) {
            this.infixInput.setText(infixInputString);
        } else if (prefixIsClicked) {
            this.prefixInput.setText(prefixInputString);
        } else if (postfixIsClicked) {
            this.postfixInput.setText(postfixInputString);
        }
    }

    void pressButton(String button){
        String low = button.toLowerCase();
        switch (low) {
            case "p", "q" -> {
                if (infixIsClicked) infixInputString = infixInputString.concat(low.toUpperCase());
                else if (prefixIsClicked) prefixInputString = prefixInputString.concat(low.toUpperCase());
                else if (postfixIsClicked) postfixInputString = postfixInputString.concat(low.toUpperCase());
            }
            case "n", "o", "c", "a", "i", "b", "(", ")", "1", "0", "t", "f" -> {
                if (infixIsClicked) {
                    switch (low) {
                        case "1", "t" -> infixInputString = infixInputString.concat("1");
                        case "0", "f" -> infixInputString = infixInputString.concat("0");
                        case "n" -> infixInputString = infixInputString.concat("¬");
                        case "o" -> infixInputString = infixInputString.concat("V");
                        case "a" -> infixInputString = infixInputString.concat("Λ");
                        case "i" -> infixInputString = infixInputString.concat("→");
                        case "b" -> infixInputString = infixInputString.concat("↔");
                        case "(" -> infixInputString = infixInputString.concat("(");
                        case ")" -> infixInputString = infixInputString.concat(")");
                        case "c" -> infixInputString = "";
                    }
                }
                else if (prefixIsClicked) {
                    switch (low) {
                        case "1", "t" -> prefixInputString = prefixInputString.concat("1");
                        case "0", "f" -> prefixInputString = prefixInputString.concat("0");
                        case "n" -> prefixInputString = prefixInputString.concat("¬");
                        case "o" -> prefixInputString = prefixInputString.concat("V");
                        case "a" -> prefixInputString = prefixInputString.concat("Λ");
                        case "i" -> prefixInputString = prefixInputString.concat("→");
                        case "b" -> prefixInputString = prefixInputString.concat("↔");
                        case "(" -> prefixInputString = prefixInputString.concat("(");
                        case ")" -> prefixInputString = prefixInputString.concat(")");
                        case "c" -> prefixInputString = "";
                    }
                }
                else if (postfixIsClicked) {
                    switch (low) {
                        case "1", "t" -> postfixInputString = postfixInputString.concat("1");
                        case "0", "f" -> postfixInputString = postfixInputString.concat("0");
                        case "n" -> postfixInputString = postfixInputString.concat("¬");
                        case "o" -> postfixInputString = postfixInputString.concat("V");
                        case "a" -> postfixInputString = postfixInputString.concat("Λ");
                        case "i" -> postfixInputString = postfixInputString.concat("→");
                        case "b" -> postfixInputString = postfixInputString.concat("↔");
                        case "(" -> postfixInputString = postfixInputString.concat("(");
                        case ")" -> postfixInputString = postfixInputString.concat(")");
                        case "c" -> postfixInputString = "";
                    }
                }

            }
            case "=" -> {

                int errorCode = 0;

                if (infixIsClicked) {
                    errorCode = InfixToPostfixAlgorithm.checkForErrors(infixInputString.replace(" ", ""));
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
                        default -> {
                            String postfix = InfixToPostfixAlgorithm.infixToPostfix(infixInputString);
                            this.postfixInput.setText(postfix);
                            String prefix = InfixToPrefixEasy.infixToPrefix(infixInputString);
                            this.prefixInput.setText(prefix);
                            System.out.println(this.postfixInput.getText());
                            System.out.println(this.prefixInput.getText());
                            System.out.println("Success");
                            System.out.println(errorCode);
                        }
                    }
                } else if (prefixIsClicked) {
                    try {
                        errorCode = PrePostFix.checkPrefixErrors(prefixInputString);
                        switch (errorCode) {
                            case 1 -> {
                                output.setText("Isi input terlebih dahulu!");
                                table.setText("");
                            }
                            case 7 -> {
                                output.setText("Tidak boleh ada kurung!");
                                table.setText("");
                            }
                            case 8 -> {
                                output.setText("Input tidak termasuk prefix!");
                                table.setText("");
                            }
                            case 9 -> {
                                output.setText("Jumlah variable kurang!");
                                table.setText("");
                            }
                            default -> {
                                String infix = PrefixToInfixAlgorithm.prefixToInfix(prefixInputString);
                                this.infixInput.setText(infix);
                                String postfix = PrefixToPostfixBridge.prefixToPostfix(prefixInputString);
                                this.postfixInput.setText(postfix);
                            }
                        }
                    } catch (Exception e) {
                        errorCode = 99;
                    }
                } else if (postfixIsClicked) {
                    try {
                        errorCode = PrePostFix.checkPostfixErrors(postfixInputString);
                        switch (errorCode) {
                            case 1 -> {
                                output.setText("Isi input terlebih dahulu!");
                                table.setText("");
                            }
                            case 7 -> {
                                output.setText("Tidak boleh ada kurung!");
                                table.setText("");
                            }
                            case 8 -> {
                                output.setText("Input tidak termasuk postfix!");
                                table.setText("");
                            }
                            case 9 -> {
                                output.setText("Jumlah variable kurang!");
                                table.setText("");
                            }
                            default -> {
                                String infix = PostfixToInfixAlgorithm.postfixToInfix(postfixInputString);
                                this.infixInput.setText(infix);
                                String prefix = PostfixToPrefixBridge.postfixToPrefix(postfixInputString);
                                this.prefixInput.setText(prefix);
                            }
                        }
                    } catch (Exception e) {
                        errorCode = 99;
                    }
                }

                if (infixIsClicked) {
                    if ((prefixInput.getText().equals("") || postfixInput.getText().equals("")) && !infixInput.getText().equals("")) {
                        errorCode = 99;
                    }
                } else if (prefixIsClicked) {
                    if ((infixInput.getText().equals("") || postfixInput.getText().equals("")) && !prefixInput.getText().equals("")) {
                        errorCode = 99;
                    }
                } else if (postfixIsClicked) {
                    if ((infixInput.getText().equals("") || prefixInput.getText().equals("")) && !postfixInput.getText().equals("")) {
                        errorCode = 99;
                    }
                }

                if (errorCode == 0) {
                    System.out.println("table");
                    String[][] truthTable = TruthTable.truthTable(infixInput.getText());
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
                } else {
                    if (errorCode == 99) {
                        output.setText("Error! Input tidak sesuai dengan ekspresi yang dipilih.");
                    }
                    table.setText("");
                    if (infixIsClicked) {
                        this.prefixInput.setText("");
                        this.postfixInput.setText("");
                    } else if (prefixIsClicked) {
                        this.infixInput.setText("");
                        this.postfixInput.setText("");
                    } else if (postfixIsClicked) {
                        this.infixInput.setText("");
                        this.prefixInput.setText("");
                    }
                }

            }
        }
        updateScreen();
    }
    void pressButtonBackslash() {
        if (infixIsClicked) {
            if (!infixInputString.isEmpty()) {
                try {
                    infixInputString = infixInputString.substring(0, infixInputString.length()-1);
                } catch (Exception ignored) {}
            }
        } else if (prefixIsClicked) {
            if (!prefixInputString.isEmpty()) {
                try {
                    prefixInputString = prefixInputString.substring(0, prefixInputString.length()-1);
                } catch (Exception ignored) {}
            }
        } else if (postfixIsClicked) {
            if (!postfixInputString.isEmpty()) {
                try {
                    postfixInputString = postfixInputString.substring(0, postfixInputString.length()-1);
                } catch (Exception ignored) {}
            }
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
