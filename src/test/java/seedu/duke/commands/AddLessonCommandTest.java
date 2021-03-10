package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.exceptions.CommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddLessonCommandTest extends LessonCommandTest {

    public static final String EXPECTED_OUTPUT = "Added tutorial." + System.lineSeparator();

    @Test
    void execute_moduleListUi_expectPrintsCorrectOutput() {
        TestUtil.removeFiles();
        ModuleList.loadModuleNames();

        UI ui = new UI();
        ModuleList.addModule(MODULE_CODE);
        ModuleList.setSelectedModule(MODULE_CODE);
        Lesson newLesson = initialiseLesson(TEACHER_NAME, TEACHER_EMAIL, LessonType.TUTORIAL, TIME, ONLINE_LINK);
        Command command = new AddLessonCommand(newLesson);
        OutputStream os = getOutputStream();

        try {
            command.execute(ui);
        } catch (CommandException e) {
            printFailedToExecuteCommand();
        }
        assertEquals(EXPECTED_OUTPUT, os.toString());
        removeOutputStream();
    }
}