package seedu.duke.module;


import seedu.duke.storage.Loader;
import seedu.duke.storage.Writer;

import java.util.ArrayList;
import java.util.Collections;

import static seedu.duke.common.CommonMethods.getDaysRemaining;

public class ModuleList {

    private static ModuleList moduleListInstance = null;
    
    private ModuleList() {
        
    }
    
    public static ModuleList getInstance() {
        if (moduleListInstance == null) {
            moduleListInstance = new ModuleList();
        }
        return moduleListInstance;
    }
    
    private static final ArrayList<String> modules = new ArrayList<>();
    private static Module selectedModule;

    public Module getSelectedModule() {
        return selectedModule;
    }

    /**
     * For testing purposes only.
     */
    public void hardSetSelectedModule(String moduleCode) {
        ModuleList.selectedModule = new Module(moduleCode);
    }

    public ArrayList<String> getModules() {
        return modules;
    }

    //@@author 8kdesign
    /**
     * Searches directory for module files.
     * Adds their name (excluding ".txt") to the module list.
     */
    public void loadModuleNames() {
        modules.clear();
        Loader loader = new Loader();
        for (String name : loader.getModules()) {
            insertModule(name);
        }
    }

    /**
     * Adds a new module to the module list and add create file for new module.
     *
     * @param moduleCode Module name, excluding ".txt".
     */
    public boolean addModule(String moduleCode) {
        if (insertModule(moduleCode)) {
            Writer writer = new Writer();
            writer.createFile(moduleCode);
            return true;
        }
        return false;
    }

    /**
     * Adds a module to the module list.
     *
     * @param moduleCode Module code, excluding ".txt".
     */
    private boolean insertModule(String moduleCode) {
        if (modules.contains(moduleCode)) {
            //Error, module already exists
            return false;
        }
        modules.add(moduleCode);
        return true;
    }

    //@@author isaharon
    /**
     * Deletes modules specified.
     *
     * @param moduleNumbers Index of modules to delete.
     * @return List of names of modules that are deleted.
     */
    public ArrayList<String> deleteModules(ArrayList<Integer> moduleNumbers) {
        ArrayList<String> deletedModules = new ArrayList<>();
        Collections.reverse(moduleNumbers);
        for (Integer moduleNumber : moduleNumbers) {
            int indexToRemove = moduleNumber - 1;
            String name = removeModule(indexToRemove);
            if (name != null) {
                deletedModules.add(0, name);
            }
        }
        return deletedModules;
    }

    //@@author 8kdesign
    /**
     * Removes selected module and deletes module file.
     *
     * @param index Index of module to remove.
     */
    public String removeModule(int index) {
        if (index < 0 || index >= modules.size()) {
            return null;
        }
        Writer writer = new Writer();
        if (writer.deleteFile(modules.get(index))) {
            String moduleName = modules.get(index);
            modules.remove(index);
            return moduleName;
        } else {
            //Unable to remove
            return null;
        }
    }

    /**
     * Loads the current module from storage.
     *
     * @param moduleCode Module name, excluding ".txt".
     * @return True if successful, false if unable to find file.
     */
    public boolean setSelectedModule(String moduleCode) {
        Loader loader = new Loader();
        if (!modules.contains(moduleCode)) {
            //Unable to find file
            return false;
        }
        selectedModule = loader.loadModule(moduleCode);
        if (selectedModule != null) {
            //Sort data
            sortLessons();
            sortTasks();
            //Remove invalid inputs
            Writer writer = new Writer();
            writer.writeModule();
        }
        return selectedModule != null;
    }

    /**
     * Resets selected module by setting it to null.
     */
    public void reset() {
        selectedModule = null;
    }

    /**
     * Writes updated data to file of selected module.
     */
    public void writeModule() {
        Writer writer = new Writer();
        writer.writeModule();
    }
    
    /**
     * Sorts tasks by deadline.
     */
    public void sortTasks() {
        selectedModule.getTaskList().sort((task1, task2) -> {
            long daysRemaining1 = getDaysRemaining(task1.getDeadline());
            long daysRemaining2 = getDaysRemaining(task2.getDeadline());
            if (daysRemaining1 != daysRemaining2) {
                return (int) (daysRemaining1 - daysRemaining2);
            }
            return task1.getDescription().compareTo(task2.getDescription());
        });
    }

    //@@author H-horizon
    /**
     * Sorts lesson list by lesson type.
     */
    public void sortLessons() {
        selectedModule.getLessonList().sort((lesson1, lesson2) -> {
            if (lesson1.getLessonType() != lesson2.getLessonType()) {
                return lesson1.getLessonType().compareTo(lesson2.getLessonType());
            }
            return lesson1.getTime().compareTo(lesson2.getTime());
        });
    }
}
