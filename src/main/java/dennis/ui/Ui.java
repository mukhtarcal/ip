package dennis.ui;

import dennis.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LOGO =
            "██████╗  " + "███████╗ " + "███╗   ██╗ " + "███╗   ██╗ " + "██╗ " + "███████╗\n" +
            "██╔══██╗ " + "██╔════╝ " + "████╗  ██║ " + "████╗  ██║ " + "██║ " + "██╔════╝\n" +
            "██║  ██║ " + "█████╗   " + "██╔██╗ ██║ " + "██╔██╗ ██║ " + "██║ " + "███████╗\n" +
            "██║  ██║ " + "███╔══╝  " + "██║╚██╗██║ " + "██║╚██╗██║ " + "██║ " + "╚════██║\n" +
            "██████╔╝ " + "███████╗ " + "██║ ╚████║ " + "██║ ╚████║ " + "██║ " + "███████║\n" +
            "╚═════╝  " + "╚══════╝ " + "╚═╝  ╚═══╝ " + "╚═╝  ╚═══╝ " + "╚═╝ " + "╚══════╝\n";


    private final Scanner in = new Scanner(System.in);

    /**
     * Prints a divider which is used to make readability easier for user.
     */
    public void showDivider() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return in.nextLine();
    }

    public void showLogo() {
        System.out.println(LOGO);
    }

    /**
     * Shows the welcome message when user first starts up Dennis.
     */
    public void showWelcome() {
        showDivider();
        showLogo();
        System.out.println(" Yooo! I'm Dennis, PRETTY SICK LOGO HUH?!\n" + " Alright, What do you want? :|\n");
        showDivider();
    }

    /**
     * Shows the farewell message when the user ends Dennis.
     */
    public void showFarewell() {
        showDivider();
        System.out.println(" Thank god! I was wondering when you'd finish! Farewell from the one and only:\n");
        showLogo();
        showDivider();
    }

    /**
     * Displays all tasks in a formatted list.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        showDivider();
        if (tasks.isEmpty()) {
            System.out.println(" Your task list is empty! Add something BEFORE trying to list!.\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
        showDivider();
    }

    /**
     * Prints all the tasks whose description contains the specific phrase to find.
     *
     * @param tasks An array list of all the tasks.
     * @param toFind The phrase being searched for.
     */
    public void showMatchingTaskList(ArrayList<Task> tasks, String toFind) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(toFind)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            showDivider();
            System.out.println(" No tasks containing {" + toFind + "} were found!");
            showDivider();
        } else {
            showDivider();
            System.out.println(" Here are the results containing {" + toFind + "}");
            showTaskList(matchingTasks);
        }


    }

    public void showTaskAdded(Task task) {
        showDivider();
        System.out.println(" Ok, I added this task, make sure you actually do it!\n");
        System.out.println(" " + task);
        showDivider();
    }

    public void showTaskDeleted(Task task) {
        showDivider();
        System.out.println(" OK, I've deleted this task, I hope you didn't just delete it because you don't feel like doing it!:\n");
        System.out.println(" " + task);
        showDivider();
    }

    public void showTaskMarked(Task task) {
        showDivider();
        System.out.println(" Alright, i've marked this task as FINALLY completed:\n");
        System.out.println(" " + task);
        showDivider();
    }

    public void showTaskUnmarked(Task task) {
        showDivider();
        System.out.println(" OK, I've marked this task as not done because you STILL haven't completed it:\n");
        System.out.println(" " + task);
        showDivider();
    }

    /**
     * Prints an error message when an invalid command is given.
     *
     * @param line The line inputted by the user.
     * @param expectedFormat The format Dennis is expecting.
     */
    public void showErrorInvalidCommand(String line, String expectedFormat) {
        showDivider();
        System.out.println(" Come on! Invalid command!\n" +
                " Expected format:\n " + expectedFormat + "\n" +
                " {" + line + "} is not a valid command!");
        showDivider();
    }

    /**
     * Prints an error message when no description for a task is given.
     *
     * @param command The command the user was attempting to use.
     */
    public void showErrorEmptyDescription(String command) {
        showDivider();
        System.out.println(" OMG your " + command + " obviously can't be empty!\n");
        showDivider();
    }

    /**
     * Prints a error message with the given message.
     *
     * @param message The error to show the user.
     */
    public void showError(String message) {
        showDivider();
        System.out.println(" Error: " + message);
        showDivider();
    }

    public void close() {
        in.close();
    }
}
