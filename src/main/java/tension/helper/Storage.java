package helper;

import task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * manages storage files
 */
public class Storage {

    String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * writes a task to the storage file
     * @param t
     */
    public void writeFile(Task t) {
        try {
            FileWriter fileWriter = new FileWriter(this.path, true);
            fileWriter.write(t.makeStoreString() + "\n");
            fileWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes line number = n in storage
     * @param n
     */
    public void deleteLine(int n) {
        try {
            Path filePath = Path.of(this.path);
            List<String> lines = Files.readAllLines(filePath);

            if (n < 0 || n > lines.size()) {
                System.out.println("Invalid line number: " + n);
                return;
            }

            lines.remove(n);

            Files.write(filePath, lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * replaces line number n with string newLine
     * @param n
     * @param newLine
     */
    public void rewriteLine(int n, String newLine) {
        try {
            Path filePath = Path.of(this.path);
            List<String> lines = Files.readAllLines(filePath);

            if (n < 0 || n > lines.size()) {
                System.out.println("Invalid line number: " + n);
                return;
            }

            lines.set(n, newLine);

            Files.write(filePath, lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieves tasks from storage
     * @return ArrayList of tasks
     */
    public ArrayList<Task> retrieveTasks(){
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Path filePath = Path.of(this.path);
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                tasks.add(Task.makeTaskFromMemory(line));
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
