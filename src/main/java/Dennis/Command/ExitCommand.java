package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
