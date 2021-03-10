package seedu.duke.storage;

import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;

import static seedu.duke.common.Messages.FILE_INSTRUCTIONS;
import static seedu.duke.storage.StorageConstants.DATE_IO_FORMAT;
import static seedu.duke.storage.StorageConstants.DIVIDER_WRITE;
import static seedu.duke.storage.StorageConstants.FOLDER_PATH;
import static seedu.duke.storage.StorageConstants.KEYWORD_LESSON;
import static seedu.duke.storage.StorageConstants.KEYWORD_TASK;
import static seedu.duke.storage.StorageConstants.TXT_FORMAT;
import static seedu.duke.storage.StorageConstants.TYPE_LAB;
import static seedu.duke.storage.StorageConstants.TYPE_LECTURE;
import static seedu.duke.storage.StorageConstants.TYPE_TUTORIAL;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Writer {


    /**
     * Creates file for new module.
     *
     * @param name Module name, excluding ".txt".
     */
    public void createFile(String name) {
        try {
            checkForDirectory();
            String fileName = name + TXT_FORMAT;
            File path = new File(FOLDER_PATH + "/" + fileName);
            if (!path.createNewFile()) {
                return;
            }
            FileWriter fileWriter = new FileWriter(path);
            writeInstructions(fileWriter, name);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            //Error creating file
        }
    }


    /**
     * Deletes specified file.
     * Returns true if deleted, or file does not exist, false if unable to delete.
     *
     * @param name Module name, excluding ".txt".
     * @return True if file is gone, false if file is still around.
     */
    public boolean deleteFile(String name) {
        String fileName = name + TXT_FORMAT;
        File path = new File(FOLDER_PATH + "/" + fileName);
        if (path.exists()) {
            return path.delete();
        }
        return true;
    }


    /**
     * Creates directory if it does not exist.
     *
     * @throws IOException Unable to create directory.
     */
    private void checkForDirectory() throws IOException {
        File directory = new File(FOLDER_PATH);
        if (directory.exists() && directory.isDirectory()) {
            return;
        }
        directory.mkdir();
    }


    /**
     * Updates changes to module in file.
     * Writes module instructions and data to module file.
     */
    public void writeModule() {
        try {
            Module module = ModuleList.getSelectedModule();
            File path = getFile(module);
            FileWriter fileWriter = new FileWriter(path);
            writeInstructions(fileWriter, module.getModuleCode());
            writeLessons(fileWriter, module);
            writeTasks(fileWriter, module);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            //Error editing file
        }
    }


    /**
     * Returns module file.
     *
     * @param module Selected module.
     * @return Module file for selected module.
     * @throws IOException Unable to create file.
     */
    private File getFile(Module module) throws IOException {
        String name = module.getModuleCode();
        String fileName = name + TXT_FORMAT;
        File path = new File(FOLDER_PATH + "/" + fileName);
        if (!path.exists()) {
            //File does not exist
            createFile(name);
        }
        return path;
    }


    /**
     * Writes instructions to module file.
     *
     * @param fileWriter FileWriter for module file.
     * @param name Module name, excluding ".txt".
     * @throws IOException Unable to write to file.
     */
    private void writeInstructions(FileWriter fileWriter, String name) throws IOException {
        fileWriter.write(name + FILE_INSTRUCTIONS);
    }


    /**
     * Writes lessons to module file.
     *
     * @param fileWriter FileWriter for module file.
     * @param module Selected module.
     * @throws IOException Unable to write to file.
     */
    private void writeLessons(FileWriter fileWriter, Module module) throws IOException {
        for (Lesson lesson : module.getLessonList()) {
            String entry = KEYWORD_LESSON;
            entry += getLessonTypeString(lesson.getLessonType()) + DIVIDER_WRITE;
            entry += lesson.getTime() + DIVIDER_WRITE;
            entry += lesson.getOnlineLink() + DIVIDER_WRITE;
            entry += lesson.getTeachingStaff().getName() + DIVIDER_WRITE;
            entry += lesson.getTeachingStaff().getEmail();
            fileWriter.write(entry + '\n');
        }
    }


    /**
     * Writes tasks to module file.
     *
     * @param fileWriter FileWriter for module file.
     * @param module Selected module.
     * @throws IOException Unable to write to file.
     */
    private void writeTasks(FileWriter fileWriter, Module module) throws IOException {
        for (Task task : module.getTaskList()) {
            String entry = KEYWORD_TASK;
            entry += task.getDescription() + DIVIDER_WRITE;
            entry += getDeadlineString(task.getDeadline()) + DIVIDER_WRITE;
            entry += getTrueFalseString(task.getDone()) + DIVIDER_WRITE;
            entry += getTrueFalseString(task.getGraded());
            if (task.getRemarks().length() > 0) {
                entry += DIVIDER_WRITE + task.getRemarks();
            }
            fileWriter.write(entry + '\n');
        }
    }


    /**
     * Returns string of lesson type.
     *
     * @param lessonType Lesson type to convert.
     * @return String of lesson type.
     */
    private String getLessonTypeString(LessonType lessonType) {
        switch (lessonType) {
        case LECTURE: {
            return TYPE_LECTURE;
        }
        case TUTORIAL: {
            return TYPE_TUTORIAL;
        }
        default: {
            return TYPE_LAB;
        }
        }
    }


    /**
     * Returns string of deadline.
     *
     * @param deadline LocalDateTime to convert.
     * @return String of deadline.
     */
    private String getDeadlineString(LocalDate deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_IO_FORMAT);
        return deadline.format(formatter);
    }


    /**
     * Converts boolean to storage format.
     *
     * @param isTrue Boolean to convert.
     * @return "T" if true, "F" if false.
     */
    private String getTrueFalseString(Boolean isTrue) {
        if (isTrue) {
            return "T";
        }
        return "F";
    }
}
