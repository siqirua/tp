package seedu.modulight.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulight.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modulight.testutil.TypicalStudents.ALICE;
import static seedu.modulight.testutil.TypicalStudents.BENSON;
import static seedu.modulight.testutil.TypicalStudents.CARL;
import static seedu.modulight.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modulight.logic.Messages;
import seedu.modulight.model.Model;
import seedu.modulight.model.ModelManager;
import seedu.modulight.model.UserPrefs;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;
import seedu.modulight.model.student.StudentMatchPredicate;
import seedu.modulight.model.studentscore.ScoreMatchPredicate;
import seedu.modulight.model.studentscore.model.StudentScoreBook;

public class FindStudentCommandTest {
    private Model model = new ModelManager(getTypicalStudentBook(), new StudentScoreBook(), new GradedComponentBook(),
            new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), new StudentScoreBook(),
            new GradedComponentBook(), new UserPrefs());

    @Test
    public void equals() {
        List<String> firstKeywords = new ArrayList<>(List.of("first"));
        List<String> secondKeywords = new ArrayList<>(List.of("second"));
        StudentMatchPredicate firstPredicate = new StudentMatchPredicate(firstKeywords, firstKeywords,
                firstKeywords, firstKeywords, firstKeywords);
        StudentMatchPredicate secondPredicate = new StudentMatchPredicate(secondKeywords, secondKeywords,
                secondKeywords, secondKeywords, secondKeywords);
        FindStudentCommand findFirstCommand = new FindStudentCommand(firstPredicate,
                new ScoreMatchPredicate(firstPredicate));
        FindStudentCommand findSecondCommand = new FindStudentCommand(secondPredicate,
                new ScoreMatchPredicate(secondPredicate));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStudentCommand findFirstCommandCopy = new FindStudentCommand(firstPredicate,
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
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 7) + "\n"
                + String.format(Messages.MESSAGE_SCORE_LISTED_OVERVIEW, 0);
        StudentMatchPredicate predicate = new StudentMatchPredicate(emptyKeywords, emptyKeywords,
                emptyKeywords, emptyKeywords, emptyKeywords);
        FindStudentCommand command = new FindStudentCommand(predicate, new ScoreMatchPredicate(predicate));
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalStudentBook().getStudentList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 3) + "\n"
                + String.format(Messages.MESSAGE_SCORE_LISTED_OVERVIEW, 0);
        List<String> emptyKeywords = new ArrayList<>();
        List<String> tgKeywords = Arrays.asList("T01");
        StudentMatchPredicate predicate = new StudentMatchPredicate(emptyKeywords, emptyKeywords, emptyKeywords,
                tgKeywords, emptyKeywords);
        FindStudentCommand command = new FindStudentCommand(predicate, new ScoreMatchPredicate(predicate));
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredStudentList());
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = Arrays.asList("keyword");
        StudentMatchPredicate predicate = new StudentMatchPredicate(keywords, keywords, keywords, keywords, keywords);
        FindStudentCommand findCommand = new FindStudentCommand(predicate, new ScoreMatchPredicate(predicate));
        String expected = FindStudentCommand.class.getCanonicalName() + "{student predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}
