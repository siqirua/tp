package seedu.modulight.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.modulight.commons.util.ModelUtil.MESSAGE_WEIGHTAGES_MORE_THAN_100;
import static seedu.modulight.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.modulight.logic.commands.EditGradedComponentCommand.MESSAGE_ASSOCIATED_SCORE_EXCEEDS;
import static seedu.modulight.logic.commands.EditGradedComponentCommand.MESSAGE_DUPLICATE_GRADED_COMPONENT;
import static seedu.modulight.logic.commands.EditGradedComponentCommand.MESSAGE_EDIT_GC_SUCCESS;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modulight.commons.core.index.Index;
import seedu.modulight.logic.Messages;
import seedu.modulight.logic.commands.EditGradedComponentCommand.EditGradedComponentDescriptor;
import seedu.modulight.logic.commands.exceptions.CommandException;
import seedu.modulight.model.Model;
import seedu.modulight.model.ModelManager;
import seedu.modulight.model.UserPrefs;
import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.MaxMarks;
import seedu.modulight.model.gradedcomponent.Weightage;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;
import seedu.modulight.model.studentscore.StudentScore;
import seedu.modulight.model.studentscore.model.StudentScoreBook;
import seedu.modulight.testutil.TestGcDataUtil;
import seedu.modulight.testutil.TestStudentDataUtil;
import seedu.modulight.testutil.TestStudentScoreDataUtil;


public class EditGradedComponentTest {
    private Model model = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("create"), new UserPrefs());

    private Model targetModel = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("create"), new UserPrefs());

    @Test
    public void execute_editGcName_success() throws CommandException {
        Index idx = Index.fromOneBased(2);
        GradedComponentBook gcBook = targetModel.getGradedComponentBook();
        List<GradedComponent> gcList = gcBook.getGradedComponentList();
        GradedComponent toEdit = gcList.get(idx.getZeroBased());
        EditGradedComponentDescriptor descriptor = new EditGradedComponentDescriptor();
        descriptor.setGcName(new GcName("Final Examination"));
        GradedComponent editedGc = EditGradedComponentCommand.createEditedGradedComponent(toEdit, descriptor);
        StudentScoreBook studentScoreBook = targetModel.getStudentScoreBook();
        List<StudentScore> studentScoreList = studentScoreBook.getStudentScoreList();
        gcBook.setGradedComponent(toEdit, editedGc);
        for (StudentScore sc : studentScoreList) {
            if (sc.getGcName().equals(toEdit.getName())) {
                StudentScore editedScore = new StudentScore(sc.getStudentId(),
                        editedGc.getName(), sc.getScore());
                studentScoreBook.setStudentScore(sc, editedScore);
            }
        }
        String expectedOutput = String.format(MESSAGE_EDIT_GC_SUCCESS,
                Messages.formatGradedComponent(editedGc));
        CommandResult result = new EditGradedComponentCommand(idx, descriptor).execute(model);
        assertEquals(expectedOutput, result.getFeedbackToUser());
        assertEquals(model, targetModel);
    }

    @Test
    public void execute_editGcInvalidIndex_fail() {
        Index idx = Index.fromOneBased(3);
        EditGradedComponentDescriptor descriptor = new EditGradedComponentDescriptor();
        descriptor.setGcName(new GcName("Exam 12"));
        descriptor.setMaxMarks(new MaxMarks(20));
        descriptor.setWeightage(new Weightage(20));
        Command c = new EditGradedComponentCommand(idx, descriptor);
        Exception e = assertThrows(CommandException.class, () -> c.execute(model));
        assertEquals(e.getMessage(), MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editGcNameDuplicate_fail() {
        Index idx = Index.fromOneBased(2);
        EditGradedComponentDescriptor descriptor = new EditGradedComponentDescriptor();
        descriptor.setGcName(new GcName("Midterm"));
        descriptor.setMaxMarks(new MaxMarks(20));
        descriptor.setWeightage(new Weightage(20));
        Command c = new EditGradedComponentCommand(idx, descriptor);
        Exception e = assertThrows(CommandException.class, () -> c.execute(model));
        assertEquals(e.getMessage(), MESSAGE_DUPLICATE_GRADED_COMPONENT);
    }

    @Test
    public void execute_editGcWeightageMoreThan100_fail() {
        Index idx = Index.fromOneBased(2);
        EditGradedComponentDescriptor descriptor = new EditGradedComponentDescriptor();
        descriptor.setWeightage(new Weightage(80));
        Command c = new EditGradedComponentCommand(idx, descriptor);
        Exception e = assertThrows(CommandException.class, () -> c.execute(model));
        assertEquals(e.getMessage(), MESSAGE_WEIGHTAGES_MORE_THAN_100);
    }

    @Test
    public void execute_editGcMaxMarksLessThanStudentScore_fail() {
        Index idx = Index.fromOneBased(1);
        EditGradedComponentDescriptor descriptor = new EditGradedComponentDescriptor();
        descriptor.setMaxMarks(new MaxMarks(1));
        Command c = new EditGradedComponentCommand(idx, descriptor);
        Exception e = assertThrows(CommandException.class, () -> c.execute(model));
        assertEquals(e.getMessage(), MESSAGE_ASSOCIATED_SCORE_EXCEEDS);
    }

}
