package com.example.javafx_matdis_calculator_logika.Legacy;

import java.util.ArrayList;
import java.util.HashMap;

public class MembuatTabel{
    static void iterate(String input) {
        ArrayList<String> listInput = new ArrayList<>();
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

        for (int i = 0; i < listInput.size(); i++) {
            System.out.println(listInput.get(i));
        }



    }

    public static void main(String[] args) {
        iterate("(n((n(P) o Q)) o (n(P) o Q))");
    }
}
