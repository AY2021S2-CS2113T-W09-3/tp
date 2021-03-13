package seedu.duke.commands;

import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static seedu.duke.common.Messages.MESSAGE_INVALID_MODULE;
import static seedu.duke.common.Messages.MESSAGE_OVERVIEW;

public class EnterModuleCommand extends Command {

    private final String moduleCode;

    public EnterModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public void execute(UI ui) throws CommandException {
        if (ModuleList.setSelectedModule(moduleCode)) {
            ui.printMessage(MESSAGE_OVERVIEW);
        } else {
            throw new CommandException(String.format(MESSAGE_INVALID_MODULE, moduleCode));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
