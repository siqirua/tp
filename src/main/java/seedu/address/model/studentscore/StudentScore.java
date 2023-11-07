package seedu.address.model.studentscore;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.gradedcomponent.MaxMarks;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;
import seedu.address.model.tag.Tag;


/**
 * Represents a Student's ID in the address book.
 * Guarantees: immutable; is valid.
 */
public class StudentScore {
    private StudentId sid;
    private GcName gcName;
    private float score;
    private String comment = "";
    private Student student;
    private GradedComponent gc;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code studentScore}.
     *
     * @param sid A valid student score.
     */
    public StudentScore(StudentId sid, GcName gcName, float score, String comment, Set<Tag> tagSet) {
        requireAllNonNull(sid, gcName, score);
        this.sid = sid;
        this.gcName = gcName;
        this.score = score;
        this.comment = comment;
        this.tags.addAll(tagSet);
    }

    /**
     * Construct a StudentScore
     *
     * @param sid A valid student score
     * @param gcName A valid Graded Component
     * @param score the score
     */
    public StudentScore(StudentId sid, GcName gcName, float score) {
        requireAllNonNull(sid, gcName, score);
        this.sid = sid;
        this.gcName = gcName;
        this.score = score;

    }

    private boolean isValidScore(float s) {
        boolean isLessEqualThanMaxMarks = true;
        if (this.gc != null) {
            isLessEqualThanMaxMarks = s <= this.gc.getMaxMarks().maxMarks;
        }
        return s >= 0 && isLessEqualThanMaxMarks;
    }

    public StudentId getStudentId() {
        return this.sid;
    }

    public GcName getGcName() {
        return this.gcName;
    }

    public float getScore() {
        return this.score;
    }

    public String getComment() {
        return this.comment;
    }
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setGcName(GcName newName) {
        this.gcName = newName;
    }
    public Student getStudent() {
        return this.student;
    }
    public void setStudent(Student s) {
        this.student = s;
    }
    public GradedComponent getGradedComponent() {
        return this.gc;
    }
    public void setGradedComponent(GradedComponent gc) {
        this.gc = gc;
    }
    /**
     * Calculates this studentScore's relative score from the GradedComponent's
     * maximum marks.
     */
    public float calcRelativeScore() {
        assert this.gc != null;
        MaxMarks mm = this.gc.getMaxMarks();
        float maxMarks = mm.maxMarks;
        if (maxMarks == 0) {
            return 0;
        }
        return Math.min(score / maxMarks * 100, 100);
    }
    /**
     * Returns true if both student scores have the same StudentId and GcName.
     * This defines a weaker notion of equality between two student scores.
     */
    public boolean isSameScore(StudentScore otherScore) {
        if (otherScore == this) {
            return true;
        }

        return otherScore != null
                && otherScore.getStudentId().equals(getStudentId())
                && otherScore.getGcName().equals(getGcName());
    }

    /**
     * Returns true if both student scores have the same identity and data fields.
     * This defines a stronger notion of equality between two student scores.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentScore)) {
            return false;
        }

        StudentScore otherScore = (StudentScore) other;
        return sid.equals(otherScore.sid) && gcName.equals(otherScore.gcName);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(sid);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student id", sid)
                .add("component name", gcName)
                .add("score", score)
                .add("comment", comment)
                .add("tags", tags)
                .toString();
    }

}

