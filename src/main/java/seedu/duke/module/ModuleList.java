package seedu.duke.module;


import java.lang.reflect.Array;
import seedu.duke.storage.Loader;
import seedu.duke.storage.Writer;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Objects.requireNonNull;

public class ModuleList {

    private static final ArrayList<String> modules = new ArrayList<>();
    private static Module selectedModule;

    public static Module getSelectedModule() {
        return selectedModule;
    }

    public static ArrayList<String> getModules() {
        return modules;
    }
 

    /**
     * Searches directory for module files.
     * Adds their name (excluding ".txt") to the module list.
     */
    public static void loadModuleNames() {
        modules.clear();
        Loader loader = new Loader();
        for (String name : loader.getModuleNames()) {
            insertModule(name);
        }
    }


    /**
     * Adds a new module to the module list and add create file for new module.
     *
     * @param moduleCode Module name, excluding ".txt".
     */
    public static boolean addModule(String moduleCode) {
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
     * @param name Module name, excluding ".txt".
     */
    private static boolean insertModule(String name) {
        if (modules.contains(name)) {
            //Error, module already exists
            return false;
        }
        modules.add(name);
        return true;
    }


    /**
     * Removes selected module and deletes module file.
     *
     * @param index index of module to remove.
     */
    public static String removeModule(int index) {
        if (index < 0 || index >= modules.size()) {
            return " ";
        }
        Writer writer = new Writer();
        if (writer.deleteFile(modules.get(index))) {
            String moduleName = modules.get(index);
            modules.remove(index);
            return moduleName;
        } else {
            //Unable to remove
            return "";
        }
    }


    /**
     * Loads the current module from storage.
     *
     * @param name Module name, excluding ".txt".
     * @return True if successful, false if unable to find file.
     */
    public static boolean setSelectedModule(String name) {
        Loader loader = new Loader();
        if (!modules.contains(name)) {
            //Unable to find file
            return false;
        }
        selectedModule = loader.loadModule(name);
        if (selectedModule != null) {
            //Remove invalid inputs
            Writer writer = new Writer();
            writer.writeModule();
        }
        return selectedModule != null;
    }


    /**
     * Resets selected module by setting it to null.
     */
    public static void reset() {
        selectedModule = null;
    }


    /**
     * Writes updated data to file of selected module.
     */
    public static void writeModule() {
        Writer writer = new Writer();
        writer.writeModule();
    }


    /**
     * Deletes modules specified.
     *
     * @param moduleNumbers Index of modules to delete.
     * @return List of names of modules that are deleted.
     */
    public static ArrayList<String> deleteModules(ArrayList<Integer> moduleNumbers) {
        ArrayList<String> deletedModules = new ArrayList<>();
        Collections.reverse(moduleNumbers);
        for (Integer moduleNumber : moduleNumbers) {
            int indexToRemove = moduleNumber - 1;
            deletedModules.add(0, removeModule(indexToRemove));
        }
        return deletedModules;
    }
}
