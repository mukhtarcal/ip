package Dennis.Storage;

import Dennis.Task.Deadline;
import Dennis.Task.Event;
import Dennis.Task.Task;
import Dennis.Task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        ensureFileExists();
    }

    // make sure storage file exists, if it doesn't then create it
    private void ensureFileExists() {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs(); // create directories if missing
            }
            if (!file.exists()) {
                file.createNewFile(); // create the file if it doesn't exist
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing storage file: " + e.getMessage(), e);
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        File file = new File(filePath);

        try (Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length < 3) {
                    return taskList;
                }

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
            return taskList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(task.toSaveFormat() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
