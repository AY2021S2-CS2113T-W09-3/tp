package seedu.duke.commands;

import seedu.duke.lesson.Lesson;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.Messages.FORMAT_PRINT_TEACHING_STAFF;
import static seedu.duke.common.Messages.MESSAGE_TEACHING_STAFF_TO_LIST;

/**
 * Represents the command used to print the teaching staff for a selected lesson.
 */
public class ViewTeachingStaffCommand extends Command {

    //@@author H-horizon
    /**
     * Prints list of teaching staff for selected module.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        Module module = moduleList.getSelectedModule();
        String moduleCode = module.getModuleCode();
        ui.printMessage(String.format(MESSAGE_TEACHING_STAFF_TO_LIST, moduleCode));
        ArrayList<Lesson> lessonList = module.getLessonList();
        int counter = 1;
        for (Lesson lesson : lessonList) {
            String teacherName = lesson.getTeachingStaff().getName();
            if (teacherName.length() == 0) {
                continue;
            }
            String teacherEmail = lesson.getTeachingStaff().getEmail();
            ui.printMessage(String.format(FORMAT_PRINT_TEACHING_STAFF, counter, teacherName, teacherEmail));
            counter++;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
