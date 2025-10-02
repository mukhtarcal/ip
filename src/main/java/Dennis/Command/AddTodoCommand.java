package Dennis.Command;

import Dennis.Storage.Storage;
import Dennis.Task.Task;
import Dennis.Task.Todo;
import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;

public class AddTodoCommand extends Command {
    String description;
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        Task addedTodo = tasks.get(tasks.size() - 1);
        ui.showTaskAdded(addedTodo);
        storage.save(tasks.getAll());
    }
}
