package seedu.address.logic.commands;

import seedu.address.logic.commands.statscalculator.StatsCalculator;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.studentscore.StudentScore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Calculates the statics.
 */
public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calculate the overall statistics.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Here are the statistics:\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Student> students = model.getStudentBook().getStudentList();
        return new CommandResult(generateOverallStatsSummary(students));
    }

    private String generateOverallStatsSummary(List<Student> students) {
        List<Float> studentScores = students.stream().map(Student::getTotalScore).collect(Collectors.toList());
        StatsCalculator st = new StatsCalculator(studentScores);
        StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS);
        sb.append(String.format("MAX = %f, MIN = %f\n", st.getMax(), st.getMin()));
        sb.append(String.format("MEAN = %f, STD = %f\n", st.getMean(), st.getStd()));
        return sb.toString();
    }

    static private ArrayList<Float> convertToFloat(List<StudentScore> scores) {
        return scores.stream().map(StudentScore::getScore).collect(Collectors.toCollection(ArrayList::new));
    }
}
