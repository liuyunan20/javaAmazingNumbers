package numbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Number {
    public static final String[] properties = {"even", "odd", "buzz",
            "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping", "happy", "sad"};
    public static final Map<String, String> conflictProperties =
            new HashMap<>(Map.of("even", "odd",
                    "odd", "even",
                    "spy", "duck",
                    "duck", "spy",
                    "square", "sunny",
                    "sunny", "square",
                    "happy", "sad",
                    "sad", "happy"));

    static boolean checkBuzz(long num) {
        return num % 7 == 0 || num % 10 == 7;
    }

    static boolean checkParity(long num) {
        return num % 2 == 0;
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

    static boolean checkSquare(long num) {
        double x = Math.sqrt((double) num);
        return x % 1 == 0;
    }

    static boolean checkJumping(long num) {
        ArrayList<Long> digits = new ArrayList<>();
        while (num > 0) {
            digits.add(num % 10);
            num = num / 10;
        }
        for (int i = 0; i < digits.size() - 1; i++) {
            if (Math.abs(digits.get(i) - digits.get(i + 1)) != 1) {
                return false;
            }
        }
        return true;
    }

    static boolean checkHappy(long num) {
        HashSet<Long> visited = new HashSet<>();
        return checkHappyHelper(num, visited);
    }

    static boolean checkHappyHelper(long num, HashSet<Long> visited) {
        if (num == 1) {
            return true;
        }
        ArrayList<Long> digits = new ArrayList<>();
        long numCopy = num;
        while (numCopy > 0) {
            digits.add(numCopy % 10);
            numCopy = numCopy / 10;
        }
        long sum = 0;
        for (Long d: digits) {
            sum += d * d;
        }
        if (!visited.add(sum)) {
            return false;
        }
        return checkHappyHelper(sum, visited);
    }
}

