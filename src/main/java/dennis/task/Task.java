package dennis.task;

public abstract class Task {
    String description;
    boolean isDone;
    String type;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks whether the task is completed.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the type of the task (e.g., T, D, E).
     *
     * @return A string representing the task type.
     */
    public abstract String getType();

    /**
     * Converts the task into a save-friendly format for storage.
     *
     * @return A string formatted for saving the task to storage.
     */
    public String toSaveFormat() { return getType() + " | " + (isDone() ? "1" : "0") + " | " + getDescription(); }

    /**
     * Returns a string representation of the task for display.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return "[" + type + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
