package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.module.ModuleList;
import seedu.duke.ui.UI;

import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.duke.TestUtilAndConstants.MODULE_CODE_1;

public class ExitModuleCommandTest {

    //@@author 8kdesign
    @Test
    void execute_selectCloseModule_selectedEqualsNull() {
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.hardSetSelectedModule(MODULE_CODE_1);
        ExitModuleCommand command = new ExitModuleCommand();
        command.execute(new UI());
        assertNull(moduleList.getSelectedModule());
    }
    
}
