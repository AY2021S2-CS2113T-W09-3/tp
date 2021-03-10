package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteModuleCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Test
    void execute_validModuleNumbersInput_expectSuccess() throws CommandException {
        ByteArrayInputStream inContent = new ByteArrayInputStream("1 3".getBytes());
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();

        ModuleList.addModule("CS2113T");
        ModuleList.addModule("CS2101");
        ModuleList.addModule("CS2105");
        ModuleList.addModule("CS2106");

        Command command = new DeleteModuleCommand();
        command.execute(new UI());

        StringBuilder sb = new StringBuilder();
        sb.append(DeleteModuleCommand.MESSAGE_PROMPT1);
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 1, "CS2113T")).append("\n");
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 2, "CS2101")).append("\n");
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 3, "CS2105")).append("\n");
        sb.append(String.format(Messages.FORMAT_LIST_ITEMS, 4, "CS2106")).append("\n");
        sb.append("\n");
        sb.append(DeleteModuleCommand.MESSAGE_PROMPT2);
        sb.append(System.lineSeparator());
        sb.append(String.format(DeleteModuleCommand.MESSAGE_SUCCESS, "CS2113T")).append("\n");
        sb.append(String.format(DeleteModuleCommand.MESSAGE_SUCCESS, "CS2105")).append("\n");

        assertEquals(sb.toString() + System.lineSeparator(), outContent.toString());

        System.setOut(originalOut);
    }
}