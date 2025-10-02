package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.Task.Task;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

import java.util.ArrayList;

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
