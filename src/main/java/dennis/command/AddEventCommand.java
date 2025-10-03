package dennis.command;

import dennis.storage.Storage;
import dennis.task.Event;
import dennis.task.Task;
import dennis.taskList.TaskList;
import dennis.ui.Ui;

public class AddEventCommand extends Command {
    String description;
    String from;
    String to;
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        Task addedEvent = tasks.get(tasks.size() - 1);

        ui.showTaskAdded(addedEvent);
        storage.save(tasks.getAll());
    }
}
