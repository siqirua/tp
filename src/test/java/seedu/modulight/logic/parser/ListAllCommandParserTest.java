package seedu.modulight.logic.parser;

import static seedu.modulight.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modulight.logic.commands.ListAllCommand;

public class ListAllCommandParserTest {
    private ListAllCommandParser parser = new ListAllCommandParser();
    @Test
    public void parse_noInput_success() {
        ListAllCommand expectedCommand = new ListAllCommand();
        assertParseSuccess(parser, "   ", expectedCommand);
    }

    @Test
    public void parse_withInput_success() {
        ListAllCommand expectedCommand = new ListAllCommand();
        assertParseSuccess(parser, "  t/t01 ", expectedCommand);
    }
}
