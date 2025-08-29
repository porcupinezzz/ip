package tension.helper;

import org.junit.jupiter.api.Test;
import tension.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    private static String PATH = "src/test/sample.txt";
    private static String ORIGINAL_CONTENT = "T|true|1\n" +
            "E|false|meeting|1pm|2pm\n" +
            "D|false|meeting|2019-10-15\n";

    @Test
    public void dummyWriteTest() throws Exception {
        Files.deleteIfExists(Path.of(PATH));
        Files.write(Path.of(PATH), ORIGINAL_CONTENT.getBytes());
        Storage storage = new Storage(PATH);
        Task task = Task.makeTask("todo sample command");
        storage.writeFile(task);
        String fileContent = Files.readString(Path.of(PATH));
        assertEquals(ORIGINAL_CONTENT + "T|false|sample command\n", fileContent);
    }

    @Test
    public void dummyDeleteTest() throws Exception {
        Files.deleteIfExists(Path.of(PATH));
        Files.write(Path.of(PATH), ORIGINAL_CONTENT.getBytes());
        Storage storage = new Storage(PATH);
        storage.deleteLine(2);
        String fileContent = Files.readString(Path.of(PATH));
        assertEquals("T|true|1\n" +
                "E|false|meeting|1pm|2pm\n", fileContent);
    }
}

