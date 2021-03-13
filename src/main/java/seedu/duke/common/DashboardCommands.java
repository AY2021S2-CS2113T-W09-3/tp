package seedu.duke.common;

/**
 * Enum class for all commands available at the dashboard.
 */
public enum DashboardCommands {

    HELP("help", "Lists out all commands available when a module isn't selected."),
    EXIT("exit", "Exits the program."),
    OPEN("open", "Opens the specified module."),
    ADD("add", "Adds a new module with specified name."),
    DELETE("delete", "Deletes the specified module."),
    MODULES("modules", "Lists all modules.");

    private final String word;
    private final String description;

    DashboardCommands(String word, String description) {
        this.word = word;
        this.description = description;
    }

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Takes in user input and returns appropriate DashboardCommands object.
     * @param input user input string
     * @return DashboardCommands object based on user input, null if there is no match
     */
    public static DashboardCommands fromInput(String input) {
        for (DashboardCommands command : DashboardCommands.values()) {
            if (input.equalsIgnoreCase(command.getWord())) {
                return command;
            }
        }
        return null;
    }
}
