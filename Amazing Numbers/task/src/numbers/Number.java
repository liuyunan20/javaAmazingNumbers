package numbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Number {
    public static final String[] properties = {"EVEN", "ODD", "BUZZ",
            "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING"};
    public static final Map<String, String> conflictProperties =
            new HashMap<>(Map.of("even", "odd",
                    "odd", "even",
                    "spy", "duck",
                    "duck", "spy",
                    "square", "sunny",
                    "sunny", "square"));
    private final long number;
    private final boolean even;
    private final boolean odd;
    private final boolean buzz;
    private final boolean duck;
    private final boolean palindromic;
    private final boolean gapful;
    private final boolean spy;
    private final boolean square;
    private final boolean sunny;

    private final boolean jumping;


    public Number(long number) {

        this.number = number;
        this.even = number % 2 == 0;
        this.odd = !this.even;
        this.buzz = number % 7 == 0 || number % 10 == 7;
        this.duck = checkDuck(number);
        this.palindromic = checkPalindromic(number);
        this.gapful = checkGapful(number);
        this.spy = checkSpy(number);
        this.square = checkSquare(number);
        this.sunny = checkSquare(number + 1);
        this.jumping = checkJumping(number);
    }

    public long getNumber() {
        return number;
    }

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

    public boolean isEven() {
        return even;
    }

    public boolean isOdd() {
        return odd;
    }

    public boolean isBuzz() {
        return buzz;
    }

    public boolean isDuck() {
        return duck;
    }

    public boolean isPalindromic() {
        return palindromic;
    }

    public boolean isGapful() {
        return gapful;
    }

    public boolean isSpy() {
        return spy;
    }

    public boolean isSquare() {
        return square;
    }

    public boolean isSunny() {
        return sunny;
    }

    public boolean isJumping() {
        return jumping;
    }
}

