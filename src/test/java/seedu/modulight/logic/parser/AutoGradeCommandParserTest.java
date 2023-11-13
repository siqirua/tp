package seedu.modulight.logic.parser;

import static seedu.modulight.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_AUTOGRADE_TYPE;
import static seedu.modulight.logic.parser.CliSyntax.PREFIX_PASSING_GRADE;
import static seedu.modulight.logic.parser.ParserTestUtil.assertParserFailure;
import static seedu.modulight.logic.parser.ParserTestUtil.assertParserSuccess;

import org.junit.jupiter.api.Test;

import seedu.modulight.logic.commands.AutoGradeCommand;

public class AutoGradeCommandParserTest {
    public static final String VALID_DESC_PERCENTILE = " " + PREFIX_AUTOGRADE_TYPE + "p";
    public static final String VALID_DESC_ABSOLUTE = " " + PREFIX_AUTOGRADE_TYPE + "a";
    public static final AutoGradeCommand.AutoGradeType PERCENTILE = AutoGradeCommand.AutoGradeType.PERCENTILE;
    public static final AutoGradeCommand.AutoGradeType ABSOLUTE = AutoGradeCommand.AutoGradeType.ABSOLUTE;

    public static final float[] VALID_DESC_PASSING_VALUE_ARRAY = {90, 75, 50, 40, 35, 30, 25, 20, 17, 15, 13};
    public static final String VALID_DESC_PASSING_VALUE = " " + PREFIX_PASSING_GRADE
            + toStringList(VALID_DESC_PASSING_VALUE_ARRAY);

    public static final float[] VALID_DESC_SINGLE_PASSING_VALUE_ARRAY = {80};
    public static final String VALID_DESC_SINGLE_PASSING_VALUE = " " + PREFIX_PASSING_GRADE
            + toStringList(VALID_DESC_SINGLE_PASSING_VALUE_ARRAY);

    private static final AutoGradeCommandParser parser = new AutoGradeCommandParser();

    @Test
    public void parse_emptyPrefix_failure() {
        String input = "";

        assertParserFailure(input, parser,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AutoGradeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validGradingType_success() {
        String input = VALID_DESC_ABSOLUTE + VALID_DESC_PASSING_VALUE;
        AutoGradeCommand expectedCommand =
                new AutoGradeCommand(VALID_DESC_PASSING_VALUE_ARRAY, ABSOLUTE);

        assertParserSuccess(input, parser, expectedCommand);

        input = VALID_DESC_PERCENTILE + VALID_DESC_PASSING_VALUE;
        expectedCommand =
                new AutoGradeCommand(VALID_DESC_PASSING_VALUE_ARRAY, PERCENTILE);

        assertParserSuccess(input, parser, expectedCommand);

    }

    @Test
    public void parse_invalidGradingType_failure() {
        String input = " " + PREFIX_AUTOGRADE_TYPE + "equalshare" + VALID_DESC_PASSING_VALUE;

        assertParserFailure(input, parser,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AutoGradeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validPassingScoreValue_success() {
        String input = VALID_DESC_ABSOLUTE + VALID_DESC_PASSING_VALUE;
        AutoGradeCommand expectedCommand =
                new AutoGradeCommand(VALID_DESC_PASSING_VALUE_ARRAY, ABSOLUTE);

        assertParserSuccess(input, parser, expectedCommand);
    }

    @Test
    public void parse_singlePassingScoreValue_success() {
        String input = VALID_DESC_ABSOLUTE + VALID_DESC_SINGLE_PASSING_VALUE;
        AutoGradeCommand expectedCommand =
                new AutoGradeCommand(VALID_DESC_SINGLE_PASSING_VALUE_ARRAY, ABSOLUTE);

        assertParserSuccess(input, parser, expectedCommand);

    }

    @Test
    public void parse_passingScoreValueOutOfBound_failure() {
        // some score below zero
        float[] invalidArray = VALID_DESC_PASSING_VALUE_ARRAY.clone();
        invalidArray[10] = -1;

        String input = VALID_DESC_ABSOLUTE + " " + PREFIX_PASSING_GRADE + toStringList(invalidArray);
        assertParserFailure(input, parser, AutoGradeCommand.MESSAGE_VALUE_LESS_THAN_ZERO);

        // some score above 100
        invalidArray = VALID_DESC_PASSING_VALUE_ARRAY.clone();
        invalidArray[0] = 101;

        input = VALID_DESC_ABSOLUTE + " " + PREFIX_PASSING_GRADE + toStringList(invalidArray);
        assertParserFailure(input, parser, AutoGradeCommand.MESSAGE_VALUE_MORE_THAN_MAXIMUM);
    }

    @Test
    public void parse_passingScoreValueIncreasing_failure() {
        float[] invalidArray = VALID_DESC_PASSING_VALUE_ARRAY.clone();
        invalidArray[2] = invalidArray[1] + 1;
        System.out.println(invalidArray[2]);

        String input = VALID_DESC_ABSOLUTE + " " + PREFIX_PASSING_GRADE + toStringList(invalidArray);
        assertParserFailure(input, parser, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AutoGradeCommand.MESSAGE_INCREASING_VALUE));

    }

    @Test
    public void parse_passingScoreValueNotParsable_failure() {
        String input = VALID_DESC_PERCENTILE + " "
                + PREFIX_PASSING_GRADE + "95 r 80 70 60 50 30";
        assertParserFailure(input, parser, AutoGradeCommand.MESSAGE_PARSE_FLOAT);
    }

    private static String toStringList(float[] input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            result.append(" ").append(input[i]);
        }

        return result.toString();
    }
}
