import helper.Command;
import helper.Storage;
import helper.Ui;
import helper.TaskList;
import helper.Parser;
import task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Tension {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Tension(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        ArrayList<Task> tasks = storage.retrieveTasks();
        this.taskList = new TaskList(tasks, new ArrayList<>(), storage, ui);
    }


    public void run() throws TensionException {
        Scanner scanner = new Scanner(System.in);
        ui.displayGreeting();
        while (true) {
            String input = scanner.nextLine();
            Parser parser = new Parser();
            Command command = parser.parse(input);
            taskList.insertCommand(command);
            if (taskList.execute()) {
                scanner.close();
                return;
            }
        }
    }
    public static void main(String[] args) throws TensionException {
        new Tension("data.txt").run();
    }
}
