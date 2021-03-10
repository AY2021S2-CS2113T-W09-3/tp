package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.exceptions.CommandException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.commands.CommandTestUtil.MESSAGE_MODULE_ERROR;
import static seedu.duke.commands.CommandTestUtil.NEWLINE;
import static seedu.duke.commands.CommandTestUtil.formatter;

class ListTasksCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    void initialiseTaskList(Module module) {
        LocalDate deadline1 = LocalDate.parse("26-02-2021", formatter);
        LocalDate deadline2 = LocalDate.parse("3-03-2021", formatter);

        Task task1 = new Task("weekly exercise", deadline1, "");
        task1.setGraded(true);
        Task task2 = new Task("iP increments", deadline2, "remember to attach JAR file");
        task2.setGraded(true);
        task2.setDone(true);
        Task task3 = new Task("lecture quiz", deadline1, "complete before next lecture");
        Task task4 = new Task("read up notes", deadline1, "complete before lecture");

        module.addTaskToList(task1);
        module.addTaskToList(task2);
        module.addTaskToList(task3);
        module.addTaskToList(task4);
    }

    @Test
    void execute_void_expectSuccess() {
        System.setOut(new PrintStream(bos));
        UI ui = new UI();

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");
        ModuleList.setSelectedModule("CS2113T");

        initialiseTaskList(ModuleList.getSelectedModule());

        ListTasksCommand listTasksCommand = new ListTasksCommand();

        try {
            listTasksCommand.execute(ui);
        } catch (CommandException e) {
            System.out.println(MESSAGE_MODULE_ERROR);
        }

        String output = "Tasks for CS2113T:" + NEWLINE
                + "[Undone]" + NEWLINE
                + "1. weekly exercise (graded) - 26 Feb 2021" + NEWLINE
                + "2. lecture quiz - 26 Feb 2021" + NEWLINE
                + "\tcomplete before next lecture" + NEWLINE
                + "3. read up notes - 26 Feb 2021" + NEWLINE
                + "\tcomplete before lecture" + NEWLINE
                + "[Done]" + NEWLINE
                + "1. iP increments (graded) - 3 Mar 2021" + NEWLINE
                + "\tremember to attach JAR file" + NEWLINE;

        // checks displayed output to user
        assertEquals(output, bos.toString());

        System.setOut(originalOut);
    }
}
