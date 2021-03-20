package seedu.duke.commands;

import seedu.duke.lesson.Lesson;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;


import java.awt.Desktop;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static seedu.duke.common.CommonMethods.getLessonTypeString;
import static seedu.duke.common.Constants.HEAD;
import static seedu.duke.common.Constants.LINUX_OPEN_COMMAND;
import static seedu.duke.common.Messages.FORMAT_INDEX_ITEM;
import static seedu.duke.common.Messages.MESSAGE_INVALID_LINK_ENTERED;
import static seedu.duke.common.Messages.MESSAGE_LESSON_TO_OPEN_LINK;
import static seedu.duke.common.Messages.MESSAGE_OPENED_LESSON_LINK;
import static seedu.duke.common.Messages.MESSAGE_UNABLE_TO_OPEN_LINK;

/**
 * Represents the command used to open a lesson link.
 */
public class OpenLessonLinkCommand extends Command {

    public static final String FILE_LOGGER_NOT_WORKING = "File logger not working.";
    private static Logger logger = Logger.getLogger(OpenLessonLinkCommand.class.getName());
    private static FileHandler fileHandler;
    //@@author H-horizon

    /**
     * Sets up logger when command is created.
     */
    public OpenLessonLinkCommand() {
        LogManager.getLogManager().reset();
        try {
            fileHandler = new FileHandler(logger.getName());
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            logger.log(Level.SEVERE, FILE_LOGGER_NOT_WORKING, e);
        }
    }

    /**
     * Opens links corresponding to specified indices.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        ui.printMessage(MESSAGE_LESSON_TO_OPEN_LINK);
        Module module = moduleList.getSelectedModule();
        ArrayList<Lesson> lessonList = module.getLessonList();
        printLessons(lessonList, ui);

        String line = ui.readCommand();
        ArrayList<Integer> indices = Parser.checkIndices(line, lessonList.size());
        printLessonsLink(lessonList, indices, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints list of links opened.
     *
     * @param lessonList ArrayList of lessons in specified module.
     * @param indices    Indices of links to open.
     * @param ui         Instance of UI.
     */
    public static void printLessonsLink(ArrayList<Lesson> lessonList, ArrayList<Integer> indices, UI ui) {
        for (int index : indices) {
            Lesson lesson = lessonList.get(index - 1);
            String lessonType = getLessonTypeString(lesson.getLessonType());
            ui.printMessage(String.format(MESSAGE_OPENED_LESSON_LINK, lessonType));
            String lessonLink = lesson.getOnlineLink();
            validateLessonLink(ui, lessonLink);


        }
    }

    /**
     * Validates link before trying to open it in a browser.
     *
     * @param ui         Instance of UI.
     * @param lessonLink link to open.
     */
    private static void validateLessonLink(UI ui, String lessonLink) {
        HttpURLConnection connection;
        try {
            URL lessonUrl = new URL(lessonLink);
            connection = (HttpURLConnection) lessonUrl.openConnection();
            connection.setRequestMethod(HEAD);
            int statusCode = connection.getResponseCode();
            assert statusCode >= -1 : MESSAGE_UNABLE_TO_OPEN_LINK;
            openLessonLink(lessonLink, ui);
        } catch (IOException e) {
            ui.printMessage(MESSAGE_INVALID_LINK_ENTERED);
            logger.info(lessonLink);
        }
    }

    /**
     * Opens the specified link in browser.
     *
     * @param onlineLink Link to open.
     * @param ui         Instance of UI.
     */
    public static void openLessonLink(String onlineLink, UI ui) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(onlineLink));
            } catch (IOException | URISyntaxException e) {
                ui.printMessage(MESSAGE_UNABLE_TO_OPEN_LINK);
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(LINUX_OPEN_COMMAND + onlineLink);
            } catch (IOException e) {
                ui.printMessage(MESSAGE_UNABLE_TO_OPEN_LINK);
            }
        }
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
}
