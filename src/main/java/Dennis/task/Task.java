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
