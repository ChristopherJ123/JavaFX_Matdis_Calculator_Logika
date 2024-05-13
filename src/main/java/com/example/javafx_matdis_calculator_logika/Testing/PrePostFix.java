package com.example.javafx_matdis_calculator_logika.Testing;

public class PrePostFix {
    public static int checkPostfixErrors(String input) {
        // 1. Error apabila input kosong
        if (input.equals("")) return 1;

        // 7. Error apabila ada kurung
        if (input.matches("[()]")) return 7;

        // 8. Error apabila di belakang tidak ada operator
        if (!String.valueOf(input.charAt(input.length()-1)).matches("[ΛV→↔¬]")) return 8;

        // 9. Error jumlah operator kurang
        int countOperator = 0;
        int countVariable = 0;
        for (int i = 0; i < input.length(); i++) {
            if (String.valueOf(input.charAt(i)).matches("[ΛV→↔]")) countOperator++;
            else if (String.valueOf(input.charAt(i)).matches("[PQ01]")) countVariable++;
        }
        if ((countOperator + 1) != countVariable) return 9;

        return 0;
    }

    public static int checkPrefixErrors(String input) {
        // 1. Error apabila input kosong
        if (input.equals("")) return 1;

        // 7. Error apabila ada kurung
        if (input.matches("[()]")) return 7;

        // 8. Error apabila di depan tidak ada operator
        if (!String.valueOf(input.charAt(0)).matches("[ΛV→↔¬]")) return 8;

        // 9. Error jumlah operator kurang
        int countOperator = 0;
        int countVariable = 0;
        for (int i = 0; i < input.length(); i++) {
            if (String.valueOf(input.charAt(i)).matches("[ΛV→↔]")) countOperator++;
            else if (String.valueOf(input.charAt(i)).matches("[PQ01]")) countVariable++;
        }
        if ((countOperator + 1) != countVariable) return 9;

        return 0;
    }
}
