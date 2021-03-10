package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static java.util.Objects.requireNonNull;

public class AddModuleCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Added %s to the module list.";
    public static final String MESSAGE_DUPLICATE = "Module code %s already exists in the module list";

    private final String moduleCode;

    public AddModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public void execute(UI ui) throws CommandException {
        if (ModuleList.addModule(moduleCode)) {
            ui.printMessage(String.format(MESSAGE_SUCCESS, moduleCode));
        } else {
            throw new CommandException(String.format(MESSAGE_DUPLICATE, moduleCode));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
