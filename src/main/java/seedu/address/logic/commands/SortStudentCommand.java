package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentMatchPredicate;
import seedu.address.model.student.model.StudentBook;

import static java.util.Objects.requireNonNull;

/**
 * Sorts the student(s) whose student id by the given order.
 */
public class SortStudentCommand extends Command {
    public static final String COMMAND_WORD = "sortStu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all students by the given order.\n "
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " s/A1234567Z";

    private final String sortingOrder;

    public SortStudentCommand(String sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        StudentBook studentBook = model.getStudentBook();
        studentBook.sortStudent();
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortStudentCommand)) {
            return false;
        }

        SortStudentCommand otherSortCommand = (SortStudentCommand) other;
        return sortingOrder.equals(otherSortCommand.sortingOrder);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("by ", sortingOrder)
                .toString();
    }
}

