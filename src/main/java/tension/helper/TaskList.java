package tension.helper;

import java.util.ArrayList;

import tension.TensionException;
import tension.task.Task;

/**
 * Stores list of all tasks and commands to execute
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private ArrayList<Command> commands;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises the task list with the necessary tools
     * @param tasks list of all tasks
     * @param commands list of commands to be done
     * @param storage storage object that links taskList to storage
     * @param ui outputs text to the UI
     */
    public TaskList(ArrayList<Task> tasks, ArrayList<Command> commands, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.commands = commands;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Inserts command into command list
     */
    public void insertCommand(Command command) {
        this.commands.add(command);
    }

    /**
     * Executes all the commands in the command list
     * and adds them to the task list
     */
    public boolean executesAndExits() throws TensionException {
        int counter = tasks.size();
        Command command = commands.remove(0);
        int index = command.getIndex();
        switch (command.getCommandName()) {
        case "find":
            ArrayList<Task> foundTasks = new ArrayList<>();
            for (Task task : tasks) {
                String keyword = command.getFullWord().split(" ")[1];
                String[] taskKeywords = (task.getDescription().split(" "));
                for (String taskKeyword : taskKeywords) {
                    if (taskKeyword.equals(keyword)) {
                        foundTasks.add(task);
                        break;
                    }
                }
            }
            ui.listMatchingTasks(foundTasks);
            break;
        case "delete":
            Task removed = tasks.remove(index);
            storage.deleteLine(index);
            counter--;
            ui.displayDeletedTask(removed, counter);
            break;
        case "event": // Fallthrough
        case "deadline": // Fallthrough
        case "todo":
            Task currTask;
            try {
                currTask = Task.makeTask(command.getFullWord());
            } catch (Exception e) {
                e.toString();
                e.printStackTrace();
                return false;
            }
            tasks.add(currTask);
            storage.writeFile(currTask);
            counter++;
            ui.displayAddedTask(currTask, counter);
            break;
        case "mark": // Fallthrough
        case "unmark":
            if (index < 0 || index >= counter) {
                System.out.println("Invalid index");
            } else {
                Task task = tasks.get(index);
                String s = task.getStatus(command.getIsMarked());
                storage.rewriteLine(index, task.makeStoreString());
                System.out.println(s);
            }
            break;
        case "list":
            ui.listTasks(tasks, counter);
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        default:
            throw new TensionException("Invalid command, please start with todo, deadline, or event");
        }
        return false;
    }

    /**
     * Executes all the commands in the command list
     * and adds them to the task list
     */
    public String executesAndReturnString() throws TensionException {
        int counter = tasks.size();
        Command command = commands.remove(0);
        int index = command.getIndex();
        switch (command.getCommandName()) {
        case "find":
            ArrayList<Task> foundTasks = new ArrayList<>();
            for (Task task : tasks) {
                String keyword = command.getFullWord().split(" ")[1];
                String[] taskKeywords = (task.getDescription().split(" "));
                for (String taskKeyword : taskKeywords) {
                    if (taskKeyword.equals(keyword)) {
                        foundTasks.add(task);
                        break;
                    }
                }
            }
            ui.listMatchingTasksAsString(foundTasks);
            break;
        case "delete":
            Task removed = tasks.remove(index);
            storage.deleteLine(index);
            counter--;
            return ui.displayDeletedTaskAsString(removed, counter);
        case "event": // Fallthrough
        case "deadline": // Fallthrough
        case "todo":
            Task currTask;
            try {
                currTask = Task.makeTask(command.getFullWord());
            } catch (Exception e) {
                return e.getMessage();
            }
            tasks.add(currTask);
            storage.writeFile(currTask);
            counter++;
            return ui.displayAddedTaskAsString(currTask, counter);
        case "mark": // Fallthrough
        case "unmark":
            if (index < 0 || index >= counter) {
                return "Invalid index input";
            } else {
                Task task = tasks.get(index);
                String s = task.getStatus(command.getIsMarked());
                storage.rewriteLine(index, task.makeStoreString());
                return s;
            }
        case "list":
            return ui.listTasksAsString(tasks, counter);
        case "bye":
            return "Bye. Hope to see you again soon!";
        default:
            return "Invalid command, please start with todo, deadline, or event";
        }
        return "";
    }
}
