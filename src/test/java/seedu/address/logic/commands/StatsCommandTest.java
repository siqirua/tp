package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TestGcDataUtil;
import seedu.address.testutil.TestStudentDataUtil;
import seedu.address.testutil.TestStudentScoreDataUtil;


public class StatsCommandTest {
    private Model model = new ModelManager(TestStudentDataUtil.getTestStudentBook("score"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("delete"), new UserPrefs());
    private String expectedOutput = "Here are the statistics:\n"
            + "MAX = 50.00, MIN = 0.00, MEAN = 25.00, STANDARD DEVIATION = 17.08\n"
            + "UPPER QUARTILE = 42.50, LOWER QUARTILE = 7.50, SKEWNESS= 0.00\n";

    @Test
    public void execute_stat_success() {
        CommandResult expectedCommandResult = new CommandResult(expectedOutput);
        assertCommandSuccess(new StatsCommand(new ArrayList<>()), model, expectedCommandResult, model);
    }
}
