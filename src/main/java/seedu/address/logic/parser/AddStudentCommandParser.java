package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentEmail;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;
import seedu.address.model.student.TutorialGroup;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TUTORIAL_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TUTORIAL_GROUP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_ID, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TUTORIAL_GROUP);
        StudentId sid = new StudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        StudentName name = new StudentName(argMultimap.getValue(PREFIX_NAME).get());
        StudentEmail email = new StudentEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        TutorialGroup tg = new TutorialGroup(argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get());


        Student student = new Student(sid, name, email, tg);
        return new AddStudentCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
