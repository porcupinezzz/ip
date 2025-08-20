public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String markDone() {
        isDone = true;
        String s = ("Nice! I've marked this task as done:\n[" +
                getStatusIcon() + "] "+description);
        return s;
    }

    public String unmarkDone() {
        isDone = false;
        String s = ("OK, I've marked this task as not done yet:\n[" +
                getStatusIcon() +"] "+ description);
        return s;
    }

    public static boolean matches(String s) {
        if (s.matches("^mark\\s+\\d+$") || s.matches("^unmark\\s+\\d+$")) {
            return true;
        }
        return false;
    }

    public static boolean isMark(String s) {
        return s.matches("^mark\\s+\\d+$");
    }

    public String getStatus(Boolean isMark) {
        if (isMark) {
            return markDone();
        }
        else {
            return unmarkDone();
        }
    }

    public String getDescription() {
        return "["+getStatusIcon()+"] " + description;
    }
}
