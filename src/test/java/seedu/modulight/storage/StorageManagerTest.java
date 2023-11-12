package seedu.modulight.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.modulight.testutil.TypicalGradedComponents.getTypicalGradedComponentBook;
import static seedu.modulight.testutil.TypicalStudentScores.getTypicalStudentScoreBook;
import static seedu.modulight.testutil.TypicalStudents.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.modulight.commons.core.GuiSettings;
import seedu.modulight.model.UserPrefs;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;
import seedu.modulight.model.gradedcomponent.model.ReadOnlyGradedComponentBook;
import seedu.modulight.model.student.model.ReadOnlyStudentBook;
import seedu.modulight.model.student.model.StudentBook;
import seedu.modulight.model.studentscore.model.ReadOnlyStudentScoreBook;
import seedu.modulight.model.studentscore.model.StudentScoreBook;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonStudentBookStorage studentBookStorage = new JsonStudentBookStorage(getTempFilePath("sb"));
        JsonGradedComponentBookStorage gradedComponentBookStorage = new JsonGradedComponentBookStorage(getTempFilePath(
            "gcb"));
        JsonStudentScoreBookStorage studentScoreBookStorage = new JsonStudentScoreBookStorage(getTempFilePath(
            "ssb"));
        storageManager = new StorageManager(studentBookStorage, studentScoreBookStorage, gradedComponentBookStorage
            , userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void studentBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonStudentBookStorage} class.
         * More extensive testing of saving/reading is done in {@link JsonStudentBookStorageTest} class.
         */
        StudentBook original = getTypicalAddressBook();
        storageManager.saveStudentBook(original);
        ReadOnlyStudentBook retrieved = storageManager.readStudentBook().get();
        assertEquals(original, new StudentBook(retrieved));
    }

    @Test
    public void studentScoreBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonStudentScoreBookStorage} class.
         * More extensive testing of saving/reading is done in {@link JsonStudentScoreBookStorageTest} class.
         */
        StudentScoreBook original = getTypicalStudentScoreBook();
        storageManager.saveStudentScoreBook(original);
        ReadOnlyStudentScoreBook retrieved = storageManager.readStudentScoreBook().get();
        assertEquals(original, new StudentScoreBook(retrieved));
    }

    @Test
    public void gradedComponentBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonGradedComponentBookStorage} class.
         * More extensive testing of saving/reading is done in {@link JsonGradedComponentBookStorageTest} class.
         */
        GradedComponentBook original = getTypicalGradedComponentBook();
        storageManager.saveGradedComponentBook(original);
        ReadOnlyGradedComponentBook retrieved = storageManager.readGradedComponentBook().get();
        assertEquals(original, new GradedComponentBook(retrieved));
    }

    @Test
    public void getStudentBookFilePath() {
        assertNotNull(storageManager.getStudentBookFilePath());
    }

    @Test
    public void getStudentScoreBookFilePath() {
        assertNotNull(storageManager.getStudentScoreBookFilePath());
    }

    @Test
    public void getGcBookFilePath() {
        assertNotNull(storageManager.getGcBookFilePath());
    }
}
