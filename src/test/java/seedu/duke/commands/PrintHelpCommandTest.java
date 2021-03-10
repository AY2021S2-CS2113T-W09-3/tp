package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.exceptions.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintHelpCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void execute_noInput_expectAllCommandsWithDescription() throws CommandException {
        System.setOut(new PrintStream(outContent));

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2105");
        ModuleList.addModule("CS2106");

        // no module selected
        ModuleList.reset();

        // execute command
        Command command1 = new PrintHelpCommand();
        command1.execute(new UI());

        String output = PrintHelpCommand.HELP_MESSAGE;
        assertEquals(output + System.lineSeparator(), outContent.toString());

        // module selected
        ModuleList.setSelectedModule("CS2105");
        Command command2 = new PrintHelpCommand();
        command2.execute(new UI());

        System.setOut(originalOut);
    }
}