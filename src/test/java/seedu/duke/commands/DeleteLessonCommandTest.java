package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.OutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteLessonCommandTest extends LessonCommandTest {

    public static final String EXPECTED_OUTPUT = "Removed tutorial." + System.lineSeparator() + "Removed lab."
            + System.lineSeparator();

    @Test
    void deleteLessonsFromList_moduleLessonListIndexes_expectPrintsCorrectOutput() {

        TestUtil.removeFiles();
        ModuleList.loadModuleNames();

        UI ui = new UI();

        ModuleList.addModule(MODULE_CODE);
        ModuleList.setSelectedModule(MODULE_CODE);

        OutputStream os = getOutputStream();
        addLessonsToList(ui);
        ArrayList<Integer> indexes = new ArrayList<>();
        initialisedIndexes(indexes);
        removeOutputStream();

        OutputStream newOs = getOutputStream();
        DeleteLessonCommand.deleteLessonsFromList(ModuleList.getSelectedModule().getLessonList(), indexes);
        assertEquals(EXPECTED_OUTPUT, newOs.toString());
        removeOutputStream();
    }
}