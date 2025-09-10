package Dennis.task;

import Dennis.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }
}
