package dennis.command;

import dennis.storage.Storage;
import dennis.taskList.TaskList;
import dennis.ui.Ui;

public class FindCommand extends Command {
    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMatchingTaskList(tasks.getAll(), toFind);
    }
}
