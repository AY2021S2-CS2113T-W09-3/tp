package seedu.duke.common;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    //@@author
    //General
    public static final String FORMAT_DATE_IO = "d-M-yyyy";
    public static final String FORMAT_DATE_NORMAL = "d MMM yyyy";
    
    public static final String LINUX_OPEN_COMMAND = "xdg-open ";
    public static final int INDEX_FIRST = 0;
    
    public static final String TYPE_MODULE = "module";
    public static final String TYPE_LESSON = "lesson";
    public static final String TYPE_TASK = "task";
    
    public static final String DELIM = ";;";
    public static final String WHITESPACE = " ";
    public static final String EMPTY_STRING = "";

    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String EDIT = "edit";

    //Lecture type
    public static final String LECTURE_STRING = "lecture";
    public static final String TUTORIAL_STRING = "tutorial";
    public static final String LAB_STRING = "lab";

    //Lesson
    public static final int INDEX_TYPE = 0;
    public static final int INDEX_DAY_TIME = 1;
    public static final int INDEX_LINK = 2;
    public static final int INDEX_TEACHER_NAME = 3;
    public static final int INDEX_TEACHER_EMAIL = 4;

    public static final int ENTRY_LESSON_MAX_PARSER = 5;
    public static final int ENTRY_LESSON_SHORT = 2;
    public static final int ENTRY_LESSON_MEDIUM = 3;
    public static final int ENTRY_LESSON_LONG = 4;
    public static final int ENTRY_LESSON_EXTRA_LONG = 5;
    public static final ArrayList<Integer> ENTRY_SIZE_LESSON
            = new ArrayList<>(Arrays.asList(ENTRY_LESSON_SHORT, ENTRY_LESSON_MEDIUM,
            ENTRY_LESSON_LONG, ENTRY_LESSON_EXTRA_LONG));
    
    public static final int EDIT_INDEX_DAY_TIME = 0;
    public static final int EDIT_INDEX_LINK = 1;
    public static final int EDIT_INDEX_TEACHER_NAME = 2;
    public static final int EDIT_INDEX_TEACHER_EMAIL = 3;
    public static final int MAX_EDITABLE_FIELDS = 4;
    
    public static final String LESSON_FIELD_1_TIME_DAY = "Time and day";
    public static final String LESSON_FIELD_2_LINK = "Lesson link";
    public static final String LESSON_FIELD_3_T_NAME = "Teaching staff name";
    public static final String LESSON_FIELD_4_T_EMAIL = "Teaching staff email";

    //Task
    public static final int INDEX_DESCRIPTION = 0;
    public static final int INDEX_DEADLINE = 1;
    public static final int INDEX_REMARKS_PARSER = 2;
    public static final int INDEX_IS_DONE = 2;
    public static final int INDEX_IS_GRADED = 3;
    public static final int INDEX_REMARKS_LOADER = 4;

    public static final int ENTRY_TASK_MAX_PARSER = 3;
    public static final int ENTRY_TASK_SHORT = 4;
    public static final int ENTRY_TASK_LONG = 5;
    public static final ArrayList<Integer> ENTRY_SIZE_TASK
            = new ArrayList<>(Arrays.asList(ENTRY_TASK_SHORT, ENTRY_TASK_LONG));
    
    public static final String TASK_FIELD_DESCRIPTION = "Description";
    public static final String TASK_FIELD_DEADLINE = "Deadline";
    public static final String TASK_FIELD_REMARKS = "Remarks";
    public static final String TASK_FIELD_GRADED_STATUS = "Graded/not graded";

    public static final String YES_STRING = "Y";
    public static final String NO_STRING = "N";

    public static final String MARK = "mark as done";
    public static final String UNMARK = "mark as undone";

    //Parser
    public static final String FORMAT_MODULE_CODE = "([A-z]{2,3}[\\d]{4}[A-z]?)";
    public static final String FORMAT_EMAIL = "[A-z0-9_.-]+@[A-z.]+";
    public static final String FORMAT_LINK = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\-\\+~#=]{2,256}\\.[a-z]{2,6}"
            + "\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

    public static final String FORMAT_COMMAND_WORD_AND_ARGS = "(^\\S+)(.*)";
    public static final String FORMAT_TWO_COMMAND_WORD_AND_ARGS = "(^\\S+\\s+\\S+)(.*)";

    public static final int INDEX_COMMAND_WORD = 0;
    public static final int INDEX_COMMAND_ARGS = 1;

    //Storage
    public static final String FOLDER_PATH = "Data";
    public static final String TXT_FORMAT = ".txt";
    public static final String STOP_LINE = "------------";
    public static final String DIVIDER_WRITE = " ;; ";
    public static final String KEYWORD_LESSON = "lesson ;; ";
    public static final String KEYWORD_TASK = "task ;; ";
    public static final String TRUE_STRING = "T";
    public static final String FALSE_STRING = "F";

    //Command
    public static final String HEAD = "HEAD";

    //Logging
    public static final String LOGGER_PATH = "GULIOLog.log";
    public static final String LOGGER_NAME = "LOGGER";

    //Editor
    public static final int TEXT_EDITOR_WIDTH = 800;
    public static final int TEXT_EDITOR_HEIGHT = 800;
    public static final int TEXT_AREA_WIDTH = 700;
    public static final int TEXT_AREA_HEIGHT = 700;
    public static final String SAVE_ICON = "Save";
    public static final String TEXT_EDITOR_TITLE = "%s - Gulio Text Editor";

    //Cheat sheet
    public static final String STRING_CHEATSHEET = "Cheatsheet";
    public static final String PATH_DELIMITER = "/";
    public static final int KEYCODE_S = 83;
    public static final int KEYCODE_UP = 38;
    public static final int KEYCODE_DOWN = 40;
    public static final int BEGIN_INDEX = 0;
    public static final int EXTENSION = 4;
    public static final String DOT = ". ";
    public static final int EMPTY = 0;
    public static final int FONT_SIZE_MIN = 10;
    public static final int FONT_SIZE_MAX = 50;
}
