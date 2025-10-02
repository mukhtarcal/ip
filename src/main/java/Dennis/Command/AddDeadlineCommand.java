package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.Task.Deadline;
import Dennis.Task.Task;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

public class AddDeadlineCommand extends Command {
    String description;
    String by;
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        Task addedDeadline = tasks.get(tasks.size() - 1);
        ui.showTaskAdded(addedDeadline);
        storage.save(tasks.getAll());
    }
}
