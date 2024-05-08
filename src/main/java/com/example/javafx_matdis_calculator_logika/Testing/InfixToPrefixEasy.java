package com.example.javafx_matdis_calculator_logika.Testing;

import com.example.javafx_matdis_calculator_logika.InfixToPostfixAlgorithm;

import java.util.Scanner;

public class InfixToPrefixEasy {
    static String infixToPrefix(String input) {
        String newInput = "";
        for (int i = input.length()-1; i >= 0; i--) {
            newInput = newInput.concat(String.valueOf(input.charAt(i)));
        }
        String newInputFromInfixToPostfix = InfixToPostfixAlgorithm.infixToPostfix(newInput);
        String output = "";
        for (int i = newInputFromInfixToPostfix.length()-1; i >= 0; i--) {
            output = output.concat(String.valueOf(newInputFromInfixToPostfix.charAt(i)));
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().replace("a", "Λ").replace("o", "V").replace("i", "→").replace("b", "↔").replace("n", "¬");
        System.out.println(infixToPrefix(input).replace("Λ", "a").replace("V", "o").replace("→", "i").replace("↔", "b").replace("¬", "n"));
    }
}
