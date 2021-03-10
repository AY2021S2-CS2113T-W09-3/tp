package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtil;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.OutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenLessonLinkCommandTest extends LessonCommandTest {

    public static final String EXPECTED_OUTPUT = "Opening tutorial link in browser." + System.lineSeparator()
            + "Opening lab link in browser." + System.lineSeparator();

    @Test
    void printLessonsLink_lessonListIndexes_expectPrintsCorrectOutput() {
        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        UI ui = new UI();

        OutputStream os = getOutputStream();
        ModuleList.addModule(MODULE_CODE);
        ModuleList.setSelectedModule(MODULE_CODE);
        addLessonsToList(ui);
        ArrayList<Integer> indexes = new ArrayList<>();
        initialisedIndexes(indexes);
        removeOutputStream();

        OutputStream newOs = getOutputStream();
        OpenLessonLinkCommand.printLessonsLink(ModuleList.getSelectedModule().getLessonList(), indexes);
        assertEquals(EXPECTED_OUTPUT, newOs.toString());
        removeOutputStream();
    }
}