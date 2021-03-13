package seedu.duke.commands;

import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.common.CommonMethods.getSpecifiedTasks;
import static seedu.duke.common.Messages.COMMAND_VERB_MARK;
import static seedu.duke.common.Messages.MESSAGE_MARKED_AS_DONE;
import static seedu.duke.common.Messages.MESSAGE_TASKS_TO_MARK;
import static seedu.duke.common.Messages.MESSAGE_TASK_SELECT_INFO;

public class MarkAsDoneCommand extends Command {

    @Override
    public void execute(UI ui) {
        Module module = ModuleList.getSelectedModule();
        ArrayList<Task> undoneTasks = module.getDoneOrUndoneTasks(false);
        printPrompt(ui, undoneTasks);
        ArrayList<Task> selectedTasks = getSpecifiedTasks(ui, undoneTasks);
        for (Task task : selectedTasks) {
            String description = task.getDescription();
            ui.printMessage(String.format(MESSAGE_MARKED_AS_DONE, description));
            module.markTask(task);
        }
        ModuleList.writeModule();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void printPrompt(UI ui, ArrayList<Task> undoneTasks) {
        ui.printMessage(MESSAGE_TASKS_TO_MARK);
        ui.printSummarisedTasks(undoneTasks);
        ui.printMessage(String.format(MESSAGE_TASK_SELECT_INFO, COMMAND_VERB_MARK));
    }
}