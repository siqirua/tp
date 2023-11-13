package seedu.modulight.logic.parser;

import static seedu.modulight.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modulight.logic.commands.FindGradedComponentCommand;
import seedu.modulight.logic.parser.exceptions.ParseException;
import seedu.modulight.model.gradedcomponent.GcMatchPredicate;
import seedu.modulight.model.studentscore.ScoreMatchPredicate;

public class FindGradedComponentCommandParserTest {
    private FindGradedComponentCommandParser parser = new FindGradedComponentCommandParser();

    @Test
    public void parse_emptyArg_returnFindGradedComponentCommand() {
        List<String> emptyKeywords = new ArrayList<>();
        GcMatchPredicate predicate = new GcMatchPredicate(emptyKeywords);
        FindGradedComponentCommand expectedFindGradedComponentCommand =
                new FindGradedComponentCommand(predicate, new ScoreMatchPredicate(predicate));
        assertParseSuccess(parser, "   ", expectedFindGradedComponentCommand);
    }

    @Test
    public void parse_validArgs_returnsFindGradedComponentCommand() throws ParseException {
        // no leading and trailing whitespaces
        List<String> gcKeywords = Arrays.asList("midterm");
        GcMatchPredicate predicate = new GcMatchPredicate(gcKeywords);
        FindGradedComponentCommand expectedFindGradedComponentCommand =
                new FindGradedComponentCommand(predicate, new ScoreMatchPredicate(predicate));
        assertParseSuccess(parser, " c/midterm", expectedFindGradedComponentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/\n midterm \n ", expectedFindGradedComponentCommand);
    }
}
