package com.example.javafx_matdis_calculator_logika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GPTTOLOL {

    public static String infixToPostfix(String string) {
        String input = string.replace("T", "1").replace("F", "0");
        StringBuilder output = new StringBuilder();

        ArrayList<String> operators = new ArrayList<>();
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('(', 0);
        precedence.put('↔', 1); // Bi-implication
        precedence.put('→', 2); // Implication
        precedence.put('V', 3); // Or
        precedence.put('Λ', 4); // And
        precedence.put('¬', 5); // Not

        for (int i = 0; i < input.length(); i++) {
            char token = input.charAt(i);
            if (Character.isLetterOrDigit(token)) { // Operand
                output.append(token);
            } else if (token == '(') {
                operators.add("(");
            } else if (token == ')') {
                while (!operators.isEmpty() && !operators.get(operators.size() - 1).equals("(")) {
                    output.append(operators.remove(operators.size() - 1));
                }
                operators.remove(operators.size() - 1); // Remove '('
            } else { // Operator
                while (!operators.isEmpty() && precedence.get(operators.get(operators.size() - 1).charAt(0)) >= precedence.get(token)) {
                    output.append(operators.remove(operators.size() - 1));
                }
                operators.add(String.valueOf(token));
            }
            System.out.println(output.toString() + " " + operators.toString().replaceAll("[\\[\\], ]", "")
                    .replace("Λ", "a").replace("V", "o").replace("→", "i").replace("↔", "b").replace("¬", "n"));
        }

        while (!operators.isEmpty()) {
            output.append(operators.remove(operators.size() - 1));
        }

        return output.toString();
    }

    public static void main(String[] args) { //TOLOL GPT
        String infix = "(PΛQ)V(P→¬P)↔¬(¬Q)".replace("a", "Λ").replace("o", "V").replace("i", "→").replace("b", "↔").replace("n", "¬");
        String postfix = infixToPostfix(infix).replace("Λ", "a").replace("V", "o").replace("→", "i").replace("↔", "b").replace("¬", "n");
        System.out.println("Postfix: " + postfix);
    }
}
