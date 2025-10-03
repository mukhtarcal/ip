package dennis.command;

import dennis.storage.Storage;
import dennis.task.Task;
import dennis.task.Todo;
import dennis.taskList.TaskList;
import dennis.ui.Ui;

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
