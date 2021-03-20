package seedu.duke.parser;

import seedu.duke.commands.AddLessonCommand;
import seedu.duke.commands.AddModuleCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ListTasksCommand;
import seedu.duke.exception.CommandException;
import seedu.duke.exception.UnknownCommandException;
import seedu.duke.module.Module;
import seedu.duke.lesson.Lesson;
import seedu.duke.lesson.LessonType;
import seedu.duke.lesson.TeachingStaff;
import seedu.duke.module.ModuleList;
import seedu.duke.task.Task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    //@@author ivanchongzhien
    @Test
    // DASHBOARD COMMAND
    void parse_dashboardCommandAddModule_addCommandObject() throws UnknownCommandException, CommandException {
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.reset();

        Parser parser = new Parser();
        String input = "add CS2113T";

        // Command expectedCommand = new AddModuleCommand("CS2113T");
        Command actualCommand = parser.parse(input);
        assertTrue(actualCommand instanceof AddModuleCommand);
    }

    @Test
    // IN MODULE COMMAND
    // add lesson command
    void parse_inModuleCommandListTask_ListTask() throws UnknownCommandException, CommandException {
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.reset();
        moduleList.hardSetSelectedModule("CS1234");

        Parser parser = new Parser();
        String input = "tasks";

        Command actualCommand = parser.parse(input);
        assertTrue(actualCommand instanceof ListTasksCommand);
    }

    @Test
    // add lesson command - all detail fields included
    void parse_inModuleCommandAddLessonFullDetails_addLessonObject() throws UnknownCommandException, CommandException {
        ModuleList moduleList = ModuleList.getInstance();
        moduleList.reset();
        moduleList.hardSetSelectedModule("CS1234");

        Parser parser = new Parser();
        String input = "add lesson tutorial ;; Wednesday 9am-10am ;; www.zoom.com/1234 ;; Xianhao Cheng ;; " 
                + "xh123@nus.edu.sg";

        Command actualCommand = parser.parse(input);
        assertTrue(actualCommand instanceof AddLessonCommand);
    }


    @Test
    void checkIndices_variousInputs_processedArrayList() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        
        int max = 5;
        String input1 = "1 2 3"; // happy
        assertEquals(expected, Parser.checkIndices(input1, max));
        
        String input2 = "1 1 2 3 3 3 2 2 1 2"; // duplicates
        assertEquals(expected, Parser.checkIndices(input2, max));
        
        String input3 = "10 1 99 2 10909 3 99 99 100 10"; // out of bounds and duplicates
        assertEquals(expected, Parser.checkIndices(input3, max));
        
        String input4 = "10 1 -1 -99 -2 10918 2 3 -99 990 990 10 0 10"; // out of bounds and duplicates and negative
        assertEquals(expected, Parser.checkIndices(input4, max));
        
        String input5 = "10 1 -1 -99 -2 10918 2 3 abc"; // out of bounds and duplicates and negative
        assertEquals(expected, Parser.checkIndices(input5, max));
    }
}