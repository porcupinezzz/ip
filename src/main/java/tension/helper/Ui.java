package tension.helper;

import java.util.ArrayList;

import tension.task.Task;

/**
 * creates a Ui class for the user
 */
public class Ui {

    /**
     * Initialises Ui object to print templates
     */
    public Ui() { }

    /**
     * Prints greeting message
     */
    public void displayGreeting() {
        System.out.println("Hello! I'm Tension\n"
                + "What can I do for you?");
    }

    /**
     * Stringifies greeting message
     */
    public String displayGreetingAsString() {
        return "Hello! I'm Tension\n" + "What can I do for you?";
    }

    /**
     * Prints deleted task
     * @param removed task removed from list
     * @param counter number of tasks remaining
     */
    public void displayDeletedTask(Task removed, int counter) {
        System.out.println("Noted. I've removed this task:\n"
                + removed.toString() + "\n"
                + "     Now you have " + counter + " tasks in the list.");
    }

    /**
     * Stringifies deleted task
     * @param removed task removed from list
     * @param counter number of tasks remaining
     */
    public String displayDeletedTaskAsString(Task removed, int counter) {
        return "Noted. I've removed this task:\n"
                + removed.toString() + "\n"
                + "     Now you have " + counter + " tasks in the list.";
    }

    /**
     * Prints added task
     * @param added task added to list
     * @param counter number of tasks in list
     */
    public void displayAddedTask(Task added, int counter) {
        System.out.println("Got it. I've added this task:\n"
                + added + "\n"
                + "     Now you have " + counter + " tasks in the list.");
    }

    /**
     * Stringifies added task
     * @param added task added to list
     * @param counter number of tasks in list
     */
    public String displayAddedTaskAsString(Task added, int counter) {
        return "Got it. I've added this task:\n"
                + added + "\n"
                + "     Now you have " + counter + " tasks in the list.";
    }

    /**
     * Prints all tasks in list
     * @param taskList list of all tasks
     * @param counter number of tasks in list
     */
    public void listTasks(ArrayList<Task> taskList, int counter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
    }

    /**
     * Stringifies all tasks in list
     * @param taskList list of all tasks
     * @param counter number of tasks in list
     */
    public String listTasksAsString(ArrayList<Task> taskList, int counter) {
        String tasks = "";
        tasks += "Here are the tasks in your list:\n";
        for (int i = 0; i < counter; i++) {
            tasks += (i + 1 + "." + taskList.get(i).toString() + "\n");
        }
        return tasks;
    }

    /**
     * prints all tasks in provided list
     */
    public void listMatchingTasks(ArrayList<Task> taskList) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
    }

    /**
     * stringifies all tasks in provided list
     */
    public String listMatchingTasksAsString(ArrayList<Task> taskList) {
        String tasks = "";
        tasks += ("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            tasks += (i + 1 + "." + taskList.get(i).toString());
        }
        return tasks;
    }
}
