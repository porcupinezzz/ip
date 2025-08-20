import java.util.Scanner;

public class Tension {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputs = new String[100];
        int counter = 0;
        System.out.println("Hello! I'm Tension\n" +
                "What can I do for you?");
        while (true) {
            String command = scanner.nextLine();
            switch (command) {
                case "list":
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + ". " + inputs[i]);
                    }
                    break;
                case "bye":
                    scanner.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default:
                    System.out.println(command);
                    inputs[counter++] = command;
                    break;
            }
        }
    }
}
