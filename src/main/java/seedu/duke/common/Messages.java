package seedu.duke.common;

public class Messages {

    /*
     * General messages
     */
    public static final String MESSAGE_EXIT = "";

    /*
     * General message formats
     */
    public static final String FORMAT_LIST_HELP = "%-8s - %s";
    public static final String FORMAT_LIST_ITEMS = "%2s. %s";

    //Main
    public static final String MESSAGE_WELCOME = "";


    //Storage
    public static final String FILE_INSTRUCTIONS = " Data File\n\n"
            + "Note for editing:\n"
            + "Please follow the format strictly when adding/editing/removing lessons or tasks.\n"
            + "Do not edit anything above the line, as well as the line.\n"
            + "Please do not use '\\n' in any of the entries.\n"
            + "Please do not use '|' except for separating fields.\n\n"
            + "Choose 1 of the 4 formats for lessons:\n"
            + "1) lesson | <type> | <Day & Time>\n"
            + "2) lesson | <type> | <Day & Time> | <Link>\n"
            + "3) lesson | <type> | <Day & Time> | <Link> | <Teaching Staff Name>\n"
            + "4) lesson | <type> | <Day & Time> | <Link> | <Teaching Staff Name> | <Teaching Staff Email>\n\n"
            + "Type: \"lecture\", \"tutorial\" or \"lab\".\n"
            + "Day & time: When the lesson occurs.\n"
            + "Link: Online meeting link for the lesson.\n"
            + "Teaching staff name: Name of teaching staff for the lesson.\n"
            + "Teaching staff email: Email of teaching staff for the lesson.\n\n"
            + "Choose 1 of the 2 formats for tasks:\n"
            + "1) task | <description> | <deadline> | <is done> | <is graded>\n"
            + "2) task | <description> | <deadline> | <is done> | <is graded> | <remarks>\n\n"
            + "Description: Name/description of the task.\n"
            + "Deadline: In the format DD-MM-YYYY.\n"
            + "Is done: 'T' for true and 'F' for false.\n"
            + "Is graded: 'T' for true and 'F' for false.\n"
            + "Remarks: Additional information for task.\n\n"
            + "--------------------------------------------------------------------------------\n\n";

    //Task
    public static final String MESSAGE_TASKS_TO_DELETE = "Which tasks would you like to delete?";
    public static final String COMMAND_VERB_DELETE = "delete";

    public static final String MESSAGE_TASKS_TO_MARK = "Which undone tasks have you completed?";
    public static final String COMMAND_VERB_MARK = "mark as done";

    public static final String MESSAGE_TASKS_TO_UNMARK = "Which done tasks would you like to undo?";
    public static final String COMMAND_VERB_UNMARK = "mark as undone";

    //Lesson
    public static final String MESSAGE_ADDED_LESSON = "Added %s." + System.lineSeparator();
    public static final String LECTURE_STRING = "lecture";
    public static final String TUTORIAL_STRING = "tutorial";
    public static final String LAB_STRING = "lab";

    public static final String MESSAGE_REMOVED_LESSON_FORMAT = "Removed %s." + System.lineSeparator();
    public static final String MESSAGE_LESSON_OPTIONS_FORMAT = "%d. %s%n";
    public static final String MESSAGE_DELETE_LESSONS = "Which lessons would you like to delete?";

    public static final String MESSAGE_PRINT_LESSONS_FORMAT = "%d. %s - %s\n \t%s\n \t%s\n \t%s"
            + System.lineSeparator();
    public static final String MESSAGE_LESSONS_FOR_MODULE_FORMAT = "Lessons for %s:";

    public static final String MESSAGE_OPEN_LESSON_LINK = "Which lesson’s link would you like to open?";
    public static final String MESSAGE_OPEN_LESSON_LINK_FORMAT = "Opening %s link in browser." + System.lineSeparator();
    public static final String MESSAGE_CANNOT_OPEN_LESSON_LINK = "Cannot open lesson link" + System.lineSeparator();
    public static final String LINUX_OPEN_LINK_COMMAND = "xdg-open ";


    public static final String MESSAGE_PRINT_LESSONS_TEACHING_STAFF_FORMAT = "%d. %s - %s" + System.lineSeparator();
    public static final String MESSAGE_VIEW_TEACHING_STAFF_FOR_MODULE_FORMAT = "Teaching staff for %s:";

}
