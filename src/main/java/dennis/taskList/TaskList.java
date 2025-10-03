package dennis.taskList;

import java.util.ArrayList;
import dennis.task.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() { this.tasks = new ArrayList<Task>(); }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize this TaskList with.
     * */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to remove (0-based).
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Returns a task from the task list at the specified index.
     *
     * @param index The index of the task to retrieve (0-based).
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done (0-based).
     */
    public void mark(int index) { tasks.get(index).markAsDone(); }

    /**
     * Unmarks the task at the specified index (marks it as not done).
     *
     * @param index The index of the task to unmark (0-based).
     */
    public void unmark(int index) {
        tasks.get(index).unmarkAsNotDone();
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getAll() { return tasks; }}
