package seedu.modulight.testutil;

import static seedu.modulight.logic.commands.CommandTestUtil.VALID_STUDENT_SCORE_AMY;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_STUDENT_SCORE_JAMES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.modulight.model.studentscore.StudentScore;
import seedu.modulight.model.studentscore.model.StudentScoreBook;

public class TypicalStudentScores {

    private TypicalStudentScores() {};

    public static StudentScoreBook getTypicalStudentScoreBook() {
        StudentScoreBook ssb = new StudentScoreBook();
        for (StudentScore studentScore : getTypicalStudentScores()) {
            ssb.addStudentScore(studentScore);
        }
        return ssb;
    }

    public static List<StudentScore> getTypicalStudentScores() {
        return new ArrayList<>(Arrays.asList(VALID_STUDENT_SCORE_AMY, VALID_STUDENT_SCORE_JAMES));
    }
}
