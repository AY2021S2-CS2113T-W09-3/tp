package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

public abstract class Command {

    public abstract void execute(UI ui) throws CommandException;

    public abstract boolean isExit();

}
