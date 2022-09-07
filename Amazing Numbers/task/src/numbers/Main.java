package numbers;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static long processInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter a request:");
            long num = scanner.nextLong();
            if (num >= 0) {
                return num;
            } else {
                System.out.println("The first parameter should be a natural number or zero.");
                return -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("The first parameter should be a natural number or zero.");
            scanner.next();
            return -1;
        }
    }

    static boolean checkParity(long num) {
        return num % 2 == 0;
    }

    static boolean checkBuzz(long num) {
        return num % 7 == 0 || num % 10 == 7;
    }

    static boolean checkDuck(long num) {
        while (num / 10 > 0) {
            if (num % 10 == 0) {
                return true;
            } else {
                num /= 10;
            }
        }
        return false;
    }

    static boolean checkPalindromic(long num) {
        String s = String.valueOf(num);
        int n = s.length();
        while (n > 1) {
            if (s.charAt(0) != s.charAt(n - 1)) {
                return false;
            }
            s = s.substring(1, n -1);
            n = s.length();
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!\n\nSupported requests:");
        System.out.println("- enter a natural number to know its properties;\n- enter 0 to exit.\n");
        long number;
        do {
            number = processInput();
            if (number > 0) {
                System.out.printf("Properties of %d\n", number);
                System.out.printf("        even: %b\n", checkParity(number));
                System.out.printf("         odd: %b\n", !checkParity(number));
                System.out.printf("        buzz: %b\n", checkBuzz(number));
                System.out.printf("        duck: %b\n", checkDuck(number));
                System.out.printf(" palindromic: %b\n", checkPalindromic(number));
            }
        } while (number != 0);
        System.out.println("Goodbye!");
    }
}
