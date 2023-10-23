package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;



/**
 * Clears all students, student scores and graded components data from the address book.
 */
public class ClearAllCommand extends Command {

    public static final String COMMAND_WORD = "clearAll";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all data. \n "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Clear all data successfully";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.getStudentBook().clearStudent();
        model.getStudentScoreBook().clearStudentScore();
        model.getGradedComponentBook().clearGradedComponent();
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearAllCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}

