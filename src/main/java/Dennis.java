import java.util.Scanner;

public class Dennis {
    private static String arrayToString(String[] list) {
        int index;
        String stringList = "";
        for (index = 0; (index < list.length) && (list[index] != null); index++) {
            stringList += " " + Integer.toString(index + 1) + ". " + list[index] + "\n";
        }

        return stringList;
    }

    public static void main(String[] args) {
        String[] arrayList = new String[100];
        int index = 0;
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________\n" + " Yooo! I'm Dennis\n" + " What do you want?! :|\n" + "____________________________________________________________\n");

        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                String stringList = arrayToString(arrayList);
                System.out.println("____________________________________________________________\n" + stringList + "____________________________________________________________\n");
            }
            else {
                System.out.println("____________________________________________________________\n" + " " + line + "\n" + "____________________________________________________________\n");
                arrayList[index] = line;
                index++;
            }
            line = in.nextLine();
        }

        System.out.println("____________________________________________________________\n" + " Bye. Thank god! I was wondering when you'd finish!\n" + "____________________________________________________________\n");
    }
}
