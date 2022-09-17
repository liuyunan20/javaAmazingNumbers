import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        StringBuilder str = new StringBuilder();
        char ch;
        for (String s: strings) {
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i);
                if (!Character.isDigit(ch)) {
                    str.append(ch);
                }
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}