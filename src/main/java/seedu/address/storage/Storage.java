package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.model.ReadOnlyStudentBook;
import seedu.address.model.studentscore.model.ReadOnlyStudentScoreBook;

/**
 * API of the Storage component
 */
public interface Storage extends StudentBookStorage, UserPrefsStorage, StudentScoreBookStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStudentBookFilePath();

    @Override
    Optional<ReadOnlyStudentBook> readStudentBook() throws DataLoadingException;

    @Override
    void saveStudentBook(ReadOnlyStudentBook addressBook) throws IOException;

    @Override
    Path getStudentScoreBookFilePath();

    @Override
    Optional<ReadOnlyStudentScoreBook> readStudentScoreBook() throws DataLoadingException;

    @Override
    void saveStudentScoreBook(ReadOnlyStudentScoreBook addressBook) throws IOException;

}
