package tension;

import java.util.ArrayList;
import java.util.Scanner;

import tension.helper.Command;
import tension.helper.Parser;
import tension.helper.Storage;
import tension.helper.TaskList;
import tension.helper.Ui;
import tension.task.Task;

/**
 * starts the main chatbot application
 */
public class Tension {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private Tension(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        ArrayList<Task> tasks = storage.retrieveTasks();
        this.taskList = new TaskList(tasks, new ArrayList<>(), storage, ui);
    }


    private void run() throws TensionException {
        Scanner scanner = new Scanner(System.in);
        ui.displayGreeting();
        while (true) {
            String input = scanner.nextLine();
            Parser parser = new Parser();
            Command command = parser.parse(input);
            taskList.insertCommand(command);
            if (taskList.executesAndExits()) {
                scanner.close();
                return;
            }
        }
    }

    public static void main(String[] args) throws TensionException {
        new Tension("data.txt").run();
    }
}
