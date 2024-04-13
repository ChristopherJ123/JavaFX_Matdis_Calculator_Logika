package com.example.javafx_matdis_calculator_logika.Testing;

import java.util.*;

public class MembacaKurung {

    static String input;
    static List<String> listInput = new ArrayList<>();
    static List<String> listOutput = new ArrayList<>();

    static boolean isNegative(String item) {
        return item.charAt(0) == 'n';
    }

    static boolean isOperator(String operator) {
        return operator.matches("[aoib^v]");
    }

    static String switchPositiveNegative(String item) {
        if (isNegative(item)) return item.substring(1);
        else return "n" + item;
    }

    static boolean hkImplikasi(String[] param) {
        if (param[1].equals("i")) {
            param[0] = switchPositiveNegative(param[0]);
            param[1] = "o";
            return true;
        }
        return false;
    }

    static String simplify(String item) {
        String[] items = item.split(" ");
        hkImplikasi(items);
        return Arrays.toString(items).replaceAll("[\\[, \\]]", "");
    }

    static boolean loopAndIterate() {
        boolean running = true;
        int index = 0;
        while (running) {
            System.out.println("INDEX-OF: " + index);
            running = false;
            listInput = new ArrayList<>();
            listOutput = new ArrayList<>();
            HashMap<Integer, Integer> brackets = new HashMap<>();
            int bracketIndex = 0;

            // Iterate through input chars
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ') continue;
                else if (input.charAt(i) == '(') {
                    brackets.put(bracketIndex, i);
                    bracketIndex++;
                } else if (input.charAt(i) == ')') {
                    bracketIndex--;
                    String a = input.substring(brackets.get(bracketIndex)+1, i);
                    listInput.add(a);
                    System.out.println("ADDED TO LIST: " + a);
                }
            }

            // Duplicate listInput to listOutput
            listOutput.addAll(listInput);

            // Iterate through list
            for (int i = 0; i < listInput.size(); i++) {
                int spaceCount = 0;
                String item = listInput.get(i);
                for (int j = 0; j < item.length(); j++) {
                    if (item.charAt(j) == ' ') spaceCount++;
                }
                if (spaceCount == 2) {
                    listOutput.set(i, simplify(listInput.get(i)));
                    System.out.println(listOutput.get(i));
                    running = true;
                }
                else {
                    System.out.println("++++++++++");
                    System.out.println("----------");
                    for (int j = i-1; j >= 0; j--) {
                        listOutput.set(i, listOutput.get(i).replace(listInput.get(j), listOutput.get(j)));
                        System.out.println("CHECKED WITH: " + listInput.get(j) + " AFTER REPLACED: " + listOutput.get(i));
                    }
                    System.out.println("----------");
                    System.out.println("++++++++++");
                }
            }
            input = "(" + listOutput.get(listOutput.size()-1) + ")";
            System.out.println("OUTPUT: " + input);

            index++;
        }
        return !input.contains(" "); // Means that there's still whitespace left in input
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        while (!loopAndIterate()) {
            input = "(".concat(input);
            int spaceCount = 0;
            boolean bracketOn = true;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ') spaceCount++;
                if (spaceCount == 3) {
                    input = input.substring(0, i) + ")" + input.substring(i);
                    bracketOn = false;
                    System.out.println("NEW INPUT: " + input);
                } else if (spaceCount == 5) {
                    input = input.substring(0, i+1) + "(" + input.substring(i+1);
                    spaceCount = 0;
                    bracketOn = true;
                    System.out.println("NEW INPUT: " + input);
                }
            }
            if (bracketOn) {
                input = input.concat(")");
                System.out.println("NEW INPUT: " + input);
            }
        }
        System.out.println("FINAL RESULT: " + input);
    }
}
// (P i nQ)
// (P i nQ) i (nP a Q)
// (P a (P i nQ)) i (nP a Q)
// ((P i Q) a (P i nQ))
// ((P i Q) a (P i nQ) o (nP i nQ) i (nP i nQ))
// (((Q i P) i (P i (P i Q))) a (P i nQ) o (nP i Q) i (nP i nQ))
// (((Q i P) i (P i (P i Q))) a (P i nQ) o (nP i Q))