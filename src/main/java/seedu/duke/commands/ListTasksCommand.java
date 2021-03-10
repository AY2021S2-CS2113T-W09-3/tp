package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

public class ListTasksCommand extends Command {

    @Override
    public void execute(UI ui) throws CommandException {
        Module module = ModuleList.getSelectedModule();
        String moduleCode = module.getModuleCode();
        ui.printTasks(moduleCode, module.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
