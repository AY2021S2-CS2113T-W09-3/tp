package seedu.duke;

import java.io.File;

import static seedu.duke.storage.StorageConstants.FOLDER_PATH;

public class TestUtil {

    public static void removeFiles() {
        File directory = new File(FOLDER_PATH);
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            file.delete();
        }
        directory.delete();
    }
}
