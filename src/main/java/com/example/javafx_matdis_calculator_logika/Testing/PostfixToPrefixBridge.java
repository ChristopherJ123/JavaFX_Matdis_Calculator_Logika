package com.example.javafx_matdis_calculator_logika.Testing;

import java.util.Scanner;

public class PostfixToPrefixBridge {
    static String postfixToPrefix(String input) {
        String inputFromPostfixToInfix = PostfixToInfixAlgorithm.postfixToInfix(input);
        return InfixToPrefixEasy.infixToPrefix(inputFromPostfixToInfix);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().replace("a", "Λ").replace("o", "V").replace("i", "→").replace("b", "↔").replace("n", "¬");
        System.out.println(postfixToPrefix(input).replace("Λ", "a").replace("V", "o").replace("→", "i").replace("↔", "b").replace("¬", "n"));
    }
}
