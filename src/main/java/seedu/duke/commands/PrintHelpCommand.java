package seedu.duke.commands;

import seedu.duke.common.CommandList;
import seedu.duke.common.DashboardCommands;
import seedu.duke.common.Messages;
import seedu.duke.common.ModuleCommands;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static seedu.duke.common.Constants.INDEX_FIRST;
import static seedu.duke.common.Messages.MESSAGE_DASHBOARD_HELP;
import static seedu.duke.common.Messages.MESSAGE_MODULE_HELP;
import static seedu.duke.common.Messages.NEWLINE;

public class PrintHelpCommand extends Command {

    //@@author isaharon
    /**
     * Prints list of commands.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        if (moduleList.getSelectedModule() == null) {
            ui.printMessage(MESSAGE_DASHBOARD_HELP);
            DashboardCommands[] commands = DashboardCommands.values();
            ui.printMessage(getCommands(commands));
        } else {
            ui.printMessage(MESSAGE_MODULE_HELP);
            ModuleCommands[] commands = ModuleCommands.values();
            ui.printMessage(getCommands(commands));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns string containing commands and their descriptions.
     *
     * @param commands Array of commands to print.
     * @return Message to print.
     */
    private static String getCommands(CommandList[] commands) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equals(DashboardCommands.INVALID) 
                    || commands[i].equals(ModuleCommands.INVALID)) {
                continue;
            } else if (i != INDEX_FIRST) {
                stringBuilder.append(NEWLINE).append(NEWLINE);
            }
            String commandWordAndArgs;
            if (commands[i].equals(DashboardCommands.OPEN)) {
                commandWordAndArgs = commands[i].getArgumentsFormat();
            } else {
                commandWordAndArgs = commands[i].getWord() + " " + commands[i].getArgumentsFormat();
            }
            String commandAndDescription = String.format(Messages.FORMAT_LIST_HELP,
                    commandWordAndArgs, commands[i].getDescription());
            stringBuilder.append(commandAndDescription);
        }
        return stringBuilder.toString();
    }
}
