package seedu.duke.commands;

import seedu.duke.common.DashboardCommands;
import seedu.duke.common.Messages;
import seedu.duke.common.ModuleCommands;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

public class PrintHelpCommand extends Command {

    public static final String HELP_MESSAGE = getHelpMessage();

    private static String getHelpMessage() {
        if (ModuleList.getSelectedModule() == null) {
            return getDashboardCommands();
        }
        return getModuleCommands();
    }

    private static String getDashboardCommands() {
        StringBuilder sb = new StringBuilder();
        for (DashboardCommands command : DashboardCommands.values()) {
            String commandAndDescription = String.format(Messages.FORMAT_LIST_HELP,
                    command.getWord(), command.getDescription());
            sb.append(commandAndDescription + "\n");
        }
        return sb.toString();
    }

    private static String getModuleCommands() {
        StringBuilder sb = new StringBuilder();
        for (ModuleCommands command : ModuleCommands.values()) {
            String commandAndDescription = String.format(Messages.FORMAT_LIST_HELP,
                    command.getWord(), command.getDescription());
            sb.append(commandAndDescription + "\n");
        }
        return sb.toString();
    }


    @Override
    public void execute(UI ui) {
        ui.printMessage(HELP_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
