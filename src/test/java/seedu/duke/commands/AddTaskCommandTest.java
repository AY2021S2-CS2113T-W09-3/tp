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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.TestUtilAndConstants.EXPECTED_ADD_TASK;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;
import static seedu.duke.TestUtilAndConstants.FORMATTER;
import static seedu.duke.common.Messages.NEWLINE;
import static seedu.duke.common.Constants.NO_STRING;
import static seedu.duke.common.Constants.YES_STRING;
import static seedu.duke.common.Messages.MESSAGE_TASK_CHECK_GRADED;
import static seedu.duke.common.Messages.MESSAGE_TASK_CHECK_GRADED_INFO;

public class AddTaskCommandTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    //@@author aliciatay-zls
    @Test
    void execute_fullTaskInput_expectSuccess() {
        String input = YES_STRING + NEWLINE;
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
        System.setOut(new PrintStream(bos));
        
        ModuleList moduleList = ModuleList.getInstance();
        TestUtilAndConstants.removeFiles();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.setSelectedModule(MODULE_CODE_1);

        LocalDate deadline = LocalDate.parse("3-3-2021", FORMATTER);
        Task task = new Task("iP submission", deadline, "remember to attach JAR file");
        AddTaskCommand addTask = new AddTaskCommand(task);

        addTask.execute(new UI());


        String output = MESSAGE_TASK_CHECK_GRADED + NEWLINE
                + EXPECTED_ADD_TASK;

        // checks displayed output to user
        assertEquals(bos.toString(), output + NEWLINE);

        // checks if task was really added to task list
        assertTrue(moduleList.getSelectedModule().getTaskList().contains(task));

        // checks if task's graded status was set correctly
        assertEquals(true, moduleList.getSelectedModule().getTaskList().get(0).getGraded());

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void execute_taskInputWithNoRemarks_expectSuccess() {
        String input = YES_STRING + NEWLINE;
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
        System.setOut(new PrintStream(bos));

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.setSelectedModule(MODULE_CODE_1);

        LocalDate deadline = LocalDate.parse("3-3-2021", FORMATTER);
        // remarks field is empty
        Task task = new Task("iP submission", deadline, "");
        AddTaskCommand addTask = new AddTaskCommand(task);

        addTask.execute(new UI());

        String output = MESSAGE_TASK_CHECK_GRADED + NEWLINE
                + EXPECTED_ADD_TASK;

        // checks displayed output to user
        assertEquals(output + NEWLINE, bos.toString());

        // checks if task was really added to task list
        assertTrue(moduleList.getSelectedModule().getTaskList().contains(task));

        // checks if task's graded status was set correctly
        assertEquals(true, moduleList.getSelectedModule().getTaskList().get(0).getGraded());

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void execute_taskInputAndInitiallyInvalidIsGradedInputs_expectSuccess() {
        String input = " " + NEWLINE
                + "no" + NEWLINE
                + NO_STRING + NEWLINE;
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
        System.setOut(new PrintStream(bos));

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.setSelectedModule(MODULE_CODE_1);

        LocalDate deadline = LocalDate.parse("3-3-2021", FORMATTER);
        Task task = new Task("iP submission", deadline, "remember to attach JAR file");
        AddTaskCommand addTask = new AddTaskCommand(task);

        addTask.execute(new UI());

        String output = MESSAGE_TASK_CHECK_GRADED + NEWLINE
                + MESSAGE_TASK_CHECK_GRADED_INFO + NEWLINE
                + MESSAGE_TASK_CHECK_GRADED_INFO + NEWLINE
                + EXPECTED_ADD_TASK;

        // checks displayed output to user
        assertEquals(output + NEWLINE, bos.toString());

        // checks if task was really added to task list
        assertTrue(moduleList.getSelectedModule().getTaskList().contains(task));

        // checks if task's graded status was set correctly
        assertEquals(false, moduleList.getSelectedModule().getTaskList().get(0).getGraded());

        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
