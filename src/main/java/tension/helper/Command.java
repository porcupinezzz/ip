package tension.helper;

public class Command {

    int index;
    boolean isMark;
    String commandName;
    String fullWord;

    public Command(int index, String commandWord, boolean isMark, String fullWord) {
        this.index = index;
        this.commandName = commandWord;
        this.isMark = isMark;
        this.fullWord = fullWord;
    }
}
