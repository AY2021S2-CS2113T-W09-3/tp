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

class AddModuleCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void execute_uniqueModuleCode_expectPrintSuccess() throws CommandException {
        System.setOut(new PrintStream(outContent));

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();

        ModuleList.addModule("CS2106");
        ModuleList.addModule("CS2105");

        String moduleCode = "CS2113T";
        Command command = new AddModuleCommand(moduleCode);
        command.execute(new UI());

        String output = String.format(AddModuleCommand.MESSAGE_SUCCESS, moduleCode);
        assertEquals(output + System.lineSeparator(), outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void execute_duplicateModuleCode_expectDuplicateModuleException() {
        System.setOut(new PrintStream(outContent));


        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");

        Command command = new AddModuleCommand("CS2113T");
        assertThrows(CommandException.class, () -> command.execute(new UI()));

        System.setOut(originalOut);
    }
}