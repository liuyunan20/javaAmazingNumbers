package numbers;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    static List<Long> processInput() {
        System.out.println("Enter a request:");
        Scanner scanner = new Scanner(System.in);
        try {
            List<Long> nums = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Long::valueOf).toList();
            if ((nums.size() == 1 && nums.get(0) >= 0) ||
                    (nums.size() == 2 && nums.get(0) > 0 && nums.get(1) > 0)) {
                return nums;
            } else if (nums.get(0) < 0){
                System.out.println("The first parameter should be a natural number or zero.");
                return null;
            } else if (nums.size() == 2 && nums.get(1) <= 0){
                System.out.println("The second parameter should be a natural number or zero.");
                return null;
            } else {
                System.out.println("You need to input one or two natural numbers or zero.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
            return null;
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

    static boolean checkGapful(long num) {
        if (num >= 100L) {
            long d0 = num % 10;
            long d1 = num;
            while (d1 > 9L) {
                d1 = d1 / 10L;
            }
            return num % (d1 * 10L + d0) == 0L;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("""
                Welcome to Amazing Numbers!
                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                * the first parameter represents a starting number;
                * the second parameter shows how many consecutive numbers are to be processed;
                - separate the parameters with one space;
                - enter 0 to exit.""");
        List<Long> number;

        do {
            do {
                number = processInput();
            } while (number == null);
            if (number.get(0) != 0 && number.size() == 1) {
                System.out.printf("Properties of %d\n", number.get(0));
                System.out.printf("        buzz: %b\n", checkBuzz(number.get(0)));
                System.out.printf("        duck: %b\n", checkDuck(number.get(0)));
                System.out.printf(" palindromic: %b\n", checkPalindromic(number.get(0)));
                System.out.printf("      gapful: %b\n", checkGapful(number.get(0)));
                System.out.printf("        even: %b\n", checkParity(number.get(0)));
                System.out.printf("         odd: %b\n", !checkParity(number.get(0)));
            } else if (number.size() == 2) {
                for (int i = 0; i < number.get(1); i++) {
                    System.out.printf("%d is ", number.get(0) + i);
                    System.out.print(checkBuzz(number.get(0) + i)?"buzz, ": "");
                    System.out.print(checkDuck(number.get(0) + i)?"duck, ": "");
                    System.out.print(checkPalindromic(number.get(0) + i)?"palindromic, ": "");
                    System.out.print(checkGapful(number.get(0) + i)?"gapful, ": "");
                    System.out.print(checkParity(number.get(0) + i)?"even\n": "odd\n");
                }
            }
        } while (number.get(0) != 0);
        System.out.println("Goodbye!");
    }
}
