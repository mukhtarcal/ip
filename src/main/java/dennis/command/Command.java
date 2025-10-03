package dennis.command;

import dennis.taskList.TaskList;
import dennis.ui.Ui;
import dennis.storage.Storage;

public abstract class Command {
    /**
     * Executes the command with the provided task list, user interface, and storage.
     *
     * This method is implemented by subclasses to define the specific
     * behavior of the command (e.g., adding a task, marking a task, listing tasks).
     *
     * @param tasks   The TaskList that stores all current tasks.
     * @param ui      The Ui for displaying messages to the user.
     * @param storage The Storage used to save and load tasks from persistent storage.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines if this command signals the chatbot to exit.
     *
     * By default, commands do not exit the application.
     * Commands like ExitCommand override this method to return true.
     *
     * @return true if this command should terminate the chatbot, false otherwise.
     */
    public boolean isExit() { return false; }
}