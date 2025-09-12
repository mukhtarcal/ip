package Dennis.task;

public class Event extends Task {
    String from;
    String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + (isDone ? "X" : " ") + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}
