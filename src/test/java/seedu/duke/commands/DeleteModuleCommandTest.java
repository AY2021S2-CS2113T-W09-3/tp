package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.common.Messages;
import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_2;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_3;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_4;
import static seedu.duke.common.Messages.MESSAGE_DELETE_MODULE_INFO;
import static seedu.duke.common.Messages.MESSAGE_MODULE_TO_DELETE;
import static seedu.duke.common.Messages.MESSAGE_REMOVED_MODULE;
import static seedu.duke.common.Messages.NEWLINE;

class DeleteModuleCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    //@@author isaharon
    @Test
    void execute_validModuleNumbersInput_expectSuccess() throws CommandException {
        ByteArrayInputStream inContent = new ByteArrayInputStream("1 3".getBytes());
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();

        moduleList.addModule(MODULE_CODE_1);
        moduleList.addModule(MODULE_CODE_4);
        moduleList.addModule(MODULE_CODE_3);
        moduleList.addModule(MODULE_CODE_2);

        Command command = new DeleteModuleCommand();
        command.execute(new UI());

        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_MODULE_TO_DELETE);
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 1, MODULE_CODE_1)).append(NEWLINE);
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 2, MODULE_CODE_4)).append(NEWLINE);
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 3, MODULE_CODE_3)).append(NEWLINE);
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 4, MODULE_CODE_2)).append(NEWLINE);
        sb.append(NEWLINE);
        sb.append(MESSAGE_DELETE_MODULE_INFO);
        sb.append(System.lineSeparator());
        sb.append(String.format(MESSAGE_REMOVED_MODULE, MODULE_CODE_1)).append(NEWLINE);
        sb.append(String.format(MESSAGE_REMOVED_MODULE, MODULE_CODE_3)).append(NEWLINE);

        assertEquals(sb.toString(), outContent.toString());

        System.setOut(originalOut);
    }
}