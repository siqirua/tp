package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentMatchPredicate;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class FindStudentCommandParser implements Parser<FindStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TUTORIAL_GROUP);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        List<String> idKeywords = argMultimap.getAllValues(PREFIX_STUDENT_ID);
        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        List<String> emailKeywords = argMultimap.getAllValues(PREFIX_EMAIL);
        List<String> tutorialGroupKeywords = argMultimap.getAllValues(PREFIX_TUTORIAL_GROUP);
        List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);

        StudentMatchPredicate matchPredicate = new StudentMatchPredicate(idKeywords, nameKeywords, emailKeywords,
                tutorialGroupKeywords, tagKeywords);
        return new FindStudentCommand(matchPredicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
