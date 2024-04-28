package com.example.javafx_matdis_calculator_logika;

import java.util.ArrayList;
import java.util.Scanner;

public class InfixToPostfixAlgorithm {

    static String infixToPostfix(String input) {
        String output = "";

        ArrayList<String> operators = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            if ("ΛV→↔¬()".contains(String.valueOf(input.charAt(i)))) { // Operator
                if ("()".contains(String.valueOf(input.charAt(i)))) {
                    if (input.charAt(i) == '(') operators.add("(");
                    else if (input.charAt(i) == ')') {
                        for (int j = operators.size()-1; j >= 0; j--) {
                            if (operators.get(j).equals("(")) {
                                operators.remove(j);
                                break;
                            }
                            output = output.concat(operators.get(j));
                            operators.remove(j);
                        }
                    }
                } else if ("ΛV".contains(String.valueOf(input.charAt(i)))) { // Level 1
                    try {
                        if (operators.get(operators.size()-1).matches("[¬↔→ΛV]")) {
                            for (int j = operators.size()-1; j >= 0; j--) {
                                if (operators.get(j).equals("(")) break;
                                output = output.concat(operators.get(j));
                                operators.remove(j);
                            }
                            operators.add(String.valueOf(input.charAt(i)));
                        } else {
                            operators.add(String.valueOf(input.charAt(i)));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        operators.add(String.valueOf(input.charAt(i)));
                    }
                } else if ("→".contains(String.valueOf(input.charAt(i)))) { // Level 2
                    try {
                        if (operators.get(operators.size()-1).matches("[¬↔→]")) {
                            for (int j = operators.size()-1; j >= 0; j--) {
                                if (operators.get(j).equals("(")) break;
                                output = output.concat(operators.get(j));
                                operators.remove(j);
                            }
                            operators.add(String.valueOf(input.charAt(i)));
                        } else {
                            operators.add(String.valueOf(input.charAt(i)));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        operators.add(String.valueOf(input.charAt(i)));
                    }
                } else if ("↔".contains(String.valueOf(input.charAt(i)))) { // Level 3
                    try {
                        if (operators.get(operators.size()-1).matches("[¬↔]")) {
                            for (int j = operators.size()-1; j >= 0; j--) {
                                if (operators.get(j).equals("(")) break;
                                output = output.concat(operators.get(j));
                                operators.remove(j);
                            }
                            operators.add(String.valueOf(input.charAt(i)));
                        } else {
                            operators.add(String.valueOf(input.charAt(i)));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        operators.add(String.valueOf(input.charAt(i)));
                    }
                } else if ("¬".contains(String.valueOf(input.charAt(i)))) { // Level 4
                    try {
                        if (operators.get(operators.size()-1).matches("[¬]")) {
                            for (int j = operators.size()-1; j >= 0; j--) {
                                if (operators.get(j).equals("(")) break;
                                output = output.concat(operators.get(j));
                                operators.remove(j);
                            }
                            operators.add(String.valueOf(input.charAt(i)));
                        } else {
                            operators.add(String.valueOf(input.charAt(i)));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        operators.add(String.valueOf(input.charAt(i)));
                    }
                }
            } else if ("pq".contains(String.valueOf(input.charAt(i)).toLowerCase())) {
                output = output.concat(String.valueOf(input.charAt(i)));
            }
        }
        for (int i = operators.size()-1; i >= 0; i--) {
            output = output.concat(operators.get(i));
            operators.remove(i);
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().replace("a", "Λ").replace("o", "V").replace("i", "→").replace("b", "↔").replace("n", "¬");
        System.out.println(infixToPostfix(input));
    }
}
