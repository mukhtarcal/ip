package dennis.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String getType() { return this.type; }
}
