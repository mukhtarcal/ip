package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.Task.Task;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

public class DeleteCommand extends Command {
    private Integer index;

    public DeleteCommand(Integer index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // check that index is in bounds
        if (index < 0 || index >= tasks.size()) {
            String format = " delete #\n" + " Where # is a valid task number\n";
            ui.showErrorInvalidCommand("delete " + (index+1), format);
            return;
        }

        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        ui.showTaskDeleted(deletedTask);
        storage.save(tasks.getAll());
    }
}
