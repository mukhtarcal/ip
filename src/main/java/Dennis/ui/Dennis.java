package Dennis.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Dennis.task.*;

public class Dennis {
    private static final String DATA_DIR = "./data";
    private static final String DATA_FILE = DATA_DIR + "/dennis.txt";
    public static void main(String[] args) {
        final int MAX_NUM_TASKS = 100;
        final String LOGO =
                "██████╗  " + "███████╗ " + "███╗   ██╗ " + "███╗   ██╗ " + "██╗ " + "███████╗\n" +
                "██╔══██╗ " + "██╔════╝ " + "████╗  ██║ " + "████╗  ██║ " + "██║ " + "██╔════╝\n" +
                "██║  ██║ " + "█████╗   " + "██╔██╗ ██║ " + "██╔██╗ ██║ " + "██║ " + "███████╗\n" +
                "██║  ██║ " + "███╔══╝  " + "██║╚██╗██║ " + "██║╚██╗██║ " + "██║ " + "╚════██║\n" +
                "██████╔╝ " + "███████╗ " + "██║ ╚████║ " + "██║ ╚████║ " + "██║ " + "███████║\n" +
                "╚═════╝  " + "╚══════╝ " + "╚═╝  ╚═══╝ " + "╚═╝  ╚═══╝ " + "╚═╝ " + "╚══════╝\n";

        ensureDataDirExists();
        ArrayList<Task> taskList = new ArrayList<>();
        loadInData(taskList);

        // greeting message
        printDivider();
        System.out.println(LOGO);
        System.out.println(" Yooo! I'm Dennis, PRETTY SICK LOGO HUH?!\n" + " Alright, What do you want? :|\n");
        printDivider();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        // as long as the user does not say bye, take in the user input
        while (!line.equals("bye")) {
            String[] words = line.split(" ");

            switch (words[0]) {
            case "list":
                printDivider();
                printTaskList(taskList);
                printDivider();
                break;

            case "mark":
                try {
                    int taskIndexMark = Integer.parseInt(words[1]) - 1;
                    taskList.get(taskIndexMark).markAsDone();

                    printDivider();
                    System.out.println(" Alright, i've marked this task as FINALLY completed:\n   "
                            + taskList.get(taskIndexMark));
                    printDivider();

                    saveTasks(taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printDivider();
                    System.out.println(" Come on! You need to give me a valid task to mark! The form of the command is:\n" +
                            " mark #\n" +
                            " Where # is a valid task number\n" +
                            " {" + line + "}" + " is not a valid command!");
                    printDivider();
                } catch (NumberFormatException e) {
                    printDivider();
                    System.out.println(" Come on! The task number needs to be a valid digit! Follow this format:\n" +
                            " mark #\n" +
                            " Where # is a valid task number\n" +
                            " {" + line + "}" + " is not a valid command!");
                    printDivider();
                } catch (NullPointerException e) {
                    printDivider();
                    System.out.println(" Come on! You need to give me a valid task to mark! The form of the command is:\n" +
                            " mark #\n" +
                            " Where # is a valid task number\n" +
                            " {" + line + "}" + " is not a valid command!\n");
                    printDivider();
                }
                break;

            case "unmark":
                try {
                    int taskIndexUnmark = Integer.parseInt(words[1]) - 1;
                    taskList.get(taskIndexUnmark).unmarkAsNotDone();

                    printDivider();
                    System.out.println(" OK, I've marked this task as not done because you STILL haven't completed it:\n   "
                            + taskList.get(taskIndexUnmark));
                    printDivider();

                    saveTasks(taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printDivider();
                    System.out.println(" Come on! You need to give me a valid task to unmark! The form of the command is:\n" +
                            " unmark #\n" +
                            " Where # is a valid task number\n" +
                            " {" + line + "}" + " is not a valid command!");
                    printDivider();
                } catch (NumberFormatException e) {
                    printDivider();
                    System.out.println(" Come on! The task number needs to be a valid digit! Follow this format:\n" +
                            " unmark #\n" +
                            " Where # is a valid task number\n" +
                            " {" + line + "}" + " is not a valid command!");
                    printDivider();
                } catch (NullPointerException e) {
                    printDivider();
                    System.out.println(" Come on! You need to give me a valid task to unmark! The form of the command is:\n" +
                            " unmark #\n" +
                            " Where # is a valid task number\n" +
                            " {" + line + "}" + " is not a valid command!\n");
                    printDivider();
                }
                break;

            case "todo":
                // if there are no words after "todo", print error message
                if (words.length == 1) {
                    printDivider();
                    System.out.println(" OMG your todo obviously can't be empty!\n");
                    printDivider();
                    break;
                }

                String todoDescription = line.substring(line.indexOf(" ") + 1).trim();

                Todo newTodo = new Todo(todoDescription);
                taskList.add(newTodo);

                printDivider();
                System.out.println(" Ok, I added this TODO, make sure you actually do it!\n");
                System.out.println(" " + taskList.get(taskList.size() - 1));
                printDivider();

                saveTasks(taskList);
                break;

            case "deadline":
                // if there are no words after "deadline", print error message
                if (words.length == 1) {
                    printDivider();
                    System.out.println(" OMG your deadline obviously can't be empty!");
                    printDivider();
                    break;
                } else if (!line.contains("/by")) { // if the event doesn't contain "/by", print error message
                    printDivider();
                    System.out.println(" You need to include the \"/by\" keyword when creating a deadline!\n" +
                            " Do I need to spell it out for you?! Fine, I will, your deadline command needs to be of the form:\n" +
                            " \"deadline deadline_name /by duedate\n" +
                            " {" + line + "}" + " is missing \"/by\" keyword");
                    printDivider();
                    break;
                }

                String[] deadlineParts = line.split("/by", 2);
                String deadlineDescription = deadlineParts[0].substring("deadline".length()).trim();
                String by = deadlineParts[1].trim();

                Deadline newDeadline = new Deadline(deadlineDescription, by);
                taskList.add(newDeadline);

                printDivider();
                System.out.println(" Ok, I added this deadline, make sure you actually do it BY THE DEADLINE!\n");
                System.out.println(" " + taskList.get(taskList.size() - 1));
                printDivider();

                saveTasks(taskList);
                break;

            case "event":
                // if there are no words after "event", print error message
                if (words.length == 1) {
                    printDivider();
                    System.out.println(" OMG your event obviously can't be empty!");
                    printDivider();
                    break;
                } else if (!line.contains("/from") && !line.contains("/to")) { // if the event doesn't contain "/from" and "/to", print error message
                    printDivider();
                    System.out.println(" You need to include the \"/from\" and \"/to\" keywords when creating an event!\n" +
                            " Do I need to spell it out for you?! Fine, I will, your event command needs to be of the form:\n" +
                            " \"event event_name /from beginning /to end\n" +
                            " {" + line + "}" + " is missing \"/from\" and \"/to\" keywords");
                    printDivider();
                    break;
                } else if (!line.contains("/from")) { // if the event doesn't contain "/from", print error message
                    printDivider();
                    System.out.println(" You need to include the \"/from\" keyword when creating an event!\n" +
                            " Do I need to spell it out for you?! Fine, I will, your event command needs to be of the form:\n" +
                            " \"event event_name /from beginning /to end\n" +
                            " {" + line + "}" + " is missing \"/from\" keyword");
                    printDivider();
                    break;
                } else if (!line.contains("/to")) { // if the event doesn't contain "/to", print error message
                    printDivider();
                    System.out.println(" You need to include the \"/to\" keyword when creating an event!\n" +
                            " Do I need to spell it out for you?! Fine, I will, your event command needs to be of the form:\n" +
                            " \"event event_name /from beginning /to end\n" +
                            " {" + line + "}" + " is missing \"/to\" keyword");
                    printDivider();
                    break;
                }

                String[] eventParts = line.split("/from", 2);
                String eventDescription = eventParts[0].substring("event".length()).trim();
                String[] timeParts = eventParts[1].split("/to", 2);
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();

                Event newEvent = new Event(eventDescription, from, to);
                taskList.add(newEvent);

                printDivider();
                System.out.println(" Ok, I added this event, make sure you actually show up ON TIME!\n");
                System.out.println(" " + taskList.get(taskList.size() - 1));
                printDivider();

                break;

            case "delete":
                try {
                    int taskIndexDelete = Integer.parseInt(words[1]) - 1;
                    Task deletedTask = taskList.get(taskIndexDelete);
                    taskList.remove(taskIndexDelete);

                    printDivider();
                    System.out.println(" OK, I've deleted this task, I hope you didn't just delete it because you don't feel like doing it!:\n   "
                            + deletedTask);
                    printDivider();
                } catch (ArrayIndexOutOfBoundsException e) {
                    printDivider();
                    System.out.println(" Come on! You need to give me a valid task to delete! The form of the command is:\n" +
                            " delete #\n" +
                            " Where # is a valid task number\n" +
                            " {" + line + "}" + " is not a valid command!");
                    printDivider();
                } catch (NumberFormatException e) {
                    printDivider();
                    System.out.println(" Come on! The task number needs to be a valid digit! Follow this format:\n" +
                            " delete #\n" +
                            " Where # is a valid task number\n" +
                            " {" + line + "}" + " is not a valid command!");
                    printDivider();
                } catch (NullPointerException e) {
                    printDivider();
                    System.out.println(" Come on! You need to give me a valid task to delete! The form of the command is:\n" +
                            " delete #\n" +
                            " Where # is a valid task number\n" +
                            " {" + line + "}" + " is not a valid command!\n");
                    printDivider();
                }

                saveTasks(taskList);
                break;

            default:
                // if the first word is not a known command, print error message
                printDivider();
                System.out.println(" What the heck are you trying to do?!\n" +
                        " Start your message with todo, deadline, or event.\n" +
                        " {" + line + "}" +
                        " doesn't start with a valid command!");
                printDivider();
                break;
            }

            line = in.nextLine();
        }

        // once recieved "bye", output farewell message
        printDivider();
        System.out.println(" Thank god! I was wondering when you'd finish! Farewell from the one and only:\n");
        System.out.println(LOGO);
        printDivider();

        saveTasks(taskList);
    }

    // make sure the data folder exists, if it does not, create it
    private static void ensureDataDirExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // load in data from ./data/dennis.txt into taskList if data exists
    private static void loadInData(ArrayList<Task> taskList) {
        File filePath = new File(DATA_FILE);

        // if there's nothing to load yet
        if (!filePath.exists()) {
            return;
        }

        try (Scanner s = new Scanner(filePath)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                Task task = null;

                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    String by = parts[3].trim();
                    task = new Deadline(description, by);
                    break;
                case "E":
                    String start = parts[3].trim();
                    String end = parts[4].trim();
                    task = new Event(description, start, end);
                    break;
                }

                if (task != null && isDone) {
                    task.markAsDone();
                }

                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

    }

    // create dennis.txt file in data folder with all the tasks
    private static void saveTasks(ArrayList<Task> list) {
        File filePath = new File(DATA_FILE);
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(createStringTaskList(list));
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // cosmetic method for printing divider between outputs
    private static void printDivider() {
        System.out.println("____________________________________________________________\n");
    }

    // method to print clean String list from Array list
    private static void printTaskList(ArrayList<Task> list) {
        String stringList = "";
        // for each element in the list, create a numbered list
        for (int index = 0; (index < list.size()) && (list.get(index) != null); index++) {
            stringList += " " + Integer.toString(index + 1) + ". " + list.get(index).toString() + "\n";
        }

        System.out.println(stringList);
    }

    // method to return clean String list from Array list
    private static String createStringTaskList(ArrayList<Task> list) {
        String stringList = "";

        for (int index = 0; index < list.size(); index++) {
            Task task = list.get(index);
            String taskLine = "";

            if (task instanceof Todo) {
                taskLine = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
            } else if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                taskLine = "D | " + (d.isDone() ? "1" : "0") + " | " + d.getDescription() + " | " + d.getBy();
            } else if (task instanceof Event) {
                Event e = (Event) task;
                taskLine = "E | " + (e.isDone() ? "1" : "0") + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
            }

            stringList += taskLine + "\n";
        }

        return stringList;
    }
}
