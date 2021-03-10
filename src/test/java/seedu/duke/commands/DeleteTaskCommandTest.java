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
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.commands.CommandTestUtil.MESSAGE_MODULE_ERROR;
import static seedu.duke.commands.CommandTestUtil.NEWLINE;
import static seedu.duke.commands.CommandTestUtil.formatter;

class DeleteTaskCommandTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    private ArrayList<Task> initialiseTaskList() {
        LocalDate deadline = LocalDate.parse("3-3-2021", formatter);
        Task task1 = new Task("weekly exercise", deadline, "");
        Task task2 = new Task("iP increments", deadline, "remember to attach JAR file");
        Task task3 = new Task("lecture quiz", deadline, "complete before next lecture");
        Task task4 = new Task("read up notes", deadline, "complete before lecture");
        ModuleList.getSelectedModule().addTaskToList(task1);
        ModuleList.getSelectedModule().addTaskToList(task2);
        ModuleList.getSelectedModule().addTaskToList(task3);
        ModuleList.getSelectedModule().addTaskToList(task4);
        return ModuleList.getSelectedModule().getTaskList();
    }

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

        ArrayList<Task> taskList = initialiseTaskList();

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand();

        try {
            deleteTaskCommand.execute(ui);
        } catch (CommandException e) {
            System.out.println(MESSAGE_MODULE_ERROR);
        }

        String output = "Which tasks would you like to delete?" + NEWLINE
                + "1. weekly exercise" + NEWLINE
                + "2. iP increments" + NEWLINE
                + "3. lecture quiz" + NEWLINE
                + "4. read up notes" + NEWLINE + NEWLINE
                + "Please enter the indices of the tasks you would like to delete." + NEWLINE
                + "Separate indices with a blank space." + NEWLINE + NEWLINE
                + "Removed weekly exercise." + NEWLINE
                + "Removed iP increments." + NEWLINE;

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
