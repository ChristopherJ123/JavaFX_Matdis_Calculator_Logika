package com.example.javafx_matdis_calculator_logika.Legacy;

import java.util.*;

public class MembacaKurung {

    static String input;
    static List<String> listInput = new ArrayList<>();
    static List<String> listOutput = new ArrayList<>();

    static boolean isNegative(String item) {
        return item.charAt(0) == 'n';
    }

    static String switchPositiveNegative(String item) {
        if (isNegative(item)) return String.valueOf(item.charAt(2));
        else return "n(" + item + ")";
    }

    // 1. Hukum Identitas
    static boolean hkIdentitas(String[] param) {
        if ((param[1].equals("o") && param[2].equals("f")) || (param[1].equals("a") && param[2].equals("t"))) {
            param[1] = "";
            param[2] = "";
            return true;
        }
        return false;
    }

    // 2. Hukum Dominasi
    static boolean hkDominasi(String[] param) {
        if ((param[1].equals("o") && param[2].equals("t"))) {
            param[0] = "t";
            param[1] = "";
            param[2] = "";
            return true;
        } else if ((param[1].equals("a") && param[2].equals("f"))) {
            param[0] = "f";
            param[1] = "";
            param[2] = "";
            return true;
        }
        return false;
    }

    // 3. Hukum Idempoten
    static boolean hkIdempoten(String[] param) {
        if (param[1].equals("a") || param[1].equals("o")) {
            if (param[0].equals(param[2])) {
                param[1] = "";
                param[2] = "";
                return true;
            }
        }
        return false;
    }

    // 9. Hukum Implikasi
    static boolean hkImplikasi(String[] param) {
        if (param[1].equals("i")) {
            param[0] = switchPositiveNegative(param[0]);
            param[1] = "o";
            return true;
        }
        return false;
    }

    // 10. Hukum Kontraposisi
    static boolean hkKontraposisi(String[] param) {
        if (param[1].equals("i")) {
            String temp = param[0];
            param[0] = switchPositiveNegative(param[2]);
            param[2] = switchPositiveNegative(temp);
            return true;
        }
        return false;
    }

    // 11. Hukum Tautologi
    static boolean hkTautologi(String[] param) {
        if (param[1].equals("o") && param[2].equals(switchPositiveNegative(param[0]))) {
            param[0] = "t";
            param[1] = "";
            param[2] = "";
            return true;
        }
        return false;
    }

    // 12. Hukum Kontradiksi
    static boolean hkKontradiksi(String[] param) {
        if (param[1].equals("a") && param[2].equals(switchPositiveNegative(param[0]))) {
            param[0] = "f";
            param[1] = "";
            param[2] = "";
            return true;
        }
        return false;
    }

    // 14. Hukum Bi-implikasi
    static boolean hkBiimplikasi(String[] param) {
        if (param[1].equals("b")) {
            String[] arr1 = {param[0], "i", param[2]};
            hkImplikasi(arr1);
            String[] arr2 = {param[2], "i", param[0]};
            hkBiimplikasi(arr2);
            param[0] = "(" + arr1[0] + " i " + arr1[2] + ")";
            param[1] = "a";
            param[2] = "(" + arr2[2] + " i " + arr2[0] + ")";
            return true;
        }
        return false;
    }


    static String[] simplify(String item) {
        String[] items = item.split(" ");
        String hukum = "";
        boolean terjadiSimplifikasi = true;
        if (hkIdentitas(items)) hukum += "Hk. Identitas"; //1
        if (hkDominasi(items)) hukum += "Hk. Dominasi"; //2
        if (hkIdempoten(items)) hukum += "Hk. Idempoten"; //3
        if (hkImplikasi(items)) hukum += "Hk. Implikasi"; //9
        if (hkKontraposisi(items)) hukum += "Hk. Kontraposisi"; //10
        if (hkTautologi(items)) hukum += "Hk. Tautologi"; //11
        if (hkKontradiksi(items)) hukum += "Hk. Dominasi"; //12
        if (hkBiimplikasi(items)) hukum += "Hk. Bi-implikasi"; //14
        if (hukum.equals("")) {
            hukum = "None";
            terjadiSimplifikasi = false;
        }
        return new String[]{Arrays.toString(items).replaceAll("[\\[, \\]]", ""), hukum, String.valueOf(terjadiSimplifikasi)};
    }

