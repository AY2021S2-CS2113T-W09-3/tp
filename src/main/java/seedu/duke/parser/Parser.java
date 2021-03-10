package seedu.duke.parser;

import seedu.duke.commands.AddLessonCommand;
import seedu.duke.commands.AddModuleCommand;
import seedu.duke.commands.AddTaskCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteLessonCommand;
import seedu.duke.commands.DeleteModuleCommand;
import seedu.duke.commands.DeleteTaskCommand;
import seedu.duke.commands.EnterModuleCommand;
import seedu.duke.commands.ExitModuleCommand;
import seedu.duke.commands.ExitProgramCommand;
import seedu.duke.commands.ListLessonsCommand;
import seedu.duke.commands.ListModuleCommand;
import seedu.duke.commands.ListTasksCommand;
import seedu.duke.commands.MarkAsDoneCommand;
import seedu.duke.commands.MarkAsUndoneCommand;
import seedu.duke.commands.OpenLessonLinkCommand;
import seedu.duke.commands.PrintHelpCommand;
import seedu.duke.commands.ViewTeachingStaffCommand;
import seedu.duke.exception.UnknownCommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.lesson.TeachingStaff;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static seedu.duke.common.ParserConstants.ADD_LESSON;
import static seedu.duke.common.ParserConstants.ADD_MODULE;
import static seedu.duke.common.ParserConstants.ADD_TASK;
import static seedu.duke.common.ParserConstants.DATE_INPUT_FORMAT;
import static seedu.duke.common.ParserConstants.DELIM;
import static seedu.duke.common.ParserConstants.DEL_LESSON;
import static seedu.duke.common.ParserConstants.DEL_MODULE;
import static seedu.duke.common.ParserConstants.DEL_TASK;
import static seedu.duke.common.ParserConstants.ENTER_MODULE;
import static seedu.duke.common.ParserConstants.EXIT_MODULE;
import static seedu.duke.common.ParserConstants.EXIT_PROGRAM;
import static seedu.duke.common.ParserConstants.LESSON_EMAIL_INDEX;
import static seedu.duke.common.ParserConstants.LESSON_LINK_INDEX;
import static seedu.duke.common.ParserConstants.LESSON_MAX_DETAILS;
import static seedu.duke.common.ParserConstants.LESSON_TEACHER_INDEX;
import static seedu.duke.common.ParserConstants.LESSON_TIME_DAY_INDEX;
import static seedu.duke.common.ParserConstants.LESSON_TYPE_INDEX;
import static seedu.duke.common.ParserConstants.LIST_LESSONS;
import static seedu.duke.common.ParserConstants.LIST_MODULE;
import static seedu.duke.common.ParserConstants.LIST_MODULE_INFO;
import static seedu.duke.common.ParserConstants.LIST_TASKS;
import static seedu.duke.common.ParserConstants.LIST_TEACHING_STAFF;
import static seedu.duke.common.ParserConstants.MARK_DONE;
import static seedu.duke.common.ParserConstants.MODULE_CODE_FORMAT;
import static seedu.duke.common.ParserConstants.OPEN_LINK;
import static seedu.duke.common.ParserConstants.PRINT_HELP;
import static seedu.duke.common.ParserConstants.PRINT_HELP_MODULE;
import static seedu.duke.common.ParserConstants.TASK_DEADLINE_INDEX;
import static seedu.duke.common.ParserConstants.TASK_DESCRIPTION_INDEX;
import static seedu.duke.common.ParserConstants.TASK_MAX_DETAILS;
import static seedu.duke.common.ParserConstants.TASK_REMARKS_INDEX;
import static seedu.duke.common.ParserConstants.UNKNOWN_COMMAND;
import static seedu.duke.common.ParserConstants.UNMARK_DONE;
import static seedu.duke.common.ParserConstants.WHITESPACE;



public class Parser {

    /**
     * Calls the appropriate parser method depending on whether user is at dashboard or has selected
     * a module.
     *
     * @param input full user input string
     * @return command object based on user input
     * @throws UnknownCommandException if valid command cannot be parsed from user input
     */
    public Command parse(String input) throws UnknownCommandException {
        Command parsedCommand;

        if (moduleIsSelected()) {
            parsedCommand = parseInModule(input);
        } else {
            parsedCommand = parseAtDashboard(input);
        }
        return parsedCommand;
    }

