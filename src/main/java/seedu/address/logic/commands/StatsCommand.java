package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.statscalculator.StatsCalculator;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;

/**
 * Calculates the statics.
 */
public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calculate the overall statistics.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Here are the statistics:\n";
    public static final String MESSAGE_EMPTY = "Please have at least one score fulfilling the condition.\n";
    public static final String MESSAGE_EMPTY_TUT = "This tutorial group does not have any valid scores."
            + "Please check if the information is correct";

    private final TutorialGroup tutorialGroup;
    private final boolean isForTut;

    /**
     * Creates a StatsCommand to calculate the overall stats.
     */
    public StatsCommand() {
        this.isForTut = false;
        this.tutorialGroup = null;
    }

    /**
     * Creates a StatsCommand to calculate the overall stats of a given tutorial group.
     * @param tutorialGroup the tutorial group to be analyzed
     */
    public StatsCommand(TutorialGroup tutorialGroup) {
        this.isForTut = true;
        this.tutorialGroup = tutorialGroup;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Student> students = model.getStudentBook().getStudentList();
        if (isForTut) {
            return new CommandResult(generateTutStatsSummary(students, tutorialGroup.toString()));
        }
        return new CommandResult(generateOverallStatsSummary(students));
    }

    private String generateOverallStatsSummary(List<Student> students) {
        if (students.isEmpty()) {
            return MESSAGE_EMPTY;
        }
        List<Float> studentScores = students.stream().map(Student::getTotalScore).collect(Collectors.toList());
        return generateSummaryFromScores(studentScores, MESSAGE_SUCCESS);
    }

    private String generateTutStatsSummary(List<Student> students, String tutGroup) {
        List<Float> studentScores = students.stream().filter(student -> student.getTutorial().toString()
                .equals(tutGroup)).map(Student::getTotalScore).collect(Collectors.toList());
        if (studentScores.isEmpty()) {
            return MESSAGE_EMPTY_TUT;
        }
        return generateSummaryFromScores(studentScores, generateSummaryOpenning(tutGroup));
    }

    private String generateSummaryFromScores(List<Float> scores, String openning) {
        StatsCalculator st = new StatsCalculator(scores);
        StringBuilder sb = new StringBuilder(openning);
        sb.append(String.format("MAX = %f, MIN = %f\n", st.getMax(), st.getMin()));
        sb.append(String.format("MEAN = %f, STD = %f\n", st.getMean(), st.getStd()));
        return sb.toString();
    }

    private String generateSummaryOpenning(String tutGroup) {
        StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS);
        sb.insert(MESSAGE_SUCCESS.length() - 2, String.format(" of Tutorial Group %S", tutGroup));
        return sb.toString();
    }
}
