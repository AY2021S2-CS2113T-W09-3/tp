package seedu.duke.commands;

import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static seedu.duke.common.Messages.MESSAGE_INVALID_MODULE;
import static seedu.duke.common.Messages.MESSAGE_MODULE_OPENED;

public class EnterModuleCommand extends Command {

    private final String moduleCode;

    //@@author isaharon
    public EnterModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Opens specified module.
     *
     * @param ui Instance of UI.
     * @throws CommandException Invalid module code.
     */
    @Override
    public void execute(UI ui) throws CommandException {
        ModuleList moduleList = ModuleList.getInstance();
        if (moduleList.setSelectedModule(moduleCode)) {
            ui.printMessage(String.format(MESSAGE_MODULE_OPENED, moduleCode));
            Command command = new ModuleInfoCommand();
            command.execute(ui);
        } else {
            throw new CommandException(String.format(MESSAGE_INVALID_MODULE, moduleCode));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
