package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.SortStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class SortStudentCommandParser implements Parser<SortStudentCommand> {
    private static final List<String> ORDERS_ACCEPTABLE = Arrays.asList("n", "name", "s", "studentId", "studentID",
            "e", "email", "g", "tutorial", "tut", "tutGroup");
    private static final List<String> REVERSES_ACCEPTABLE = Arrays.asList("increasing", "decreasing", "true", "false",
            "1", "0", "t", "f");
    private static final Boolean REVERSE_DEFAULT = false;
    private static final String ORDER_DEFAULT = "s";

    /**
     * Parses the given {@code String} of arguments in the context of the SortStudentCommand
     * and returns an SortStudentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortStudentCommand parse(String args) throws ParseException {
        String[] input = args.split(" ");

        if (input.length != 2 && input.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortStudentCommand.MESSAGE_USAGE));
        }

        if (!ORDERS_ACCEPTABLE.contains(input[1])) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortStudentCommand.MESSAGE_USAGE));
        }

        if (input.length == 3 && !REVERSES_ACCEPTABLE.contains(input[2])) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortStudentCommand.MESSAGE_USAGE));
        }

        String order = ORDER_DEFAULT;
        boolean reverse = REVERSE_DEFAULT;

        switch (input[1]) {
        case "n":
        case "name":
            order = "n";
            break;
        case "s":
        case "studentId":
        case "studentID":
            order = "s";
            break;
        case "e":
        case "email":
            order = "e";
            break;
        case "g":
        case "tut":
        case "tutorial":
        case "tutGroup":
            order = "g";
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortStudentCommand.MESSAGE_USAGE));
        }

        if (input.length == 3) {
            switch (input[2]) {
            case "decreasing":
            case "0":
            case "false":
            case "f":
                reverse = false;
                break;
            case "increasing":
            case "1":
            case "true":
            case "t":
                reverse = true;
                break;
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SortStudentCommand.MESSAGE_USAGE));
            }
        }

        return new SortStudentCommand(order, reverse);
    }
}
