package seedu.duke.commands;

import seedu.duke.exceptions.CommandException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.Messages.COMMAND_VERB_DELETE;
import static seedu.duke.common.Messages.MESSAGE_TASKS_TO_DELETE;

public class DeleteTaskCommand extends Command {

    @Override
    public void execute(UI ui) throws CommandException {
        Module module = ModuleList.getSelectedModule();
        ui.printGetChosenTasksPrompt(MESSAGE_TASKS_TO_DELETE, COMMAND_VERB_DELETE, module.getTaskList());
        ArrayList<Task> chosenTasks = ui.getChosenTasks(module.getTaskList());
        for (Task task : chosenTasks) {
            String description = task.getDescription();
            String confirmation = "Removed " + description + ".";
            ui.printStatement(confirmation);
            module.deleteTaskFromList(task);
        }
        ModuleList.writeModule();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
