package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ModelUtil.addCommandUpdateBooks;
import static seedu.address.commons.util.ModelUtil.addCommandUpdateLinks;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.gradedcomponent.model.GradedComponentBook;
import seedu.address.model.student.Student;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentscore.StudentScore;


/**
 * Command Class for adding Student Score.
 */
public class AddStudentScoreCommand extends Command {
    public static final String COMMAND_WORD = "addStuScore";
    // to expand
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student score to the database.\n"
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT ID"
            + PREFIX_NAME + "STUDENT NAME "
            + PREFIX_COMPONENT_NAME + "GRADED COMPONENT "
            + PREFIX_MARKS + "MARKS "
            + "[" + PREFIX_COMMENT + "COMMENT] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "s/A1234567Y "
            + PREFIX_NAME + "John "
            + PREFIX_COMPONENT_NAME + "Midterm "
            + PREFIX_MARKS + "57 "
            + PREFIX_TAG + "Makeup exam";
    public static final String MESSAGE_SUCCESS = "New student score added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENTSCORE = "This student score already exists in the database";

    private StudentScore toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentScoreCommand(StudentScore score) {
        requireNonNull(score);
        toAdd = score;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        GradedComponentBook gradedComponentBook = model.getGradedComponentBook();
        StudentBook studentBook = model.getStudentBook();

        if (model.getStudentScoreBook().hasStudentScore(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENTSCORE);
        }

        Student student = studentBook.getStudentById(toAdd.getStudentId());
        GradedComponent gc = gradedComponentBook.getGradedComponentByName(toAdd.getGcName());

        addCommandUpdateLinks(student, gc, toAdd);
        addCommandUpdateBooks(model, student, gc, toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatStudentScore(toAdd)));
    }



}
