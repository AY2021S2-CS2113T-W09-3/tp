package seedu.duke.commands;

import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static seedu.duke.common.Messages.MESSAGE_CLOSED_MODULE;

public class ExitModuleCommand extends Command {

    @Override
    public void execute(UI ui) {
        String moduleCode = ModuleList.getSelectedModule().getModuleCode();
        ModuleList.reset();
        ui.printMessage(String.format(MESSAGE_CLOSED_MODULE, moduleCode));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
