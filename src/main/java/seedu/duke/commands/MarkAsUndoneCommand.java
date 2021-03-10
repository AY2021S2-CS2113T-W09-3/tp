package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.Messages.COMMAND_VERB_UNMARK;
import static seedu.duke.common.Messages.MESSAGE_TASKS_TO_UNMARK;

public class MarkAsUndoneCommand extends Command {

    @Override
    public void execute(UI ui) throws CommandException {
        Module module = ModuleList.getSelectedModule();
        ArrayList<Task> doneTasks = ui.getFilteredTasks(module.getTaskList(), true);
        ui.printGetChosenTasksPrompt(MESSAGE_TASKS_TO_UNMARK, COMMAND_VERB_UNMARK, doneTasks);
        ArrayList<Task> chosenTasks = ui.getChosenTasks(doneTasks);
        for (Task task : chosenTasks) {
            String description = task.getDescription();
            String confirmation = "Marked " + description + " as undone.";
            ui.printStatement(confirmation);
            module.unmarkTaskInList(task);
        }
        ModuleList.writeModule();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
