package seedu.modulight.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modulight.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.modulight.testutil.Assert.assertThrows;
import static seedu.modulight.testutil.TypicalStudents.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.modulight.commons.exceptions.IllegalValueException;
import seedu.modulight.model.student.StudentEmail;
import seedu.modulight.model.student.StudentGrade;
import seedu.modulight.model.student.StudentId;
import seedu.modulight.model.student.StudentName;
import seedu.modulight.model.student.TutorialGroup;

public class JsonAdaptedStudentTest {

    private static final String INVALID_STUDENT_ID = "0000";
    private static final String INVALID_STUDENT_NAME = "R@chel";
    private static final String INVALID_STUDENT_EMAIL = "example.com";
    private static final String INVALID_TUTORIAL_GROUP = "12A";
    private static final String INVALID_STUDENT_GRADE = "AAA";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_STUDENT_ID = BENSON.getStudentId().toString();
    private static final String VALID_STUDENT_NAME = BENSON.getName().toString();
    private static final String VALID_STUDENT_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_TUTORIAL_GROUP = BENSON.getTutorial().toString();
    private static final String VALID_STUDENT_GRADE = BENSON.getStudentGrade().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_InvalidStudentID_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(INVALID_STUDENT_ID, VALID_STUDENT_NAME, VALID_STUDENT_EMAIL
            , VALID_TUTORIAL_GROUP, VALID_TAGS, VALID_STUDENT_GRADE);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentID_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_STUDENT_NAME, VALID_STUDENT_EMAIL
            , VALID_TUTORIAL_GROUP, VALID_TAGS, VALID_STUDENT_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_InvalidStudentName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, INVALID_STUDENT_NAME, VALID_STUDENT_EMAIL
            , VALID_TUTORIAL_GROUP, VALID_TAGS, VALID_STUDENT_GRADE);
        String expectedMessage = StudentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, null, VALID_STUDENT_EMAIL
            , VALID_TUTORIAL_GROUP, VALID_TAGS, VALID_STUDENT_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_InvalidStudentEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_STUDENT_NAME, INVALID_STUDENT_EMAIL
            , VALID_TUTORIAL_GROUP, VALID_TAGS, VALID_STUDENT_GRADE);
        String expectedMessage = StudentEmail.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_STUDENT_NAME, null
            , VALID_TUTORIAL_GROUP, VALID_TAGS, VALID_STUDENT_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentEmail.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_InvalidTutorialGroup_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_STUDENT_NAME, VALID_STUDENT_EMAIL
            , INVALID_TUTORIAL_GROUP, VALID_TAGS, VALID_STUDENT_GRADE);
        String expectedMessage = TutorialGroup.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullTutorialGroup_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_STUDENT_NAME, VALID_STUDENT_EMAIL
            , null, VALID_TAGS, VALID_STUDENT_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TutorialGroup.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_InvalidStudentGrade_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_STUDENT_NAME, VALID_STUDENT_EMAIL
            , VALID_TUTORIAL_GROUP, VALID_TAGS, INVALID_STUDENT_GRADE);
        String expectedMessage = StudentGrade.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentGrade_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_STUDENT_NAME, VALID_STUDENT_EMAIL
            , VALID_TUTORIAL_GROUP, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentGrade.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_STUDENT_NAME, VALID_STUDENT_EMAIL
            , VALID_TUTORIAL_GROUP, invalidTags, VALID_STUDENT_GRADE);
        assertThrows(IllegalValueException.class, student::toModelType);
    }


}
