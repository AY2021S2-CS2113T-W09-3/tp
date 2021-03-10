package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.exceptions.CommandException;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewTeachingStaffCommandTest extends LessonCommandTest {

    public static final String EXPECTED_OUTPUT = "1. " + TEACHER_NAME + " - " + TEACHER_EMAIL + System.lineSeparator()
            + "2. " + TEACHER_NAME1 + " - " + TEACHER_EMAIL1 + System.lineSeparator();

    @Test
    void execute_moduleListUi_expectPrintsCorrectOutput() {
        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        UI ui = new UI();

        OutputStream os = getOutputStream();
        ModuleList.addModule(MODULE_CODE);
        ModuleList.setSelectedModule(MODULE_CODE);
        addLessonsToList(ui);
        Command command = new ViewTeachingStaffCommand();
        removeOutputStream();

        OutputStream newOs = getOutputStream();
        try {
            command.execute(ui);
        } catch (CommandException e) {
            printFailedToExecuteCommand();
        }
        assertEquals(EXPECTED_OUTPUT, newOs.toString());
        removeOutputStream();
    }
}