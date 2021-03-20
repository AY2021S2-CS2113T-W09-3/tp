package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.common.Messages;
import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_2;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_3;
import static seedu.duke.common.Messages.MESSAGE_MODULE_TO_LIST;
import static seedu.duke.common.Messages.NEWLINE;

class ListModulesCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    //@@author isaharon
    @Test
    void execute_noInput_expectListAllModulesAdded() throws CommandException {
        System.setOut(new PrintStream(outContent));

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.addModule(MODULE_CODE_3);
        moduleList.addModule(MODULE_CODE_2);

        Command command = new ListModulesCommand();
        command.execute(new UI());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MESSAGE_MODULE_TO_LIST).append(NEWLINE);
        stringBuilder.append(String.format(Messages.FORMAT_LIST_ITEMS, 1, MODULE_CODE_1)).append(NEWLINE);
        stringBuilder.append(String.format(Messages.FORMAT_LIST_ITEMS, 2, MODULE_CODE_3)).append(NEWLINE);
        stringBuilder.append(String.format(Messages.FORMAT_LIST_ITEMS, 3, MODULE_CODE_2)).append(NEWLINE);

        assertEquals(stringBuilder.toString(), outContent.toString());

        System.setOut(originalOut);
    }
}