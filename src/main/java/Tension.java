import java.util.Scanner;

public class Tension {
    public static void main(String[] args) throws TensionException {
        Scanner scanner = new Scanner(System.in);
        Task[] inputs = new Task[100];
        int counter = 0;
        System.out.println("Hello! I'm Tension\n" +
                "What can I do for you?");
        while (true) {
            String command = scanner.nextLine();
            Integer index=-1;
            Boolean isMark=false;
            Task t = null;
            if (Task.matches(command)) {
                if (Task.startsWithMark(command)) {
                    isMark = Task.isMark(command);
                    String[] parts = command.split("\\s+");
                    index = Integer.parseInt(parts[1]) - 1;
                    command = "special-case";
                }
                else {
                    try {
                        t = Task.makeTask(command);
                    }
                    catch (Exception e) {
                        e.toString();
                        e.printStackTrace();
                        return;
                    }
                    command = "task-made";
                }
            }
            switch (command) {
                case "task-made":
                    inputs[counter++] = t;
                    System.out.println("Got it. I've added this task:\n" +
                            t.toString() + "\n" +
                            "     Now you have " + counter + " tasks in the list.");
                    break;
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
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + "." + inputs[i].toString());
                    }
                    break;
                case "bye":
                    scanner.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default:
                    throw new TensionException("Invalid command, please start with todo, deadline, or event");
            }
        }
    }
}
