package seedu.address.model.gradedcomponent;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Represents a graded component in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class GradedComponent {

    // Identity fields
    private final GcName name;

    // Data fields
    private final MaxMarks maxMarks;
    private final Weightage weightage;


    /**
     * Every field must be present and not null.
     */
    public GradedComponent(GcName name, MaxMarks maxMarks, Weightage weightage) {
        requireAllNonNull(name);
        this.name = name;
        this.maxMarks = maxMarks;
        this.weightage = weightage;
    }

    public GcName getName() {
        return name;
    }

    public MaxMarks getMaxMarks() {
        return maxMarks;
    }

    public Weightage getWeightage() {
        return weightage;
    }
    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameGc(GradedComponent otherGc) {
        if (otherGc == this) {
            return true;
        }

        return otherGc != null
                && otherGc.getName().equals(getName());
    }
    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradedComponent)) {
            return false;
        }

        GradedComponent otherGc = (GradedComponent) other;
        return otherGc.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .toString();
    }

}
