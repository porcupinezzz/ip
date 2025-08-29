package tension.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void dummyTest(){
        Command command = new Command(0, "help", true, "help me");
        assertEquals(0, command.index);
        assertEquals("help", command.commandName);
        assertEquals(true, command.isMarked);
        assertEquals("help me", command.fullWord);
    }
}
