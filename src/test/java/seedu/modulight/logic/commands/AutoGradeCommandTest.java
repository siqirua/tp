package seedu.modulight.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.assertCommandFailure;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.assertCommandSuccess;
import static seedu.modulight.testutil.TestGcDataUtil.getTestGcBook;
import static seedu.modulight.testutil.TestStudentDataUtil.getTestStudentBook;
import static seedu.modulight.testutil.TestStudentScoreDataUtil.getSampleStudentScoreBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.modulight.commons.core.index.Index;
import seedu.modulight.logic.commands.exceptions.CommandException;
import seedu.modulight.model.Model;
import seedu.modulight.model.ModelManager;
import seedu.modulight.model.UserPrefs;
import seedu.modulight.model.student.Student;
import seedu.modulight.testutil.EditStudentDescriptorBuilder;

class AutoGradeCommandTest {
    private final Model model = new ModelManager(getTestStudentBook("create"),
            getSampleStudentScoreBook("twoScores"),
            getTestGcBook("create"), new UserPrefs());

    @Test
    void execute_validAutoGradeCommand_success() {
        float[] passingValue = {70, 40};
        AutoGradeCommand autoGradeCommand = new AutoGradeCommand(passingValue, AutoGradeCommand.AutoGradeType.ABSOLUTE);

        Model expectedModel = new ModelManager(getTestStudentBook("create"),
                getSampleStudentScoreBook("twoScores"),
                getTestGcBook("create"), new UserPrefs());
        expectedModel.getStudentBook().sortStudent("o", true);
        for (int i = 0; i < expectedModel.getStudentBook().getSize(); i++) {
            Index index = Index.fromZeroBased(i);
            Student student = expectedModel.getStudentBook().getStudentList().get(i);

            String grade = "F";
            if (student.getTotalScore() >= passingValue[0]) {
                grade = "A+";
            } else if (student.getTotalScore() >= passingValue[1]) {
                grade = "A";
            }

            EditStudentCommand.EditStudentDescriptor descriptor =
                    new EditStudentDescriptorBuilder().withGrade(grade).build();
            try {
                new EditStudentCommand(index, descriptor).execute(expectedModel);
            } catch (CommandException ce) {
                throw new AssertionError("Execution of command in positive test cases should not fail.", ce);
            }
        }

        assertCommandSuccess(autoGradeCommand, model, AutoGradeCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_passingScoreValueIsZero_success() {
        float[] passingValueZero = {0};
        // percentile
        AutoGradeCommand autoGradeCommand =
                new AutoGradeCommand(passingValueZero, AutoGradeCommand.AutoGradeType.PERCENTILE);

        Model expectedModel = new ModelManager(getTestStudentBook("create"),
                getSampleStudentScoreBook("twoScores"),
                getTestGcBook("create"), new UserPrefs());
        expectedModel.getStudentBook().sortStudent("o", true);
        EditStudentCommand.EditStudentDescriptor descriptor =
                new EditStudentDescriptorBuilder().withGrade("A+").build();
        for (int i = 0; i < expectedModel.getStudentBook().getSize(); i++) {
            Index index = Index.fromZeroBased(i);
            try {
                new EditStudentCommand(index, descriptor).execute(expectedModel);
            } catch (CommandException ce) {
                throw new AssertionError("Execution of command in positive test cases should not fail.", ce);
            }
        }

        assertCommandSuccess(autoGradeCommand, model, AutoGradeCommand.MESSAGE_SUCCESS, expectedModel);

        // absolute
        autoGradeCommand = new AutoGradeCommand(passingValueZero, AutoGradeCommand.AutoGradeType.ABSOLUTE);

        expectedModel = new ModelManager(getTestStudentBook("create"),
                getSampleStudentScoreBook("twoScores"),
                getTestGcBook("create"), new UserPrefs());
        expectedModel.getStudentBook().sortStudent("o", true);
        descriptor = new EditStudentDescriptorBuilder().withGrade("A+").build();
        for (int i = 0; i < expectedModel.getStudentBook().getSize(); i++) {
            Index index = Index.fromZeroBased(i);
            try {
                new EditStudentCommand(index, descriptor).execute(expectedModel);
            } catch (CommandException ce) {
                throw new AssertionError("Execution of command in positive test cases should not fail.", ce);
            }
        }

        assertCommandSuccess(autoGradeCommand, model, AutoGradeCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_passingScoreValueIs100_success() {
        float[] passingValue100 = {100};
        // percentile
        AutoGradeCommand autoGradeCommand =
                new AutoGradeCommand(passingValue100, AutoGradeCommand.AutoGradeType.PERCENTILE);

        Model expectedModel = new ModelManager(getTestStudentBook("create"),
                getSampleStudentScoreBook("twoScores"),
                getTestGcBook("create"), new UserPrefs());
        expectedModel.getStudentBook().sortStudent("o", true);

        EditStudentCommand.EditStudentDescriptor descriptor =
                new EditStudentDescriptorBuilder().withGrade("A+").build();
        Index index = Index.fromZeroBased(0);
        try {
            new EditStudentCommand(index, descriptor).execute(expectedModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command in positive test cases should not fail.", ce);
        }

        descriptor = new EditStudentDescriptorBuilder().withGrade("F").build();
        for (int i = 1; i < expectedModel.getStudentBook().getSize(); i++) {
            index = Index.fromZeroBased(i);
            try {
                new EditStudentCommand(index, descriptor).execute(expectedModel);
            } catch (CommandException ce) {
                throw new AssertionError("Execution of command in positive test cases should not fail.", ce);
            }
        }

        assertCommandSuccess(autoGradeCommand, model, AutoGradeCommand.MESSAGE_SUCCESS, expectedModel);

        // absolute
        autoGradeCommand = new AutoGradeCommand(passingValue100, AutoGradeCommand.AutoGradeType.ABSOLUTE);

        expectedModel = new ModelManager(getTestStudentBook("create"),
                getSampleStudentScoreBook("twoScores"),
                getTestGcBook("create"), new UserPrefs());
        expectedModel.getStudentBook().sortStudent("o", true);
        descriptor = new EditStudentDescriptorBuilder().withGrade("F").build();
        for (int i = 0; i < expectedModel.getStudentBook().getSize(); i++) {
            index = Index.fromZeroBased(i);
            try {
                new EditStudentCommand(index, descriptor).execute(expectedModel);
            } catch (CommandException ce) {
                throw new AssertionError("Execution of command in positive test cases should not fail.", ce);
            }
        }

        assertCommandSuccess(autoGradeCommand, model, AutoGradeCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_passingScoreValueTooMany_failure() {
        float[] passingValue = {95, 90, 85, 80, 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20};
        AutoGradeCommand autoGradeCommand =
                new AutoGradeCommand(passingValue, AutoGradeCommand.AutoGradeType.PERCENTILE);

        assertCommandFailure(autoGradeCommand, model, AutoGradeCommand.MESSAGE_TOO_MANY_VALUE);
    }

    @Test
    void testEquals() {
        final float[] passingValueArray = {90, 75, 50, 40, 35, 30, 25, 20, 17, 15, 13};
        // same value -> true
        AutoGradeCommand autoGradeCommand =
                new AutoGradeCommand(passingValueArray, AutoGradeCommand.AutoGradeType.PERCENTILE);
        AutoGradeCommand anotherAutoGradeCommand =
                new AutoGradeCommand(passingValueArray, AutoGradeCommand.AutoGradeType.PERCENTILE);
        assertTrue(autoGradeCommand.equals(anotherAutoGradeCommand));
        // same object -> True
        assertTrue(autoGradeCommand.equals(autoGradeCommand));

        // null -> false
        assertFalse(autoGradeCommand.equals(null));

        // different type -> false
        assertFalse(autoGradeCommand.equals(new ExitCommand()));

        // different grading type -> false
        anotherAutoGradeCommand =
                new AutoGradeCommand(passingValueArray, AutoGradeCommand.AutoGradeType.ABSOLUTE);
        assertFalse(autoGradeCommand.equals(anotherAutoGradeCommand));

        // different passing value -> false
        float[] anotherPassingValueArray = {95, 75, 52, 41, 35, 33, 25, 20, 17, 14, 13};
        anotherAutoGradeCommand =
                new AutoGradeCommand(anotherPassingValueArray, AutoGradeCommand.AutoGradeType.PERCENTILE);
        assertFalse(autoGradeCommand.equals(anotherAutoGradeCommand));

    }

    @Test
    void testToString() {
        final float[] passingValueArray = {90, 75, 50, 40, 35, 30, 25, 20, 17, 15, 13};
        AutoGradeCommand autoGradeCommand =
                new AutoGradeCommand(passingValueArray, AutoGradeCommand.AutoGradeType.ABSOLUTE);
        try {
            autoGradeCommand.execute(model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command in positive test cases should not fail.", ce);
        }

        String expectedString = AutoGradeCommand.class.getCanonicalName()
                + "{autoGrade type=" + AutoGradeCommand.AutoGradeType.ABSOLUTE
                + ", gradeThreshold=" + Arrays.toString(passingValueArray)
                + "}";

        assertEquals(autoGradeCommand.toString(), expectedString);

    }
}
