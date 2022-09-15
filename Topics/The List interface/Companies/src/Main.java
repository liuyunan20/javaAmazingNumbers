import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> companies = new ArrayList<>();
        while (scanner.hasNext()) {
            companies.add(scanner.next());
        }
        System.out.println(companies);
    }
}