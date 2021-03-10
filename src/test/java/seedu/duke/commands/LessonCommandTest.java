package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.lesson.TeachingStaff;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class LessonCommandTest {

    public static final String MODULE_CODE = "CS3235";
    public static final String TEACHER_NAME = "Ivan Chong Zhi En";
    public static final String TEACHER_EMAIL = "ivanchong@zhi.en";
    public static final String TIME = "Wednesday 9 am - 10am";
    public static final String ONLINE_LINK = "https://youtu.be/dQw4w9WgXcQ";
    public static final String TEACHER_NAME1 = "Isa Bin Haron";
    public static final String TEACHER_EMAIL1 = "isa@bin.haron";
    public static final String TIME1 = "Wednesday 9 pm - 10 pm";
    public static final String ONLINE_LINK1 = "https://youtu.be/YnopHCL1Jk8";
    public static final int FIRST_INDEX = 1;
    public static final int SECOND_INDEX = 2;
    public static final String COMMAND_FAILED = "Command failed";

    public static void removeOutputStream() {
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    public static OutputStream getOutputStream() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        return os;
    }

    public static Lesson initialiseLesson(String teacherName, String teacherEmail, LessonType lessonType, String time,
                                          String onlineLink) {
        TeachingStaff teachingStaff = new TeachingStaff(teacherName, teacherEmail);
        teachingStaff.setName(teacherName);
        teachingStaff.setEmail(teacherEmail);
        Lesson newLesson = new Lesson(lessonType, time, onlineLink, teachingStaff);
        newLesson.setTime(time);
        newLesson.setTeachingStaff(teachingStaff);
        newLesson.setOnlineLink(onlineLink);
        return newLesson;
    }

    public static void addLessonsToList(UI ui) {
        Lesson newLesson = initialiseLesson(TEACHER_NAME, TEACHER_EMAIL, LessonType.TUTORIAL, TIME, ONLINE_LINK);
        Command command = new AddLessonCommand(newLesson);
        try {
            command.execute(ui);
        } catch (CommandException e) {
            printFailedToExecuteCommand();
        }

        newLesson = initialiseLesson(TEACHER_NAME1, TEACHER_EMAIL1, LessonType.LAB, TIME1, ONLINE_LINK1);
        command = new AddLessonCommand(newLesson);
        try {
            command.execute(ui);
        } catch (CommandException e) {
            printFailedToExecuteCommand();
        }
    }

    public static void printFailedToExecuteCommand() {
        System.out.println(COMMAND_FAILED);
    }

    public static void initialisedIndexes(ArrayList<Integer> indexes) {
        indexes.add(FIRST_INDEX);
        indexes.add(SECOND_INDEX);
    }
}
