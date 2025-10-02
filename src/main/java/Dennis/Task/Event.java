package Dennis.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    String fromRaw;
    String toRaw;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Event(String description, String from, String to) {
        super(description);
        this.type = "E";
        this.fromRaw = from;
        this.toRaw = to;

        // Try parsing as LocalDateTime first
        this.fromDateTime = tryParseDateTime(from);
        this.toDateTime = tryParseDateTime(to);

        // If LocalDateTime failed, try LocalDate
        if (this.fromDateTime == null) {
            this.fromDate = tryParseDate(from);
        }
        if (this.toDateTime == null) {
            this.toDate = tryParseDate(to);
        }
    }

    private LocalDateTime tryParseDateTime(String input) {
        try {
            return LocalDateTime.parse(input, INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private LocalDate tryParseDate(String input) {
        try {
            return LocalDate.parse(input, INPUT_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public String getFrom() {
        if (fromDateTime != null) {
            return fromDateTime.format(OUTPUT_DATE_TIME_FORMATTER);
        } else if (fromDate != null) {
            return fromDate.format(OUTPUT_DATE_FORMATTER);
        } else {
            return fromRaw;
        }
    }

    public String getTo() {
        if (toDateTime != null) {
            return toDateTime.format(OUTPUT_DATE_TIME_FORMATTER);
        } else if (toDate != null) {
            return toDate.format(OUTPUT_DATE_FORMATTER);
        } else {
            return toRaw;
        }
    }

    @Override
    public String getType() { return this.type; }

    @Override
    public String toSaveFormat() { return super.toSaveFormat() + " | " + fromRaw + " | " + toRaw; }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone ? "X" : " ") + "] " + description + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
