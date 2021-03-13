package seedu.duke.commands;

import seedu.duke.lesson.Lesson;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.CommonMethods.getLessonTypeString;
import static seedu.duke.common.Messages.FORMAT_INDEX_ITEM;
import static seedu.duke.common.Messages.MESSAGE_LESSONS_TO_DELETE;
import static seedu.duke.common.Messages.MESSAGE_REMOVED_LESSON;

/**
 * Represents the command used to delete a lesson from the list of lessons.
 */
public class DeleteLessonCommand extends Command {

    public DeleteLessonCommand() {

    }

    @Override
    public void execute(UI ui) {
        ui.printMessage(MESSAGE_LESSONS_TO_DELETE);
        Module module = ModuleList.getSelectedModule();
        ArrayList<Lesson> lessonList = module.getLessonList();
        printLessons(lessonList, ui);

        String line = ui.readCommand();
        ArrayList<Integer> indices = Parser.checkIndices(line, lessonList.size());

        deleteLessonsFromList(lessonList, indices, ui);
        ModuleList.writeModule();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints list of lessons in specified module.
     *
     * @param lessonList ArrayList of lessons in specified module.
     * @param ui         Instance of UI.
     */
    private static void printLessons(ArrayList<Lesson> lessonList, UI ui) {
        int counter = 1;
        for (Lesson lesson : lessonList) {
            String lessonType = getLessonTypeString(lesson.getLessonType());
            ui.printMessage(String.format(FORMAT_INDEX_ITEM, counter, lessonType));
            counter++;
        }
    }

    /**
     * Removes lessons corresponding to the indices from the specified module.
     *
     * @param lessonList ArrayList of lessons in specified module.
     * @param indices    Indices of lessons to delete.
     * @param ui         Instance of UI.
     */
    public static void deleteLessonsFromList(ArrayList<Lesson> lessonList, ArrayList<Integer> indices, UI ui) {
        int pointer = 1;
        for (int index : indices) {
            int modifiedIndex = index - pointer;
            Lesson lesson = lessonList.get(modifiedIndex);
            String lessonType = getLessonTypeString(lesson.getLessonType());
            ui.printMessage(String.format(MESSAGE_REMOVED_LESSON, lessonType));
            ModuleList.getSelectedModule().removeLesson(modifiedIndex);
            pointer++;
        }
    }


}
