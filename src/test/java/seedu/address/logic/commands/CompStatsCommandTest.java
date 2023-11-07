package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.student.TutorialGroup;
import seedu.address.testutil.TestGcDataUtil;
import seedu.address.testutil.TestStudentDataUtil;
import seedu.address.testutil.TestStudentScoreDataUtil;

public class CompStatsCommandTest {
    private Model model = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("twoScores"),
            TestGcDataUtil.getTestGcBook("create"), new UserPrefs());
    private String expectedOutput = "Here are the statistics:\n"
            + "MAX = 50.00, MIN = 0.00, MEAN = 25.00, STANDARD DEVIATION = 17.08\n"
            + "UPPER QUARTILE = 42.50, LOWER QUARTILE = 7.50, SKEWNESS = 0.00\n";

    private String expectedOutputOfTut3 = "Here are the statistics of Final of Tutorial Group T03:\n"
            + "MAX = 100.00, MIN = 60.00, MEAN = 80.00, STANDARD DEVIATION = 14.14\n"
            + "UPPER QUARTILE = 95.00, LOWER QUARTILE = 65.00, SKEWNESS = 0.00\n";

    @Test
    public void execute_stat_success() {
        assertCommandSuccess(new CompStatsCommand(new ArrayList<>(), new GcName("Midterm")),
                model, expectedOutput, model);
    }

    @Test
    public void execute_stat_success_tut() {
        assertCommandSuccess(new CompStatsCommand(new ArrayList<>(), new GcName("Final"),
                        new TutorialGroup("T03")), model, expectedOutputOfTut3, model);
    }
}
