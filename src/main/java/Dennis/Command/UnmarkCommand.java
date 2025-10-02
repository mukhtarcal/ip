package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.Task.Task;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

public class UnmarkCommand extends Command {
    private Integer index;

    public UnmarkCommand(Integer index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // check that index is in bounds
        if (index < 0 || index >= tasks.size()) {
            String format = " unmark #\n" + " Where # is a valid task number\n";
            ui.showErrorInvalidCommand("unmark " + (index+1), format);
            return;
        }

        tasks.get(index).unmarkAsNotDone();
        Task unmarkedTask = tasks.get(index);
        ui.showTaskUnmarked(unmarkedTask);
        storage.save(tasks.getAll());
    }
}
