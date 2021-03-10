package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

public class AddLessonCommand extends Command {
    public static final String MESSAGE_ADDED_LESSON = "Added %s." + System.lineSeparator();
    public static final String LECTURE_STRING = "lecture";
    public static final String TUTORIAL_STRING = "tutorial";
    public static final String LAB_STRING = "lab";
    private Lesson newLessonForModule;

    public AddLessonCommand(Lesson newLesson) {
        setNewLessonForModule(newLesson);
    }

    private void setNewLessonForModule(Lesson newLesson) {
        newLessonForModule = newLesson;
    }

    private Lesson getNewLessonForModule() {
        return newLessonForModule;
    }

    @Override
    public void execute(UI ui) throws CommandException {
        Module module = ModuleList.getSelectedModule();
        module.addLessonToList(getNewLessonForModule());
        LessonType newLessonType = getNewLessonForModule().getLessonType();
        String lessonName = getLessonTypeName(newLessonType);
        System.out.print(String.format(MESSAGE_ADDED_LESSON, lessonName));
        ModuleList.writeModule();
    }

    //To be added in common class(DELETE AFTER DONE)
    public static String getLessonTypeName(LessonType lessonType) {
        switch (lessonType) {
        case LECTURE: {
            return LECTURE_STRING;
        }
        case TUTORIAL: {
            return TUTORIAL_STRING;
        }
        default: {
            return LAB_STRING;
        }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
