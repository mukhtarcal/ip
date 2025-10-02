package Dennis;

import Dennis.Command.Command;
import Dennis.Parser.Parser;

import Dennis.Ui.Ui;
import Dennis.Storage.Storage;
import Dennis.TaskList.TaskList;

public class Dennis {
    private static final String DATA_FILE = "./data/dennis.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Dennis(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Something went wrong with setup: " + e.getMessage());
        }
    }

    public void run() {
        try {
            ui.showWelcome();
            boolean isExit = false;
            while (!isExit) {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }
        } catch (Exception e) {
            ui.showError("Something went wrong: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Dennis(DATA_FILE).run();
    }
}