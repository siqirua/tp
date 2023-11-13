package seedu.modulight.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.modulight.commons.util.ModelUtil.MESSAGE_WEIGHTAGES_MORE_THAN_100;
import static seedu.modulight.logic.commands.AddGradedComponentCommand.MESSAGE_DUPLICATE_GRADED_COMPONENT;
import static seedu.modulight.logic.commands.AddGradedComponentCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.modulight.logic.Messages;
import seedu.modulight.logic.commands.exceptions.CommandException;
import seedu.modulight.model.Model;
import seedu.modulight.model.ModelManager;
import seedu.modulight.model.UserPrefs;
import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.MaxMarks;
import seedu.modulight.model.gradedcomponent.Weightage;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;
import seedu.modulight.model.student.Student;
import seedu.modulight.model.student.model.StudentBook;
import seedu.modulight.model.studentscore.StudentScore;
import seedu.modulight.model.studentscore.model.StudentScoreBook;
import seedu.modulight.testutil.TestGcDataUtil;
import seedu.modulight.testutil.TestStudentDataUtil;
import seedu.modulight.testutil.TestStudentScoreDataUtil;



public class AddGradedComponentTest {
    private Model model = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("create"), new UserPrefs());

    private Model targetModel = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("create"), new UserPrefs());

    @Test
    public void execute_addGc_success() throws CommandException {
        GradedComponent toAdd = new GradedComponent(
                new GcName("Practical Exam"), new MaxMarks(30), new Weightage(30)
        );
        GradedComponentBook gcBook = targetModel.getGradedComponentBook();
        gcBook.addGradedComponent(toAdd);
        StudentBook studentBook = targetModel.getStudentBook();
        StudentScoreBook scoreBook = targetModel.getStudentScoreBook();
        for (Student s : studentBook.getStudentList()) {
            scoreBook.addStudentScore(new StudentScore(s.getStudentId(), toAdd.getName(), 0));
        }
        String expectedOutput = String.format(MESSAGE_SUCCESS, Messages.formatGradedComponent(toAdd));
        Command c = new AddGradedComponentCommand(toAdd);
        CommandResult result = c.execute(model);
        assertEquals(expectedOutput, result.getFeedbackToUser());
        assertEquals(model, targetModel);
    }

    @Test
    public void execute_addTotalWeightageExceeding100_fail() {
        GradedComponent toAdd = new GradedComponent(
                new GcName("Practical Exam"), new MaxMarks(30), new Weightage(80)
        );
        Command c = new AddGradedComponentCommand(toAdd);
        Exception e = assertThrows(CommandException.class, () -> c.execute(model));
        assertEquals(e.getMessage(), MESSAGE_WEIGHTAGES_MORE_THAN_100);
    }

    @Test
    public void execute_addDuplicateGc_fail() {
        GradedComponent toAdd = new GradedComponent(
                new GcName("Midterm"), new MaxMarks(50), new Weightage(30)
        );
        Command c = new AddGradedComponentCommand(toAdd);
        Exception e = assertThrows(CommandException.class, () -> c.execute(model));
        assertEquals(e.getMessage(), MESSAGE_DUPLICATE_GRADED_COMPONENT);
    }

}
