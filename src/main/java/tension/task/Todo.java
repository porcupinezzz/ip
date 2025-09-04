package tension.task;

/**
 * creates Todo task for tasks with description only
 */
public class Todo extends Task {

    /**
     * returns Todo object
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String makeStoreString() {
        return "T|" + super.isDone + "|" + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
