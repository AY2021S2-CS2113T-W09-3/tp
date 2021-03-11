package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.commands.AddLessonCommand.getLessonTypeName;
import static seedu.duke.common.Messages.MESSAGE_DELETE_LESSONS;
import static seedu.duke.common.Messages.MESSAGE_LESSON_OPTIONS_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_REMOVED_LESSON_FORMAT;

public class DeleteLessonCommand extends Command {
    private final Scanner commandLineReader = new Scanner(System.in);

    public DeleteLessonCommand() {
        System.out.println(MESSAGE_DELETE_LESSONS);

    }

    public Scanner getCommandLineReader() {
        return commandLineReader;
    }

    @Override
    public void execute(UI ui) throws CommandException {
        Module module = ModuleList.getSelectedModule();
        ArrayList<Lesson> lessonList = module.getLessonList();
        printLessonOptions(lessonList);

        Scanner input = getCommandLineReader();
        String line = input.nextLine();
        ArrayList<Integer> indexes = Parser.checkIndices(line, lessonList.size());

        deleteLessonsFromList(lessonList, indexes);
        ModuleList.writeModule();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static void deleteLessonsFromList(ArrayList<Lesson> lessonList, ArrayList<Integer> indexes) {
        int pointer = 1;
        for (int index : indexes) {
            int modifiedIndex = index - pointer;
            Lesson lesson = lessonList.get(modifiedIndex);
            String lessonName = getLessonName(lesson);
            System.out.print(String.format(MESSAGE_REMOVED_LESSON_FORMAT, lessonName));
            ModuleList.getSelectedModule().deleteLessonFromList(lessonList, modifiedIndex);
            pointer++;
        }
    }

    public static String getLessonName(Lesson lesson) {
        LessonType newLessonType = lesson.getLessonType();
        return getLessonTypeName(newLessonType);
    }

    public static void printLessonOptions(ArrayList<Lesson> lessonList) {
        int counter = 1;
        for (Lesson lesson : lessonList) {
            String lessonName = getLessonName(lesson);
            System.out.printf(MESSAGE_LESSON_OPTIONS_FORMAT, counter, lessonName);
            counter++;
        }
    }
}
