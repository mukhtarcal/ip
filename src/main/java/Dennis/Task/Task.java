package Dennis.Task;

public abstract class Task {
    String description;
    boolean isDone;
    String type;

    // create task with isDone defaulted to false
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // method to return description
    public String getDescription() {
        return this.description;
    }

    // method to return isDone
    public boolean isDone() {
        return this.isDone;
    }

    // method to mark task as complete
    public void markAsDone() {
        this.isDone = true;
    }

    // method to unmark task as incomplete
    public void unmarkAsNotDone() {
        this.isDone = false;
    }

    // method to get type of task
    public abstract String getType();

    // method to get task in save format
    public String toSaveFormat() { return getType() + " | " + (isDone() ? "1" : "0") + " | " + getDescription(); }

    // method to display task
    public String toString() {
        return "[" + type + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
