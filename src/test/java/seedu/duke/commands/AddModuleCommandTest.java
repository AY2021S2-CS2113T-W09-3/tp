package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_2;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_3;
import static seedu.duke.common.Messages.MESSAGE_ADDED_MODULE;

class AddModuleCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    //@@author isaharon
    @Test
    void execute_uniqueModuleCode_expectPrintSuccess() throws CommandException {
        ModuleList moduleList = ModuleList.getInstance();
        System.setOut(new PrintStream(outContent));

        TestUtilAndConstants.removeFiles();
        moduleList.loadModuleNames();

        moduleList.addModule(MODULE_CODE_2);
        moduleList.addModule(MODULE_CODE_3);

        String moduleCode = MODULE_CODE_1;
        Command command = new AddModuleCommand(moduleCode);
        command.execute(new UI());

        String output = String.format(MESSAGE_ADDED_MODULE, moduleCode);
        assertEquals(output + System.lineSeparator(), outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void execute_duplicateModuleCode_expectDuplicateModuleException() {
        ModuleList moduleList = ModuleList.getInstance();
        System.setOut(new PrintStream(outContent));
        
        TestUtilAndConstants.removeFiles();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);

        Command command = new AddModuleCommand(MODULE_CODE_1);
        assertThrows(CommandException.class, () -> command.execute(new UI()));

        System.setOut(originalOut);
    }
}