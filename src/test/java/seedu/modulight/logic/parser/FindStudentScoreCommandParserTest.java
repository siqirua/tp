package seedu.modulight.logic.parser;

import static seedu.modulight.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modulight.logic.commands.FindStudentScoreCommand;
import seedu.modulight.logic.parser.exceptions.ParseException;
import seedu.modulight.model.studentscore.ScoreMatchPredicate;


public class FindStudentScoreCommandParserTest {
    private FindStudentScoreCommandParser parser = new FindStudentScoreCommandParser();

    @Test
    public void parse_emptyArg_returnFindStudentCommand() {
        List<String> emptyKeywords = new ArrayList<>();
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(emptyKeywords, emptyKeywords, emptyKeywords,
                emptyKeywords, emptyKeywords, emptyKeywords);
        FindStudentScoreCommand expectedFindStudentCommand = new FindStudentScoreCommand(predicate);
        assertParseSuccess(parser, "   ", expectedFindStudentCommand);
    }

    @Test
    public void parse_validArgs_returnsFindStudentCommand() throws ParseException {
        // no leading and trailing whitespaces
        List<String> emptyKeywords = new ArrayList<>();
        List<String> tgKeywords = Arrays.asList("T01");
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(emptyKeywords, emptyKeywords,
                tgKeywords, emptyKeywords, emptyKeywords, emptyKeywords);
        FindStudentScoreCommand expectedFindStudentCommand =
                new FindStudentScoreCommand(predicate);
        assertParseSuccess(parser, " g/T01", expectedFindStudentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " g/\n T01 \n ", expectedFindStudentCommand);
    }
}
