package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

public class ExitModuleCommand extends Command {
    @Override
    public void execute(UI ui) throws CommandException {
        ModuleList.reset();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