    static boolean loopPenyederhanaanKurung() {
        boolean masihLooping = false;
        // Cek hukum dobel negasi
        if (input.contains("n(n(P))") || input.contains("n(n(Q))")) {
            String hukum = "Hk. Dobel Negasi";
            input = input.replace("n(n(P))" , "P");
            input = input.replace("n(n(Q))" , "Q");
            System.out.println("Hukum: " + hukum);
            System.out.println(input);
            masihLooping = true;
        }

        // Sederhanain kurung
        if (input.contains("(P)") || input.contains("(Q)")) {
            if (input.length() == 3) return false;
            for (int i = 2; i < input.length(); i++) {
                if (input.substring(i-2, i+1).equals("(P)")) {
                    try {
                        if (input.charAt(i-3) != 'n') {
                            input = input.substring(0, i-2).concat(String.valueOf(input.charAt(i-1)).concat(input.substring(i+1)));
                            masihLooping = true;
                            System.out.println("Terjadi penyederhanaan: " + input);
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        input = input.substring(0, i-2).concat(String.valueOf(input.charAt(i-1)).concat(input.substring(i)));
                        masihLooping = true;
                        System.out.println("Terjadi penyederhanaan: " + input);
                    }
                }
            }
        }

        // Cek hukum de morgan
        if (input.contains("n(P o Q)") || input.contains("n(n(P) o Q)") || input.contains("n(P o n(Q))") || input.contains("n(n(P) o (Q))") ||
                input.contains("n(Q o P)") || input.contains("n(n(Q) o P)") || input.contains("n(Q o n(P))") || input.contains("n(n(Q) o n(P))") ||
                input.contains("n(P a Q)") || input.contains("n(n(P) a Q)") || input.contains("n(P a n(Q))") || input.contains("n(n(P) a n(Q))") ||
                input.contains("n(Q a P)") || input.contains("n(n(Q) a P)") || input.contains("n(Q a n(P))") || input.contains("n(n(Q) a n(P))")
        ) {
            String hukum = "Hk. De Morgan";
            input = input.replace("n(P o Q)", "n(P) a n(Q)");
            input = input.replace("n(n(P) o Q)", "P a n(Q)");
            input = input.replace("n(P o n(Q))", "n(P) a Q");
            input = input.replace("n(n(P) o n(Q))", "P a Q");
            input = input.replace("n(Q o P)", "n(Q) a n(P)");
            input = input.replace("n(n(Q) o P)", "Q a n(P)");
            input = input.replace("n(Q o n(P))", "n(Q) a P");
            input = input.replace("n(n(Q) o n(P))", "Q a P");

            input = input.replace("n(P a Q)", "n(P) o n(Q)");
            input = input.replace("n(n(P) a Q)", "P o n(Q)");
            input = input.replace("n(P a n(Q))", "n(P) o Q");
            input = input.replace("n(n(P) a n(Q))", "P o Q");
            input = input.replace("n(Q a P)", "n(Q) o n(P)");
            input = input.replace("n(n(Q) a P)", "Q o n(P)");
            input = input.replace("n(Q a n(P))", "n(Q) o P");
            input = input.replace("n(n(Q) a n(P))", "Q o P");
            System.out.println("Hukum: " + hukum);
            masihLooping = true;
        }
        return masihLooping;
    }

    static boolean loopAndIterate() {
        boolean running = true;
        int index = 0;
        while (running) { // Running samapai tidak ada spasi
            running = false;
            System.out.println("INDEX-OF: " + index);
            listInput = new ArrayList<>();
            listOutput = new ArrayList<>();
            HashMap<Integer, Integer> brackets = new HashMap<>();
            int bracketIndex = 0;

            while (loopPenyederhanaanKurung());

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
                    String[] simplified = simplify(listInput.get(i));
                    listOutput.set(i, simplified[0].trim());
                    System.out.print("Hukum: " + simplified[1] + "(" + listInput.get(i) + ")");
                    System.out.println(" menjadi: " + listOutput.get(i));
                    running = Boolean.parseBoolean(simplified[2]);
                } else {
                    System.out.println("----------");
                    for (int j = i-1; j >= 0; j--) {
                        listOutput.set(i, listOutput.get(i).replace(listInput.get(j), listOutput.get(j)));
                        System.out.println("REPLACE: " + listInput.get(j) + " WITH: " + listOutput.get(j) + " MENJADI: " + listOutput.get(i));
                    }
                    System.out.println("----------");
                }
            }

            input = "(" + listOutput.get(listOutput.size()-1).trim() + ")";
            System.out.println("OUTPUT: " + input);

            index++;
        }
        return !input.contains(" "); // Means that there's still whitespace left in input
    }

    public static String calculate(String prompt) {
        input = prompt;
        input = input.replace("V", "o").replace("Λ", "a").replace("→", "i").replace("↔", "b").replace("~", "n");

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
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a' || input.charAt(i) == 'o') {
                input = input.substring(0, i) + " " + input.charAt(i) + " " + input.substring(i+1);
                i++;
            }
        }
        return input.replace("o", "V").replace("a", "Λ").replace("i", "→").replace("b", "↔").replace("n", "~");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String prompt = scanner.nextLine();

        System.out.println("FINAL RESULT: " + calculate(prompt));
    }
}

/*
Testing Prompts
1. Hukum Identitas
(P o f) == P
((P o f) a (P a t)) == P

2. Hukum Dominasi
(P o t) == t
(P a f) == f
((P o t) a (P a f)) == t a f

3. Hukum Idempoten
(P o P) == P
(P a P) == P
((P o P) a (P a P)) == P
((P o P) a (P a P) o (P o P)) == P

9. Hukum Implikasi
(P i nQ) == (nPonQ) == nP o NQ
((P i nQ) i (nP i Q)) == (n(nPonQ)o(PoQ)) == n((nP o nQ) o (P o Q))
((P i Q) i (P i Q))
(((Q i P) i (P i (P i Q))) a (P i nQ) o (nP i Q) i (nP i nQ)) == (((((QonP)onP)on(PonQ))a(nQonP))o((nQoP)on(QoP)))

10. Hukum Kontraposisi
()

11. Hukum Tautologi
(P o nP) == t
((P o nP) o (P o nP)) == (t o t) == t (hukum idempoten)

12. Hukum Kontradiksi
(P a nP) == f

14. Hukum Bi-implikasi
(P b Q) == ((PiQ)a(QiP)) == (P i Q) a (Q i P)
 */

// (P i nQ)
// (P i nQ) i (nP a Q)
// (P a (P i nQ)) i (nP a Q)
// ((P i Q) a (P i nQ))
// ((n(P) i n(Q)) i (n(P) i n(Q)))
// ((P i Q) a (P i nQ) o (nP i nQ) i (nP i nQ))
// (((Q i P) i (P i (P i Q))) a (P i nQ) o (nP i Q) i (nP i nQ))
// (((Q i P) i (P i (P i Q))) a (P i nQ) o (nP i Q))

/*
(n(n(P)))
(n(P o Q))
(n(n(n(n(P)))))
 */