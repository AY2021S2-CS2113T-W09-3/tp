package seedu.duke.common;

/**
 * Enum class for all commands available inside a module.
 */
public enum ModuleCommands {

    HELP("help", "List out commands available for the selected module."),
    CLOSE("close", "Closes the current module."),
    INFO("info", "Prints an overview of the current module."),
    ADD_LESSON("add lesson", "Adds a new lesson."),
    DELETE_LESSON("delete lesson", "Deletes lessons."),
    LINK("link", "Opens link to the lesson in a browser."),
    LESSONS("lessons", "Lists all lessons."),
    TEACHER("teacher", "Lists all teaching staff."),
    ADD_TASK("add task", "Adds new task."),
    DELETE_TASK("delete task", "Deletes specified tasks."),
    MARK("mark", "Marks specified tasks as done."),
    UNMARK("unmark", "Marks specified tasks as undone."),
    TASKS("tasks", "Lists all tasks.");

    private final String word;
    private final String description;

    ModuleCommands(String word, String description) {
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
     * Takes in user input and returns appropriate ModuleCommands object.
     * @param input user input string
     * @return ModuleCommands object based on user input, null if there is no match
     */
    public static ModuleCommands fromInput(String input) {
        for (ModuleCommands command : ModuleCommands.values()) {
            if (input.equalsIgnoreCase(command.getWord())) {
                return command;
            }
        }
        return null;
    }
}
