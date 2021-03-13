package seedu.duke.commands;

import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

public class ListTasksCommand extends Command {


    @Override
    public void execute(UI ui) {
        ui.printAllTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
