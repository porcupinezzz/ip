import java.util.Scanner;

public class Tension {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Tension\n" +
                "What can I do for you?");
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                scanner.close();
                break;
            }
            System.out.println(command);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
