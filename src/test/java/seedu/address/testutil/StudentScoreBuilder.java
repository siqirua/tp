package seedu.address.testutil;

import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentGrade;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

public class StudentScoreBuilder {
    public static final String DEFAULT_STUDENT_ID = "A1234567R";
    public static final String DEFAULT_GCNAME = "Midterm";
    public static final float DEFAULT_SCORE = (float) 1.1;
    public static final String DEFAULT_COMMENT = "Nice!";


    private StudentId sid;
    private StudentName name;
    private GcName gcName;
    private float score;
    private String comment;
    private Student student;
    private GradedComponent gc;
    private Set<Tag> tags;

    public StudentScoreBuilder() {
        sid = new StudentId(DEFAULT_STUDENT_ID);
        gcName = new GcName(DEFAULT_GCNAME);
        score = DEFAULT_SCORE;
        comment = DEFAULT_COMMENT;
        tags = new HashSet<>();
    }

    public StudentScoreBuilder(StudentScore studentScoreToCopy) {
        sid = studentScoreToCopy.getStudentId();
        gcName = studentScoreToCopy.getGcName();
        score = studentScoreToCopy.getScore();
        comment = studentScoreToCopy.getComment();
        tags = studentScoreToCopy.getTags();
    }

    public StudentScoreBuilder withStudentId(String studentId) {
        this.sid = new StudentId(studentId);
        return this;
    }

    public StudentScoreBuilder withGcName(String gcName) {
        this.gcName = new GcName(gcName);
        return this;
    }

    public StudentScoreBuilder withScore(float score) {
        this.score = score;
        return this;
    }

    public StudentScoreBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public StudentScore build() {
        return new StudentScore(sid, gcName, score, comment, tags);
    }
}
