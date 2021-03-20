package seedu.duke.commands;

import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static seedu.duke.common.Messages.MESSAGE_DUPLICATE_MODULE;
import static seedu.duke.common.Messages.MESSAGE_ADDED_MODULE;

public class AddModuleCommand extends Command {

    private final String moduleCode;

    //@@author isaharon
    public AddModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Creates new module.
     *
     * @param ui Instance of UI.
     * @throws CommandException Specified module already exists.
     */
    @Override
    public void execute(UI ui) throws CommandException {
        ModuleList moduleList = ModuleList.getInstance();
        if (moduleList.addModule(moduleCode)) {
            ui.printMessage(String.format(MESSAGE_ADDED_MODULE, moduleCode));
        } else {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_MODULE, moduleCode));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
