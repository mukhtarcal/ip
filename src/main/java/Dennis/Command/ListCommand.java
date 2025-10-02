package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getAll());
    }
}
