package seedu.address.storage;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.studentscore.model.ReadOnlyStudentScoreBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.address.model.student.model.StudentBook}.
 */
public interface StudentScoreBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStudentScoreBookFilePath();

    /**
     * Returns StudentScoreBook data as a {@link ReadOnlyStudentScoreBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyStudentScoreBook> readStudentScoreBook() throws DataLoadingException;

    /**
     * @see #getStudentScoreBookFilePath()
     */
    Optional<ReadOnlyStudentScoreBook> readStudentScoreBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyStudentScoreBook} to the storage.
     * @param studentScoreBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStudentScoreBook(ReadOnlyStudentScoreBook studentScoreBook) throws IOException;

    /**
     * @see #saveStudentScoreBook(ReadOnlyStudentScoreBook)
     */
    void saveStudentScoreBook(ReadOnlyStudentScoreBook studentBook, Path filePath) throws IOException;
}
