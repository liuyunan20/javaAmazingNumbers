import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }
        int n = numbers.get(numbers.size() - 1);
        numbers.remove(numbers.size() - 1);
        Collections.sort(numbers);
        int distance = Integer.MAX_VALUE;
        for (int num: numbers) {
            int d = Math.abs(n - num);
            if (d <= distance) {
                distance = d;
            }
        }
        for (int num: numbers) {
            if (Math.abs(n - num) == distance) {
                System.out.print(num + " ");
            }
        }
    }
}