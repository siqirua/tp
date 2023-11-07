package seedu.address.model.studentscore;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.StudentScoreBuilder;

import static org.junit.jupiter.api.Assertions.*;

class StudentScoreTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        StudentScore studentScore = new StudentScoreBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> studentScore.getTags().remove(0));
    }

    @Test
    public void testEquals() {
        // same object -> returns true

        // null -> returns false

        // same studentId, same gradedComponent Name -> returns true


        // different name, all other attributes same -> returns false

        // name differs in case, all other attributes same -> returns false


        // name has trailing spaces, all other attributes same -> returns false

        // different studentId -> return false

        // different gradedComponent Name -> return false


    }

    @Test
    public void ToStringMethod() {
    }
}