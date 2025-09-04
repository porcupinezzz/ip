package tension.task;

/**
 * creates Event class for events with start and end time
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * returns Event object
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String makeStoreString() {
        return "E|" + super.isDone + "|" + description + "|" + startTime + "|" + endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
