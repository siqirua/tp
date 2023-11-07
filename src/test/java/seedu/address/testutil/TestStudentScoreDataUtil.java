package seedu.address.testutil;

import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.studentscore.model.ReadOnlyStudentScoreBook;
import seedu.address.model.studentscore.model.StudentScoreBook;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Contains utility methods for populating StudentBook with sample data.
 */
public class TestStudentScoreDataUtil {

    private static List<StudentScore> scoreList = new ArrayList<>();

    private static Set<Tag> tags = new HashSet<>();

    public static StudentScore[] getSampleStudentScores() {
        return new StudentScore[]{ };
    }

    public static ReadOnlyStudentScoreBook getSampleStudentScoreBook() {
        StudentScoreBook sampleAb = new StudentScoreBook();
        for (StudentScore sampleStudentScore : getSampleStudentScores()) {
            sampleAb.addStudentScore(sampleStudentScore);
        }
        return sampleAb;
    }
}
