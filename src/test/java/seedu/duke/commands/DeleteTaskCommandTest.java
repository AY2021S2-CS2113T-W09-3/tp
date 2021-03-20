package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;
import static seedu.duke.TestUtilAndConstants.FORMATTER;
import static seedu.duke.common.Messages.NEWLINE;

class DeleteTaskCommandTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    //@@author aliciatay-zls
    private ArrayList<Task> initialiseTaskList() {
        LocalDate deadline = LocalDate.parse("3-3-2021", FORMATTER);
        Task task1 = new Task("weekly exercise", deadline, "");
        Task task2 = new Task("iP increments", deadline, "remember to attach JAR file");
        Task task3 = new Task("lecture quiz", deadline, "complete before next lecture");
        Task task4 = new Task("read up notes", deadline, "complete before lecture");
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.getSelectedModule().addTask(task1);
        moduleList.getSelectedModule().addTask(task2);
        moduleList.getSelectedModule().addTask(task3);
        moduleList.getSelectedModule().addTask(task4);
        return moduleList.getSelectedModule().getTaskList();
    }

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

        ArrayList<Task> taskList = initialiseTaskList();

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand();
        deleteTaskCommand.execute(new UI());

        String output = "Which tasks would you like to delete?" + NEWLINE
                + "1. weekly exercise" + NEWLINE
                + "2. iP increments" + NEWLINE
                + "3. lecture quiz" + NEWLINE
                + "4. read up notes" + NEWLINE + NEWLINE
                + "Please enter the indices of the tasks you would like to delete." + NEWLINE
                + "Separate indices with a blank space." + NEWLINE
                + "Removed weekly exercise from the task list." + NEWLINE
                + "Removed iP increments from the task list." + NEWLINE;

        // checks displayed output to user
        assertEquals(output, bos.toString());

        StringBuilder actualRemaining = new StringBuilder();
        for (Task task : taskList) {
            actualRemaining.append(task.getDescription()).append(NEWLINE);
        }
        String expectedRemaining = "lecture quiz" + NEWLINE
                + "read up notes" + NEWLINE;

        //checks if tasks were really deleted from task list
        assertEquals(expectedRemaining, actualRemaining.toString());

        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
