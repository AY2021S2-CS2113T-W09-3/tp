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
import seedu.duke.exception.CommandException;
import seedu.duke.exception.UnknownCommandException;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.lesson.TeachingStaff;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static seedu.duke.common.Constants.DELIM;
import static seedu.duke.common.Constants.ENTRY_LESSON_MAX_PARSER;
import static seedu.duke.common.Constants.ENTRY_TASK_MAX_PARSER;
import static seedu.duke.common.Constants.FORMAT_DATE_IO;
import static seedu.duke.common.Constants.FORMAT_EMAIL;
import static seedu.duke.common.Constants.FORMAT_LINK;
import static seedu.duke.common.Constants.FORMAT_MODULE_CODE;
import static seedu.duke.common.Constants.INDEX_DAY_TIME;
import static seedu.duke.common.Constants.INDEX_DEADLINE;
import static seedu.duke.common.Constants.INDEX_DESCRIPTION;
import static seedu.duke.common.Constants.INDEX_LINK;
import static seedu.duke.common.Constants.INDEX_REMARKS_PARSER;
import static seedu.duke.common.Constants.INDEX_TEACHER_EMAIL;
import static seedu.duke.common.Constants.INDEX_TEACHER_NAME;
import static seedu.duke.common.Constants.INDEX_TYPE;
import static seedu.duke.common.Constants.EMPTY_STRING;
import static seedu.duke.common.Constants.WHITESPACE;
import static seedu.duke.common.DashboardCommands.ADD;
import static seedu.duke.common.DashboardCommands.DELETE;
import static seedu.duke.common.DashboardCommands.EXIT;
import static seedu.duke.common.DashboardCommands.MODULES;
import static seedu.duke.common.DashboardCommands.OPEN;
import static seedu.duke.common.Messages.MESSAGE_INVALID_LESSON_EMAIL;
import static seedu.duke.common.Messages.MESSAGE_INVALID_LESSON_LINK;
import static seedu.duke.common.Messages.MESSAGE_INVALID_LESSON_TYPE;
import static seedu.duke.common.Messages.MESSAGE_INVALID_MODULE_CODE;
import static seedu.duke.common.Messages.MESSAGE_INVALID_TASK_DEADLINE;
import static seedu.duke.common.Messages.MESSAGE_LESSON_FIELDS_EMPTY;
import static seedu.duke.common.Messages.MESSAGE_MODULE_CODE_EMPTY;
import static seedu.duke.common.Messages.MESSAGE_NON_INTEGER_INDICES;
import static seedu.duke.common.Messages.MESSAGE_OUT_OF_BOUNDS_INDICES;
import static seedu.duke.common.Messages.MESSAGE_TASK_FIELDS_EMPTY;
import static seedu.duke.common.Messages.MESSAGE_UNKNOWN_COMMAND;
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
    public Command parse(String input) throws UnknownCommandException, CommandException {
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
    private Command parseAtDashboard(String input) throws UnknownCommandException, CommandException {
        DashboardCommands command = parseDashboardCommandFromInput(input);
        switch (command) {
        case ADD:
            String moduleCode = getModuleCode(input);
            return new AddModuleCommand(moduleCode);
        case DELETE:
            return new DeleteModuleCommand();
        case MODULES:
            return new ListModulesCommand();
        case OPEN:
            return new EnterModuleCommand(input.toUpperCase());
        case HELP:
            return new PrintHelpCommand();
        case EXIT:
            return new ExitProgramCommand();
        default:
            throw new UnknownCommandException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input to determine the dashboard command specified.
     *
     * @param input full user input string
     * @return an integer representing the command specified
     */
    private DashboardCommands parseDashboardCommandFromInput(String input) {
        if (input.equalsIgnoreCase(DashboardCommands.HELP.getWord())) {
            return DashboardCommands.HELP;
        } else if (input.equalsIgnoreCase(EXIT.getWord())) {
            return EXIT;
        } else if (input.equalsIgnoreCase(MODULES.getWord())) {
            return MODULES;
        } else if (isStartsWith(input, ADD.getWord())) {
            return ADD;
        } else if (isStartsWith(input, DELETE.getWord())) {
            return DELETE;
        } else if (isValidModuleCode(input)) {
            return OPEN;
        } else {
            return DashboardCommands.INVALID;
        }
    }

    /**
     * Parses module code from user input.
     *
     * @param input full user input string
     * @return module code string
     */
    private String getModuleCode(String input) throws CommandException {
        String[] words = input.split(WHITESPACE);
        if (words.length < 2) {
            throw new CommandException(MESSAGE_MODULE_CODE_EMPTY);
        }

        String moduleCode = words[1].toUpperCase();

        if (!isValidModuleCode(moduleCode)) {
            throw new CommandException(MESSAGE_INVALID_MODULE_CODE);
        }

        return moduleCode;
    }

    /**
     * Checks if user is at dashboard or has already entered a module.
     *
     * @return true if user has already entered a module, false otherwise
     */
    private boolean moduleIsSelected() {
        return ModuleList.getInstance().getSelectedModule() != null;
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
    private boolean isStartsWith(String input, String command) {
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
    private Command parseInModule(String input) throws UnknownCommandException, CommandException {
        ModuleCommands command = parseInModuleCommandsFromInput(input);

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
            throw new UnknownCommandException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input to determine in-module command specified.
     *
     * @param input full user input string
     * @return an integer representing the command specified
     */
    private ModuleCommands parseInModuleCommandsFromInput(String input) {
        if (input.equalsIgnoreCase(ModuleCommands.HELP.getWord())) {
            return ModuleCommands.HELP;
        } else if (input.equalsIgnoreCase(CLOSE.getWord())) {
            return CLOSE;
        } else if (input.equalsIgnoreCase(INFO.getWord())) {
            return INFO;
        } else if (input.equalsIgnoreCase(LESSONS.getWord())) {
            return LESSONS;
        } else if (input.equalsIgnoreCase(LINK.getWord())) {
            return LINK;
        } else if (input.equalsIgnoreCase(TASKS.getWord())) {
            return TASKS;
        } else if (input.equalsIgnoreCase(MARK.getWord())) {
            return MARK;
        } else if (input.equalsIgnoreCase(UNMARK.getWord())) {
            return UNMARK;
        } else if (input.equalsIgnoreCase(TEACHER.getWord())) {
            return TEACHER;
        } else if (isStartsWith(input, ADD_LESSON.getWord())) {
            return ADD_LESSON;
        } else if (isStartsWith(input, DELETE_LESSON.getWord())) {
            return DELETE_LESSON;
        } else if (isStartsWith(input, ADD_TASK.getWord())) {
            return ADD_TASK;
        } else if (isStartsWith(input, DELETE_TASK.getWord())) {
            return DELETE_TASK;
        } else {
            return ModuleCommands.INVALID;
        }
    }

    /**
     * Parses details of new lesson from user input string.
     *
     * @param input full user input string
     * @return a Lesson object with the details entered by the user. Fields not found will be left as an empty string.
     */
    private Lesson parseNewLessonDetails(String input) throws CommandException {

        // initialize an array of empty strings to store lesson details
        String[] allDetails = new String[ENTRY_LESSON_MAX_PARSER];
        Arrays.fill(allDetails, EMPTY_STRING);

        // to remove only the first two words "add lesson"
        String[] lessonDetails = input.trim().split(WHITESPACE, 3);
        // assumption that the "add lesson" will always be present in input
        assert (lessonDetails.length >= 2);

        // ERROR - User does not enter any parameters.
        if (lessonDetails.length < 3) {
            throw new CommandException(MESSAGE_LESSON_FIELDS_EMPTY);
        }

        // split the details field using DELIMITER to get the individual detail fields
        String[] details = lessonDetails[2].split(DELIM);

        // store detail fields that have been filled by the user into an array
        // if user did not enter that field, it will remain as an empty string
        for (int i = 0; i < details.length && i < allDetails.length; i++) {
            allDetails[i] = details[i].trim();
        }

        // Creating Lesson Object
        String type = allDetails[INDEX_TYPE].toUpperCase();
        LessonType lessonType;
        try {
            lessonType = LessonType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new CommandException(MESSAGE_INVALID_LESSON_TYPE);
        }

        String timeAndDay = allDetails[INDEX_DAY_TIME];

        String link = allDetails[INDEX_LINK];
        if (!isValidLink(link) && !link.equals(EMPTY_STRING)) {
            throw new CommandException(MESSAGE_INVALID_LESSON_LINK);
        }

        String teacherName = allDetails[INDEX_TEACHER_NAME];

        String email = allDetails[INDEX_TEACHER_EMAIL];
        if (!isValidEmail(email) && !email.equals(EMPTY_STRING)) {
            throw new CommandException(MESSAGE_INVALID_LESSON_EMAIL);
        }

        TeachingStaff teacher = new TeachingStaff(teacherName, email);

        return new Lesson(lessonType, timeAndDay, link, teacher);
    }

    /**
     * Check if given string is a valid email.
     *
     * @param email string to be checked
     * @return true if string follows the format of a valid email
     */
    private boolean isValidEmail(String email) {
        return email.trim().matches(FORMAT_EMAIL);
    }

    /**
     * Check if given string is a valid link.
     *
     * @param link string to be checked
     * @return true if string follows the format of a valid email
     */
    private boolean isValidLink(String link) {
        return link.matches(FORMAT_LINK);
    }

    /**
     * Parses details of new task from user input string.
     *
     * @param input full user input string
     * @return a Task object with the details entered by the user. Fields not found will be left as an empty string.
     */
    private Task parseNewTaskDetails(String input) throws CommandException {

        // initialize an array of empty strings to store task details
        String[] allDetails = new String[ENTRY_TASK_MAX_PARSER];
        Arrays.fill(allDetails, EMPTY_STRING);

        // to remove only the first two words "add task"
        String[] taskDetails = input.trim().split(WHITESPACE, 3);
        // assumption that the "add task" will always be present in input
        assert (taskDetails.length >= 2);

        // ERROR - User does not enter any parameters.
        if (taskDetails.length < 3) {
            throw new CommandException(MESSAGE_TASK_FIELDS_EMPTY);
        }

        // split the details field using DELIMITER to get the individual detail fields
        String[] details = taskDetails[2].split(DELIM);

        // store detail fields that have been filled by the user into an array
        // if user did not enter that field, it will remain as an empty string
        for (int i = 0; i < details.length && i < allDetails.length; i++) {
            allDetails[i] = details[i].trim();
        }

        // Creating Task object
        String description = allDetails[INDEX_DESCRIPTION];

        String deadlineString = allDetails[INDEX_DEADLINE];
        LocalDate deadline;
        try {
            deadline = convertToDate(deadlineString);
        } catch (DateTimeParseException e) {
            throw new CommandException(MESSAGE_INVALID_TASK_DEADLINE);
        }

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
    public static ArrayList<Integer> checkIndices(String input, int max) {
        ArrayList<Integer> rawIndices = new ArrayList<>();
        ArrayList<String> nonIntegers = new ArrayList<>();
        int index;
        UI ui = new UI();

        // assumption that input is non-null
        assert (input != null);
        String[] words = input.trim().split(WHITESPACE);
        
        for (String word : words) {
            try {
                index = Integer.parseInt(word);
                rawIndices.add(index);
            } catch (NumberFormatException e) {
                // Non-integer inputs will not be added to the array list rawIndices.
                nonIntegers.add(word);
            }
        }
        if (nonIntegers.size() != 0) {
            printNonIntegerWarning(nonIntegers, ui);
        }

        // Remove duplicates
        ArrayList<Integer> indices = new ArrayList<>();
        ArrayList<Integer> removed = new ArrayList<>();

        for (int number : rawIndices) {
            if (!indices.contains(number)) {
                indices.add(number);
            }
        }

        // Remove out of bounds/ invalid index
        for (int i = 0; i < indices.size(); i++) {
            if (indices.get(i) > max || indices.get(i) <= 0) {
                int removedIndex = indices.remove(i);
                removed.add(removedIndex);
                i--;
            }
        }
        // Prints indices that were removed.
        if (removed.size() != 0) {
            printOutOfBoundsWarning(removed, ui);
        }
        return indices;
    }

    /**
     * Prints warning to inform user that some inputs were out of bounds and removed.
     * Prints the integers that were removed.
     * 
     * @param removed array list of integers that were out of bounds and have been removed
     * @param ui UI object for printing
     */
    private static void printOutOfBoundsWarning(ArrayList<Integer> removed, UI ui) {
        ui.printMessage(String.format(MESSAGE_OUT_OF_BOUNDS_INDICES, removed));
    }

    /**
     * Prints warning to inform user that some inputs were not integers.
     * Prints the strings that were removed.
     *
     * @param removed array list of strings that were invalid and have been removed
     * @param ui UI object for printing
     */
    private static void printNonIntegerWarning(ArrayList<String> removed, UI ui) {
        ui.printMessage(String.format(MESSAGE_NON_INTEGER_INDICES, removed));
    }
}
