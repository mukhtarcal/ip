package Dennis.task;

public class Task {
    String description;
    boolean isDone;
    String type = " ";

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

    // method to display task
    public String toString() {
        return "[" + type + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
