package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;
import static seedu.duke.TestUtilAndConstants.FORMATTER;
import static seedu.duke.common.Messages.NEWLINE;


class ListTasksCommandTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    //@@author aliciatay-zls
    void initialiseTaskList(Module module) {
        LocalDate deadline1 = LocalDate.parse("26-02-2021", FORMATTER);
        LocalDate deadline2 = LocalDate.parse("3-03-2021", FORMATTER);

        Task task1 = new Task("weekly exercise", deadline1, "");
        task1.setGraded(true);
        Task task2 = new Task("iP increments", deadline2, "remember to attach JAR file");
        task2.setGraded(true);
        task2.setDone(true);
        Task task3 = new Task("lecture quiz", deadline1, "complete before next lecture");
        Task task4 = new Task("read up notes", deadline1, "complete before lecture");

        module.addTask(task1);
        module.addTask(task2);
        module.addTask(task3);
        module.addTask(task4);
    }

    @Test
    void execute_void_expectSuccess() {
        System.setOut(new PrintStream(bos));

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.setSelectedModule(MODULE_CODE_1);

        initialiseTaskList(moduleList.getSelectedModule());

        UI ui = new UI();
        LocalDate date = LocalDate.parse("26-02-2021", FORMATTER);
        
        ListTasksCommand listTasksCommand = new ListTasksCommand();
        listTasksCommand.execute(ui);

        String output = "Tasks for " + MODULE_CODE_1 + ":" + NEWLINE + NEWLINE
                + "[Undone]" + NEWLINE
                + "1. weekly exercise (graded) - 26 Feb 2021" + ui.getDaysRemainingMessage(date) + NEWLINE
                + "2. lecture quiz - 26 Feb 2021" + ui.getDaysRemainingMessage(date) + NEWLINE
                + "\t\tcomplete before next lecture" + NEWLINE
                + "3. read up notes - 26 Feb 2021" + ui.getDaysRemainingMessage(date) + NEWLINE
                + "\t\tcomplete before lecture" + NEWLINE + NEWLINE
                + "[Done]" + NEWLINE
                + "1. iP increments (graded) - 3 Mar 2021" + NEWLINE
                + "\t\tremember to attach JAR file" + NEWLINE;

        // checks displayed output to user
        assertEquals(output, bos.toString());

        System.setOut(originalOut);
    }
}
