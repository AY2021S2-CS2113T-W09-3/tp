package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.OutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.EXPECTED_DELETE_LESSON;

class DeleteLessonCommandTest extends LessonCommandTest {

    //@@author H-horizon
    @Test
    void deleteLessonsFromList_moduleLessonListIndexes_expectPrintsCorrectOutput() {

        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();

        UI ui = new UI();

        moduleList.addModule(MODULE_CODE);
        moduleList.setSelectedModule(MODULE_CODE);

        OutputStream os = getOutputStream();
        addLessonsToList(ui);
        ArrayList<Integer> indexes = new ArrayList<>();
        initialisedIndexes(indexes);
        removeOutputStream();

        OutputStream newOs = getOutputStream();
        DeleteLessonCommand.deleteLessonsFromList(moduleList.getSelectedModule().getLessonList(), indexes, ui);
        assertEquals(EXPECTED_DELETE_LESSON, newOs.toString());
        removeOutputStream();
    }
}