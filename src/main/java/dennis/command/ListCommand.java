package dennis.command;

import dennis.storage.Storage;
import dennis.taskList.TaskList;
import dennis.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getAll());
    }
}
