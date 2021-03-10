package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.Messages.COMMAND_VERB_MARK;
import static seedu.duke.common.Messages.MESSAGE_TASKS_TO_MARK;

public class MarkAsDoneCommand extends Command {

    // Extracts undone tasks as a new list, shows the list to the user and obtains their
    // chosen tasks. Then, marks each of these tasks as done in the actual list.
    @Override
    public void execute(UI ui) throws CommandException {
        Module module = ModuleList.getSelectedModule();
        ArrayList<Task> undoneTasks = ui.getFilteredTasks(module.getTaskList(), false);
        ui.printGetChosenTasksPrompt(MESSAGE_TASKS_TO_MARK, COMMAND_VERB_MARK, undoneTasks);
        ArrayList<Task> chosenTasks = ui.getChosenTasks(undoneTasks);
        for (Task task : chosenTasks) {
            String description = task.getDescription();
            String confirmation = "Marked " + description + " as done.";
            ui.printStatement(confirmation);
            module.markTaskInList(task);
        }
        ModuleList.writeModule();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
