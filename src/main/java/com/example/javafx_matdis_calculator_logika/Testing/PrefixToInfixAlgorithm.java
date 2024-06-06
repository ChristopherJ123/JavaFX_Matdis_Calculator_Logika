package com.example.javafx_matdis_calculator_logika.Testing;

import java.util.ArrayList;
import java.util.Scanner;

public class PrefixToInfixAlgorithm {
    public static String prefixToInfix(String input) {
        String output = "";

        ArrayList<String> variables = new ArrayList<>();

        for (int i = input.length()-1; i >= 0; i--) {
            if ("PQ10".contains(String.valueOf(input.charAt(i)))) {
                variables.add(String.valueOf(input.charAt(i)));
            } else if ("nΛV→↔¬".contains(String.valueOf(input.charAt(i)))) {
                if (input.charAt(i) == '¬') {
                    if (input.charAt(i+1) == '¬') output = input.charAt(i) + "(" + variables.get(variables.size()-1) + ")";
                    else output = input.charAt(i) + variables.get(variables.size()-1);
                    variables.remove(variables.size() - 1);
                    variables.add(output);
                } else {
                    output = "(" + variables.get(variables.size() - 1) + input.charAt(i) + variables.get(variables.size() - 2) + ")";
                    variables.remove(variables.size() - 2);
                    variables.remove(variables.size() - 1);
                    variables.add(output);
                }
            }
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().replace("a", "Λ").replace("o", "V").replace("i", "→").replace("b", "↔").replace("n", "¬");
        System.out.println(prefixToInfix(input).replace("Λ", "a").replace("V", "o").replace("→", "i").replace("↔", "b").replace("¬", "n"));
    }
}
