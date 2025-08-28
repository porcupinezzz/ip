package task;

public class Todo extends Task {

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
