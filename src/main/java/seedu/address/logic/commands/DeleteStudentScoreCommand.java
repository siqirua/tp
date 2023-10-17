package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.studentscore.StudentScore;


/**
 * Command class for deleting StudentScore.
 */
public class DeleteStudentScoreCommand extends Command {

    public static final String COMMAND_WORD = "deleteStuScore";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the studentScore identified by the index number used "
            + "in the displayed studentScore list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STUDENT_SCORE_SUCCESS = "Deleted StudentScore: %1$s";

    private final Index targetIndex;

    /**
     * Constructor for the class
     *
     * @param targetIndex Index of the object that want to be deleted.
     */
    public DeleteStudentScoreCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<StudentScore> lastShownList = model.getFilteredStudentScoreList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        StudentScore studentScoreToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.getStudentScoreBook().removeStudentScore(studentScoreToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SCORE_SUCCESS,
                Messages.formatStudentScore(studentScoreToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteStudentScoreCommand)) {
            return false;
        }

        DeleteStudentScoreCommand otherDeleteCommand = (DeleteStudentScoreCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }


}
