package Dennis.TaskList;

import java.util.ArrayList;
import Dennis.Task.Task;


public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmark(int index) {
        tasks.get(index).unmarkAsNotDone();
    }

    public void list() {
        if (tasks.isEmpty()) {
            System.out.println(" Your task list is empty! Add something BEFORE trying to list!.\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }
}
