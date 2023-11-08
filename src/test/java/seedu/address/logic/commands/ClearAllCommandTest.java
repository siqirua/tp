package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TestGcDataUtil;
import seedu.address.testutil.TestStudentDataUtil;
import seedu.address.testutil.TestStudentScoreDataUtil;

import static seedu.address.logic.commands.ClearAllCommand.MESSAGE_CLEAR_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

public class ClearAllCommandTest {
    private Model model = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("delete"), new UserPrefs());
    private Model modelCleared = new ModelManager();

    @Test
    public void execute_clear_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_CLEAR_SUCCESS);
        assertCommandSuccess(new ClearAllCommand(), model, expectedCommandResult, modelCleared);
    }
}
