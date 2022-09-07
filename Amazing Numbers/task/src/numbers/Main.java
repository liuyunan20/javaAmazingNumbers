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

    static int checkBuzz(int num) {
        if (num % 7 == 0 && num % 10 == 7) {
            return 1;
        } else if (num % 7 == 0) {
            return 2;
        } else if (num % 10 == 7) {
            return 3;
        } else {
            return 4;
        }
    }

    public static void main(String[] args) {
        int number = processInput();
        if (number != 0) {
            if (checkParity(number)) {
                System.out.println("This number is Even.");
            } else {
                System.out.println("This number is Odd.");
            }
            switch (checkBuzz(number)) {
                case 1: {
                    System.out.println("It is a Buzz number.");
                    System.out.println("Explanation:");
                    System.out.printf("%d is divisible by 7 and ends with 7.", number);
                }
                case 2: {
                    System.out.println("It is a Buzz number.");
                    System.out.println("Explanation:");
                    System.out.printf("%d is divisible by 7.", number);
                }
                case 3: {
                    System.out.println("It is a Buzz number.");
                    System.out.println("Explanation:");
                    System.out.printf("%d ends with 7.", number);
                }
                case 4: {
                    System.out.println("It is not a Buzz number.");
                    System.out.println("Explanation:");
                    System.out.printf("%d is neither divisible by 7 nor does it end with 7.", number);
                }
            }
        }
    }
}
