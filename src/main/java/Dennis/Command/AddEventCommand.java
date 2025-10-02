package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.Task.Event;
import Dennis.Task.Task;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

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
