package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListModuleCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void execute_noInput_expectListAllModulesAdded() throws CommandException {
        System.setOut(new PrintStream(outContent));

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");
        ModuleList.addModule("CS2105");
        ModuleList.addModule("CS2106");

        Command command = new ListModuleCommand();
        command.execute(new UI());

        StringBuilder sb = new StringBuilder();
        sb.append(ListModuleCommand.MESSAGE_INFO);
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 1, "CS2113T")).append("\n");
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 2, "CS2105")).append("\n");
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 3, "CS2106")).append("\n");

        assertEquals(sb.toString() + System.lineSeparator(), outContent.toString());

        System.setOut(originalOut);
    }
}