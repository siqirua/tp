package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ModelUtil.MESSAGE_WEIGHTAGES_MORE_THAN_100;
import static seedu.address.commons.util.ModelUtil.addCommandUpdateBooks;
import static seedu.address.commons.util.ModelUtil.addCommandUpdateLinks;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAX_MARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.util.List;

import seedu.address.commons.util.ModelUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.gradedcomponent.model.GradedComponentBook;
import seedu.address.model.student.Student;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentscore.StudentScore;

/**
 * Format full help instructions for every command for display.
 */
public class AddGradedComponentCommand extends Command {

    public static final String COMMAND_WORD = "addComp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a graded component to the address book. "
            + "Parameters: "
            + PREFIX_COMPONENT_NAME + "COMPONENT NAME "
            + PREFIX_MAX_MARKS + "MAX MARKS "
            + PREFIX_WEIGHTAGE + "WEIGHTAGE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPONENT_NAME + "Midterm Exam "
            + PREFIX_MAX_MARKS + "75 "
            + PREFIX_WEIGHTAGE + "12.5 ";


    public static final String MESSAGE_SUCCESS = "New graded component added: %1$s";
    public static final String MESSAGE_DUPLICATE_GRADED_COMPONENT = "This graded component already "
            + "exists in the database";


    private GradedComponent toAdd;

    /**
     * Creates an AddGradedComponentCommand to add the specified {@code GradedComponent}
     */
    public AddGradedComponentCommand(GradedComponent gc) {
        requireNonNull(gc);
        toAdd = gc;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getGradedComponentBook().hasGradedComponent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GRADED_COMPONENT);
        }
        GradedComponentBook gradedComponentBook = model.getGradedComponentBook();
        StudentBook studentBook = model.getStudentBook();

        float weightageToAdd = toAdd.getWeightage().weightage;
        if (ModelUtil.weightageSum(gradedComponentBook) + weightageToAdd > 100) {
            throw new CommandException(MESSAGE_WEIGHTAGES_MORE_THAN_100);
        }
        gradedComponentBook.addGradedComponent(toAdd);
        List<Student> students = studentBook.getStudentList();

        for (Student student : students) {
            StudentScore sc = new StudentScore(student.getStudentId(), toAdd.getName(), 0);
            addCommandUpdateLinks(student, toAdd, sc);
            addCommandUpdateBooks(model, student, toAdd, sc);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatGradedComponent(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGradedComponentCommand)) {
            return false;
        }

        AddGradedComponentCommand otherAddCommand = (AddGradedComponentCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
