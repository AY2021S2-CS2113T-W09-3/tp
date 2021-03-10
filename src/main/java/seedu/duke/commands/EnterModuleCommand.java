package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static java.util.Objects.requireNonNull;

public class EnterModuleCommand extends Command {

    public static final String MESSAGE_OVERVIEW = "<Placeholder for overview>";
    public static final String MESSAGE_ERROR = "%s is an invalid module code.";

    private final String moduleCode;

    public EnterModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public void execute(UI ui) throws CommandException {
        if (ModuleList.setSelectedModule(moduleCode)) {
            ui.printMessage(MESSAGE_OVERVIEW);
        } else {
            ui.printMessage(String.format(MESSAGE_ERROR, moduleCode));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
