package seedu.address.model.studentScore;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 * A list of student scores that enforces uniqueness between its elements and does not allow nulls.
 * A student score is considered unique by comparing using {@code StudentScore#isSameScore(StudentScore)}. As such,
 * adding and updating of student scores uses StudentScore#isSameScore(StudentScore) for equality to ensure that the person
 * being added or updated is unique in terms of identity in the UniqueScoreList. However, the removal of a person uses
 * StudentScore#equals(Object) to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see StudentScore#isSameScore(StudentScore)
 */
public class UniqueScoreList implements Iterable<StudentScore> {

    private final ObservableList<StudentScore> internalList = FXCollections.observableArrayList();
    private final ObservableList<StudentScore> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student score as the given argument.
     */
    public boolean contains(StudentScore toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameScore);
    }

    /**
     * Adds a student score to the list.
     * The student score must not already exist in the list.
     */
    public void add(StudentScore toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            //to change
            throw new RuntimeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student score {@code target} in the list with {@code editedScore}.
     * {@code target} must exist in the list.
     * The student score identity of {@code editedScore} must not be the same as another existing
     * student score in the list.
     */
    public void setPerson(StudentScore target, StudentScore editedScore) {
        requireAllNonNull(target, editedScore);

        int index = internalList.indexOf(target);
        if (index == -1) {
            //to change
            throw new RuntimeException();
        }

        if (!target.isSameScore(editedScore) && contains(editedScore)) {
            //to change
            throw new RuntimeException();
        }

        internalList.set(index, editedScore);
    }

    /**
     * Removes the equivalent student score from the list.
     * The student score must exist in the list.
     */
    public void remove(StudentScore toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RuntimeException();
        }
    }

    public void setPersons(seedu.address.model.studentScore.UniqueScoreList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code scores}.
     * {@code scores} must not contain duplicate scores.
     */
    public void setPersons(List<StudentScore> scores) {
        requireAllNonNull(scores);
        if (!scoresAreUnique(scores)) {
            // to change
            throw new RuntimeException();
        }

        internalList.setAll(scores);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<StudentScore> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<StudentScore> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.studentScore.UniqueScoreList)) {
            return false;
        }

        seedu.address.model.studentScore.UniqueScoreList otherUniquePersonList =
                (seedu.address.model.studentScore.UniqueScoreList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique student scores.
     */
    private boolean scoresAreUnique(List<StudentScore> scores) {
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = i + 1; j < scores.size(); j++) {
                if (scores.get(i).isSameScore(scores.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
