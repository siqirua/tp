package seedu.address.model.studentscore.model;

import javafx.collections.ObservableList;
import seedu.address.model.studentscore.StudentScore;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyStudentScoreBook {

    /**
     * Returns an unmodifiable view of the studentScores list.
     * This list will not contain any duplicate studentScores.
     */
    ObservableList<StudentScore> getStudentScoreList();

}
