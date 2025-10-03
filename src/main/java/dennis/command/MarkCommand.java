package dennis.command;

import dennis.storage.Storage;
import dennis.task.Task;
import dennis.taskList.TaskList;
import dennis.ui.Ui;

public class MarkCommand extends Command {
    private Integer index;

    public MarkCommand(Integer index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // check that index is in bounds
        if (index < 0 || index >= tasks.size()) {
            String format = " mark #\n" + " Where # is a valid task number\n";
            ui.showErrorInvalidCommand("mark " + (index+1), format);
            return;
        }

        tasks.mark(index);
        Task markedTask = tasks.get(index);
        ui.showTaskMarked(markedTask);
        storage.save(tasks.getAll());
    }
}
