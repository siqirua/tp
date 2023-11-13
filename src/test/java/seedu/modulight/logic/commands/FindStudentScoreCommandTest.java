package seedu.modulight.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulight.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modulight.testutil.TypicalStudents.getTypicalComponentBook;
import static seedu.modulight.testutil.TypicalStudents.getTypicalScoreBook;
import static seedu.modulight.testutil.TypicalStudents.getTypicalStudentBook;
import static seedu.modulight.testutil.TypicalStudents.getTypicalStudentScores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modulight.logic.Messages;
import seedu.modulight.model.Model;
import seedu.modulight.model.ModelManager;
import seedu.modulight.model.UserPrefs;
import seedu.modulight.model.studentscore.ScoreMatchPredicate;

public class FindStudentScoreCommandTest {
    private Model model = new ModelManager(getTypicalStudentBook(), getTypicalScoreBook(), getTypicalComponentBook(),
            new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), getTypicalScoreBook(),
            getTypicalComponentBook(), new UserPrefs());
    @Test
    public void equals() {
        List<String> firstKeywords = new ArrayList<>(List.of("first"));
        List<String> secondKeywords = new ArrayList<>(List.of("second"));
        ScoreMatchPredicate firstPredicate = new ScoreMatchPredicate(firstKeywords, firstKeywords,
                firstKeywords, firstKeywords, firstKeywords, firstKeywords);
        ScoreMatchPredicate secondPredicate = new ScoreMatchPredicate(secondKeywords, secondKeywords,
                secondKeywords, secondKeywords, secondKeywords, secondKeywords);
        FindStudentScoreCommand findFirstCommand = new FindStudentScoreCommand(firstPredicate);
        FindStudentScoreCommand findSecondCommand = new FindStudentScoreCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStudentScoreCommand findFirstCommandCopy = new FindStudentScoreCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allScoresShown() {
        model.updateFilteredStudentScoreList(Model.PREDICATE_SHOW_ALL_STUDENT_SCORES);
        expectedModel.updateFilteredStudentScoreList(Model.PREDICATE_SHOW_ALL_STUDENT_SCORES);
        expectedModel.updateFilteredStudentList(Model.PREDICATE_SHOW_NO_STUDENTS);
        expectedModel.updateFilteredGradedComponentList(Model.PREDICATE_SHOW_NO_COMPONENT);
        List<String> emptyKeywords = new ArrayList<>();
        String expectedMessage = String.format(Messages.MESSAGE_SCORE_LISTED_OVERVIEW, 14);
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(emptyKeywords, emptyKeywords,
                emptyKeywords, emptyKeywords, emptyKeywords, emptyKeywords);
        FindStudentScoreCommand command = new FindStudentScoreCommand(predicate);
        expectedModel.updateFilteredStudentScoreList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalStudentScores(), model.getFilteredStudentScoreList());
    }

    @Test
    public void execute_tgKeywords_multipleScoresFound() {
        model.updateFilteredStudentScoreList(Model.PREDICATE_SHOW_ALL_STUDENT_SCORES);
        expectedModel.updateFilteredStudentScoreList(Model.PREDICATE_SHOW_ALL_STUDENT_SCORES);
        String expectedMessage = String.format(Messages.MESSAGE_SCORE_LISTED_OVERVIEW, 6);
        List<String> emptyKeywords = new ArrayList<>();
        List<String> tgKeywords = Arrays.asList("T01");
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(emptyKeywords, emptyKeywords, tgKeywords,
                emptyKeywords, emptyKeywords, emptyKeywords);
        FindStudentScoreCommand command = new FindStudentScoreCommand(predicate);
        expectedModel.updateFilteredStudentList(Model.PREDICATE_SHOW_NO_STUDENTS);
        expectedModel.updateFilteredGradedComponentList(Model.PREDICATE_SHOW_NO_COMPONENT);
        expectedModel.updateFilteredStudentScoreList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = Arrays.asList("keyword");
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(keywords, keywords, keywords,
                keywords, keywords, keywords);
        FindStudentScoreCommand findCommand = new FindStudentScoreCommand(predicate);
        String expected = FindStudentScoreCommand.class.getCanonicalName() + "{score predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}
