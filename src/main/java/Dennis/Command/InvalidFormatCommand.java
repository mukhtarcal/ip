package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

public class InvalidFormatCommand extends Command {
    private String command;
    private String format;

    public InvalidFormatCommand(String command, String format) {
        this.command = command;
        this.format = format;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showErrorInvalidCommand(command, format);
    }
}
