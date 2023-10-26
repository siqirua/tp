package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.stream.Stream;

import seedu.address.logic.commands.StatsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.TutorialGroup;

/**
 * Parses input arguments and creates a new StatsCommand object
 */
public class StatsCommandParser implements Parser<StatsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StatsCommand
     * and returns an StatsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_GROUP);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_TUTORIAL_GROUP)) {
            return new StatsCommand();
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TUTORIAL_GROUP);

        TutorialGroup tg = new TutorialGroup(argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get());
        return new StatsCommand(tg);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
