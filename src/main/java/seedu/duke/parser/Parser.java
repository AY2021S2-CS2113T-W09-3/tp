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
import seedu.duke.commands.ListModulesCommand;
import seedu.duke.commands.ListTasksCommand;
import seedu.duke.commands.MarkAsDoneCommand;
import seedu.duke.commands.MarkAsUndoneCommand;
import seedu.duke.commands.ModuleInfoCommand;
import seedu.duke.commands.OpenLessonLinkCommand;
import seedu.duke.commands.PrintHelpCommand;
import seedu.duke.commands.ViewTeachingStaffCommand;
import seedu.duke.common.DashboardCommands;
import seedu.duke.common.ModuleCommands;
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

import static seedu.duke.common.Constants.DELIM;
import static seedu.duke.common.Constants.ENTRY_LESSON_MAX_PARSER;
import static seedu.duke.common.Constants.ENTRY_TASK_MAX_PARSER;
import static seedu.duke.common.Constants.FORMAT_DATE_IO;
import static seedu.duke.common.Constants.FORMAT_MODULE_CODE;
import static seedu.duke.common.Constants.INDEX_DAY_TIME;
import static seedu.duke.common.Constants.INDEX_DEADLINE;
import static seedu.duke.common.Constants.INDEX_DESCRIPTION;
import static seedu.duke.common.Constants.INDEX_LINK;
import static seedu.duke.common.Constants.INDEX_REMARKS_PARSER;
import static seedu.duke.common.Constants.INDEX_TEACHER_EMAIL;
import static seedu.duke.common.Constants.INDEX_TEACHER_NAME;
import static seedu.duke.common.Constants.INDEX_TYPE;
import static seedu.duke.common.Constants.WHITESPACE;
import static seedu.duke.common.DashboardCommands.ADD;
import static seedu.duke.common.DashboardCommands.DELETE;
import static seedu.duke.common.DashboardCommands.EXIT;
import static seedu.duke.common.DashboardCommands.MODULES;
import static seedu.duke.common.DashboardCommands.OPEN;
import static seedu.duke.common.ModuleCommands.ADD_LESSON;
import static seedu.duke.common.ModuleCommands.ADD_TASK;
import static seedu.duke.common.ModuleCommands.CLOSE;
import static seedu.duke.common.ModuleCommands.DELETE_LESSON;
import static seedu.duke.common.ModuleCommands.DELETE_TASK;
import static seedu.duke.common.ModuleCommands.INFO;
import static seedu.duke.common.ModuleCommands.LESSONS;
import static seedu.duke.common.ModuleCommands.LINK;
import static seedu.duke.common.ModuleCommands.MARK;
import static seedu.duke.common.ModuleCommands.TASKS;
import static seedu.duke.common.ModuleCommands.TEACHER;
import static seedu.duke.common.ModuleCommands.UNMARK;

public class Parser {

    //@@author ivanchongzhien
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
        DashboardCommands command = DashboardCommands.fromInput(input);
        switch (command) {
        case ADD:
            String moduleCode = getModuleCode(input);
            return new AddModuleCommand(moduleCode);
        case DELETE:
            return new DeleteModuleCommand();
        case MODULES:
            return new ListModulesCommand();
        case OPEN:
            return new EnterModuleCommand(input);
        case HELP:
            return new PrintHelpCommand();
        case EXIT:
            return new ExitProgramCommand();
        default:
            throw new UnknownCommandException();
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
     * @param moduleCode string to be validated
     * @return true if string is a valid module name
     */
    private boolean isValidModuleCode(String moduleCode) {
        moduleCode = moduleCode.trim();

        // check that input matches the convention of a standard NUS module code.
        return (moduleCode.matches(FORMAT_MODULE_CODE));
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
        ModuleCommands command = ModuleCommands.fromInput(input);

        switch (command) {
        case HELP:
            return new PrintHelpCommand();
        case CLOSE:
            return new ExitModuleCommand();
        case INFO:
            return new ModuleInfoCommand();
        case LESSONS:
            return new ListLessonsCommand();
        case LINK:
            return new OpenLessonLinkCommand();
        case TASKS:
            return new ListTasksCommand();
        case MARK:
            return new MarkAsDoneCommand();
        case UNMARK:
            return new MarkAsUndoneCommand();
        case TEACHER:
            return new ViewTeachingStaffCommand();
        case ADD_LESSON:
            Lesson newLesson = parseNewLessonDetails(input);
            return new AddLessonCommand(newLesson);
        case DELETE_LESSON:
            return new DeleteLessonCommand();
        case ADD_TASK:
            Task newTask = parseNewTaskDetails(input);
            return new AddTaskCommand(newTask);
        case DELETE_TASK:
            return new DeleteTaskCommand();
        default:
            throw new UnknownCommandException();
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
        String[] allDetails = new String[ENTRY_LESSON_MAX_PARSER];
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
        String type = allDetails[INDEX_TYPE].toUpperCase();
        // TODO - throw "illegal argument exception" if enum value is invalid
        LessonType lessonType = LessonType.valueOf(type);
        String timeAndDay = allDetails[INDEX_DAY_TIME];
        String link = allDetails[INDEX_LINK];
        String teacherName = allDetails[INDEX_TEACHER_NAME];
        String email = allDetails[INDEX_TEACHER_EMAIL];

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
        String[] allDetails = new String[ENTRY_TASK_MAX_PARSER];
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
        String description = allDetails[INDEX_DESCRIPTION];
        String deadlineString = allDetails[INDEX_DEADLINE];
        LocalDate deadline = convertToDate(deadlineString);
        String remarks = allDetails[INDEX_REMARKS_PARSER];

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
        DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern(FORMAT_DATE_IO);

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
                indices.remove(i);
            }
        }
        return indices;
    }
}
