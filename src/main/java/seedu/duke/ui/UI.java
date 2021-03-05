package seedu.duke.ui;


import seedu.duke.lesson.LessonType;

import static seedu.duke.common.Messages.*;

public class UI {
    public static void printAddNewLesson(LessonType lesson) {
        System.out.println(ADDED_LESSON_MESSAGE + lesson);
    }
}
