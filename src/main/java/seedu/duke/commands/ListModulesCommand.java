package seedu.duke.commands;

import seedu.duke.common.Messages;
import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static seedu.duke.common.Messages.MESSAGE_MODULE_TO_LIST;
import static seedu.duke.common.Messages.NEWLINE;

public class ListModulesCommand extends Command {

    @Override
    public void execute(UI ui) {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_MODULE_TO_LIST);
        for (String moduleCode : ModuleList.getModules()) {
            int counter = ModuleList.getModules().indexOf(moduleCode) + 1;
            stringBuilder.append(String.format(Messages.FORMAT_LIST_ITEMS, counter, moduleCode));
            stringBuilder.append(NEWLINE);
        }
        String listMessage = stringBuilder.toString();
        ui.printMessage(listMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
