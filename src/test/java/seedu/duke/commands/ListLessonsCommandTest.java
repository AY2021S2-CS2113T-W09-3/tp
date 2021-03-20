package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.common.Messages.INDENTATION;
import static seedu.duke.common.Messages.MESSAGE_LESSONS_TO_LIST;
import static seedu.duke.common.Messages.NEWLINE;

class ListLessonsCommandTest extends LessonCommandTest {

    public static final String EXPECTED_OUTPUT = String.format(MESSAGE_LESSONS_TO_LIST, MODULE_CODE) + NEWLINE
            + "1. lab - " + TIME1 + NEWLINE + INDENTATION + ONLINE_LINK1 + NEWLINE + INDENTATION
            + TEACHER_NAME1 + NEWLINE + INDENTATION + TEACHER_EMAIL1 + NEWLINE
            + "2. tutorial - " + TIME + NEWLINE + INDENTATION + ONLINE_LINK + NEWLINE + INDENTATION
            + TEACHER_NAME + NEWLINE + INDENTATION + TEACHER_EMAIL + NEWLINE;

    //@@author H-horizon
    @Test
    void printLessonsFromList_lessonList_expectPrintsCorrectOutput() {
        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        UI ui = new UI();

        OutputStream os = getOutputStream();
        moduleList.addModule(MODULE_CODE);
        moduleList.setSelectedModule(MODULE_CODE);
        addLessonsToList(ui);
        removeOutputStream();

        OutputStream newOs = getOutputStream();
        ListLessonsCommand listLessonsCommand = new ListLessonsCommand();
        listLessonsCommand.execute(ui);
        assertEquals(EXPECTED_OUTPUT, newOs.toString());
        removeOutputStream();
    }
}