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
     * Stringifies greeting message
     */
    public String displayGreetingAsString() {
        return "Hello! I'm Tension\n" + "What can I do for you?";
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
