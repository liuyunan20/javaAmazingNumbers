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
        System.out.println("Enter a request:");
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        Long num1 = checkNaturalNumber(input[0]);
        Long num2 = null;
        String [] props = null;
        // check if the first number is natural or zero
        if (num1 < 0L) {
            System.out.println("The first parameter should be a natural number or zero.");
            return null;
        }

        // if the 1st number is good and there is the 2nd number,
        // then check if the second number is natural
        if (input.length > 1) {
            num2 = checkNaturalNumber(input[1]);
            if (num2 <= 0L) {
                System.out.println("The second parameter should be a natural number.");
                return null;
            }
        }
        // check if the input parameters are available number property
        if (input.length > 2) {
            props = Arrays.copyOfRange(input, 2, input.length);
            ArrayList<String> wrongProp = new ArrayList<>();
            // check if there is wrong property input
            for (String prop: props) {
                if (!Arrays.asList(Number.properties).contains(prop.toUpperCase())) {
                    wrongProp.add(prop);
                }
            }
            // if there is only one wrong property, print error message and return null
            if (wrongProp.size() == 1) {
                System.out.printf("The property [%s] is wrong.\n", wrongProp.get(0).toUpperCase());
                System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                return null;
            }
            // if wrong properties are more, print and return
            if (wrongProp.size() > 1) {
                StringBuilder errorMsg = new StringBuilder();
                for (int i = 0; i < wrongProp.size() - 1; i++) {
                    errorMsg.append(wrongProp.get(i)).append(", ");
                }
                errorMsg.append(wrongProp.get(wrongProp.size() - 1));
                System.out.printf("The properties [%s] are wrong.\n", errorMsg.toString().toUpperCase());
                System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]\n");
                return null;
            }
            // check if there are mutually exclusive properties
            for (String p: props) {
                if (Number.conflictProperties.containsKey(p.toLowerCase())
                        && Arrays.asList(props).contains(Number.conflictProperties.get(p.toLowerCase()))) {
                    System.out.printf("The request contains mutually exclusive properties: " +
                            "[%s, %s]\n", p.toUpperCase(), Number.conflictProperties.get(p.toLowerCase()).toUpperCase());
                    System.out.println("There are no numbers with these properties.\n");
                    return null;
                }
            }
        }
        return new Parameters(num1, num2, props);
    }

    static boolean checkProperty(long num, String prop) {
        return switch (prop.toLowerCase()) {
            case "buzz" -> Number.checkBuzz(num);
            case "duck" -> Number.checkDuck(num);
            case "palindromic" -> Number.checkPalindromic(num);
            case "gapful" -> Number.checkGapful(num);
            case "spy" -> Number.checkSpy(num);
            case "even" -> Number.checkParity(num);
            case "odd" -> !Number.checkParity(num);
            case "square" -> Number.checkSquare(num);
            case "sunny" -> Number.checkSquare(num + 1L);
            case "jumping" -> Number.checkJumping(num);
            default -> false;
        };
    }

    static void printProperties(Long num) {
        System.out.printf("%d is ", num);
        System.out.print(Number.checkBuzz(num)?"buzz, ": "");
        System.out.print(Number.checkDuck(num)?"duck, ": "");
        System.out.print(Number.checkPalindromic(num)?"palindromic, ": "");
        System.out.print(Number.checkGapful(num)?"gapful, ": "");
        System.out.print(Number.checkSpy(num)?"spy, ": "");
        System.out.print(Number.checkSquare(num)?"square, ": "");
        System.out.print(Number.checkSquare(num + 1)?"sunny, ": "");
        System.out.print(Number.checkJumping(num)?"jumping, ": "");
        System.out.print(Number.checkParity(num)?"even\n": "odd\n");
    }

    public static void main(String[] args) {
        System.out.println("""
                Welcome to Amazing Numbers!
                                                    
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameters show how many consecutive numbers are to be processed;
                - two natural numbers and two properties to search for;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
        Parameters parameters;

        do {
            do {
                parameters = processInput();
            } while (parameters == null);
            Long startNumber = parameters.getStartNumber();
            if (startNumber == 0L) {
                break;
            }
            Number number = new Number(startNumber);
            if (parameters.getCount() == null) {
                System.out.printf("Properties of %d\n", number.getNumber());
                System.out.printf("        buzz: %b\n", number.isBuzz());
                System.out.printf("        duck: %b\n", number.isDuck());
                System.out.printf(" palindromic: %b\n", number.isPalindromic());
                System.out.printf("      gapful: %b\n", number.isGapful());
                System.out.printf("         spy: %b\n", number.isSpy());
                System.out.printf("      square: %b\n", number.isSquare());
                System.out.printf("       sunny: %b\n", number.isSunny());
                System.out.printf("     jumping: %b\n", number.isJumping());
                System.out.printf("        even: %b\n", number.isEven());
                System.out.printf("         odd: %b\n", number.isOdd());
            } else if (parameters.getCount() != null
                    && parameters.getProps() == null) {
                for (int i = 0; i < parameters.getCount(); i++) {
                    printProperties(startNumber + i);
                }
            } else if (parameters.getCount() != null
                    && parameters.getProps() != null) {
                Long count = parameters.getCount();
                String[] props = parameters.getProps();
                int i = 0;
                while (count > 0L) {
                    boolean indicate = true;
                    for (String prop: props) {
                        indicate = indicate && checkProperty(startNumber + i, prop);
                    }
                    if (indicate) {
                        printProperties(startNumber + i);
                        count--;
                    }
                    i++;
                }
            }
        } while (parameters.getStartNumber() != 0);
        System.out.println("Goodbye!");
    }
}
