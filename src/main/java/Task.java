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
        String command = s.split("\\s+")[0];
        String[] valid= new String[]{"mark", "unmark","delete", "todo", "deadline", "event"};
        for (String v : valid) {
            if (command.equals(v)) {
                return true;
            }
        }
        return false;
    }

    public static Task makeTask(String command) throws Exception {
        String[] parts = command.split("\\s+");
        String firstWord = parts[0];
        String[] splitBySlash = command.split("/");
        if (firstWord.equals("todo")) {
            String description = Task.secondWordOnwards("todo", splitBySlash[0]);
            checkStrings(description);
            return new Todo(description);
        }
        else if (firstWord.equals("deadline")) {
            String description = Task.secondWordOnwards("deadline", splitBySlash[0]);
            String byTime = Task.secondWordOnwards("by", splitBySlash[1]);
            checkStrings(description, byTime);
            return new Deadline(description, byTime);
        }
        else if (firstWord.equals("event")) {
            String description = Task.secondWordOnwards("event", splitBySlash[0]);
            String startTime = Task.secondWordOnwards("from", splitBySlash[1]);
            String endTime = Task.secondWordOnwards("to", splitBySlash[2]);
            checkStrings(description, startTime, endTime);
            return new Event(description, startTime, endTime);
        }
        return new Task("");
    }

    public static boolean startsWithMark(String s) {
        return s.matches("^mark\\s+\\d+$") || s.matches("^unmark\\s+\\d+$");
    }

    public static boolean startsWithDelete(String s) {
        String firstWord = s.split("\\s+")[0];
        return firstWord.equals("delete");
    }

    public static void checkStrings(String ...strings) {
        for (String s : strings) {
            if (s != null && s.isEmpty()) {
                throw new Error("String cannot be empty");
            }
        }
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

    public static String secondWordOnwards(String valid, String s) throws TensionException {
        String[] parts = s.strip().split("\\s+");
        if (!parts[0].equals(valid)) {
            throw new TensionException("Invalid task description, keyword missing is: " + valid);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            result.append(parts[i]);
            result.append(" ");
        }
        return result.toString().strip();
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "["+getStatusIcon()+"] " + description;
    }
}
