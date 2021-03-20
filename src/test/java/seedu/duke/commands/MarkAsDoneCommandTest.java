package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.MESSAGE_MODULE_ERROR;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;
import static seedu.duke.TestUtilAndConstants.initialiseTaskList;
import static seedu.duke.common.Messages.NEWLINE;

class MarkAsDoneCommandTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    //@@author aliciatay-zls
    @Test
    void execute_twoValidTaskIndices_expectSuccess() {
        String input = "1 2" + NEWLINE;
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
        System.setOut(new PrintStream(bos));

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.setSelectedModule(MODULE_CODE_1);

        ArrayList<Task> taskList = initialiseTaskList(moduleList.getSelectedModule());

        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand();
        markAsDoneCommand.execute(new UI());


        String output = "Which undone tasks have you completed?" + NEWLINE
                + "1. weekly exercise" + NEWLINE
                + "2. lecture quiz" + NEWLINE
                + "3. read up notes" + NEWLINE + NEWLINE
                + "Please enter the indices of the tasks you would like to mark as done." + NEWLINE
                + "Separate indices with a blank space." + NEWLINE
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
