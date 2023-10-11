package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final StudentId sid;

    /**
     * Every field must be present and not null.
     */
    public Student(StudentId sid) {
        requireAllNonNull(sid);
        this.sid = sid;
    }

    public StudentId getSid() {
        return this.sid;
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
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherPerson = (Student) other;
        return sid.equals(otherPerson.sid);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(sid);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student id", sid)
                .toString();
    }

}
