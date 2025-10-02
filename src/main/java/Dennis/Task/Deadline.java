package Dennis.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    String byRaw;
    private LocalDateTime byDateTime;
    private LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.type = "D";
        this.byRaw = by;

        // Try parsing as LocalDateTime first
        this.byDateTime = tryParseDateTime(by);

        // If LocalDateTime failed, try LocalDate
        if (this.byDateTime == null) {
            this.byDate = tryParseDate(by);
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

    public String getBy() {
        if (byDateTime != null) {
            return byDateTime.format(OUTPUT_DATE_TIME_FORMATTER);
        } else if (byDate != null) {
            return byDate.format(OUTPUT_DATE_FORMATTER);
        } else {
            return byRaw;
        }
    }

    @Override
    public String getType() { return this.type; }

    @Override
    public String toSaveFormat() { return super.toSaveFormat() + " | " + byRaw; }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + (isDone ? "X" : " ") + "] " + description + " (by: " + getBy() + ")";
    }
}
