package seedu.duke.commands;

import seedu.duke.editor.TextEditor;
import seedu.duke.ui.UI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static seedu.duke.common.Constants.MESSAGE_OPEN_FILE;
import static seedu.duke.common.Messages.MESSAGE_FILE_DOES_NOT_EXIST;

public class EditCheatSheetCommand extends DeleteCheatSheetCommand {

    public EditCheatSheetCommand(String nameOfFile) {
        super(nameOfFile);
    }

    @Override
    public void performFunction(UI ui, Path path) throws IOException {
        openTextEditor(ui, path, filePath);
    }

    @Override
    public void openTextEditor(UI ui, Path path, String filePath) {
        if (Files.exists(path)) {
            TextEditor textEditor = new TextEditor(filePath);
            textEditor.setTextAreaToVoid();
            textEditor.loadFile(filePath);
            ui.printMessage(String.format(MESSAGE_OPEN_FILE, fileName));
        } else {
            ui.printMessage(String.format(MESSAGE_FILE_DOES_NOT_EXIST, fileName));
        }
    }
}
