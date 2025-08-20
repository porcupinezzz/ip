import java.util.ArrayList;
import java.util.Scanner;

public class Tension {
    public static void main(String[] args) throws TensionException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> inputs = new ArrayList<>();
        int counter = 0;
        System.out.println("Hello! I'm Tension\n" +
                "What can I do for you?");
        while (true) {
            String command = scanner.nextLine();
            int index=-1;
            Boolean isMark=false;
            Task t = null;
            if (Task.matches(command)) {
                // mark tasks
                if (Task.startsWithMark(command)) {
                    isMark = Task.isMark(command);
                    String[] parts = command.split("\\s+");
                    index = Integer.parseInt(parts[1]) - 1;
                    command = "special-case";
                }
                // delete tasks
                else if (Task.startsWithDelete(command)) {
                    String[] parts = command.split("\\s+");
                    index = Integer.parseInt(parts[1]) - 1;
                    command = "delete";
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
                case "delete":
                    Task removed = inputs.remove(index);
                    counter--;
                    System.out.println("Noted. I've removed this task:\n" +
                            removed.toString() + "\n" +
                            "     Now you have " + counter +" tasks in the list.");
                    break;
                case "task-made":
                    inputs.add(t);
                    counter++;
                    System.out.println("Got it. I've added this task:\n" +
                            t.toString() + "\n" +
                            "     Now you have " + counter + " tasks in the list.");
                    break;
                case "special-case":
                    if (index <0 || index >= counter) {
                        System.out.println("Invalid index");
                    }
                    else {
                        String s = inputs.get(index).getStatus(isMark);
                        System.out.println(s);
                    }
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + "." + inputs.get(i).toString());
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
