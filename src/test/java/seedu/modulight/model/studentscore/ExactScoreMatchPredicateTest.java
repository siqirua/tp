package seedu.modulight.model.studentscore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_GCNAME_AMY;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_GCNAME_JAMES;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_SID_AMY;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.MaxMarks;
import seedu.modulight.model.gradedcomponent.Weightage;
import seedu.modulight.model.student.StudentMatchPredicate;
import seedu.modulight.testutil.StudentScoreBuilder;



class ExactScoreMatchPredicateTest {

    @Test
    void test_validPredicate_returnTrue() {
        // empty keyword
        ExactScoreMatchPredicate exactScoreMatchPredicate = new ExactScoreMatchPredicate(List.of());

        GradedComponent gradedComponent =
                new GradedComponent(new GcName(VALID_GCNAME_AMY), new MaxMarks(100), new Weightage(30));
        StudentScore studentScore = new StudentScoreBuilder()
                .withStudentId(VALID_SID_AMY).withGc(gradedComponent).build();

        assertTrue(exactScoreMatchPredicate.test(studentScore));

        // same keyword
        exactScoreMatchPredicate = new ExactScoreMatchPredicate(List.of(new GcName(VALID_GCNAME_AMY)));
        studentScore = new StudentScoreBuilder()
                .withStudentId(VALID_SID_AMY).withGcName(VALID_GCNAME_AMY).build();

        assertTrue(exactScoreMatchPredicate.test(studentScore));

    }

    @Test
    void testEquals() {
        List<GcName> gcNameList = List.of(new GcName(VALID_GCNAME_AMY), new GcName(VALID_GCNAME_JAMES));
        ExactScoreMatchPredicate exactScoreMatchPredicate = new ExactScoreMatchPredicate(gcNameList);

        // Same values -> return true
        ExactScoreMatchPredicate anotherExactScoreMatchPredicate = new ExactScoreMatchPredicate(gcNameList);
        assertTrue(exactScoreMatchPredicate.equals(anotherExactScoreMatchPredicate));

        // Same object -> return true
        assertTrue(exactScoreMatchPredicate.equals(exactScoreMatchPredicate));

        // null -> return false
        assertFalse(exactScoreMatchPredicate.equals(null));

        // Different Type -> return false
        assertFalse(exactScoreMatchPredicate.equals(new StudentMatchPredicate()));

        // Different scoreMatchPredicate -> return false
        ExactScoreMatchPredicate differentExactScoreMatchPredicate =
                new ExactScoreMatchPredicate(List.of(new GcName(VALID_GCNAME_AMY)));
        assertFalse(exactScoreMatchPredicate.equals(differentExactScoreMatchPredicate));

    }

    @Test
    void testToString() {
        ExactScoreMatchPredicate exactScoreMatchPredicate =
                new ExactScoreMatchPredicate(List.of(new GcName(VALID_GCNAME_AMY)));

        assertEquals(exactScoreMatchPredicate.toString(), "Find students by given criteria");
    }
}
