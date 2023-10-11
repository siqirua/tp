package seedu.address.model.gradedComponent;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class GradedComponent {

    // Identity fields
    private final GcName name;

    /**
     * Every field must be present and not null.
     */
    public GradedComponent(GcName name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public GcName getSid() {
        return this.name;
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

        GradedComponent otherPerson = (GradedComponent) other;
        return name.equals(otherPerson.name);
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
