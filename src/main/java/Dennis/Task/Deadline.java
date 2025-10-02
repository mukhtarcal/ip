package Dennis.Task;

public class Deadline extends Task {
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getType() { return this.type; }

    @Override
    public String toSaveFormat() { return super.toSaveFormat() + " | " + getBy(); }


    @Override
    public String toString() {
        return "[" + type + "]" + "[" + (isDone ? "X" : " ") + "] " + description + " (by: " + by + ")";
    }
}
