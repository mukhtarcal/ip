package Dennis.ui;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import Dennis.task.*;

public class Dennis {
    private static final String DATA_DIR = "./data";
    private static final String DATA_FILE = DATA_DIR + "/dennis.txt";
    public static void main(String[] args) throws IOException {
        final int MAX_NUM_TASKS = 100;
        final String LOGO =
                "██████╗  " + "███████╗ " + "███╗   ██╗ " + "███╗   ██╗ " + "██╗ " + "███████╗\n" +
                "██╔══██╗ " + "██╔════╝ " + "████╗  ██║ " + "████╗  ██║ " + "██║ " + "██╔════╝\n" +
                "██║  ██║ " + "█████╗   " + "██╔██╗ ██║ " + "██╔██╗ ██║ " + "██║ " + "███████╗\n" +
                "██║  ██║ " + "███╔══╝  " + "██║╚██╗██║ " + "██║╚██╗██║ " + "██║ " + "╚════██║\n" +
                "██████╔╝ " + "███████╗ " + "██║ ╚████║ " + "██║ ╚████║ " + "██║ " + "███████║\n" +
                "╚═════╝  " + "╚══════╝ " + "╚═╝  ╚═══╝ " + "╚═╝  ╚═══╝ " + "╚═╝ " + "╚══════╝\n";

        ensureDataDirExists();
        Task[] taskList = new Task[MAX_NUM_TASKS];
        int index = 0;

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
                    taskList[taskIndexMark].markAsDone();

                    printDivider();
                    System.out.println(" Alright, i've marked this task as FINALLY completed:\n   "
                            + taskList[taskIndexMark]);
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
                    taskList[taskIndexUnmark].unmarkAsNotDone();

                    printDivider();
                    System.out.println(" OK, I've marked this task as not done because you STILL haven't completed it:\n   "
                            + taskList[taskIndexUnmark]);
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

                taskList[index] = new Todo(todoDescription);

                printDivider();
                System.out.println(" Ok, I added this TODO, make sure you actually do it!\n");
                System.out.println(" " + taskList[index]);
                printDivider();

                index++;
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

                taskList[index] = new Deadline(deadlineDescription, by);

                printDivider();
                System.out.println(" Ok, I added this deadline, make sure you actually do it BY THE DEADLINE!\n");
                System.out.println(" " + taskList[index]);
                printDivider();

                index++;
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

                taskList[index] = new Event(eventDescription, from, to);

                printDivider();
                System.out.println(" Ok, I added this event, make sure you actually show up ON TIME!\n");
                System.out.println(" " + taskList[index]);
                printDivider();

                index++;
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

    // create dennis.txt file in data folder with all the tasks
    private static void saveTasks(Task[] list) throws IOException {
        File filePath = new File(DATA_FILE);
        FileWriter fw = new FileWriter(filePath);
        fw.write(createStringTaskList(list));
        fw.close();
    }

    // cosmetic method for printing divider between outputs
    private static void printDivider() {
        System.out.println("____________________________________________________________\n");
    }

    // method to print clean String list from Array list
    private static void printTaskList(Task[] list) {
        String stringList = "";
        // for each element in the list, create a numbered list
        for (int index = 0; (index < list.length) && (list[index] != null); index++) {
            stringList += " " + Integer.toString(index + 1) + ". " + list[index].toString() + "\n";
        }

        System.out.println(stringList);
    }

    // method to return clean String list from Array list
    private static String createStringTaskList(Task[] list) {
        String stringList = "";
        // for each element in the list, create a numbered list
        for (int index = 0; (index < list.length) && (list[index] != null); index++) {
            stringList += " " + Integer.toString(index + 1) + ". " + list[index].toString() + "\n";
        }

        return stringList;
    }
}
