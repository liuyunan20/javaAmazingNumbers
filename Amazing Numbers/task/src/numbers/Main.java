package numbers;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static int processInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter a natural number:");
            int num = scanner.nextInt();
            if (num > 0) {
                return num;
            } else {
                System.out.println("This number is not natural!");
                return 0;
            }
        } catch (InputMismatchException e) {
            System.out.println("This number is not natural!");
            scanner.next();
            return 0;
        }
    }

    static boolean checkParity(int num) {
        return num % 2 == 0;
    }

    static boolean checkBuzz(int num) {
        return num % 7 == 0 || num % 10 == 7;
    }

    static boolean checkDuck(int num) {
        while (num / 10 > 0) {
            if (num % 10 == 0) {
                return true;
            } else {
                num /= 10;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int number = processInput();
        if (number != 0) {
            System.out.printf("Properties of %d\n", number);
            System.out.printf("        even: %b\n", checkParity(number));
            System.out.printf("         odd: %b\n", !checkParity(number));
            System.out.printf("        buzz: %b\n", checkBuzz(number));
            System.out.printf("        duck: %b\n", checkDuck(number));
        }
    }
}
