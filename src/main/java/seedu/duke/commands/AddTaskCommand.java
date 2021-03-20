package seedu.duke.commands;

import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;
import seedu.duke.ui.UI;

import static seedu.duke.common.Constants.NO_STRING;
import static seedu.duke.common.Constants.YES_STRING;
import static seedu.duke.common.Messages.MESSAGE_ADDED_TASK;
import static seedu.duke.common.Messages.MESSAGE_TASK_CHECK_GRADED;
import static seedu.duke.common.Messages.MESSAGE_TASK_CHECK_GRADED_INFO;

public class AddTaskCommand extends Command {

    private final Task task;

    //@@author aliciatay-zls
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds new task to selected module.
     *
     * @param ui Instance of UI.
     */
    @Override
    public void execute(UI ui) {
        ModuleList moduleList = ModuleList.getInstance();
        Module module = moduleList.getSelectedModule();
        boolean isGraded = getIsTaskGraded(ui);
        task.setGraded(isGraded);
        module.addTask(task);
        ui.printMessage(String.format(MESSAGE_ADDED_TASK, task.getDescription()));
        moduleList.writeModule();
        moduleList.sortTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Asks user if the task to be added is a graded one.
     *
     * @param ui Instance of UI.
     * @return Boolean of whether new task is graded.
     */
    public boolean getIsTaskGraded(UI ui) {
        ui.printMessage(MESSAGE_TASK_CHECK_GRADED);
        String userInput = ui.readCommand().toUpperCase();
        while (!userInput.equals(YES_STRING) && !userInput.equals(NO_STRING)) {
            ui.printMessage(MESSAGE_TASK_CHECK_GRADED_INFO);
            userInput = ui.readCommand();
        }
        return userInput.equals(YES_STRING);
    }
}
