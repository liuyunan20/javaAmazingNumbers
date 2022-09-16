import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        // receive 4 parameters from input
        int nA = scanner.nextInt();
        int nB = scanner.nextInt();
        int nC = scanner.nextInt();
        int nN = scanner.nextInt();
        // initialize password and define candidates
        StringBuilder password = new StringBuilder();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "1234567890";
        char c;
        for (int i = 0; i < nA; i++) {
            c = letters.charAt((int) (random.nextDouble() * letters.length()));
            while (password.length() > 0 && c == password.charAt(password.length() - 1)) {
                c = letters.charAt((int) (random.nextDouble() * letters.length()));
            }
            password.append(c);
        }
        for (int i = 0; i < nB; i++) {
            c = letters.toLowerCase().charAt((int) (random.nextDouble() * letters.length()));
            while (password.length() > 0 && c == password.charAt(password.length() - 1)) {
                c = letters.toLowerCase().charAt((int) (random.nextDouble() * letters.length()));
            }
            password.append(c);
        }
        for (int i = 0; i < nC; i++) {
            c = digits.charAt((int) (random.nextDouble() * digits.length()));
            while (password.length() > 0 && c == password.charAt(password.length() - 1)) {
                c = digits.charAt((int) (random.nextDouble() * digits.length()));
            }
            password.append(c);
        }
        int nD = nN - (nA + nB + nC);
        if (nD > 0) {
            for (int i = 0; i < nD; i++) {
                c = letters.charAt((int) (random.nextDouble() * letters.length()));
                while (password.length() > 0 && c == password.charAt(password.length() - 1)) {
                    c = letters.charAt((int) (random.nextDouble() * letters.length()));
                }
                password.append(c);
            }
        }
        System.out.println(password);
    }
}