package seedu.duke.commands;

import seedu.duke.common.DashboardCommands;
import seedu.duke.common.Messages;
import seedu.duke.common.ModuleCommands;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static seedu.duke.common.Messages.NEWLINE;

public class PrintHelpCommand extends Command {

    public PrintHelpCommand() {

    }

    @Override
    public void execute(UI ui) {
        if (ModuleList.getSelectedModule() == null) {
            ui.printMessage(getDashboardCommands());
        } else {
            ui.printMessage(getModuleCommands());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private static String getDashboardCommands() {
        StringBuilder stringBuilder = new StringBuilder();
        for (DashboardCommands command : DashboardCommands.values()) {
            String commandAndDescription = String.format(Messages.FORMAT_LIST_HELP,
                    command.getWord(), command.getDescription());
            stringBuilder.append(commandAndDescription).append(NEWLINE);
        }
        return stringBuilder.toString();
    }

    private static String getModuleCommands() {
        StringBuilder sb = new StringBuilder();
        for (ModuleCommands command : ModuleCommands.values()) {
            String commandAndDescription = String.format(Messages.FORMAT_LIST_HELP,
                    command.getWord(), command.getDescription());
            sb.append(commandAndDescription).append(NEWLINE);
        }
        return sb.toString();
    }
}
