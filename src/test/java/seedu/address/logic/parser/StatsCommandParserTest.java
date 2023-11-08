package seedu.address.logic.parser;

import static seedu.address.logic.parser.ParserTestUtil.assertParserSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.StatsCommand;
import seedu.address.model.student.TutorialGroup;

import java.util.ArrayList;
import java.util.List;


public class StatsCommandParserTest {
    private StatsCommandParser parser = new StatsCommandParser();
    @Test
    public void execute_stat_success() {
        String input = "";
        String inputWithTut = " g/T01";
        String inputWithStats = " st/max";
        assertParserSuccess(input, parser, new StatsCommand(new ArrayList<>()));
        assertParserSuccess(inputWithTut, parser, new StatsCommand(new ArrayList<>(),new TutorialGroup("T01")));
        assertParserSuccess(inputWithStats, parser, new StatsCommand(new ArrayList<>(List.of("max"))));
    }
}
