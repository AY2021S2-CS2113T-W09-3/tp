package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.commands.DeleteLessonCommand.getLessonName;

public class ListLessonsCommand extends Command {
    public static final String PRINT_LESSONS_FORMAT = "%d. %s - %s\n \t%s\n \t%s\n \t%s" + System.lineSeparator();
    public static final String MESSAGE_LESSONS_FOR_MODULE = "Lessons for %s:";

    public ListLessonsCommand() {
        Module module = ModuleList.getSelectedModule();
        String moduleCode = module.getModuleCode();
        System.out.println(String.format(MESSAGE_LESSONS_FOR_MODULE, moduleCode));
    }

    @Override
    public void execute(UI ui) throws CommandException {
        Module module = ModuleList.getSelectedModule();
        ArrayList<Lesson> lessonList = module.getLessonList();
        printLessonsFromList(lessonList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static void printLessonsFromList(ArrayList<Lesson> lessonList) {
        int counter = 1;
        for (Lesson lesson : lessonList) {
            String lessonName = getLessonName(lesson);
            String lessonTime = lesson.getTime();
            String lessonOnlineLink = lesson.getOnlineLink();
            String teacherName = lesson.getTeachingStaff().getName();
            String teacherEmail = lesson.getTeachingStaff().getEmail();
            System.out.print(String.format(PRINT_LESSONS_FORMAT, counter, lessonName, lessonTime, lessonOnlineLink,
                    teacherName, teacherEmail));
            counter++;
        }
    }
}
