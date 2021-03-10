package seedu.duke.commands;

import seedu.duke.TestUtil;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CommandTestUtil {
    public static final String NEWLINE = System.lineSeparator();
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
    public static final String MESSAGE_MODULE_ERROR = "There was a problem with getting selected module." + NEWLINE;

    public static void initialiseModuleList() {
        TestUtil.removeFiles();
        ModuleList.loadModuleNames();
        ModuleList.addModule("CS2113T");
        ModuleList.setSelectedModule("CS2113T");
    }

    // Creates task list for testing MarkAsDoneCommand and MarkAsUndoneCommand.
    // Contains mix of undone tasks (by default) and done tasks (explicitly set here).
    public static ArrayList<Task> initialiseTaskList(Module module) {
        LocalDate deadline = LocalDate.parse("3-3-2021", formatter);

        Task task1 = new Task("weekly exercise", deadline, "");
        module.addTaskToList(task1);

        Task task2 = new Task("lecture quiz", deadline, "complete before next lecture");
        module.addTaskToList(task2);

        Task task3 = new Task("iP increments", deadline, "remember to attach JAR file");
        task3.setDone(true);
        module.addTaskToList(task3);

        Task task4 = new Task("read up notes", deadline, "complete before lecture");
        module.addTaskToList(task4);

        Task task5 = new Task("tP milestone", deadline, "meet with team before merging");
        task5.setDone(true);
        module.addTaskToList(task5);

        Task task6 = new Task("watch video snippets", deadline, "complete before lecture");
        task6.setDone(true);
        module.addTaskToList(task6);

        return module.getTaskList();
    }
}
