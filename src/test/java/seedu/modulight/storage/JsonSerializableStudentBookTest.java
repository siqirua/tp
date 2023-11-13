package seedu.modulight.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modulight.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.modulight.commons.exceptions.IllegalValueException;
import seedu.modulight.commons.util.JsonUtil;
import seedu.modulight.model.student.model.StudentBook;
import seedu.modulight.testutil.TypicalStudents;

public class JsonSerializableStudentBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
        "JsonSerializableStudentBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentBook.json");
    private static final Path INVALID_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("invalidStudentBook.json");
    private static final Path DUPLICATE_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentBook.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
            JsonSerializableStudentBook.class).get();
        StudentBook studentBookFromFile = dataFromFile.toModelType();
        StudentBook typicalStudentBook = TypicalStudents.getTypicalStudentBook();
        assertEquals(studentBookFromFile, typicalStudentBook);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENTS_FILE,
            JsonSerializableStudentBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENTS_FILE,
            JsonSerializableStudentBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudentBook.MESSAGE_DUPLICATE_PERSON,
            dataFromFile::toModelType);
    }
}
