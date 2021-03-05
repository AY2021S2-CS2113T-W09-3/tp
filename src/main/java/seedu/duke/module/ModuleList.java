package seedu.duke.module;

import seedu.duke.storage.Storage;

import java.util.ArrayList;

public class ModuleList {

    private static final Storage storage = new Storage();

    public static ArrayList<String> moduleList;
    public static Module selectedModule;

    public static boolean setSelectedModule(String name) {
        if (!moduleList.contains(name)) {
            //unable to find
            return false;
        }
        //selectedModule = storage.loadModule(name);
        return true;
    }

    public static void reset() {
        selectedModule = null;
    }
}
