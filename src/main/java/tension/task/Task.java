package tension.task;

import tension.TensionException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * returns status icon based on tasks completion
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * returns a string of a task marked as done
     */
    public String markDone() {
        isDone = true;
        String s = ("Nice! I've marked this tension.task as done:\n[" +
                getStatusIcon() + "] "+ description);
        return s;
    }

    /**
     * returns a string of a task unmarked as done
     */
    public String unmarkDone() {
        isDone = false;
        String s = ("OK, I've marked this tension.task as not done yet:\n[" +
                getStatusIcon() +"] "+ description);
        return s;
    }

    /**
     * returns Task made from string input by user
     */
    public static Task makeTask(String command) throws Exception {
        String[] parts = command.split("\\s+");
        String firstWord = parts[0];
        String[] splitBySlash = command.split("/");
        if (firstWord.equals("todo")) {
            String description = Task.secondWordOnwards("todo", splitBySlash[0]);
            checkStrings(description);
            return new Todo(description);
        } else if (firstWord.equals("deadline")) {
            String description = Task.secondWordOnwards("deadline", splitBySlash[0]);
            String byTime = Task.secondWordOnwards("dueDate", splitBySlash[1]);
            checkStrings(description, byTime);
            Deadline.checkFormat(byTime);
            return new Deadline(description, byTime);
        } else if (firstWord.equals("event")) {
            String description = Task.secondWordOnwards("event", splitBySlash[0]);
            String startTime = Task.secondWordOnwards("from", splitBySlash[1]);
            String endTime = Task.secondWordOnwards("to", splitBySlash[2]);
            checkStrings(description, startTime, endTime);
            return new Event(description, startTime, endTime);
        }
        return new Task("");
    }

    /**
     * returns Task made from string returned from storage
     */
    public static Task makeTaskFromMemory(String command) throws Exception {
        String[] parts = command.split("[|]");
        String firstWord = parts[0];
        Task task = new Task("");
        if (firstWord.equals("T")) {
            checkStrings(parts[2]);
            task = new Todo(parts[2]);
        } else if (firstWord.equals("D")) {
            checkStrings(parts[2], parts[3]);
            task = new Deadline(parts[2], parts[3]);
        } else if (firstWord.equals("E")) {
            checkStrings(parts[2], parts[3], parts[4]);
            task = new Event(parts[2], parts[3], parts[4]);
        }
        task.getStatus(Boolean.parseBoolean(parts[1]));
        return task;
    }

    public static void checkStrings(String... strings) {
        for (String s : strings) {
            if (s != null && s.isEmpty()) {
                throw new Error("String cannot be empty");
            }
        }
    }

    public String getStatus(Boolean isMark) {
        if (isMark) {
            return markDone();
        } else {
            return unmarkDone();
        }
    }

    /**
     * Returns second word after key phrase
     * @param valid the keyword that is needed
     * @param s the whole string
     */
    private static String secondWordOnwards(String valid, String s) throws TensionException {
        String[] parts = s.strip().split("\\s+");
        if (!parts[0].equals(valid)) {
            throw new TensionException("Invalid tension.task description, keyword missing is: " + valid);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            result.append(parts[i]);
            result.append(" ");
        }
        return result.toString().strip();
    }

    /**
     * returns string to store in storage
     */
    public String makeStoreString() {
        return "";
    }

    @Override
    public String toString() {
        return "["+getStatusIcon()+"] " + description;
    }
}
