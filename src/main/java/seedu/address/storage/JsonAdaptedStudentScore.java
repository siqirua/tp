package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.gradedcomponent.MaxMarks;
import seedu.address.model.gradedcomponent.Weightage;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;
import seedu.address.model.studentscore.StudentScore;


/**
 * Jackson-friendly version of {@link StudentScore}.
 */
public class JsonAdaptedStudentScore {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Score's %s field is missing!";
    private static final String DEFAULT_MAX = "100";
    private static final String DEFAULT_WEIGHTAGE = "100";

    private final String studentId;
    private final String studentName;
    private final String gcName;
    private final String gcMaxMarks;
    private final String gcWeightage;
    private final String score;
    private final String comment;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudentScore(@JsonProperty("studentId") String studentId,
                                   @JsonProperty("studentName") String studentName,
                                   @JsonProperty("gradedComponentName") String gcName,
                                   @JsonProperty("gradedComponentMaximumMarks") String gcMax,
                                   @JsonProperty("gradedComponentWeightage") String gcWeightage,
                                   @JsonProperty("score") String score,
                                   @JsonProperty("comment") String comment) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gcName = gcName;
        this.gcMaxMarks = gcMax;
        this.gcWeightage = gcWeightage;
        this.score = score;
        this.comment = comment;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudentScore(StudentScore source) {
        studentId = source.getStudentId().sid;
        studentName = source.getName().fullName;
        gcName = source.getGcName().gcName;
        gcMaxMarks = DEFAULT_MAX;
        gcWeightage = DEFAULT_WEIGHTAGE;
        score = String.valueOf(source.getScore());
        comment = source.getComment();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public StudentScore toModelType() throws IllegalValueException {
        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidName(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        if (studentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StudentName.class.getSimpleName()));
        }
        if (!StudentName.isValidName(studentName)) {
            throw new IllegalValueException(StudentName.MESSAGE_CONSTRAINTS);
        }
        final StudentName modelStudentName = new StudentName(studentName);

        final GcName newGcName = new GcName(gcName);
        final GradedComponent gradedComponent = new GradedComponent(newGcName,
                new MaxMarks(Double.parseDouble(gcMaxMarks)), new Weightage(Double.parseDouble(gcWeightage)));

        //check validity of scores
        final float modelScore = Float.parseFloat(score);

        final String modelComment = comment;

        return new StudentScore(modelStudentId, newGcName, modelScore, modelComment);
    }

}
