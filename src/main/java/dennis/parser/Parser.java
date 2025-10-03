package dennis.parser;

import dennis.command.AddDeadlineCommand;
import dennis.command.AddEventCommand;
import dennis.command.AddTodoCommand;
import dennis.command.Command;
import dennis.command.DeleteCommand;
import dennis.command.ExitCommand;
import dennis.command.FindCommand;
import dennis.command.InvalidEmptyCommand;
import dennis.command.InvalidFormatCommand;
import dennis.command.ListCommand;
import dennis.command.MarkCommand;
import dennis.command.UnmarkCommand;

public class Parser {
    private static final String DEADLINE_KEY = "/by";
    private static final String EVENT_FROM_KEY = "/from";
    private static final String EVENT_TO_KEY = "/to";
    /**
     * Parses the given user input string and returns the corresponding Command.
     *
     * @param fullCommand The full user input string entered in the chatbot.
     * @return A Command object representing the parsed input.
     *         Returns an error command if the input is invalid or incomplete.
     */
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
            } else if (!fullCommand.contains(DEADLINE_KEY)) { // if the event doesn't contain "/by", print error message
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

            String[] eventParts = fullCommand.split(EVENT_FROM_KEY, 2);
            String eventDescription = eventParts[0].substring("event".length()).trim();
            String[] timeParts = eventParts[1].split(EVENT_TO_KEY, 2);
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            return new AddEventCommand(eventDescription, from, to);
        case "delete":
            try {
                Integer indexToDelete = Integer.parseInt(parts[1]) - 1;
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
        case "find":
            // if there are no words after "find", print error message
            if (parts.length == 1) {
                return new InvalidEmptyCommand(commandWord);
            } else {
                String toFind = fullCommand.substring(fullCommand.indexOf(" ") + 1).trim();
                return new FindCommand(toFind);
            }
        case "bye":
            return new ExitCommand();
        default:
            String format = " Start your message with todo, deadline, or event.\n";
            return new InvalidFormatCommand(fullCommand, format);
        }
    }
}
