package seedu.modulight.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulight.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modulight.testutil.TypicalStudents.getTypicalComponentBook;
import static seedu.modulight.testutil.TypicalStudents.getTypicalScoreBook;
import static seedu.modulight.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import seedu.modulight.model.Model;
import seedu.modulight.model.ModelManager;
import seedu.modulight.model.UserPrefs;

public class ListAllCommandTest {
    private Model model = new ModelManager(getTypicalStudentBook(), getTypicalScoreBook(), getTypicalComponentBook(),
            new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), getTypicalScoreBook(),
            getTypicalComponentBook(), new UserPrefs());
    @Test
    public void excute_nonFiltered_success() {
        ListAllCommand command = new ListAllCommand();
        String expectedMessage = "Listed all students, student scores and graded components";
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void excute_filtered_success() {
        ListAllCommand command = new ListAllCommand();
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_NO_STUDENTS);
        model.updateFilteredGradedComponentList(Model.PREDICATE_SHOW_NO_COMPONENT);
        String expectedMessage = "Listed all students, student scores and graded components";
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {

        ListAllCommand firstCommand = new ListAllCommand();
        ListAllCommand secondCommand = new ListAllCommand();

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same type -> returns true
        assertTrue(firstCommand.equals(secondCommand));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));
    }
}
