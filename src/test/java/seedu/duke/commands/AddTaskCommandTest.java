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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.commands.CommandTestUtil.MESSAGE_MODULE_ERROR;
import static seedu.duke.commands.CommandTestUtil.NEWLINE;
import static seedu.duke.commands.CommandTestUtil.formatter;

public class AddTaskCommandTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    @Test
    void execute_fullTaskInput_expectSuccess() {
        String input = "Y" + NEWLINE;
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
        System.setOut(new PrintStream(bos));
        UI ui = new UI();

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");
        ModuleList.setSelectedModule("CS2113T");

        LocalDate deadline = LocalDate.parse("3-3-2021", formatter);
        Task task = new Task("iP submission", deadline, "remember to attach JAR file");
        AddTaskCommand addTask = new AddTaskCommand(task);

        try {
            addTask.execute(ui);
        } catch (CommandException e) {
            System.out.println(MESSAGE_MODULE_ERROR);
        }

        String output = "Is this task graded? (Y / N)" + NEWLINE
                + "Added iP submission.";

        // checks displayed output to user
        assertEquals(bos.toString(), output + NEWLINE);

        // checks if task was really added to task list
        assertTrue(ModuleList.getSelectedModule().getTaskList().contains(task));

        // checks if task's graded status was set correctly
        assertEquals(true, ModuleList.getSelectedModule().getTaskList().get(0).getGraded());

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void execute_taskInputWithNoRemarks_expectSuccess() {
        String input = "Y" + NEWLINE;
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
        System.setOut(new PrintStream(bos));
        UI ui = new UI();

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");
        ModuleList.setSelectedModule("CS2113T");

        LocalDate deadline = LocalDate.parse("3-3-2021", formatter);
        // remarks field is empty
        Task task = new Task("iP submission", deadline, "");
        AddTaskCommand addTask = new AddTaskCommand(task);

        try {
            addTask.execute(ui);
        } catch (CommandException e) {
            System.out.println(MESSAGE_MODULE_ERROR);
        }

        String output = "Is this task graded? (Y / N)" + NEWLINE
                + "Added iP submission.";

        // checks displayed output to user
        assertEquals(output + NEWLINE, bos.toString());

        // checks if task was really added to task list
        assertTrue(ModuleList.getSelectedModule().getTaskList().contains(task));

        // checks if task's graded status was set correctly
        assertEquals(true, ModuleList.getSelectedModule().getTaskList().get(0).getGraded());

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void execute_taskInputAndInitiallyInvalidIsGradedInputs_expectSuccess() {
        String input = "n" + NEWLINE
                + "nooooo" + NEWLINE
                + "N" + NEWLINE;
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
        System.setOut(new PrintStream(bos));
        UI ui = new UI();

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");
        ModuleList.setSelectedModule("CS2113T");

        LocalDate deadline = LocalDate.parse("3-3-2021", formatter);
        Task task = new Task("iP submission", deadline, "remember to attach JAR file");
        AddTaskCommand addTask = new AddTaskCommand(task);

        try {
            addTask.execute(ui);
        } catch (CommandException e) {
            System.out.println(MESSAGE_MODULE_ERROR);
        }

        String output = "Is this task graded? (Y / N)" + NEWLINE
                + "Please enter \"Y\" or \"N\"" + NEWLINE
                + "Please enter \"Y\" or \"N\"" + NEWLINE
                + "Added iP submission.";

        // checks displayed output to user
        assertEquals(output + NEWLINE, bos.toString());

        // checks if task was really added to task list
        assertTrue(ModuleList.getSelectedModule().getTaskList().contains(task));

        // checks if task's graded status was set correctly
        assertEquals(false, ModuleList.getSelectedModule().getTaskList().get(0).getGraded());

        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
