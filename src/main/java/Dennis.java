import java.util.Scanner;

public class Dennis {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________\n" + " Hello! I'm Dennis\n" + " What do you want?! :|\n" + "____________________________________________________________\n");

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________\n" + " " + line + "\n" + "____________________________________________________________\n");
            line = in.nextLine();
        }

        System.out.println("____________________________________________________________\n" + " Bye. Hope to see you again soon!\n" + "____________________________________________________________\n");
    }
}
