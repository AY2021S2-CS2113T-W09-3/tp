package seedu.duke.commands;

import seedu.duke.lesson.Lesson;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.CommonMethods.getLessonTypeString;
import static seedu.duke.common.Messages.FORMAT_PRINT_LESSON;
import static seedu.duke.common.Messages.INDENTATION;
import static seedu.duke.common.Messages.MESSAGE_LESSONS_TO_LIST;

/**
 * Represents the command used to print the list of lessons.
 */
public class ListLessonsCommand extends Command {

    //@@author H-horizon
    /**
     * Prints list of lessons in selected module.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        Module module = moduleList.getSelectedModule();
        String moduleCode = module.getModuleCode();
        ui.printMessage(String.format(MESSAGE_LESSONS_TO_LIST, moduleCode));
        printLessons(module.getLessonList(), ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints list of lessons.
     *
     * @param lessonList ArrayList of lessons.
     * @param ui         Instance of lessons.
     */
    public static void printLessons(ArrayList<Lesson> lessonList, UI ui) {
        int counter = 1;
        for (Lesson lesson : lessonList) {
            String lessonType = getLessonTypeString(lesson.getLessonType());
            String lessonTime = lesson.getTime();
            ui.printMessage(String.format(FORMAT_PRINT_LESSON, counter, lessonType, lessonTime));
            String lessonOnlineLink = lesson.getOnlineLink();
            if (!lessonOnlineLink.isEmpty()) {
                ui.printMessage(INDENTATION + lessonOnlineLink);
            }
            String teacherName = lesson.getTeachingStaff().getName();
            if (!teacherName.isEmpty()) {
                ui.printMessage(INDENTATION + teacherName);
            }
            String teacherEmail = lesson.getTeachingStaff().getEmail();
            if (!teacherEmail.isEmpty()) {
                ui.printMessage(INDENTATION + teacherEmail);
            }
            counter++;
        }
    }
}
