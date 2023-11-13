package seedu.modulight.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.modulight.testutil.Assert.assertThrows;
import static seedu.modulight.testutil.TypicalGradedComponents.CA1;
import static seedu.modulight.testutil.TypicalGradedComponents.CA2;
import static seedu.modulight.testutil.TypicalGradedComponents.MIDTERMS;
import static seedu.modulight.testutil.TypicalGradedComponents.getTypicalGradedComponentBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.modulight.commons.exceptions.DataLoadingException;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;
import seedu.modulight.model.gradedcomponent.model.ReadOnlyGradedComponentBook;

public class JsonGradedComponentBookStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data"
        , "JsonGradedComponentBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readGradedComponentBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readGradedComponentBook(null));
    }

    private java.util.Optional<ReadOnlyGradedComponentBook> readGradedComponentBook(String filePath) throws Exception {
        return new JsonGradedComponentBookStorage(Paths.get(filePath)).readGradedComponentBook(
            addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readGradedComponentBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readGradedComponentBook("notJsonFormatGcBook.json"));
    }

    @Test
    public void readGradedComponentBook_invalidGradedComponentBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readGradedComponentBook("invalidGcBook.json"));
    }

    @Test
    public void readGradedComponentBook_invalidAndValidStudentBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readGradedComponentBook(
            "invalidAndValidGcBook.json"));
    }

    @Test
    public void readAndSaveGradedComponentBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempGradedComponentBook.json");
        GradedComponentBook original = getTypicalGradedComponentBook();
        JsonGradedComponentBookStorage jsonGradedComponentBookStorage = new JsonGradedComponentBookStorage(filePath);

        // Save in new file and read back
        jsonGradedComponentBookStorage.saveGradedComponentBook(original, filePath);
        ReadOnlyGradedComponentBook readBack = jsonGradedComponentBookStorage.readGradedComponentBook(filePath).get();
        assertEquals(original, new GradedComponentBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGradedComponent(CA1);
        original.removeGradedComponent(MIDTERMS);
        jsonGradedComponentBookStorage.saveGradedComponentBook(original, filePath);
        readBack = jsonGradedComponentBookStorage.readGradedComponentBook(filePath).get();
        assertEquals(original, new GradedComponentBook(readBack));

        // Save and read without specifying file path
        original.addGradedComponent(CA2);
        jsonGradedComponentBookStorage.saveGradedComponentBook(original); // file path not specified
        readBack = jsonGradedComponentBookStorage.readGradedComponentBook().get(); // file path not specified
        assertEquals(original, new GradedComponentBook(readBack));

    }

    @Test
    public void saveGradedComponentBook_nullGradedComponentBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGradedComponentBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code gradedComponentBook} at the specified {@code filePath}.
     */
    private void saveGradedComponentBook(ReadOnlyGradedComponentBook gradedComponentBook, String filePath) {
        try {
            new JsonGradedComponentBookStorage(Paths.get(filePath))
                .saveGradedComponentBook(gradedComponentBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveGradedComponentBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGradedComponentBook(new GradedComponentBook()
            , null));
    }
}
