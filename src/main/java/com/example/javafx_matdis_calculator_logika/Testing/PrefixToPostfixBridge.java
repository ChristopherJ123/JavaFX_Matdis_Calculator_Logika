package com.example.javafx_matdis_calculator_logika.Testing;

import com.example.javafx_matdis_calculator_logika.InfixToPostfixAlgorithm;

import java.util.Scanner;

public class PrefixToPostfixBridge {
    static String prefixToPostfix(String input) {
        String inputFromPrefixToInfix = PrefixToInfixAlgorithm.prefixToInfix(input);
        return InfixToPostfixAlgorithm.infixToPostfix(inputFromPrefixToInfix);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().replace("a", "Λ").replace("o", "V").replace("i", "→").replace("b", "↔").replace("n", "¬");
        System.out.println(prefixToPostfix(input).replace("Λ", "a").replace("V", "o").replace("→", "i").replace("↔", "b").replace("¬", "n"));
    }
}
