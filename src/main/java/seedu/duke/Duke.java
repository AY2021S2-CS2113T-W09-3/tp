package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.module.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

import static seedu.duke.common.Messages.DIVIDER;
import static seedu.duke.common.Messages.MESSAGE_WELCOME;
import static seedu.duke.common.Messages.NEWLINE;

public class Duke {

    private static final UI ui = new UI();

    public static void main(String[] args) {
        start();
        run();
    }

    private static void start() {
        ModuleList moduleList = ModuleList.getInstance();
        ui.printMessage(DIVIDER + NEWLINE + MESSAGE_WELCOME);
        moduleList.loadModuleNames();
    }

    private static void run() {
        boolean isExit = false;
        Parser parser = new Parser();

        while (!isExit) {
            ui.printMessage(DIVIDER);
            ui.printModuleIndicator();
            String input = ui.readCommand();
            ui.printMessage("");
            try {
                Command command = parser.parse(input);
                command.execute(ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }
}
