package Dennis.Parser;

import Dennis.Command.Command;
import Dennis.Command.ListCommand;
import Dennis.Command.ExitCommand;
import Dennis.Command.AddDeadlineCommand;
import Dennis.Command.AddEventCommand;
import Dennis.Command.AddTodoCommand;
import Dennis.Command.MarkCommand;
import Dennis.Command.UnmarkCommand;
import Dennis.Command.DeleteCommand;
import Dennis.Command.InvalidFormatCommand;
import Dennis.Command.InvalidEmptyCommand;

public class Parser {
    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "todo":
            // if there are no words after "todo", print error message
            if (parts.length == 1) {
                return new InvalidEmptyCommand(commandWord);
            }

            String todoDescription = fullCommand.substring(fullCommand.indexOf(" ") + 1).trim();
            return new AddTodoCommand(todoDescription);
        case "deadline":
            // if there are no words after "deadline", print error message
            if (parts.length == 1) {
                return new InvalidEmptyCommand(commandWord);
            } else if (!fullCommand.contains("/by")) { // if the event doesn't contain "/by", print error message
                String format = " \"deadline deadline_name /by duedate\n";
                return new InvalidFormatCommand(fullCommand, format);
            }

            String[] deadlineParts = fullCommand.split("/by", 2);
            String deadlineDescription = deadlineParts[0].substring("deadline".length()).trim();
            String by = deadlineParts[1].trim();
            return new AddDeadlineCommand(deadlineDescription, by);
        case "event":
            // if there are no words after "event", print error message
            if (parts.length == 1) {
                return new InvalidEmptyCommand(commandWord);
            } else if (!fullCommand.contains("/from") || !fullCommand.contains("/to")) { // if the event doesn't contain "/from" or "/to", print error message
                String format = " \"event event_name /from beginning /to end\n";
                return new InvalidFormatCommand(fullCommand, format);
            }

            String[] eventParts = fullCommand.split("/from", 2);
            String eventDescription = eventParts[0].substring("event".length()).trim();
            String[] timeParts = eventParts[1].split("/to", 2);
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            return new AddEventCommand(eventDescription, from, to);
        case "delete":
            try {
                Integer indexToDelete = Integer.valueOf(parts[1]);
                return new DeleteCommand(indexToDelete);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                String format = " delete #\n" + " Where # is a valid task number\n";
                return new InvalidFormatCommand(fullCommand, format);
            }
        case "mark":
            try {
                Integer indexToMark = Integer.parseInt(parts[1]) - 1;
                return new MarkCommand(indexToMark);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                String format = " mark #\n" + " Where # is a valid task number\n";
                return new InvalidFormatCommand(fullCommand, format);
            }
        case "unmark":
            try {
                Integer indexToUnmark = Integer.parseInt(parts[1]) - 1;
                return new UnmarkCommand(indexToUnmark);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                String format = " unmark #\n" + " Where # is a valid task number\n";
                return new InvalidFormatCommand(fullCommand, format);
            }
        case "bye":
            return new ExitCommand();
        default:
            String format = " Start your message with todo, deadline, or event.\n";
            return new InvalidFormatCommand(fullCommand, format);
        }
    }
}
