package seedu.duke.commands;

import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static seedu.duke.common.Messages.MESSAGE_CLOSED_MODULE;

public class ExitModuleCommand extends Command {

    //@@author 8kdesign
    /**
     * Exits from selected module.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        String moduleCode = moduleList.getSelectedModule().getModuleCode();
        moduleList.reset();
        ui.printMessage(String.format(MESSAGE_CLOSED_MODULE, moduleCode));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
