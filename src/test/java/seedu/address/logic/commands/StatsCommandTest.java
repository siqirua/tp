package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.TutorialGroup;
import seedu.address.testutil.TestGcDataUtil;
import seedu.address.testutil.TestStudentDataUtil;
import seedu.address.testutil.TestStudentScoreDataUtil;

public class StatsCommandTest {
    private Model model = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("delete"), new UserPrefs());
    private String expectedOutput = "Here are the statistics:\n"
            + "MAX = 100.00, MIN = 0.00, MEAN = 50.00, STANDARD DEVIATION = 34.16\n"
            + "UPPER QUARTILE = 85.00, LOWER QUARTILE = 15.00, SKEWNESS = 0.00\n";

    private String expectedOutputOfTut3 = "Here are the statistics of Tutorial Group T03:\n"
            + "MAX = 80.00, MIN = 0.00, MEAN = 40.00, STANDARD DEVIATION = 28.28\n"
            + "UPPER QUARTILE = 70.00, LOWER QUARTILE = 10.00, SKEWNESS = 0.00\n";

    @Test
    public void execute_stat_success() {
        assertCommandSuccess(new StatsCommand(new ArrayList<>()), model, expectedOutput, model);
    }

    @Test
    public void execute_tutStat_success() {
        assertCommandSuccess(new StatsCommand(new ArrayList<>(), new TutorialGroup("T03")), model,
                expectedOutputOfTut3, model);
    }
}
