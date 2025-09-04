import java.util.Scanner;

public class Dennis {
    public static void main(String[] args) {
        final int MAX_NUM_TASKS = 100;
        final String LOGO =
                "██████╗  " + "███████╗ " + "███╗   ██╗ " + "███╗   ██╗ " + "██╗ " + "███████╗\n" +
                "██╔══██╗ " + "██╔════╝ " + "████╗  ██║ " + "████╗  ██║ " + "██║ " + "██╔════╝\n" +
                "██║  ██║ " + "█████╗   " + "██╔██╗ ██║ " + "██╔██╗ ██║ " + "██║ " + "███████╗\n" +
                "██║  ██║ " + "███╔══╝  " + "██║╚██╗██║ " + "██║╚██╗██║ " + "██║ " + "╚════██║\n" +
                "██████╔╝ " + "███████╗ " + "██║ ╚████║ " + "██║ ╚████║ " + "██║ " + "███████║\n" +
                "╚═════╝  " + "╚══════╝ " + "╚═╝  ╚═══╝ " + "╚═╝  ╚═══╝ " + "╚═╝ " + "╚══════╝\n";

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
                String stringList = arrayToString(taskList);

                printDivider();
                System.out.println(stringList);
                printDivider();
                break;

            case "mark":
                int taskIndexMark = Integer.parseInt(words[1]) - 1;
                taskList[taskIndexMark].markAsDone();

                printDivider();
                System.out.println(" Alright, i've marked this task as FINALLY completed:\n   "
                        + taskList[taskIndexMark]);
                printDivider();
                break;

            case "unmark":
                int taskIndexUnmark = Integer.parseInt(words[1]) - 1;
                taskList[taskIndexUnmark].unmarkAsNotDone();

                printDivider();
                System.out.println(" OK, I've marked this task as not done because you STILL haven't completed it:\n   "
                        + taskList[taskIndexUnmark]);
                printDivider();
                break;

            case "todo":
                String todoDescription = line.substring(line.indexOf(" ") + 1).trim();

                taskList[index] = new Todo(todoDescription);

                printDivider();
                System.out.println("Ok, I added this TODO, make sure you actually do it!\n");
                System.out.println(" " + taskList[index]);
                printDivider();

                index++;
                break;

            case "deadline":
                String[] deadlineParts = line.split("/by", 2);
                String deadlineDescription = deadlineParts[0].substring("deadline".length()).trim();
                String by = deadlineParts[1].trim();

                taskList[index] = new Deadline(deadlineDescription, by);

                printDivider();
                System.out.println("Ok, I added this deadline, make sure you actually do it BY THE DEADLINE!\n");
                System.out.println(" " + taskList[index]);
                printDivider();

                index++;
                break;

            case "event":
                String[] eventParts = line.split("/from", 2);
                String eventDescription = eventParts[0].substring("event".length()).trim();
                String[] timeParts = eventParts[1].split("/to", 2);
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();

                taskList[index] = new Event(eventDescription, from, to);

                printDivider();
                System.out.println("Ok, I added this even, make sure you actually show up ON TIME!\n");
                System.out.println(" " + taskList[index]);
                printDivider();

                index++;
                break;

            default:
                // if the first word is not a known command, treat it as a new task
                printDivider();
                System.out.println(" " + line);
                printDivider();

                taskList[index] = new Task(line);
                index++;
                break;
            }

            line = in.nextLine();
        }

        // once recieved "bye", output farewell message
        printDivider();
        System.out.println(" Thank god! I was wondering when you'd finish! Farewell from the one and only: \n");
        System.out.println(LOGO);
        printDivider();
    }

    // cosmetic method for printing divider between outputs
    private static void printDivider() {
        System.out.println("____________________________________________________________\n");
    }

    // method to create clean String list from Array list
    private static String arrayToString(Task[] list) {
        String stringList = "";
        // for each element in the list, create a numbered list
        for (int index = 0; (index < list.length) && (list[index] != null); index++) {
            stringList += " " + Integer.toString(index + 1) + ". " + list[index].toString() + "\n";
        }

        return stringList;
    }
}
