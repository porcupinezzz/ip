import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tension {
    public static void main(String[] args) throws TensionException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> inputs = DataWriter.retrieveTasks();
        int counter = inputs.size();
        System.out.println("Hello! I'm Tension\n" +
                "What can I do for you?");
        while (true) {
            String command = scanner.nextLine();
            int index=-1;
            boolean isMark=false;
            Task currTask = null;
            if (Task.matches(command)) {
                String[] parts = command.split("\\s+");
                if (Task.startsWithMark(command)) {
                    isMark = Task.isMark(command);
                    index = Integer.parseInt(parts[1]) - 1;
                }
                else if (Task.startsWithDelete(command)) {
                    index = Integer.parseInt(parts[1]) - 1;
                }
                else {
                    try {
                        currTask = Task.makeTask(command);
                    }
                    catch (Exception e) {
                        e.toString();
                        e.printStackTrace();
                        return;
                    }
                }
                command = parts[0];
            }
            switch (command) {
                case "delete":
                    Task removed = inputs.remove(index);
                    DataWriter.deleteLine(index);
                    counter--;
                    System.out.println("Noted. I've removed this task:\n" +
                            removed.toString() + "\n" +
                            "     Now you have " + counter +" tasks in the list.");
                    break;
                case "event":
                case "deadline":
                case "todo":
                    inputs.add(currTask);
                    DataWriter.writeFile(currTask);
                    counter++;
                    System.out.println("Got it. I've added this task:\n" +
                            currTask.toString() + "\n" +
                            "     Now you have " + counter + " tasks in the list.");
                    break;
                case "mark":
                case "unmark":
                    if (index <0 || index >= counter) {
                        System.out.println("Invalid index");
                    }
                    else {
                        Task task = inputs.get(index);
                        String s = task.getStatus(isMark);
                        DataWriter.rewriteLine(index, task.makeStoreString());
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
