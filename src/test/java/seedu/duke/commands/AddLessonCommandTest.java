package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.exception.CommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.EXPECTED_ADD_LESSON;

class AddLessonCommandTest extends LessonCommandTest {

    //@@author H-horizon
    @Test
    void execute_ui_expectPrintsCorrectOutput() {
        ModuleList moduleList = ModuleList.getInstance();
        TestUtilAndConstants.removeFiles();
        moduleList.loadModuleNames();

        UI ui = new UI();
        moduleList.addModule(MODULE_CODE);
        moduleList.setSelectedModule(MODULE_CODE);
        Lesson newLesson = initialiseLesson(TEACHER_NAME, TEACHER_EMAIL, LessonType.TUTORIAL, TIME, ONLINE_LINK);
        Command command = new AddLessonCommand(newLesson);

        OutputStream os = getOutputStream();
        try {
            command.execute(ui);
        } catch (CommandException e) {
            printFailedToExecuteCommand();
        }
        assertEquals(EXPECTED_ADD_LESSON, os.toString());
        removeOutputStream();
    }
}