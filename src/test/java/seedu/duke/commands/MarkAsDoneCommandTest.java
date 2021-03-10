package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.exceptions.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.commands.CommandTestUtil.MESSAGE_MODULE_ERROR;
import static seedu.duke.commands.CommandTestUtil.NEWLINE;
import static seedu.duke.commands.CommandTestUtil.initialiseTaskList;

class MarkAsDoneCommandTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    @Test
    void execute_twoValidTaskIndices_expectSuccess() {
        String input = "1 2" + NEWLINE;
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
        System.setOut(new PrintStream(bos));
        UI ui = new UI();

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");
        ModuleList.setSelectedModule("CS2113T");

        ArrayList<Task> taskList = initialiseTaskList(ModuleList.getSelectedModule());

        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand();

        try {
            markAsDoneCommand.execute(ui);
        } catch (CommandException e) {
            System.out.println(MESSAGE_MODULE_ERROR);
        }

        String output = "Which undone tasks have you completed?" + NEWLINE
                + "1. weekly exercise" + NEWLINE
                + "2. lecture quiz" + NEWLINE
                + "3. read up notes" + NEWLINE + NEWLINE
                + "Please enter the indices of the tasks you would like to mark as done." + NEWLINE
                + "Separate indices with a blank space." + NEWLINE + NEWLINE
                + "Marked weekly exercise as done." + NEWLINE
                + "Marked lecture quiz as done." + NEWLINE;

        // checks displayed output to user
        assertEquals(output, bos.toString());

        StringBuilder actualDone = new StringBuilder();
        for (Task task : taskList) {
            actualDone.append(task.getDone()).append(NEWLINE);
        }
        String expectedDone = "true" + NEWLINE
                + "true" + NEWLINE
                + "true" + NEWLINE
                + "false" + NEWLINE
                + "true" + NEWLINE
                + "true" + NEWLINE;

        // checks if tasks were correctly marked in task list
        assertEquals(expectedDone, actualDone.toString());

        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
