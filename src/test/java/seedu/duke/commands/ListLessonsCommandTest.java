package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListLessonsCommandTest extends LessonCommandTest {

    public static final String EXPECTED_OUTPUT = "1. tutorial - " + TIME + "\n \t" + ONLINE_LINK + "\n \t"
            + TEACHER_NAME + "\n \t" + TEACHER_EMAIL + System.lineSeparator() + "2. lab - " + TIME1 + "\n \t"
            + ONLINE_LINK1 + "\n \t" + TEACHER_NAME1 + "\n \t" + TEACHER_EMAIL1 + System.lineSeparator();

    @Test
    void printLessonsFromList_lessonList_expectPrintsCorrectOutput() {
        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        UI ui = new UI();

        OutputStream os = getOutputStream();
        ModuleList.addModule(MODULE_CODE);
        ModuleList.setSelectedModule(MODULE_CODE);
        addLessonsToList(ui);
        removeOutputStream();

        OutputStream newOs = getOutputStream();
        ListLessonsCommand.printLessonsFromList(ModuleList.getSelectedModule().getLessonList());
        assertEquals(EXPECTED_OUTPUT, newOs.toString());
        removeOutputStream();
    }
}