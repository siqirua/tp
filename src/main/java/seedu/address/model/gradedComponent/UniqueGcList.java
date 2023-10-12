package seedu.address.model.gradedComponent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


/**
 * A list of graded components that enforces uniqueness between i ts elements and does not allow nulls.
 *  * A graded component is considered unique by comparing using {s@code GradedComponent#isSameGc(Person)}.
 * As such, adding and updating of persons uses GradedComponent#isSameGc(Person) for equality
 * to ensure that the person being added or updated is unique in terms of identity in the
 * UniqueGcList. However, the removal of a person uses GradedComponent#equals(Object)
 * to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see GradedComponent#isSameGc(GradedComponent)
 */
public class UniqueGcList implements Iterable<GradedComponent> {

    private final ObservableList<GradedComponent> internalList = FXCollections.observableArrayList();
    private final ObservableList<GradedComponent> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent GradedComponent as the given argument.
     */
    public boolean contains(GradedComponent toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameGc);
    }

    /**
     * Adds a GradedComponent to the list.
     * The GradedComponent must not already exist in the list.
     */
    public void add(GradedComponent toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            //to change
            throw new RuntimeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedGc}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedGc} must not be the same as another existing graded
     * component in the list.
     */
    public void setPerson(GradedComponent target, GradedComponent editedGc) {
        requireAllNonNull(target, editedGc);

        int index = internalList.indexOf(target);
        if (index == -1) {
            //to change
            throw new RuntimeException();
        }

        if (!target.isSameGc(editedGc) && contains(editedGc)) {
            //to change
            throw new RuntimeException();
        }

        internalList.set(index, editedGc);
    }

    /**
     * Removes the equivalent graded component from the list.
     * The graded component must exist in the list.
     */
    public void remove(GradedComponent toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RuntimeException();
        }
    }

    public void setPersons(UniqueGcList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code comps}.
     * {@code comps} must not contain duplicate graded components.
     */
    public void setPersons(List<GradedComponent> comps) {
        requireAllNonNull(comps);
        if (!gcsAreUnique(comps)) {
            // to change
            throw new RuntimeException();
        }

        internalList.setAll(comps);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<GradedComponent> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<GradedComponent> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueGcList)) {
            return false;
        }

        UniqueGcList otherUniquePersonList =
                (UniqueGcList) other;
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
     * Returns true if {@code comps} contains only unique graded components.
     */
    private boolean gcsAreUnique(List<GradedComponent> comps) {
        for (int i = 0; i < comps.size() - 1; i++) {
            for (int j = i + 1; j < comps.size(); j++) {
                if (comps.get(i).isSameGc(comps.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
