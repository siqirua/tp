package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStudentScoreCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * A Class to handle parsing for EditStudentScoreCommand.
 */
public class EditStudentScoreCommandParser implements Parser<EditStudentScoreCommand> {

    /**
     * Parse the string arguments
     * @param args arguments in string
     * @return EditStudentScoreCommand
     * @throws ParseException an Exception
     */
    public EditStudentScoreCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_COMPONENT_NAME,
                        PREFIX_MARKS, PREFIX_COMMENT, PREFIX_TAG);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_ID, PREFIX_COMPONENT_NAME,
                PREFIX_MARKS, PREFIX_COMMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditStudentScoreCommand.MESSAGE_USAGE), pe);
        }

        EditStudentScoreCommand.EditStudentScoreDescriptor editStudentScoreDescriptor =
                new EditStudentScoreCommand.EditStudentScoreDescriptor();


        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            editStudentScoreDescriptor.setStudentId(
                    ParserUtil.parseId(argMultimap.getValue(PREFIX_STUDENT_ID).get())
            );
        }

        if (argMultimap.getValue(PREFIX_COMPONENT_NAME).isPresent()) {
            editStudentScoreDescriptor.setGcName(
                    ParserUtil.parseGcName(argMultimap.getValue(PREFIX_COMPONENT_NAME).get())
            );
        }

        if (argMultimap.getValue(PREFIX_MARKS).isPresent()) {
            editStudentScoreDescriptor.setScore(
                    ParserUtil.parseScore(argMultimap.getValue(PREFIX_MARKS).get())
            );
        }

        if (argMultimap.getValue(PREFIX_COMMENT).isPresent()) {
            editStudentScoreDescriptor.setComment(
                    argMultimap.getValue(PREFIX_COMMENT).get());
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentScoreDescriptor::setTags);

        return new EditStudentScoreCommand(index, editStudentScoreDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
