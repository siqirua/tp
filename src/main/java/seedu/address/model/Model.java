package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.gradedComponent.GradedComponent;
import seedu.address.model.gradedComponent.model.GradedComponentBook;
import seedu.address.model.student.Student;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentScore.StudentScore;
import seedu.address.model.studentScore.model.StudentScoreBook;

/**
 * The API of the Model component.
 */
public interface Model {

    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<GradedComponent> PREDICATE_SHOW_ALL_GRADED_COMPONENTS = unused -> true;
    Predicate<StudentScore> PREDICATE_SHOW_ALL_STUDENT_SCORES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' application file path.
     */
    Path getApplicationFilePath();

    /**
     * Sets the user prefs' application file path.
     */
    void setApplicationFilePath(Path applicationFilePath);

    /**
     * Returns the student data with the data in {@code studentBook}.
     */
    StudentBook getStudentBook();

    /**
     * Returns the student score data with the data in {@code studentScoreBook}.
     */
    StudentScoreBook getStudentScoreBook();

    /**
     * Returns the graded component data with the data in {@code gradedComponentBook}.
     */
    GradedComponentBook getGradedComponentBook();

    ObservableList<Student> getFilteredStudentList();

    ObservableList<GradedComponent> getFilteredGradedComponentList();

    ObservableList<StudentScore> getFilteredStudentScoreList();

    void updateFilteredStudentList(Predicate<Student> predicate);

    void updateFilteredGradedComponentList(Predicate<GradedComponent> predicate);

    void updateFilteredStudentScoreList(Predicate<StudentScore> predicate);


}
