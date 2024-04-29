package com.example.javafx_matdis_calculator_logika;

import java.util.Scanner;

public class TruthTable {
    static boolean calculate(String input, boolean A) {
        String prompt = input.replace("P", A ? "1" : "0").replace("Q", A ? "1" : "0");
        for (int i = 0; i < prompt.length()-1; i++) {
            switch (prompt.charAt(i+1)) {
                case 'Λ' -> {
                    int value = (prompt.charAt(i-1) == '1' && prompt.charAt(i) == '1') ? 1 : 0;
                    prompt = prompt.substring(0, i-1) + value + prompt.substring(i+2);
                    i-=2;
                }
                case 'V' -> {
                    int value = (prompt.charAt(i-1) == '1' || prompt.charAt(i) == '1') ? 1 : 0;
                    prompt = prompt.substring(0, i-1) + value + prompt.substring(i+2);
                    i-=2;
                }
                case '→' -> {
                    int value = (prompt.charAt(i-1) != '1' || prompt.charAt(i) == '1') ? 1 : 0;
                    prompt = prompt.substring(0, i-1) + value + prompt.substring(i+2);
                    i-=2;
                }
                case '↔' -> {
                    int value = (prompt.charAt(i-1) == prompt.charAt(i)) ? 1 : 0;
                    prompt = prompt.substring(0, i-1) + value + prompt.substring(i+2);
                    i-=2;
                }
                case '¬' -> {
                    int value = prompt.charAt(i) == '0' ? 1 : 0;
                    prompt = prompt.substring(0, i) + value + prompt.substring(i+2);
                    i--;
                }
            }
//            System.out.println(prompt);
        }
        return prompt.equals("1");
    }

    static boolean calculate(String input, boolean P, boolean Q) {
        String prompt = input.replace("P", P ? "1" : "0").replace("Q", Q ? "1" : "0");
        for (int i = 0; i < prompt.length()-1; i++) {
            switch (prompt.charAt(i+1)) {
                case 'Λ' -> {
                    int value = (prompt.charAt(i-1) == '1' && prompt.charAt(i) == '1') ? 1 : 0;
                    prompt = prompt.substring(0, i-1) + value + prompt.substring(i+2);
                    i-=2;
                }
                case 'V' -> {
                    int value = (prompt.charAt(i-1) == '1' || prompt.charAt(i) == '1') ? 1 : 0;
                    prompt = prompt.substring(0, i-1) + value + prompt.substring(i+2);
                    i-=2;
                }
                case '→' -> {
                    int value = (prompt.charAt(i-1) != '1' || prompt.charAt(i) == '1') ? 1 : 0;
                    prompt = prompt.substring(0, i-1) + value + prompt.substring(i+2);
                    i-=2;
                }
                case '↔' -> {
                    int value = (prompt.charAt(i-1) == prompt.charAt(i)) ? 1 : 0;
                    prompt = prompt.substring(0, i-1) + value + prompt.substring(i+2);
                    i-=2;
                }
                case '¬' -> {
                    int value = prompt.charAt(i) == '0' ? 1 : 0;
                    prompt = prompt.substring(0, i) + value + prompt.substring(i+2);
                    i--;
                }
            }
//            System.out.println(prompt);
        }
        return prompt.equals("1");
    }
    static String[][] truthTable (String input) {
        String variables = "";
        for (int i = 0; i < input.length(); i++) {
            if (variables.contains(String.valueOf(input.charAt(i)))) continue;
            if (input.charAt(i) == 'P') variables = variables.concat(String.valueOf(input.charAt(i)));
            if (input.charAt(i) == 'Q') variables = variables.concat(String.valueOf(input.charAt(i)));
        }
        if (variables.equals("")) {
            input = InfixToPostfixAlgorithm.infixToPostfix(input);
            return new String[][]{{calculate(input, true) ? "1" : "0"}};
        }

            String[][] table = new String[1 + variables.length()][1 + (variables.length()*2)];
        for (int i = 0; i < variables.length(); i++) {
            table[i][0] = String.valueOf(variables.charAt(i));
        }
        table[variables.length()][0] = input;
        input = InfixToPostfixAlgorithm.infixToPostfix(input);
        int index = 1;

        if (variables.length() == 1) {
            for (int P = 0; P < 2; P++) {
                boolean result = calculate(input, P==1);
                table[0][index] = String.valueOf(P);
                table[1][index] = result ? "1" : "0";
                index++;
            }
        } else if (variables.length() == 2) {
            for (int P = 0; P < 2; P++) {
                for (int Q = 0; Q < 2; Q++) {
//                System.out.println("Q=" + Q + " P=" + P);
                    boolean result = calculate(input, P==1, Q==1);
                    table[0][index] = String.valueOf(P);
                    table[1][index] = String.valueOf(Q);
                    table[2][index] = result ? "1" : "0";
//                System.out.println();
                    index++;
                }
            }
        }
        return table;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[][] hasil = truthTable(input);

        for (String[] row : hasil) {
            for (String item : row) {
                System.out.print(item + "   ");
            }
            System.out.println();
        }
    }
}
