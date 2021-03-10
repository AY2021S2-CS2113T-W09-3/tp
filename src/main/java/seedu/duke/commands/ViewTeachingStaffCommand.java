package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.util.ArrayList;

public class ViewTeachingStaffCommand extends Command {

    public static final String PRINT_LESSONS_FORMAT = "%d. %s - %s" + System.lineSeparator();
    public static final String MESSAGE_VIEW_TEACHING_STAFF_FOR_MODULE = "Teaching staff for %s:";

    public ViewTeachingStaffCommand() {
        Module module = ModuleList.getSelectedModule();
        String moduleCode = module.getModuleCode();
        System.out.println(String.format(MESSAGE_VIEW_TEACHING_STAFF_FOR_MODULE, moduleCode));
    }

    @Override
    public void execute(UI ui) throws CommandException {
        Module module = ModuleList.getSelectedModule();
        ArrayList<Lesson> lessonList = module.getLessonList();
        int counter = 1;
        for (Lesson lesson : lessonList) {
            String teacherName = lesson.getTeachingStaff().getName();
            String teacherEmail = lesson.getTeachingStaff().getEmail();
            System.out.print(String.format(PRINT_LESSONS_FORMAT, counter, teacherName, teacherEmail));
            counter++;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
