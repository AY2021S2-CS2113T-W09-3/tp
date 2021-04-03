package seedu.duke.storage;

import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.lesson.TeachingStaff;
import seedu.duke.module.Module;
import seedu.duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.common.CommonMethods.writeLog;
import static seedu.duke.common.Constants.DELIM;
import static seedu.duke.common.Constants.EMPTY_STRING;
import static seedu.duke.common.Constants.ENTRY_LESSON_EXTRA_LONG;
import static seedu.duke.common.Constants.ENTRY_LESSON_LONG;
import static seedu.duke.common.Constants.ENTRY_LESSON_MEDIUM;
import static seedu.duke.common.Constants.ENTRY_SIZE_LESSON;
import static seedu.duke.common.Constants.ENTRY_SIZE_TASK;
import static seedu.duke.common.Constants.ENTRY_TASK_LONG;
import static seedu.duke.common.Constants.FOLDER_PATH;
import static seedu.duke.common.Constants.FORMAT_DATE_IO;
import static seedu.duke.common.Constants.INDEX_DAY_TIME;
import static seedu.duke.common.Constants.INDEX_DEADLINE;
import static seedu.duke.common.Constants.INDEX_DESCRIPTION;
import static seedu.duke.common.Constants.INDEX_IS_DONE;
import static seedu.duke.common.Constants.INDEX_IS_GRADED;
import static seedu.duke.common.Constants.INDEX_LINK;
import static seedu.duke.common.Constants.INDEX_REMARKS_LOADER;
import static seedu.duke.common.Constants.INDEX_TEACHER_EMAIL;
import static seedu.duke.common.Constants.INDEX_TEACHER_NAME;
import static seedu.duke.common.Constants.INDEX_TYPE;
import static seedu.duke.common.Constants.KEYWORD_LESSON;
import static seedu.duke.common.Constants.KEYWORD_TASK;
import static seedu.duke.common.Constants.STOP_LINE;
import static seedu.duke.common.Constants.TRUE_STRING;
import static seedu.duke.common.Constants.TXT_FORMAT;
import static seedu.duke.common.Messages.MESSAGE_LOAD_FAILED;

public class Loader {

    //@@author 8kdesign
    /**
     * Searches directory for module files.
     * Returns ArrayList of names (excluding ".txt").
     */
    public ArrayList<String> getModules() {
        ArrayList<String> moduleCodes = new ArrayList<>();
        File directory = new File(FOLDER_PATH);
        File[] files = directory.listFiles();
        if (files == null) {
            return moduleCodes;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                moduleCodes.add(file.getName());
            }
        }
        return moduleCodes;
    }

    /**
     * Loads data from the selected module file.
     *
     * @param moduleCode Module code, excluding ".txt".
     * @return Loaded module.
     */
    public Module loadModule(String moduleCode) {
        String fileName = moduleCode + TXT_FORMAT;
        Module module = new Module(moduleCode);
        File path = new File(FOLDER_PATH + "/" + moduleCode + "/" + fileName);
        try {
            Scanner scanner = new Scanner(path);
            readTillLine(scanner);
            readData(scanner, module);
            scanner.close();
        } catch (FileNotFoundException e) {
            //Unable to find file, return null
            writeLog(String.format(MESSAGE_LOAD_FAILED, moduleCode));
            return null;
        }
        return module;
    }

    /**
     * Skips to first line of data.
     *
     * @param scanner Scanner for module file.
     */
    private void readTillLine(Scanner scanner) {
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.startsWith(STOP_LINE)) {
                return;
            }
        }
    }

    /**
     * Identifies data type and calls methods to handle them.
     * Runs through all data.
     *
     * @param scanner Scanner for module file.
     * @param module Module to add data to.
     */
    private void readData(Scanner scanner, Module module) {
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.startsWith(KEYWORD_LESSON)) {
                readLessonData(input.substring(KEYWORD_LESSON.length()), module);
            } else if (input.startsWith(KEYWORD_TASK)) {
                readTaskData(input.substring(KEYWORD_TASK.length()), module);
            }
        }
    }

    /**
     * Reads data for lesson.
     * Adds lesson to lesson list in module.
     *
     * @param module Module to add data to.
     */
    private void readLessonData(String input, Module module) {
        String[] fields = input.split(DELIM);
        if (!ENTRY_SIZE_LESSON.contains(fields.length)) {
            //Invalid format
            return;
        }
        LessonType lessonType = LessonType.getLessonTypeFromString(fields[INDEX_TYPE].trim());
        if (lessonType == null) {
            //Invalid lesson type
            return;
        }
        String time = fields[INDEX_DAY_TIME].trim();
        String link = "";
        TeachingStaff teachingStaff = new TeachingStaff(EMPTY_STRING,EMPTY_STRING);

        switch (fields.length) {
        case ENTRY_LESSON_EXTRA_LONG:
            teachingStaff.setEmail(fields[INDEX_TEACHER_EMAIL].trim());
            // fallthrough
        case ENTRY_LESSON_LONG:
            teachingStaff.setName(fields[INDEX_TEACHER_NAME].trim());
            // fallthrough
        case ENTRY_LESSON_MEDIUM:
            link = fields[INDEX_LINK].trim();
            // fallthrough
        default:
        }
        Lesson lesson = new Lesson(lessonType, time, link, teachingStaff);
        module.addLesson(lesson);
    }

    /**
     * Reads data for task.
     * Adds task to task list in module.
     *
     * @param module Module to add data to.
     */
    private void readTaskData(String input, Module module) {
        String[] fields = input.split(DELIM);
        if (!ENTRY_SIZE_TASK.contains(fields.length)) {
            //Invalid format
            return;
        }
        String description = fields[INDEX_DESCRIPTION].trim();
        LocalDate deadline = getDeadline(fields[INDEX_DEADLINE].trim());
        if (deadline == null) {
            //Invalid deadline
            return;
        }
        boolean isDone = getTrueFalse(fields[INDEX_IS_DONE].trim());
        boolean isGraded = getTrueFalse(fields[INDEX_IS_GRADED].trim());
        String remarks = EMPTY_STRING;
        if (fields.length == ENTRY_TASK_LONG) {
            remarks = fields[INDEX_REMARKS_LOADER].trim();
        }
        Task task = new Task(description, deadline, remarks, isDone, isGraded);
        module.addTask(task);
    }

    /**
     * Returns deadline of task.
     *
     * @param input String of deadline.
     * @return Deadline in LocalDateTime.
     */
    private LocalDate getDeadline(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_IO);
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            //invalid deadline
            return null;
        }
    }

    /**
     * Returns true if "T" and false if "F".
     * Returns false if invalid.
     *
     * @param input "T" for true or "F" for false.
     * @return Boolean of input.
     */
    private boolean getTrueFalse(String input) {
        //Default is false
        return input.equalsIgnoreCase(TRUE_STRING);
    }

}
