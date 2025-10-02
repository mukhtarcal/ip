package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

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