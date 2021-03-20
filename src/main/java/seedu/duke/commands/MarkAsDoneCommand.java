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

    //@@author aliciatay-zls
    /**
     * Displays list of undone tasks, asks user for a string of space-separated  
     * indices and marks all corresponding tasks as done.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        Module module = moduleList.getSelectedModule();
        ArrayList<Task> undoneTasks = module.getDoneOrUndoneTasks(false);
        printPrompt(ui, undoneTasks);
        ArrayList<Task> selectedTasks = getSpecifiedTasks(ui, undoneTasks);
        for (Task task : selectedTasks) {
            String description = task.getDescription();
            ui.printMessage(String.format(MESSAGE_MARKED_AS_DONE, description));
            module.markTask(task);
        }
        moduleList.writeModule();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints prompt for indices of tasks to mark as done.
     *
     * @param ui Instance of UI.
     * @param undoneTasks Array list of undone tasks.
     */
    private void printPrompt(UI ui, ArrayList<Task> undoneTasks) {
        ui.printMessage(MESSAGE_TASKS_TO_MARK);
        ui.printSummarisedTasks(undoneTasks);
        ui.printMessage(String.format(MESSAGE_TASK_SELECT_INFO, COMMAND_VERB_MARK));
    }
}