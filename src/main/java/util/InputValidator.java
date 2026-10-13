package util;

import java.util.Scanner;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input ga boleh kosong! Coba lagi.");
        }
    }

    public static double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= 0) {
                    return value;
                }
                System.out.println("Nilai ga boleh negatif!");
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka yg valid!");
            }
        }
    }

    public static int readIntRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Pilih angka antara " + min + " sampai " + max + "!");
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka bulat!");
            }
        }
    }
}