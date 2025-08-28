package tension.helper;

import tension.task.Task;

/**
 * Provides methods to parse information given by
 * the user
 *
 */
public class Parser {

    public Parser() {}

    /**
     * returns relevant information based on command given
     *
     * @param input a string that starts with list, mark, unmark,
     *                delete, todo, deadline, event
     * @return tension.helper.Command
     */
    public Command parse(String input) {
        int index = -1;
        boolean isMark = false;
        String[] parts = input.split("\\s+");
        String firstString = parts[0];
        if (matches(firstString)) {
            if (firstString.equals("mark") || parts[0].equals("unmark")) {
                isMark = Task.isMark(input);
                index = Integer.parseInt(parts[1]) - 1;
            }
            else if (firstString.equals("delete")) {
                index = Integer.parseInt(parts[1]) - 1;
            }
            return new Command(index, firstString, isMark, input);
        }
        return null;
    }

    public static boolean matches(String s) {
        String[] valid= new String[]{"bye","list", "mark", "unmark","delete", "todo", "deadline", "event"};
        for (String v : valid) {
            if (s.equals(v)) {
                return true;
            }
        }
        return false;
    }


}
