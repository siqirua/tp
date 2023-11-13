package seedu.modulight.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modulight.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.modulight.commons.exceptions.IllegalValueException;
import seedu.modulight.commons.util.JsonUtil;
import seedu.modulight.model.studentscore.model.StudentScoreBook;

public class JsonSerializableStudentScoreTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data"
        , "JsonSerializableStudentScoreBookTest");
    private static final Path INVALID_STUDENT_SCORE_FILE = TEST_DATA_FOLDER.resolve(
        "invalidStudentScoreBook.json");

}
