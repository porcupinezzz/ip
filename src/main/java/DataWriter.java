import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataWriter {

    public static void writeFile(Task t) {
        try {
            FileWriter fileWriter = new FileWriter("data.txt", true);
            fileWriter.write(t.makeStoreString() + "\n");
            fileWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteLine(int line) {
        try {
            Path filePath = Path.of("./data.txt");
            List<String> lines = Files.readAllLines(filePath);

            if (line < 0 || line > lines.size()) {
                System.out.println("Invalid line number: " + line);
                return;
            }

            lines.remove(line);

            Files.write(filePath, lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rewriteLine(int line, String newLine) {
        try {
            Path filePath = Path.of("./data.txt");
            List<String> lines = Files.readAllLines(filePath);

            if (line < 0 || line > lines.size()) {
                System.out.println("Invalid line number: " + line);
                return;
            }

            lines.set(line, newLine);

            Files.write(filePath, lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> retrieveTasks(){
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Path filePath = Path.of("./data.txt");
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
