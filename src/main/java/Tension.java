import java.util.Scanner;

public class Tension {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] inputs = new Task[100];
        int counter = 0;
        System.out.println("Hello! I'm Tension\n" +
                "What can I do for you?");
        while (true) {
            String command = scanner.nextLine();
            Integer index=-1;
            Boolean isMark=false;
            if (Task.matches(command)) {
                isMark = Task.isMark(command);
                String[] parts = command.split("\\s+");
                index = Integer.parseInt(parts[1]) - 1;
                command = "special-case";
            }
            switch (command) {
                case "special-case":
                    if (index <0 || index >= counter) {
                        System.out.println("Invalid index");
                    }
                    else {
                        String s = inputs[index].getStatus(isMark);
                        System.out.println(s);
                    }
                    break;
                case "list":
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + "." + inputs[i].getDescription());
                    }
                    break;
                case "bye":
                    scanner.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default:
                    System.out.println(command);
                    inputs[counter++] = new Task(command);
                    break;
            }
        }
    }
}
