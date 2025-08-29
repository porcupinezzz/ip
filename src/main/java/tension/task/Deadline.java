package tension.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
    }

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
