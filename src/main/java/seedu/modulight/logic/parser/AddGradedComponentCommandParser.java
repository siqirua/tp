package seedu.modulight.logic.parser;

import static seedu.modulight.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_MAX_MARKS;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.util.stream.Stream;

import seedu.modulight.logic.commands.AddGradedComponentCommand;
import seedu.modulight.logic.parser.exceptions.ParseException;
import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.MaxMarks;
import seedu.modulight.model.gradedcomponent.Weightage;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddGradedComponentCommandParser implements Parser<AddGradedComponentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddGradedComponentCommand
     * and returns an AddGradedComponentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddGradedComponentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPONENT_NAME, PREFIX_WEIGHTAGE, PREFIX_MAX_MARKS);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPONENT_NAME, PREFIX_WEIGHTAGE, PREFIX_MAX_MARKS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddGradedComponentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPONENT_NAME, PREFIX_WEIGHTAGE, PREFIX_MAX_MARKS);

        String nameString = argMultimap.getValue(PREFIX_COMPONENT_NAME).get();
        String weightageString = argMultimap.getValue(PREFIX_WEIGHTAGE).get();
        String maxMarksString = argMultimap.getValue(PREFIX_MAX_MARKS).get();
        GcName name = ParserUtil.parseGcName(nameString);
        Weightage weightage = ParserUtil.parseWeightage(weightageString);
        MaxMarks maxMarks = ParserUtil.parseMaxMarks(maxMarksString);

        GradedComponent gc = new GradedComponent(name, maxMarks, weightage);
        return new AddGradedComponentCommand(gc);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
