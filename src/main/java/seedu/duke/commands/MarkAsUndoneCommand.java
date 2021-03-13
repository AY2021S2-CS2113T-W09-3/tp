package seedu.duke.commands;

import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.CommonMethods.getSpecifiedTasks;
import static seedu.duke.common.Messages.COMMAND_VERB_UNMARK;
import static seedu.duke.common.Messages.MESSAGE_MARKED_AS_UNDONE;
import static seedu.duke.common.Messages.MESSAGE_TASKS_TO_UNMARK;
import static seedu.duke.common.Messages.MESSAGE_TASK_SELECT_INFO;

public class MarkAsUndoneCommand extends Command {

    @Override
    public void execute(UI ui) {
        Module module = ModuleList.getSelectedModule();
        ArrayList<Task> doneTasks = module.getDoneOrUndoneTasks(true);
        printPrompt(ui, doneTasks);
        ArrayList<Task> selectedTasks = getSpecifiedTasks(ui, doneTasks);
        for (Task task : selectedTasks) {
            String description = task.getDescription();
            ui.printMessage(String.format(MESSAGE_MARKED_AS_UNDONE,description));
            module.unmarkTask(task);
        }
        ModuleList.writeModule();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void printPrompt(UI ui, ArrayList<Task> doneTasks) {
        ui.printMessage(MESSAGE_TASKS_TO_UNMARK);
        ui.printSummarisedTasks(doneTasks);
        ui.printMessage(String.format(MESSAGE_TASK_SELECT_INFO, COMMAND_VERB_UNMARK));
    }
}