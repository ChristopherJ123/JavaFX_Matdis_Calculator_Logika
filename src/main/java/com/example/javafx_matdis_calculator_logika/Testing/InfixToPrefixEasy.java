package com.example.javafx_matdis_calculator_logika.Testing;

import com.example.javafx_matdis_calculator_logika.InfixToPostfixAlgorithm;

import java.util.Scanner;

public class InfixToPrefixEasy {

    public static String infixToPrefix(String input) {
        String newInput = "";
        for (int i = input.length()-1; i >= 0; i--) { // 1. Reverse order dari input (Misal "n(nPoQ)" menjadi ")QoPn(n" )
            newInput = newInput.concat(String.valueOf(input.charAt(i)));
        }
        newInput = newInput.replace("(", "*").replace(")", "(").replace("*", ")"); // 2. Replace "(" menjadi ")" dan ")" menjadi "("
        String newInputFromInfixToPostfix = InfixToPostfixAlgorithm.infixToPostfix(newInput); // 3. Infix to POSTFIX
        String output = "";
        for (int i = newInputFromInfixToPostfix.length()-1; i >= 0; i--) { // 4. Reverse order lagi & done
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
