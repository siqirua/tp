package seedu.modulight.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulight.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modulight.testutil.TypicalStudents.getTypicalComponentBook;
import static seedu.modulight.testutil.TypicalStudents.getTypicalComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modulight.logic.Messages;
import seedu.modulight.model.Model;
import seedu.modulight.model.ModelManager;
import seedu.modulight.model.UserPrefs;
import seedu.modulight.model.gradedcomponent.GcMatchPredicate;
import seedu.modulight.model.student.model.StudentBook;
import seedu.modulight.model.studentscore.ScoreMatchPredicate;
import seedu.modulight.model.studentscore.model.StudentScoreBook;
import seedu.modulight.testutil.TestGcDataUtil;

public class FindGradedComponentCommandTest {
    private Model model = new ModelManager(new StudentBook(), new StudentScoreBook(), getTypicalComponentBook(),
            new UserPrefs());
    private Model expectedModel = new ModelManager(new StudentBook(), new StudentScoreBook(), getTypicalComponentBook(),
            new UserPrefs());
    @Test
    public void equals() {
        List<String> firstKeywords = new ArrayList<>(List.of("first"));
        List<String> secondKeywords = new ArrayList<>(List.of("second"));
        GcMatchPredicate firstPredicate = new GcMatchPredicate(firstKeywords);
        GcMatchPredicate secondPredicate = new GcMatchPredicate(secondKeywords);
        FindGradedComponentCommand findFirstCommand = new FindGradedComponentCommand(firstPredicate,
                new ScoreMatchPredicate(firstPredicate));
        FindGradedComponentCommand findSecondCommand = new FindGradedComponentCommand(secondPredicate,
                new ScoreMatchPredicate(secondPredicate));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindGradedComponentCommand findFirstCommandCopy = new FindGradedComponentCommand(firstPredicate,
                new ScoreMatchPredicate(firstPredicate));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allStudentsShown() {
        List<String> emptyKeywords = new ArrayList<>();
        String expectedMessage = String.format(Messages.MESSAGE_COMP_LISTED_OVERVIEW, 2) + "\n"
                + String.format(Messages.MESSAGE_SCORE_LISTED_OVERVIEW, 0);
        GcMatchPredicate predicate = new GcMatchPredicate(emptyKeywords);
        FindGradedComponentCommand command = new FindGradedComponentCommand(predicate,
                new ScoreMatchPredicate(predicate));
        expectedModel.updateFilteredGradedComponentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalComponents(), model.getFilteredGradedComponentList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_COMP_LISTED_OVERVIEW, 1) + "\n"
                + String.format(Messages.MESSAGE_SCORE_LISTED_OVERVIEW, 0);
        List<String> gcKeywords = Arrays.asList("midterm");
        GcMatchPredicate predicate = new GcMatchPredicate(gcKeywords);
        FindGradedComponentCommand command = new FindGradedComponentCommand(predicate,
                new ScoreMatchPredicate(predicate));
        expectedModel.updateFilteredGradedComponentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TestGcDataUtil.getTestGradedComponents()[0]),
                model.getFilteredGradedComponentList());
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = Arrays.asList("keyword");
        GcMatchPredicate predicate = new GcMatchPredicate(keywords);
        FindGradedComponentCommand findCommand = new FindGradedComponentCommand(predicate,
                new ScoreMatchPredicate(predicate));
        String expected = FindGradedComponentCommand.class.getCanonicalName() + "{graded component predicate="
                + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}
