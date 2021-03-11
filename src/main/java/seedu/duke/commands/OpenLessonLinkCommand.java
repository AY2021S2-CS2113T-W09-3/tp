package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.commands.DeleteLessonCommand.getLessonName;
import static seedu.duke.common.Messages.LINUX_OPEN_LINK_COMMAND;
import static seedu.duke.common.Messages.MESSAGE_CANNOT_OPEN_LESSON_LINK;
import static seedu.duke.common.Messages.MESSAGE_OPEN_LESSON_LINK;
import static seedu.duke.common.Messages.MESSAGE_OPEN_LESSON_LINK_FORMAT;

public class OpenLessonLinkCommand extends Command {
    private final Scanner commandLineReader = new Scanner(System.in);

    public OpenLessonLinkCommand() {
        System.out.println(MESSAGE_OPEN_LESSON_LINK);
    }

    public Scanner getCommandLineReader() {
        return commandLineReader;
    }

    @Override
    public void execute(UI ui) throws CommandException {
        Module module = ModuleList.getSelectedModule();
        ArrayList<Lesson> lessonList = module.getLessonList();
        DeleteLessonCommand.printLessonOptions(lessonList);
        Scanner input = getCommandLineReader();
        String line = input.nextLine();

        ArrayList<Integer> indexes = Parser.checkIndices(line, lessonList.size());
        printLessonsLink(lessonList, indexes);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static void printLessonsLink(ArrayList<Lesson> lessonList, ArrayList<Integer> indexes) {
        for (int index : indexes) {
            Lesson lesson = lessonList.get(index - 1);
            String lessonName = getLessonName(lesson);
            System.out.print(String.format(MESSAGE_OPEN_LESSON_LINK_FORMAT, lessonName));
            openLessonLink(lesson.getOnlineLink());
        }
    }

    public static void openLessonLink(String onlineLink) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(onlineLink));
            } catch (IOException | URISyntaxException e) {
                System.out.print(MESSAGE_CANNOT_OPEN_LESSON_LINK);
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(LINUX_OPEN_LINK_COMMAND + onlineLink);
            } catch (IOException e) {
                System.out.print(MESSAGE_CANNOT_OPEN_LESSON_LINK);
            }
        }
    }
}
