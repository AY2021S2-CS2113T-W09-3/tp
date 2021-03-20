package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.TestUtilAndConstants;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.lesson.TeachingStaff;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_4;

class WriterTest {

    //@@author 8kdesign
    @Test
    void writeModule_noContentNoDirectory_instructionOnly() throws IOException {
        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.setSelectedModule(MODULE_CODE_1);
        Writer writer = new Writer();
        writer.writeModule();
        Path reference = Paths.get("src/test/java/seedu/duke/storage/reference/empty_reference.txt");
        Path actual = Paths.get("Data/CS2113T.txt");
        assertEquals(Files.readAllLines(reference), Files.readAllLines(actual));
    }

    @Test
    void writeModule_twoTask_instructionAndTask() throws IOException {
        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.setSelectedModule(MODULE_CODE_1);
        Task task1 = new Task("Task1", LocalDate.of(2020,2,20), "Task1Remarks",
                false,false);
        Task task2 = new Task("Task2", LocalDate.of(2021,12,12),
                "", true,true);
        moduleList.getSelectedModule().addTask(task1);
        moduleList.getSelectedModule().addTask(task2);
        Writer writer = new Writer();
        writer.writeModule();
        Path reference = Paths.get("src/test/java/seedu/duke/storage/reference/task_only_reference.txt");
        Path actual = Paths.get("Data/CS2113T.txt");
        assertEquals(Files.readAllLines(reference), Files.readAllLines(actual));
    }

    @Test
    void writeModule_twoLesson_instructionAndLesson() throws IOException {
        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.setSelectedModule(MODULE_CODE_1);
        Lesson lesson1 = new Lesson(LessonType.LECTURE, "Friday 2pm", "test.com",
                new TeachingStaff("",""));
        Lesson lesson2 = new Lesson(LessonType.LECTURE, "Weekdays", "",
                new TeachingStaff("Name2","Email2"));
        moduleList.getSelectedModule().addLesson(lesson1);
        moduleList.getSelectedModule().addLesson(lesson2);
        Writer writer = new Writer();
        writer.writeModule();
        Path reference = Paths.get("src/test/java/seedu/duke/storage/reference/lesson_only_reference.txt");
        Path actual = Paths.get("Data/CS2113T.txt");
        assertEquals(Files.readAllLines(reference), Files.readAllLines(actual));
    }

    @Test
    void writeModule_twoLessonTwoTask_allContent() throws IOException {
        TestUtilAndConstants.removeFiles();
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.loadModuleNames();
        moduleList.addModule(MODULE_CODE_1);
        moduleList.setSelectedModule(MODULE_CODE_1);
        Lesson lesson1 = new Lesson(LessonType.LECTURE, "Friday 2pm", "test.com",
                new TeachingStaff("",""));
        Lesson lesson2 = new Lesson(LessonType.LECTURE, "Weekdays", "",
                new TeachingStaff("Name2","Email2"));
        moduleList.getSelectedModule().addLesson(lesson1);
        moduleList.getSelectedModule().addLesson(lesson2);
        Task task1 = new Task("Task1", LocalDate.of(2020,2,20), 
                "Task1Remarks", false,false);
        Task task2 = new Task("Task2", LocalDate.of(2021,12,12), 
                "", true,true);
        moduleList.getSelectedModule().addTask(task1);
        moduleList.getSelectedModule().addTask(task2);
        Writer writer = new Writer();
        writer.writeModule();
        Path reference = Paths.get("src/test/java/seedu/duke/storage/reference/all_content_reference.txt");
        Path actual = Paths.get("Data/CS2113T.txt");
        assertEquals(Files.readAllLines(reference), Files.readAllLines(actual));
    }
}