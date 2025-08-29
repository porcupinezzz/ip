package tension.helper;

public class Command {

    int index;
    boolean isMarked;
    String commandName;
    String fullWord;

    public Command(int index, String commandWord, boolean isMarked, String fullWord) {
        this.index = index;
        this.commandName = commandWord;
        this.isMarked = isMarked;
        this.fullWord = fullWord;
    }
}
