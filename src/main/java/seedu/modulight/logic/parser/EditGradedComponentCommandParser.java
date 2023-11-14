package seedu.modulight.logic.parser;

import static seedu.modulight.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_MAX_MARKS;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import seedu.modulight.commons.core.index.Index;
import seedu.modulight.logic.commands.EditGradedComponentCommand;
import seedu.modulight.logic.commands.EditGradedComponentCommand.EditGradedComponentDescriptor;
import seedu.modulight.logic.parser.exceptions.ParseException;
import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.gradedcomponent.MaxMarks;
import seedu.modulight.model.gradedcomponent.Weightage;

/**
 * Parses input arguments and creates a new EditGradedComponentCommand object
 */
public class EditGradedComponentCommandParser implements Parser<EditGradedComponentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditGradedComponentCommand
     * and returns an EditGradedComponentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditGradedComponentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPONENT_NAME, PREFIX_WEIGHTAGE, PREFIX_MAX_MARKS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditGradedComponentCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPONENT_NAME, PREFIX_WEIGHTAGE, PREFIX_MAX_MARKS);
        EditGradedComponentDescriptor editGradedComponentDescriptor = new EditGradedComponentDescriptor();

        if (argMultimap.getValue(PREFIX_COMPONENT_NAME).isPresent()) {
            String nameString = argMultimap.getValue(PREFIX_COMPONENT_NAME).get();
            GcName gcName = ParserUtil.parseGcName(nameString);
            editGradedComponentDescriptor.setGcName(gcName);
        }
        if (argMultimap.getValue(PREFIX_WEIGHTAGE).isPresent()) {
            String weightageString = argMultimap.getValue(PREFIX_WEIGHTAGE).get();
            Weightage weightage = ParserUtil.parseWeightage(weightageString);
            editGradedComponentDescriptor.setWeightage(weightage);
        }
        if (argMultimap.getValue(PREFIX_MAX_MARKS).isPresent()) {
            String maxMarkString = argMultimap.getValue(PREFIX_MAX_MARKS).get();
            MaxMarks maxMarks = ParserUtil.parseMaxMarks(maxMarkString);
            editGradedComponentDescriptor.setMaxMarks(maxMarks);
        }

        if (!editGradedComponentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditGradedComponentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditGradedComponentCommand(index, editGradedComponentDescriptor);
    }
}
