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
import static seedu.duke.TestUtilAndConstants.EXPECTED_ENTER_MODULE;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_2;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_3;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_4;

class EnterModuleCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    //@@author isaharon
    @Test
    void execute_validModuleCodeInput_expectSuccess() throws CommandException {
        System.setOut(new PrintStream(outContent));

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.addModule(MODULE_CODE_4);
        moduleList.addModule(MODULE_CODE_2);
        moduleList.addModule(MODULE_CODE_3);

        Command command = new EnterModuleCommand(MODULE_CODE_2);
        command.execute(new UI());

        assertEquals(EXPECTED_ENTER_MODULE, outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void execute_invalidModuleCodeInput_expectException() {
        System.setOut(new PrintStream(outContent));

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.addModule(MODULE_CODE_4);
        moduleList.addModule(MODULE_CODE_2);
        moduleList.addModule(MODULE_CODE_3);

        Command command = new EnterModuleCommand("CS3235");

        assertThrows(CommandException.class, () -> command.execute(new UI()));

        System.setOut(originalOut);
    }
}