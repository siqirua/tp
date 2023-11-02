package seedu.address.logic.parser;

import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * The type List all command parser.
 */
public class ListAllCommandParser implements Parser<ListAllCommand> {
    public ListAllCommand parse(String args) throws ParseException {
        return new ListAllCommand();
    }
}
