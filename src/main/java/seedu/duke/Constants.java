package seedu.duke;

public class Constants {
    public static final String ADD_INFO_MESSAGE = "Add a patient to the list\n"
            + "Command prefix: add\n"
            + "Argument(s): IC number\n"
            + "Usage: add IC_NUMBER\n"
            + "Example: add S1234567D\n";
    public static final String LIST_INFO_MESSAGE = "Show the list of all patients\n"
            + "Usage: list\n";
    public static final String LOAD_INFO_MESSAGE = "Select a specified patient to add and retrieve records\n"
            + "Command prefix: load\n"
            + "Arguments(s): IC number\n"
            + "Usage: load IC_NUMBER\n"
            + "Example: load S1234567D\n";
    public static final String RECORD_CONSULTATION_INFO_MESSAGE = "Add a consultation record to the selected patient\n"
            + "Command prefix: record\n"
            + "Arguments(s): consultation details, symptom, diagnosis, prescription\n"
            + "Usage: record [CONSULTATION_DATE] [/s SYMPTOM] [/d DIAGNOSIS] [/p PRESCRIPTION]\n"
            + "Example: record /s fever\n";
    public static final String RETRIEVE_INFO_MESSAGE = "Retrieve past consultation record from the selected patient\n"
            + "Usage: retrieve\n";
    public static final String CURRENT_INFO_MESSAGE = "Print out the current patient being loaded.\n"
            + "Usage: current\n";
    public static final String HELP_INFO_MESSAGE = "Display information about all commands or selected commands only\n"
            + "Command prefix: help\n"
            + "Argument(s): commands\n"
            + "Usage: help [OPTIONAL_COMMANDS]\n"
            + "Example: help list add\n";
    public static final String EXIT_INFO_MESSAGE = "Exit the program\n"
            + "Usage: exit\n";

    public static final String INVALID_COMMAND_MESSAGE = "Invalid command: %s";
    public static final String EXIT_MESSAGE = "Goodbye, we hope to see you again!";
    public static final String WELCOME_MESSAGE = "Welcome to the Patient Manager.\n";
    public static final String INPUT_PROMPT = "Please input a command: ";

    public static final String EMPTY_LIST_MESSAGE = "List is currently empty!";

    public static final String LONG_LINE = "----------------------------------------------------------------------";

    // Exception messages
    public static final String EXCEPTION_INDENT = "\t";
    
    public static final String INVALID_INPUT = "Input command and/or arguments are invalid";
    public static final String INVALID_INPUT_EMPTY_STRING = "Empty string is found when trying to parse command!";
    public static final String INVALID_INPUT_UNKNOWN_COMMAND = "Invalid command is provided!";
    public static final String INVALID_INPUT_INVALID_NRIC = "Please key in a valid NRIC number!";
    public static final String INVALID_INPUT_PATIENT_EXISTED = "Patient already exists!";
    public static final String INVALID_INPUT_NO_PATIENT_LOADED = "No patient loaded!";
    public static final String INVALID_INPUT_EMPTY_DESCRIPTION = "Please provide details about the patient's visit!";
    public static final String INVALID_INPUT_INVALID_DATE = "Please provide a valid date (format: dd/MM/yyyy)";

    public static final String UNKNOWN = "We have encountered an unknown error";

    // Number constants
    public static final int ID_NUMBER_OF_CHARACTERS = 9;
    public static final int INDEX_OF_FIRST_CHARACTER = 0;
    public static final int INDEX_OF_LAST_CHARACTER = 8;

    // Keys for the argument HashMap
    public static final String PAYLOAD_KEY = "payload";
    public static final String SYMPTOM_KEY = "s";
    public static final String DIAGNOSIS_KEY = "d";
    public static final String PRESCRIPTION_KEY = "p";
    public static final String EXCEPTION_RECORD_RETRIEVE_INVALID_DATE = "That's not a valid date";

    // Date format
    public static final String DATE_PATTERN = "dd/MM/yyyy";

    // Class path for command classes
    public static final String COMMAND_CLASS_PREFIX = "seedu.duke.command.";

    // Delimiters for storage class
    public static final String ID_DELIMITER = "||";
    public static final String DATE_DELIMITER = "``";
    public static final String SYMPTOM_DELIMITER = "^^";
    public static final String DIAGNOSIS_DELIMITER = "##";
    public static final String PRESCRIPTION_DELIMITER = "@@";
    public static final String RECORDS_DELIMITER = "[R]";

}