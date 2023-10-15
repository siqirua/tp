package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.gradedcomponent.model.GradedComponentBook;
import seedu.address.model.student.Student;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.studentscore.model.StudentScoreBook;
/**
 * Represents the in-memory model of the application data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StudentBook studentBook;
    private final StudentScoreBook studentScoreBook;
    private final GradedComponentBook gradedComponentBook;
    private final UserPrefs userPrefs;

    private final FilteredList<Student> filteredStudentList;
    private final FilteredList<GradedComponent> filteredGradedComponentList;
    private final FilteredList<StudentScore> filteredStudentScoreList;


    /**
     * Initializes a UnifiedBookManager with the given studentBook, gradedComponentBook,
     * studentScoreBook and userPrefs.
     */
    public ModelManager(StudentBook studentBook, StudentScoreBook studentScoreBook,
                               GradedComponentBook gradedComponentBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(studentBook, studentScoreBook, gradedComponentBook, userPrefs);

        logger.fine("Initializing with students: " + studentBook
                + "student scores: " + studentScoreBook
                + "graded components: " + gradedComponentBook
                + "and user prefs " + userPrefs);

        this.studentBook = studentBook;
        this.gradedComponentBook = gradedComponentBook;
        this.studentScoreBook = studentScoreBook;
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredStudentList = new FilteredList<>(this.studentBook.getStudentList());
        this.filteredGradedComponentList = new FilteredList<>(this.gradedComponentBook.getGradedComponentList());
        this.filteredStudentScoreList = new FilteredList<>(this.studentScoreBook.getStudentScoreList());
    }


    /**
     * Initializes a UnifiedBookManager with an empty studentBook, gradedComponentBook,
     * studentScoreBook and userPrefs.
     */
    public ModelManager() {
        this(new StudentBook(), new StudentScoreBook(),
                new GradedComponentBook(), new UserPrefs());
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getApplicationFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setApplicationFilePath(Path applicationFilePath) {
        requireNonNull(applicationFilePath);
        userPrefs.setAddressBookFilePath(applicationFilePath);
    }

    @Override
    public StudentBook getStudentBook() {
        return studentBook;
    }

    @Override
    public StudentScoreBook getStudentScoreBook() {
        return studentScoreBook;
    }

    @Override
    public GradedComponentBook getGradedComponentBook() {
        return gradedComponentBook;
    }

    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudentList;
    }

    public ObservableList<GradedComponent> getFilteredGradedComponentList() {
        return filteredGradedComponentList;
    }

    public ObservableList<StudentScore> getFilteredStudentScoreList() {
        return filteredStudentScoreList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return studentBook.equals(otherModelManager.studentBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && gradedComponentBook.equals(otherModelManager.gradedComponentBook)
                && studentScoreBook.equals(otherModelManager.studentScoreBook);
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudentList.setPredicate(predicate);
    }

    @Override
    public void updateFilteredStudentScoreList(Predicate<StudentScore> predicate) {
        requireNonNull(predicate);
        filteredStudentScoreList.setPredicate(predicate);
    }

    @Override
    public void updateFilteredGradedComponentList(Predicate<GradedComponent> predicate) {
        requireNonNull(predicate);
        filteredGradedComponentList.setPredicate(predicate);
    }
}
