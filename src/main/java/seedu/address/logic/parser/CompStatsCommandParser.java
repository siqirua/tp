package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.stream.Stream;

import seedu.address.logic.commands.CompStatsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.student.TutorialGroup;






/**
 * Parses input arguments and creates a new StatsCommand object
 */
public class CompStatsCommandParser implements Parser<CompStatsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CompStatsCommand
     * and returns an CompStatsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompStatsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPONENT_NAME, PREFIX_TUTORIAL_GROUP);

        if (!argMultimap.getPreamble().isEmpty() || !arePrefixesPresent(argMultimap, PREFIX_COMPONENT_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompStatsCommand.MESSAGE_USAGE));
        }
        GcName gcName = new GcName(argMultimap.getValue(PREFIX_COMPONENT_NAME).get());
        if (!arePrefixesPresent(argMultimap, PREFIX_TUTORIAL_GROUP)) {
            return new CompStatsCommand(gcName);
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TUTORIAL_GROUP);

        TutorialGroup tg = new TutorialGroup(argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get());
        return new CompStatsCommand(gcName, tg);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
