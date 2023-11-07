package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.studentscore.model.ReadOnlyStudentScoreBook;
import seedu.address.model.studentscore.model.StudentScoreBook;


/**
 * Contains utility methods for populating StudentBook with sample data.
 */
public class TestStudentScoreDataUtil {
    private static GcName gcName1 = new GcName("Midterm");

    public static ArrayList<StudentScore> getTestStudentScoresEmpty() {
        return new ArrayList<>();
    }

    public static ArrayList<StudentScore> getTestStudentZeroScores() {
        StudentScore[] studentScores = new StudentScore[]{
            new StudentScore(new StudentId("A0000000Y"), gcName1, 0),
            new StudentScore(new StudentId("A0000001Y"), gcName1, 0),
            new StudentScore(new StudentId("A0000002Y"), gcName1, 0),
            new StudentScore(new StudentId("A0000003Y"), gcName1, 0),
            new StudentScore(new StudentId("A0000004Y"), gcName1, 0),
            new StudentScore(new StudentId("A0000005Y"), gcName1, 0),
        };
        return new ArrayList<>(List.of(studentScores));
    }

    public static ArrayList<StudentScore> getTestStudentScores() {
        float markToBeAdded = 0;
        int size = getTestStudentScoresEmpty().size();
        ArrayList<StudentScore> newScores = new ArrayList<>();
        for (StudentScore score : getTestStudentZeroScores()) {
            newScores.add(new StudentScore(score.getStudentId(), score.getGcName(), markToBeAdded,
                    score.getComment(), score.getTags()));
            markToBeAdded += size > 1 ? (float) 100 / (size - 1) : 0;
        }
        return newScores;
    }

    public static ReadOnlyStudentScoreBook getSampleStudentScoreBook(String selectedScore) {
        StudentScoreBook sampleScore = new StudentScoreBook();
        ArrayList<StudentScore> scoreToBeAdded;
        switch (selectedScore) {
        case "create":
            scoreToBeAdded = getTestStudentScores();
            break;
        case "zeroScore":
            scoreToBeAdded = getTestStudentZeroScores();
            break;
        default:
            scoreToBeAdded = getTestStudentScoresEmpty();
        }
        for (StudentScore testScore: scoreToBeAdded) {
            sampleScore.addStudentScore(testScore);
        }
        return sampleScore;
    }
}
