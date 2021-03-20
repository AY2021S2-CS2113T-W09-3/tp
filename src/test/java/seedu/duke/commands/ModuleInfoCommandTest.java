package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.exception.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.EXPECTED_MODULE_OVERVIEW;
import static seedu.duke.TestUtilAndConstants.FORMATTER;

class ModuleInfoCommandTest extends LessonCommandTest {


    //@@author H-horizon
    @Test
    void execute_ui_expectPrintsCorrectOutput() {
        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        UI ui = new UI();

        OutputStream os = getOutputStream();
        moduleList.addModule(MODULE_CODE);
        moduleList.setSelectedModule(MODULE_CODE);
        addLessonsToList(ui);
        LocalDate deadline = LocalDate.parse("3-3-2021", FORMATTER);
        Task task = new Task("iP submission", deadline, "remember to attach JAR file");
        task.setGraded(true);
        ArrayList<Task> tasksList = moduleList.getSelectedModule().getTaskList();
        tasksList.add(task);
        removeOutputStream();

        OutputStream newOs = getOutputStream();
        Command command = new ModuleInfoCommand();
        try {
            command.execute(ui);
        } catch (CommandException e) {
            printFailedToExecuteCommand();
        }

        String deadlineMessage = ui.getDaysRemainingMessage(deadline);
        String expectedMessage = String.format(EXPECTED_MODULE_OVERVIEW, deadlineMessage);
        assertEquals(expectedMessage, newOs.toString());
        removeOutputStream();
    }
}