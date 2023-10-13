package seedu.address.model.studentScore;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.gradedComponent.GcName;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;


/**
 * Represents a Student's ID in the address book.
 * Guarantees: immutable; is valid.
 */
public class StudentScore {
    private StudentId sid;
    private StudentName name;
    private GcName gcName;
    private float score;
    private String comment = "";
    /**
     * Constructs a {@code studentScore}.
     *
     * @param sid A valid student score.
     */
    public StudentScore(StudentId sid, GcName gcName, float score, String comment) {
        this.sid = sid;
        this.gcName = gcName;
        this.score = score;
        this.comment = comment;
        // Add student name based on the student id
    }

    /**
     * Construct a StudentScore
     *
     * @param sid A valid student score
     * @param gcName A valid Graded Component
     * @param score the score
     */
    public StudentScore(StudentId sid, GcName gcName, float score) {
        this.sid = sid;
        this.gcName = gcName;
        this.score = score;
        // Add student name based on the student id
    }

    public StudentId getStudentId() {
        return this.sid;
    }

    public StudentName getName() {
        return this.name;
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
    /**
     * Returns true if both student scores have the same StudentId and GcName.
     * This defines a weaker notion of equality between two student scores.
     */
    public boolean isSameScore(StudentScore otherscore) {
        if (otherscore == this) {
            return true;
        }

        return otherscore != null
                && otherscore.getStudentId().equals(getStudentId())
                && otherscore.getGcName().equals(getGcName());
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
        return sid.equals(otherScore.sid) && gcName.equals(((StudentScore) other).gcName);
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
                .add("student name", name)
                .add("component name", gcName)
                .add("score", score)
                .add("comment", comment)
                .toString();
    }

}

