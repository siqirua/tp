package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.address.logic.commands.EditStudentScoreCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.student.StudentId;


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
                        PREFIX_MARKS, PREFIX_COMMENT);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_ID, PREFIX_COMPONENT_NAME,
                PREFIX_MARKS, PREFIX_COMMENT);
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

        StudentId sid = editStudentScoreDescriptor.getStudentId().orElse(null);
        GcName gcName = editStudentScoreDescriptor.getGcName().orElse(null);

        return new EditStudentScoreCommand(sid, gcName, editStudentScoreDescriptor);
    }

}
