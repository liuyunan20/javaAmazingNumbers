package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    static Long checkNaturalNumber(String str) {
        try {
            long num = Long.parseLong(str);
            if (num >= 0L) {
                return num;
            } else {
                return -1L;
            }
        } catch (NumberFormatException e) {
            return -1L;
        }
    }
    static Parameters processInput() {
        String[] properties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY"};
        System.out.println("Enter a request:");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        Long num1 = checkNaturalNumber(input[0]);
        Long num2 = null;
        String prop = null;
        if (num1 < 0L) {
            System.out.println("The first parameter should be a natural number or zero.");
            return null;
        } else if (input.length == 2) {
            num2 = checkNaturalNumber(input[1]);
            if (num2 <= 0L) {
                System.out.println("The second parameter should be a natural number.");
                return null;
            }
        } else if (input.length == 3) {
            num2 = checkNaturalNumber(input[1]);
            if (num2 <= 0L) {
                System.out.println("The second parameter should be a natural number.");
                return null;
            }
            prop = input[2];
            if (!Arrays.asList(properties).contains(prop.toUpperCase())) {
                System.out.printf("The property [%s] is wrong.", prop.toUpperCase());
                System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]");
                return null;
            }
        }
        return new Parameters(num1, num2, prop);
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

    static boolean checkSpy(long num) {
        ArrayList<Long> digits = new ArrayList<>();
        while (num > 0L) {
            digits.add(num % 10L);
            num = num / 10L;
        }
        Long sum = 0L;
        Long product = 1L;
        for (Long d: digits) {
            sum += d;
            product *= d;
        }
        return sum.equals(product);
    }

    static boolean checkProperty(Long num, String prop) {
        return switch (prop.toLowerCase()) {
            case "buzz" -> checkBuzz(num);
            case "duck" -> checkDuck(num);
            case "palindromic" -> checkPalindromic(num);
            case "gapful" -> checkGapful(num);
            case "spy" -> checkSpy(num);
            case "even" -> checkParity(num);
            case "odd" -> !checkParity(num);
            default -> false;
        };
    }

    public static void main(String[] args) {
        System.out.println("""
                Welcome to Amazing Numbers!
                                  
                                  Supported requests:
                                  - enter a natural number to know its properties;
                                  - enter two natural numbers to obtain the properties of the list:
                                    * the first parameter represents a starting number;
                                    * the second parameters show how many consecutive numbers are to be processed;
                                  - two natural numbers and a property to search for;
                                  - separate the parameters with one space;
                                  - enter 0 to exit.
                                  
                                  """);
        Parameters parameters;

        do {
            do {
                parameters = processInput();
            } while (parameters == null);
            Long startNumber = parameters.getStartNumber();
            if (startNumber != 0 && parameters.getCount() == null) {
                System.out.printf("Properties of %d\n", startNumber);
                System.out.printf("        buzz: %b\n", checkBuzz(startNumber));
                System.out.printf("        duck: %b\n", checkDuck(startNumber));
                System.out.printf(" palindromic: %b\n", checkPalindromic(startNumber));
                System.out.printf("      gapful: %b\n", checkGapful(startNumber));
                System.out.printf("         spy: %b\n", checkSpy(startNumber));
                System.out.printf("        even: %b\n", checkParity(startNumber));
                System.out.printf("         odd: %b\n", !checkParity(startNumber));
            } else if (startNumber != 0 && parameters.getCount() != null
                    && parameters.getProperty() == null) {
                for (int i = 0; i < parameters.getCount(); i++) {
                    System.out.printf("%d is ", startNumber + i);
                    System.out.print(checkBuzz(startNumber + i)?"buzz, ": "");
                    System.out.print(checkDuck(startNumber + i)?"duck, ": "");
                    System.out.print(checkPalindromic(startNumber + i)?"palindromic, ": "");
                    System.out.print(checkGapful(startNumber + i)?"gapful, ": "");
                    System.out.print(checkSpy(startNumber + i)?"spy, ": "");
                    System.out.print(checkParity(startNumber + i)?"even\n": "odd\n");
                }
            } else if (startNumber != 0 && parameters.getCount() != null
                    && parameters.getProperty() != null){
                Long count = parameters.getCount();
                String prop = parameters.getProperty();
                int i = 0;
                while (count > 0L) {
                    if (checkProperty(startNumber + i, prop)) {
                        System.out.printf("%d is ", startNumber + i);
                        System.out.print(checkBuzz(startNumber + i)?"buzz, ": "");
                        System.out.print(checkDuck(startNumber + i)?"duck, ": "");
                        System.out.print(checkPalindromic(startNumber + i)?"palindromic, ": "");
                        System.out.print(checkGapful(startNumber + i)?"gapful, ": "");
                        System.out.print(checkSpy(startNumber + i)?"spy, ": "");
                        System.out.print(checkParity(startNumber + i)?"even\n": "odd\n");
                        count--;
                    }
                    i++;
                }
            }
        } while (parameters.getStartNumber() != 0);
        System.out.println("Goodbye!");
    }
}
