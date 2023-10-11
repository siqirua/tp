package seedu.address.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.studentScore.StudentScore;


/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see StudentScore#isSameScore(StudentScore)
 */
public class AssociatedScoreList implements Iterable<StudentScore> {

    private final ObservableList<StudentScore> internalList = FXCollections.observableArrayList();
    private final ObservableList<StudentScore> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(StudentScore toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameScore);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
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
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
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
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(StudentScore toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RuntimeException();
        }
    }

    public void setPersons(seedu.address.model.util.AssociatedScoreList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
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
        if (!(other instanceof seedu.address.model.util.AssociatedScoreList)) {
            return false;
        }

        seedu.address.model.util.AssociatedScoreList otherUniquePersonList =
                (seedu.address.model.util.AssociatedScoreList) other;
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
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean scoresAreUnique(List<StudentScore> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSameScore(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
