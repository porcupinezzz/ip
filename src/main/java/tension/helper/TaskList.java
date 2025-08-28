package tension.helper;

import tension.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private ArrayList<Command> commands;
    private Storage storage;
    private Ui ui;

    public TaskList(ArrayList<Task> tasks, ArrayList<Command> command, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.commands = command;
        this.storage = storage;
        this.ui = ui;
    }

    public void insertCommand(Command command) {
        this.commands.add(command);
    }

    public boolean execute() throws TensionException {
        int counter = tasks.size();
        Command command = commands.remove(0);
        int index = command.index;
        switch (command.commandName) {
            case "delete":
                Task removed = tasks.remove(index);
                storage.deleteLine(index);
                counter--;
                ui.displayDeletedTask(removed, counter);
                break;
            case "event":
            case "deadline":
            case "todo":
                Task currTask;
                try {
                    currTask = Task.makeTask(command.fullWord);
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
            case "mark":
            case "unmark":
                if (index < 0 || index >= counter) {
                    System.out.println("Invalid index");
                } else {
                    Task task = tasks.get(index);
                    String s = task.getStatus(command.isMark);
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
}
