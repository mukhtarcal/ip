package dennis.command;

import dennis.storage.Storage;
import dennis.taskList.TaskList;
import dennis.ui.Ui;

public class InvalidEmptyCommand extends Command {
    private String command;

    public InvalidEmptyCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showErrorEmptyDescription(command);
    }
}