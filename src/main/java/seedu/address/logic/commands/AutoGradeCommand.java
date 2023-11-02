package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTOGRADE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSING_GRADE;

import java.util.Arrays;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentGrade;
import seedu.address.model.student.model.StudentBook;


/**
 * Format full help instructions for every command for display.
 */
public class AutoGradeCommand extends Command {
    public static final String COMMAND_WORD = "autoGrade";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Automatically grade students based on total score.\n"
            + "Parameters:\n"
            + PREFIX_AUTOGRADE_TYPE + "GRADING_METHOD "
            + PREFIX_PASSING_GRADE + "PASSING_GRADE_VALUE \n"
            + "Grading method: Must be one of \"p\", \"percentile\", \"Percentile\", \"a\", "
            + "\"absolute\", \"Absolute\"\n"
            + "Passing grade value: must be float and must be filled at most with 11 values.\n"
            + "Values are in format of " + PREFIX_PASSING_GRADE + "[A+] [A] [A-] [B+] [B] [B-] [C+] [C] [D+] [D] [F] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_AUTOGRADE_TYPE + "percentile "
            + PREFIX_PASSING_GRADE + "90 80 70 60 50 40 30 20 18 15 12";
    public static final String MESSAGE_INCREASING_VALUE = "Lower grades cannot have "
            + "higher value than higher grades!";
    public static final String MESSAGE_PARSE_FLOAT = "Grade value is not parsable. "
            + "Please correctly input the grade value.";
    public static final String MESSAGE_TOO_MANY_VALUE = "Too many inputted passing grade value!";
    private final String[] gradeList =
        {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D+", "D", "F"}; //Enum can only use alphanumeric
    private float[] gradeThreshold = new float[11];
    private final float[] passingGradeValue;
    private final boolean autoGradeType;

    /**
     * Creates an AutoGradeCommand to autoGrade students based on total score.
     * @param passingGradeValue Array of passingGradeValue
     * @param autoGradeType Method of auto grading.
     */
    public AutoGradeCommand(float[] passingGradeValue, boolean autoGradeType) {
        this.passingGradeValue = passingGradeValue;
        this.autoGradeType = autoGradeType;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StudentBook studentBook = model.getStudentBook();
        studentBook.sortStudent("ts", true);

        Arrays.fill(gradeThreshold, 0);

        if (passingGradeValue.length > gradeList.length) {
            throw new CommandException(MESSAGE_TOO_MANY_VALUE);
        }

        if (autoGradeType) {
            setGradeThresholdPercentile(model);
        } else {
            setGradeThresholdAbsolute();
        }

        addGradeToAllStudent(model);

        return new CommandResult(String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW,
                model.getFilteredStudentList().size()));

    }

    private void setGradeThresholdPercentile(Model model) {
        int size = model.getStudentBook().getSize();
        for (int i = 0; i < passingGradeValue.length; i++) {
            int percentileIndex = (int) Math.floor((1 - this.passingGradeValue[i] / 100) * (size - 1));
            Student student = model.getFilteredStudentList().get(percentileIndex);
            float percentileScore = student.getTotalScore();

            this.gradeThreshold[i] = percentileScore;
        }
    }

    private void setGradeThresholdAbsolute() {
        this.gradeThreshold = this.passingGradeValue;
    }

    private StudentGrade createGraded(Student student) {
        float score = student.getTotalScore();
        StudentGrade studentGrade;

        for (int i = 0; i < passingGradeValue.length; i++) {
            if (score >= gradeThreshold[i]) {
                studentGrade = new StudentGrade(gradeList[i]);
                return studentGrade;
            }
        }
        studentGrade = new StudentGrade(gradeList[10]);
        return studentGrade;
    }

    private EditStudentCommand.EditStudentDescriptor addGradeToStudent(Student student) {
        EditStudentCommand.EditStudentDescriptor editStudentDescriptor = new EditStudentCommand.EditStudentDescriptor();

        StudentGrade studentGrade = createGraded(student);
        editStudentDescriptor.setStudentGrade(studentGrade);

        return editStudentDescriptor;
    }

    private void addGradeToAllStudent(Model model) throws CommandException {
        ObservableList<Student> studentList = model.getFilteredStudentList();

        for (int i = 0; i < studentList.size(); i++) {
            EditStudentCommand.EditStudentDescriptor editStudentDescriptor = addGradeToStudent(studentList.get(i));
            Index index = Index.fromZeroBased(i);

            new EditStudentCommand(index, editStudentDescriptor).execute(model);
        }

    }
}
