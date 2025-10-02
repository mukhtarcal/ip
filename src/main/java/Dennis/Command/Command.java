package Dennis.Command;

import Dennis.TaskList.TaskList;
import Dennis.Ui.Ui;
import Dennis.Storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() { return false; }
}