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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_2;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_3;

class ExitProgramCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    //@@author isaharon
    @Test
    void execute_noInput_expectMessageExit() throws CommandException {
        System.setOut(new PrintStream(outContent));

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_3);
        moduleList.addModule(MODULE_CODE_2);

        Command command = new ExitProgramCommand();
        command.execute(new UI());

        String output = Messages.MESSAGE_EXIT;
        assertEquals(output + System.lineSeparator(), outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void isExit() {
        assertTrue(new ExitProgramCommand().isExit());
    }
}