package seedu.duke.commands;

import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;


import static seedu.duke.common.CommonMethods.getLessonTypeString;
import static seedu.duke.common.Messages.MESSAGE_ADDED_LESSON;

/**
 * Represents the command used to add a new lesson to the list of lessons.
 */
public class AddLessonCommand extends Command {

    private final Lesson lesson;

    //@@author H-horizon
    public AddLessonCommand(Lesson lesson) {
        this.lesson = lesson;
    }

    /**
     * Adds new lesson to selected module.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        Module module = moduleList.getSelectedModule();
        module.addLesson(lesson);
        LessonType lessonType = lesson.getLessonType();
        String lessonTypeString = getLessonTypeString(lessonType);
        ui.printMessage(String.format(MESSAGE_ADDED_LESSON, lessonTypeString));
        moduleList.writeModule();
        moduleList.sortLessons();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
