import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] parameters = scanner.nextLine().split("\\?")[1].split("&");
        String[] parameter;
        String password = null;
        for (String par: parameters) {
            parameter = par.split("=");
            if (parameter.length == 1) {
                System.out.println(parameter[0] + " : not found");
            } else {
                System.out.println(parameter[0] + " : " + parameter[1]);
            }
            if ("pass".equals(parameter[0])) password = parameter[1];
        }
        if (password != null) System.out.println("password : " + password);
    }
}