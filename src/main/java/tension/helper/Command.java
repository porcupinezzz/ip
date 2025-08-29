package tension.helper;

public class Command {

    int index;
    boolean isMarked;
    String commandName;
    String fullWord;

    /**
     * initializes the command given the following parameters
     *
     * @param index the index the command is on
     * @param commandWord the specific keyword that dictates the command
     * @param fullCommand the whole command
     */
    public Command(int index, String commandWord, boolean isMark, String fullCommand) {
        this.index = index;
        this.commandName = commandWord;
        this.isMarked = isMarked;
        this.fullWord = fullWord;
    }
}
