package seedu.duke.commands;

import seedu.duke.lesson.Lesson;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.CommonMethods.getLessonTypeString;
import static seedu.duke.common.Messages.FORMAT_PRINT_LESSON;
import static seedu.duke.common.Messages.MESSAGE_LESSONS_TO_LIST;

/**
 * Represents the command used to print the list of lessons.
 */
public class ListLessonsCommand extends Command {

    public ListLessonsCommand() {

    }

    @Override
    public void execute(UI ui) {
        Module module = ModuleList.getSelectedModule();
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
    private static void printLessons(ArrayList<Lesson> lessonList, UI ui) {
        int counter = 1;
        for (Lesson lesson : lessonList) {
            String lessonType = getLessonTypeString(lesson.getLessonType());
            String lessonTime = lesson.getTime();
            String lessonOnlineLink = lesson.getOnlineLink();
            String teacherName = lesson.getTeachingStaff().getName();
            String teacherEmail = lesson.getTeachingStaff().getEmail();
            ui.printMessage(String.format(FORMAT_PRINT_LESSON, counter, lessonType, lessonTime,
                    lessonOnlineLink, teacherName, teacherEmail));
            counter++;
        }
    }
}
