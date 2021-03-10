package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.exceptions.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnterModuleCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void execute_validModuleCodeInput_expectSuccess() throws CommandException {
        System.setOut(new PrintStream(outContent));

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");
        ModuleList.addModule("CS2102");
        ModuleList.addModule("CS2106");
        ModuleList.addModule("CS2105");

        Command command = new EnterModuleCommand("CS2106");
        command.execute(new UI());

        String output = EnterModuleCommand.MESSAGE_OVERVIEW;
        assertEquals(output + System.lineSeparator(), outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void execute_invalidModuleCodeInput_expectException() {
        System.setOut(new PrintStream(outContent));

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");
        ModuleList.addModule("CS2102");
        ModuleList.addModule("CS2106");
        ModuleList.addModule("CS2105");

        Command command = new EnterModuleCommand("CS3235");

        assertThrows(CommandException.class, () -> command.execute(new UI()));

        System.setOut(originalOut);
    }
}