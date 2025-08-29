package tension.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void makeTaskTest() throws Exception {
        Task todo = Task.makeTask("todo yes");
        Task deadline = Task.makeTask("deadline yes/dueDate 2019-01-01");
        Task event = Task.makeTask("event yes/from 6pm/to 8pm");
        Task faulty = Task.makeTask("faulty");
        assertEquals((new Todo("yes")).toString(),todo.toString());
        assertEquals((new Deadline("yes", "2019-01-01")).toString(),deadline.toString());
        assertEquals((new Event("yes", "6pm", "8pm")).toString(),event.toString());
        assertEquals((new Task("").toString()), faulty.toString());
    }
}
