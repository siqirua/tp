package seedu.modulight.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.modulight.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.modulight.logic.commands.DeleteGradedComponentCommand.MESSAGE_DELETE_GC_SUCCESS;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modulight.commons.core.index.Index;
import seedu.modulight.logic.Messages;
import seedu.modulight.logic.commands.exceptions.CommandException;
import seedu.modulight.model.Model;
import seedu.modulight.model.ModelManager;
import seedu.modulight.model.UserPrefs;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;
import seedu.modulight.model.studentscore.StudentScore;
import seedu.modulight.model.studentscore.model.StudentScoreBook;
import seedu.modulight.testutil.TestGcDataUtil;
import seedu.modulight.testutil.TestStudentDataUtil;
import seedu.modulight.testutil.TestStudentScoreDataUtil;


public class DeleteGradedComponentTest {
    private Model model = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("create"), new UserPrefs());

    private Model targetModel = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("create"), new UserPrefs());

    @Test
    public void execute_deleteGc_success() throws CommandException {
        Index idx = Index.fromOneBased(1);
        GradedComponentBook gcBook = targetModel.getGradedComponentBook();
        List<GradedComponent> gcList = gcBook.getGradedComponentList();
        GradedComponent toDelete = gcList.get(idx.getZeroBased());
        gcBook.removeGradedComponent(toDelete);
        StudentScoreBook scoreBook = targetModel.getStudentScoreBook();
        List<StudentScore> studentScoreList = scoreBook.getStudentScoreList();
        for (int i = studentScoreList.size() - 1; i >= 0; i--) {
            if (studentScoreList.get(i).getGcName().equals(toDelete.getName())) {
                scoreBook.removeStudentScore(studentScoreList.get(i));
            }
        }
        String expectedOutput = String.format(MESSAGE_DELETE_GC_SUCCESS,
                Messages.formatGradedComponent(toDelete));
        Command c = new DeleteGradedComponentCommand(idx);
        CommandResult result = c.execute(model);
        System.out.println(gcList.size());
        System.out.println(model.getGradedComponentBook().getGradedComponentList().size());
        assertEquals(expectedOutput, result.getFeedbackToUser());
        assertEquals(model, targetModel);
    }

    @Test
    public void execute_deleteGcInvalidIndex1_fail() {
        Index idx = Index.fromOneBased(3);
        Command c = new DeleteGradedComponentCommand(idx);
        Exception e = assertThrows(CommandException.class, () -> c.execute(model));
        assertEquals(e.getMessage(), MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


}
