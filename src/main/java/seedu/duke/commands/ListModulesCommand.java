package seedu.duke.commands;

import seedu.duke.common.Messages;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static seedu.duke.common.Constants.INDEX_FIRST;
import static seedu.duke.common.Messages.MESSAGE_MODULE_TO_LIST;
import static seedu.duke.common.Messages.NEWLINE;

public class ListModulesCommand extends Command {

    //@@author isaharon
    /**
     * Prints list of modules.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        ui.printMessage(MESSAGE_MODULE_TO_LIST);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < moduleList.getModules().size(); i++) {
            if (i != INDEX_FIRST) {
                stringBuilder.append(NEWLINE);
            }
            String moduleCode = moduleList.getModules().get(i);
            int counter = moduleList.getModules().indexOf(moduleCode) + 1;
            stringBuilder.append(String.format(Messages.FORMAT_LIST_ITEMS, counter, moduleCode));
        }
        String listMessage = stringBuilder.toString();
        if (!listMessage.isEmpty()) {
            ui.printMessage(listMessage);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
