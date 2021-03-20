package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.common.DashboardCommands;
import seedu.duke.common.Messages;
import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_2;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_3;
import static seedu.duke.common.Messages.MESSAGE_DASHBOARD_HELP;
import static seedu.duke.common.Messages.MESSAGE_MODULE_HELP;
import static seedu.duke.common.Messages.NEWLINE;

class PrintHelpCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    //@@author isaharon
    @Test
    void execute_noInput_expectAllCommandsWithDescription() throws CommandException {
        System.setOut(new PrintStream(outContent));

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_3);
        moduleList.addModule(MODULE_CODE_2);

        // no module selected
        moduleList.reset();

        // execute command
        PrintHelpCommand printHelpCommand = new PrintHelpCommand();
        printHelpCommand.execute(new UI());

        StringBuilder stringBuilder = new StringBuilder();

        if (moduleList.getSelectedModule() == null) {
            stringBuilder.append(MESSAGE_DASHBOARD_HELP).append(NEWLINE);
        } else {
            stringBuilder.append(MESSAGE_MODULE_HELP).append(NEWLINE);
        }
        for (DashboardCommands command : DashboardCommands.values()) {
            if (command.equals(DashboardCommands.INVALID)) {
                continue;
            }
            String commandWordAndArgs;
            if (command.equals(DashboardCommands.OPEN)) {
                commandWordAndArgs = command.getArgumentsFormat();
            } else {
                commandWordAndArgs = command.getWord() + " " + command.getArgumentsFormat();
            }
            String commandAndDescription = String.format(Messages.FORMAT_LIST_HELP,
                    commandWordAndArgs, command.getDescription());
            stringBuilder.append(commandAndDescription).append(NEWLINE).append(NEWLINE);
        }
        String output = stringBuilder.toString();
        assertEquals(output, outContent.toString() + NEWLINE);

        // module selected
        moduleList.setSelectedModule(MODULE_CODE_3);
        printHelpCommand = new PrintHelpCommand();
        printHelpCommand.execute(new UI());

        System.setOut(originalOut);
    }
}