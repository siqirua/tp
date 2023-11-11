package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.gradedcomponent.model.GradedComponentBook;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentMatchPredicate;
import seedu.address.model.studentscore.model.StudentScoreBook;

public class DeleteStudentCommandTest {
    private Model model = new ModelManager(getTypicalStudentBook(), new StudentScoreBook(),
            new GradedComponentBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStudentCommand deleteCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(studentToDelete));

        ModelManager expectedModel = new ModelManager(model.getStudentBook(), new StudentScoreBook(),
                new GradedComponentBook(),new UserPrefs());
        expectedModel.getStudentBook().removeStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Model modelCopy = new ModelManager(getTypicalStudentBook(), new StudentScoreBook(),
                new GradedComponentBook(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteStudentCommand deleteCommand = new DeleteStudentCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model,
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX), modelCopy);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {
        List<String> emptyKeywords= new ArrayList<>();
        List<String> nameMatchKeywords = new ArrayList<>(List.of("Alice Pauline"));
        StudentMatchPredicate nameMatchPredicate = new StudentMatchPredicate(emptyKeywords, nameMatchKeywords,
                emptyKeywords, emptyKeywords, emptyKeywords);
        model.updateFilteredStudentList(nameMatchPredicate);
        DeleteStudentCommand deleteCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);

        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        System.out.println(studentToDelete.getName());
        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(studentToDelete));
        Model expectedModel = new ModelManager(model.getStudentBook(), new StudentScoreBook(),
                new GradedComponentBook(), new UserPrefs());
        expectedModel.getStudentBook().removeStudent(studentToDelete);
        showNoStudent(expectedModel);
        System.out.println(expectedModel.getFilteredStudentList().size());
        System.out.println(model.getFilteredStudentList().size());
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Model modelCopy = new ModelManager(getTypicalStudentBook(), new StudentScoreBook(),
                new GradedComponentBook(), new UserPrefs());
        List<String> emptyKeywords= new ArrayList<>();
        List<String> noMatchKeywords = new ArrayList<>(List.of("no match"));
        StudentMatchPredicate noMatchPredicate = new StudentMatchPredicate(noMatchKeywords,
                emptyKeywords, emptyKeywords, emptyKeywords,
                emptyKeywords);
        model.updateFilteredStudentList(noMatchPredicate);
        modelCopy.updateFilteredStudentList(noMatchPredicate);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudentBook().getStudentList().size());

        DeleteStudentCommand deleteCommand = new DeleteStudentCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model,
                new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX), modelCopy);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteStudentCommand deleteCommand = new DeleteStudentCommand(targetIndex);
        String expected = DeleteStudentCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);
        assertTrue(model.getFilteredStudentList().isEmpty());
    }
    
}
