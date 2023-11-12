package seedu.modulight.model.studentscore.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_COMMENT_AMY;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_GCNAME_AMY;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_SCORE_AMY;
import static seedu.modulight.testutil.TestGcDataUtil.getTestGcBook;
import static seedu.modulight.testutil.TestStudentDataUtil.getTestStudentBook;
import static seedu.modulight.testutil.TestStudentScoreDataUtil.getSampleStudentScoreBook;

import org.junit.jupiter.api.Test;

import seedu.modulight.model.Model;
import seedu.modulight.model.ModelManager;
import seedu.modulight.model.UserPrefs;
import seedu.modulight.model.studentscore.StudentScore;
import seedu.modulight.testutil.StudentScoreBuilder;

public class StudentScoreBookTest {
    @Test
    public void hasStudentScore_studentScoreExist_success() {
        Model model = new ModelManager(getTestStudentBook("create"),
                getSampleStudentScoreBook("twoScores"),
                getTestGcBook("create"), new UserPrefs());

        StudentScore studentScore = new StudentScoreBuilder()
                .withScore(VALID_SCORE_AMY).withComment(VALID_COMMENT_AMY).build();

        StudentScoreBook studentScoreBook = model.getStudentScoreBook();
        studentScoreBook.addStudentScore(studentScore);

        assertTrue(studentScoreBook.hasStudentScore(studentScore));

    }

    @Test
    public void getScoreByIdAndName_studentScoreExist_success() {
        Model model = new ModelManager(getTestStudentBook("create"),
                getSampleStudentScoreBook("twoScores"),
                getTestGcBook("create"), new UserPrefs());

        StudentScore studentScore = new StudentScoreBuilder().withGcName(VALID_GCNAME_AMY)
                .withScore(VALID_SCORE_AMY).withComment(VALID_COMMENT_AMY).build();

        StudentScoreBook studentScoreBook = model.getStudentScoreBook();
        studentScoreBook.addStudentScore(studentScore);

        StudentScore retrievedStudentScore = studentScoreBook.getScoreByIdAndName(
                studentScore.getStudentId(), studentScore.getGcName());

        assertTrue(studentScore.isSameScore(retrievedStudentScore));

    }
}
