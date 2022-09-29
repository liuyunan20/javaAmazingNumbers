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
        ArrayList<String> props = null;
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
            props = new ArrayList<>();
            // construct props with lowercase string
            for (int i = 2; i < input.length; i++) {
                props.add(input[i].toLowerCase());
            }
            ArrayList<String> wrongProp = new ArrayList<>();
            // check if there is wrong property input
            for (String prop: props) {
                if ((!prop.startsWith("-") && !Arrays.asList(Number.properties).contains(prop))
                        || (prop.startsWith("-") && !Arrays.asList(Number.properties).contains(prop.substring(1)))) {
                    wrongProp.add(prop);
                }
            }
            // if there is only one wrong property, print error message and return null
            if (wrongProp.size() == 1) {
                System.out.printf("The property [%s] is wrong.\n", wrongProp.get(0).toUpperCase());
                System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, " +
                        "GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
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
                System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, " +
                        "GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]\n");
                return null;
            }
            // check if there are mutually exclusive properties
            for (String p: props) {
                // check if input direct opposites
                if (p.startsWith("-") && props.contains(p.substring(1))) {
                    System.out.printf("The request contains mutually exclusive properties: " +
                            "[%s, %s]\n", p.toUpperCase(), p.substring(1).toUpperCase());
                    System.out.println("There are no numbers with these properties.\n");
                    return null;
                }
                // check if input mutually exclusive properties
                if (Number.conflictProperties.containsKey(p) && props.contains(Number.conflictProperties.get(p))) {
                    System.out.printf("The request contains mutually exclusive properties: " +
                            "[%s, %s]\n", p.toUpperCase(), Number.conflictProperties.get(p).toUpperCase());
                    System.out.println("There are no numbers with these properties.\n");
                    return null;
                }
                if (p.startsWith("-") && Number.conflictProperties.containsKey(p.substring(1))
                        && props.contains("-" + Number.conflictProperties.get(p.substring(1)))) {
                    System.out.printf("The request contains mutually exclusive properties: " +
                            "[%s, %s]\n", p.toUpperCase(), "-" + Number.conflictProperties.get(p.substring(1)).toUpperCase());
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
            case "happy" -> Number.checkHappy(num);
            case "sad" -> !Number.checkHappy(num);
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
        System.out.print(Number.checkParity(num)?"even, ": "odd, ");
        System.out.print(Number.checkHappy(num)?"happy\n": "sad\n");
    }

    public static void main(String[] args) {
        System.out.println("""
                Welcome to Amazing Numbers!
                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                 * the first parameter represents a starting number;
                 * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
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
            if (parameters.getCount() == null) {
                System.out.printf("Properties of %d\n", startNumber);
                System.out.printf("        buzz: %b\n", Number.checkBuzz(startNumber));
                System.out.printf("        duck: %b\n", Number.checkDuck(startNumber));
                System.out.printf(" palindromic: %b\n", Number.checkPalindromic(startNumber));
                System.out.printf("      gapful: %b\n", Number.checkGapful(startNumber));
                System.out.printf("         spy: %b\n", Number.checkSpy(startNumber));
                System.out.printf("      square: %b\n", Number.checkSquare(startNumber));
                System.out.printf("       sunny: %b\n", Number.checkSquare(startNumber + 1));
                System.out.printf("     jumping: %b\n", Number.checkJumping(startNumber));
                System.out.printf("        even: %b\n", Number.checkParity(startNumber));
                System.out.printf("         odd: %b\n", !Number.checkParity(startNumber));
                System.out.printf("       happy: %b\n", Number.checkHappy(startNumber));
                System.out.printf("         sad: %b\n", !Number.checkHappy(startNumber));
            } else if (parameters.getCount() != null
                    && parameters.getProps() == null) {
                for (int i = 0; i < parameters.getCount(); i++) {
                    printProperties(startNumber + i);
                }
            } else if (parameters.getCount() != null
                    && parameters.getProps() != null) {
                Long count = parameters.getCount();
                ArrayList<String> props = parameters.getProps();
                int i = 0;
                while (count > 0L) {
                    boolean indicate = true;
                    for (String prop: props) {
                        if (prop.startsWith("-")) {
                            indicate = indicate && !checkProperty(startNumber + i, prop.substring(1));
                        } else {
                            indicate = indicate && checkProperty(startNumber + i, prop);
                        }
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
