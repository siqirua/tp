package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TestGcDataUtil;
import seedu.address.testutil.TestStudentDataUtil;
import seedu.address.testutil.TestStudentScoreDataUtil;



public class SortCommandTest {
    private Model model = new ModelManager(TestStudentDataUtil.getTestStudentBook("create"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("delete"), new UserPrefs());

    private Model expectedModelSortedByName = new ModelManager(TestStudentDataUtil
            .getTestStudentBook("sortByName"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("delete"), new UserPrefs());

    private Model expectedModelSortedByGradeReverse = new ModelManager(TestStudentDataUtil
            .getTestStudentBook("sortByTsReverse"),
            TestStudentScoreDataUtil.getSampleStudentScoreBook("create"),
            TestGcDataUtil.getTestGcBook("delete"), new UserPrefs());

    @Test
    public void execute_sort_success() {
        String expectedOutput = "6 students sorted!";
        assertCommandSuccess(new SortStudentCommand("n", false), model, expectedOutput,
                expectedModelSortedByName);
        assertCommandSuccess(new SortStudentCommand("o", true), model, expectedOutput,
                expectedModelSortedByGradeReverse);
    }

}
