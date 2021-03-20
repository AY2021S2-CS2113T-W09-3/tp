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

    //@@author aliciatay-zls
    /**
     * Displays list of done tasks, asks user for a string of space-separated  
     * indices and marks all corresponding tasks as undone.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        Module module = moduleList.getSelectedModule();
        ArrayList<Task> doneTasks = module.getDoneOrUndoneTasks(true);
        printPrompt(ui, doneTasks);
        ArrayList<Task> selectedTasks = getSpecifiedTasks(ui, doneTasks);
        for (Task task : selectedTasks) {
            String description = task.getDescription();
            ui.printMessage(String.format(MESSAGE_MARKED_AS_UNDONE,description));
            module.unmarkTask(task);
        }
        moduleList.writeModule();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints prompt for indices of tasks to mark as undone.
     *
     * @param ui Instance of UI.
     * @param doneTasks Array list of done tasks.
     */
    private void printPrompt(UI ui, ArrayList<Task> doneTasks) {
        ui.printMessage(MESSAGE_TASKS_TO_UNMARK);
        ui.printSummarisedTasks(doneTasks);
        ui.printMessage(String.format(MESSAGE_TASK_SELECT_INFO, COMMAND_VERB_UNMARK));
    }
}