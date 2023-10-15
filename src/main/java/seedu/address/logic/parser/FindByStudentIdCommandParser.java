package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindByStudentIdCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentIdMatchPredicate;

/**
 * Parses input arguments and creates a new FindByStudentIdCommand object
 */
public class FindByStudentIdCommandParser implements Parser<FindByStudentIdCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByStudentIdCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByStudentIdCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindByStudentIdCommand(new StudentIdMatchPredicate(Arrays.asList(nameKeywords)));
    }
}
