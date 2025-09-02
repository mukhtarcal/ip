import java.util.Scanner;

public class Dennis {
    // object for each task containing the description and whether its complete or not
    private static class Task {
        String description;
        boolean isDone;

        // create task with isDone defaulted to false
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        // method to mark task as complete
        public void mark() {
            this.isDone = true;
        }

        // method to unmark task as incomplete
        public void unmark() {
            this.isDone = false;
        }

        // method to display task
        public String toString() {
            String complete;
            if (isDone) {
                complete = "X";
            }
            else {
                complete = " ";
            }
            return "[" + complete + "] " + description;
        }
    }


    // method to create clean String list from Array list
    private static String arrayToString(Task[] list) {
        int index;
        String stringList = "";
        // for each element in the list, create a numbered list
        for (index = 0; (index < list.length) && (list[index] != null); index++) {
            stringList += " " + Integer.toString(index + 1) + ". " + list[index].toString() + "\n";
        }

        return stringList;
    }

    public static void main(String[] args) {
        Task[] arrayList = new Task[100];
        int index = 0;
        String line;
        Scanner in = new Scanner(System.in);

        // greeting message
        System.out.println("____________________________________________________________\n"
                + " Yooo! I'm Dennis\n" + " What do you want?! :|\n"
                + "____________________________________________________________\n");

        line = in.nextLine();
        // as long as the user does not say bye, take in the user input
        while (!line.equals("bye")) {
            String[] words = line.split(" ");

            // when prompted for the list, display the clean String list using the method arrayToString
            if (line.equals("list")) {
                String stringList = arrayToString(arrayList);
                System.out.println("____________________________________________________________\n"
                        + stringList + "____________________________________________________________\n");
            }
            // if told to mark the task, mark the task at the given index as complete
            else if (words[0].equals("mark")) {
                int taskIndex = Integer.valueOf(words[1]) - 1;
                arrayList[taskIndex].mark();
                System.out.println("____________________________________________________________\n"
                        + " Alright, i've marked this task as FINALLY completed:\n   " + arrayList[taskIndex].toString()
                        + "\n____________________________________________________________\n");
            }
            // if told to unmark the task, unmark the task at the given index as incomplete
            else if (words[0].equals("unmark")) {
                int taskIndex = Integer.valueOf(words[1]) - 1;
                arrayList[taskIndex].unmark();
                System.out.println("____________________________________________________________\n"
                        + " OK, I've marked this task as not done because you STILL haven't completed it:\n   " + arrayList[taskIndex]
                        + "\n____________________________________________________________\n");
            }
            // otherwise add the task and echo
            else {
                System.out.println("____________________________________________________________\n"
                        + " " + line
                        + "\n" + "____________________________________________________________\n");
                arrayList[index] = new Task(line);
                index++;
            }
            line = in.nextLine();
        }

        // once recieved "bye", output farewell message
        System.out.println("____________________________________________________________\n"
                + " Bye. Thank god! I was wondering when you'd finish!\n"
                + "____________________________________________________________\n");
    }
}
