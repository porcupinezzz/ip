package tension.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * creates Deadline class for tasks with deadline
 */
public class Deadline extends Task {

    protected LocalDate dueDate;

    /**
     * returns new Deadline object
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
    }

    /**
     * checks that time given is in an acceptable format
     */
    public static void checkFormat(String time) {
        try {
            LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: needs to be yyyy-mm-dd");
        }
    }

    @Override
    public String makeStoreString() {
        return "D|" + super.isDone + "|" + description + "|" + dueDate;
    }

    @Override
    public String toString() {
        String date = dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