    /**
     * Parses dashboard commands from user input.
     * User is yet to select a module.
     *
     * @param input full user input string
     * @return dashboard command object based on user input
     * @throws UnknownCommandException if valid command cannot be parsed from user input
     */
    private Command parseAtDashboard(String input) throws UnknownCommandException {
        int commandCode = parseDashboardCommandFromInput(input);
        String moduleCode;

        switch (commandCode) {
        case ADD_MODULE:
            moduleCode = getModuleCode(input);
            return new AddModuleCommand(moduleCode);
        case DEL_MODULE:
            return new DeleteModuleCommand();
        case LIST_MODULE:
            return new ListModuleCommand();
        case ENTER_MODULE:
            return new EnterModuleCommand(input);
        case PRINT_HELP:
            return new PrintHelpCommand();
        case EXIT_PROGRAM:
            return new ExitProgramCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses user input to determine the dashboard command specified.
     *
     * @param input full user input string
     * @return an integer representing the command specified
     */
    private int parseDashboardCommandFromInput(String input) {
        if (input.equalsIgnoreCase("help")) {
            return PRINT_HELP;
        } else if (input.equalsIgnoreCase("exit")) {
            return EXIT_PROGRAM;
        } else if (input.equalsIgnoreCase("modules")) {
            return LIST_MODULE;
        } else if (startsWith(input, "add")) {
            return ADD_MODULE;
        } else if (startsWith(input, "delete")) {
            return DEL_MODULE;
        } else if (isValidModuleName(input)) {
            return ENTER_MODULE;
        } else {
            return UNKNOWN_COMMAND;
        }
    }

    /**
     * Parses module code from user input.
     *
     * @param input full user input string
     * @return module code string
     */
    private String getModuleCode(String input) {
        // TODO  - error handling
        String[] words = input.split(" ");

        return words[1];
    }

    /**
     * Checks if user is at dashboard or has already entered a module.
     *
     * @return true if user has already entered a module, false otherwise
     */
    private boolean moduleIsSelected() {
        return ModuleList.getSelectedModule() != null;
    }

    /**
     * Checks if given string is a valid module name.
     *
     * @param name string to be validated
     * @return true if string is a valid module name
     */
    private boolean isValidModuleName(String name) {
        name = name.trim();

        // check that input matches the convention of a standard NUS module code.
        return (name.matches(MODULE_CODE_FORMAT));
    }

    /**
     * Checks if user input string starts with a particular command string.
     *
     * @param input   full user input string
     * @param command command string to be checked with
     * @return true if user input starts with command string
     */
    private boolean startsWith(String input, String command) {
        return input.toUpperCase().startsWith(command.toUpperCase());
    }

    /**
     * Parses in-module commands from user input.
     * User has selected a module and is currently in the module.
     *
     * @param input full user input string
     * @return in-module command object based on user input
     * @throws UnknownCommandException if valid command cannot be parsed from user input
     */
    private Command parseInModule(String input) throws UnknownCommandException {
        int commandCode = parseInModuleCommandsFromInput(input);

        switch (commandCode) {
        case PRINT_HELP_MODULE:
            return new PrintHelpCommand();
        case EXIT_MODULE:
            return new ExitModuleCommand();
        case LIST_MODULE_INFO:
            // TODO
            return new PrintHelpCommand();
        case LIST_LESSONS:
            return new ListLessonsCommand();
        case OPEN_LINK:
            return new OpenLessonLinkCommand();
        case LIST_TASKS:
            return new ListTasksCommand();
        case MARK_DONE:
            return new MarkAsDoneCommand();
        case UNMARK_DONE:
            return new MarkAsUndoneCommand();
        case LIST_TEACHING_STAFF:
            return new ViewTeachingStaffCommand();
        case ADD_LESSON:
            Lesson newLesson = parseNewLessonDetails(input);
            return new AddLessonCommand(newLesson);
        case DEL_LESSON:
            return new DeleteLessonCommand();
        case ADD_TASK:
            Task newTask = parseNewTaskDetails(input);
            return new AddTaskCommand(newTask);
        case DEL_TASK:
            return new DeleteTaskCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses user input to determine in-module command specified.
     *
     * @param input full user input string
     * @return an integer representing the command specified
     */
    private int parseInModuleCommandsFromInput(String input) {
        if (input.equalsIgnoreCase("help")) {
            return PRINT_HELP_MODULE;
        } else if (input.equalsIgnoreCase("close")) {
            return EXIT_MODULE;
        } else if (input.equalsIgnoreCase("info")) {
            return LIST_MODULE_INFO;
        } else if (input.equalsIgnoreCase("lessons")) {
            return LIST_LESSONS;
        } else if (input.equalsIgnoreCase("link")) {
            return OPEN_LINK;
        } else if (input.equalsIgnoreCase("tasks")) {
            return LIST_TASKS;
        } else if (input.equalsIgnoreCase("mark")) {
            return MARK_DONE;
        } else if (input.equalsIgnoreCase("unmark")) {
            return UNMARK_DONE;
        } else if (input.equalsIgnoreCase("teacher")) {
            return LIST_TEACHING_STAFF;
        } else if (startsWith(input, "add lesson")) {
            return ADD_LESSON;
        } else if (startsWith(input, "delete lesson")) {
            return DEL_LESSON;
        } else if (startsWith(input, "add task")) {
            return ADD_TASK;
        } else if (startsWith(input, "delete task")) {
            return DEL_TASK;
        } else {
            return UNKNOWN_COMMAND;
        }
    }

    /**
     * Parses details of new lesson from user input string.
     *
     * @param input full user input string
     * @return a Lesson object with the details entered by the user. Fields not found will be left as an empty string.
     */
    private Lesson parseNewLessonDetails(String input) {
        // TODO - validate input

        // initialize an array of empty strings to store lesson details
        String[] allDetails = new String[LESSON_MAX_DETAILS];
        Arrays.fill(allDetails, "");

        // to remove only the first two words "add lesson"
        String[] lessonDetails = input.trim().split(" ", 3);

        // split the details field using DELIMITER to get the individual detail fields
        String[] details = lessonDetails[2].split(DELIM);

        // store detail fields that have been filled by the user into an array
        // if user did not enter that field, it will remain as an empty string
        for (int i = 0; i < details.length && i < allDetails.length; i++) {
            allDetails[i] = details[i].trim();
        }

        // Creating Lesson Object
        String type = allDetails[LESSON_TYPE_INDEX].toUpperCase();
        // TODO - throw "illegal argument exception" if enum value is invalid
        LessonType lessonType = LessonType.valueOf(type);
        String timeAndDay = allDetails[LESSON_TIME_DAY_INDEX];
        String link = allDetails[LESSON_LINK_INDEX];
        String teacherName = allDetails[LESSON_TEACHER_INDEX];
        String email = allDetails[LESSON_EMAIL_INDEX];

        TeachingStaff teacher = new TeachingStaff(teacherName, email);

        return new Lesson(lessonType, timeAndDay, link, teacher);
    }

    /**
     * Parses details of new task from user input string.
     *
     * @param input full user input string
     * @return a Task object with the details entered by the user. Fields not found will be left as an empty string.
     */
    private Task parseNewTaskDetails(String input) {

        // initialize an array of empty strings to store task details
        String[] allDetails = new String[TASK_MAX_DETAILS];
        Arrays.fill(allDetails, "");

        // to remove only the first two words "add task"
        String[] taskDetails = input.trim().split(WHITESPACE, 3);

        // split the details field using DELIMITER to get the individual detail fields
        String[] details = taskDetails[2].split(DELIM);

        // store detail fields that have been filled by the user into an array
        // if user did not enter that field, it will remain as an empty string
        for (int i = 0; i < details.length && i < allDetails.length; i++) {
            allDetails[i] = details[i].trim();
        }

        // Creating Task object
        // TODO - throw exception for invalid deadline
        String description = allDetails[TASK_DESCRIPTION_INDEX];
        String deadlineString = allDetails[TASK_DEADLINE_INDEX];
        LocalDate deadline = convertToDate(deadlineString);
        String remarks = allDetails[TASK_REMARKS_INDEX];

        return new Task(description, deadline, remarks);
    }

    /**
     * Converts given string to LocalDate object.
     *
     * @param string string to be converted
     * @return LocalDate object of the date represented by the string
     * @throws DateTimeParseException if invalid input format / invalid date given
     */
    private LocalDate convertToDate(String string) throws DateTimeParseException {
        DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT);

        return LocalDate.parse(string, parseFormat);
    }


    /**
     * Converts given input string to an arraylist of integers.
     * Removes duplicates and indices which are out of bounds.
     *
     * @param input full user input string
     * @param max   the maximum accepted index
     * @return an integer arraylist with valid indices
     * @throws NumberFormatException if non-integer value is present in the input
     */
    public static ArrayList<Integer> checkIndices(String input, int max) throws NumberFormatException {
        ArrayList<Integer> rawIndices = new ArrayList<>();
        int index;

        String[] words = input.trim().split(WHITESPACE);

        for (String word : words) {
            index = Integer.parseInt(word);
            rawIndices.add(index);
        }

        // remove duplicates
        ArrayList<Integer> indices = new ArrayList<>();

        for (int number : rawIndices) {
            if (!indices.contains(number)) {
                indices.add(number);
            }
        }

        // remove out of bounds/ invalid index
        for (int i = 0; i < indices.size(); i++) {
            if (indices.get(i) > max || indices.get(i) < 1) {
                Integer removed = indices.remove(i);
            }
        }
        return indices;
    }
}
