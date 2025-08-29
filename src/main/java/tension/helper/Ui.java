package tension.helper;

import tension.task.Task;

import java.util.ArrayList;

public class Ui {

    public Ui(){}

    public void displayGreeting() {
        System.out.println("Hello! I'm Tension\n" +
                "What can I do for you?");
    }

    public void displayDeletedTask(Task removed, int counter) {
        System.out.println("Noted. I've removed this task:\n" +
                removed.toString() + "\n" +
                "     Now you have " + counter +" tasks in the list.");
    }

    public void displayAddedTask(Task added, int counter) {
        System.out.println("Got it. I've added this task:\n" +
                added + "\n" +
                "     Now you have " + counter + " tasks in the list.");
    }

    public void listTasks(ArrayList<Task> taskList, int counter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println(i+1 + "." + taskList.get(i).toString());
        }
    }

    /**
     * prints all tasks in provided list
     */
    public void listMatchingTasks(ArrayList<Task> taskList) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + "." + taskList.get(i).toString());
        }
    }
}
