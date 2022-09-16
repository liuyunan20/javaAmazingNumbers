import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int n = scanner.nextInt();
        if (n >= str.length()) {
            System.out.println(str);
        } else {
            System.out.println(str.substring(n) + str.substring(0, n));
        }
    }
}