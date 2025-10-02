package Dennis.Ui;

import Dennis.Task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    final String LOGO =
            "██████╗  " + "███████╗ " + "███╗   ██╗ " + "███╗   ██╗ " + "██╗ " + "███████╗\n" +
            "██╔══██╗ " + "██╔════╝ " + "████╗  ██║ " + "████╗  ██║ " + "██║ " + "██╔════╝\n" +
            "██║  ██║ " + "█████╗   " + "██╔██╗ ██║ " + "██╔██╗ ██║ " + "██║ " + "███████╗\n" +
            "██║  ██║ " + "███╔══╝  " + "██║╚██╗██║ " + "██║╚██╗██║ " + "██║ " + "╚════██║\n" +
            "██████╔╝ " + "███████╗ " + "██║ ╚████║ " + "██║ ╚████║ " + "██║ " + "███████║\n" +
            "╚═════╝  " + "╚══════╝ " + "╚═╝  ╚═══╝ " + "╚═╝  ╚═══╝ " + "╚═╝ " + "╚══════╝\n";


    private final Scanner in = new Scanner(System.in);

    public void showDivider() {
        System.out.println("____________________________________________________________\n");
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLogo() {
        System.out.println(LOGO);
    }

    public void showWelcome() {
        showDivider();
        showLogo();
        System.out.println(" Yooo! I'm Dennis, PRETTY SICK LOGO HUH?!\n" + " Alright, What do you want? :|\n");
        showDivider();
    }

    public void showFarewell() {
        showDivider();
        System.out.println(" Thank god! I was wondering when you'd finish! Farewell from the one and only:\n");
        showLogo();
        showDivider();
    }

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

    public void showErrorInvalidCommand(String line, String expectedFormat) {
        showDivider();
        System.out.println(" Come on! Invalid command!\n" +
                " Expected format:\n " + expectedFormat + "\n" +
                " {" + line + "} is not a valid command!");
        showDivider();
    }

    public void showErrorEmptyDescription(String command) {
        showDivider();
        System.out.println(" OMG your " + command + " obviously can't be empty!\n");
        showDivider();
    }

    public void showError(String message) {
        showDivider();
        System.out.println(" Error: " + message);
        showDivider();
    }
}
