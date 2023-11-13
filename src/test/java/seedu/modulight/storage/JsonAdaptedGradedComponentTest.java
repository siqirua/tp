package seedu.modulight.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modulight.storage.JsonAdaptedGradedComponent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.modulight.testutil.Assert.assertThrows;
import static seedu.modulight.testutil.TypicalGradedComponents.MIDTERMS;

import org.junit.jupiter.api.Test;

import seedu.modulight.commons.exceptions.IllegalValueException;
import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.gradedcomponent.MaxMarks;
import seedu.modulight.model.gradedcomponent.Weightage;

public class JsonAdaptedGradedComponentTest {

    private static final String INVALID_GRADED_COMPONENT_NAME = "M@idterm";
    private static final String INVALID_MAX_MARKS = "10001";
    private static final String INVALID_WEIGHTAGE = "1000";

    private static final String VALID_GRADED_COMPONENT_NAME = MIDTERMS.getName().toString();
    private static final String VALID_MAX_MARKS = MIDTERMS.getMaxMarks().toString();
    private static final String VALID_WEIGHTAGE = MIDTERMS.getWeightage().toString();

    @Test
    public void toModelType_validGcDetails_returnsGc() throws Exception {
        JsonAdaptedGradedComponent gc = new JsonAdaptedGradedComponent(MIDTERMS);
        assertEquals(MIDTERMS, gc.toModelType());
    }

    @Test
    public void toModelType_InvalidGcName_throwsIllegalValueException() {
        JsonAdaptedGradedComponent gc = new JsonAdaptedGradedComponent(INVALID_GRADED_COMPONENT_NAME, VALID_MAX_MARKS
            , VALID_WEIGHTAGE);
        String expectedMessage = GcName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, gc::toModelType);
    }

    @Test
    public void toModelType_nullGcName_throwsIllegalValueException() {
        JsonAdaptedGradedComponent gc = new JsonAdaptedGradedComponent(null, VALID_MAX_MARKS, VALID_WEIGHTAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GcName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, gc::toModelType);
    }

    @Test
    public void toModelType_InvalidMaxMarks_throwsIllegalValueException() {
        JsonAdaptedGradedComponent gc = new JsonAdaptedGradedComponent(VALID_GRADED_COMPONENT_NAME, INVALID_MAX_MARKS
            , VALID_WEIGHTAGE);
        String expectedMessage = MaxMarks.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, gc::toModelType);
    }

    @Test
    public void toModelType_nullMaxMarks_throwsIllegalValueException() {
        JsonAdaptedGradedComponent gc = new JsonAdaptedGradedComponent(VALID_GRADED_COMPONENT_NAME, null
            , VALID_WEIGHTAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MaxMarks.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, gc::toModelType);
    }

    @Test
    public void toModelType_InvalidWeightage_throwsIllegalValueException() {
        JsonAdaptedGradedComponent gc = new JsonAdaptedGradedComponent(VALID_GRADED_COMPONENT_NAME, VALID_MAX_MARKS
            , INVALID_WEIGHTAGE);
        String expectedMessage = Weightage.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, gc::toModelType);
    }

    @Test
    public void toModelType_nullWeightage_throwsIllegalValueException() {
        JsonAdaptedGradedComponent gc = new JsonAdaptedGradedComponent(VALID_GRADED_COMPONENT_NAME, VALID_MAX_MARKS
            , null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weightage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, gc::toModelType);
    }




}
