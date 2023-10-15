package seedu.address.model.gradedComponent;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a graded component's maximum marks in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMarks(double)}
 */
public class MaxMarks {


    public static final String MESSAGE_CONSTRAINTS =
            "Maximum mark argument should be an number larger than or equal to 0.";

    public final Double maxMarks;

    /**
     * Constructs a {@code MaxMarks}.
     *
     * @param maxMarks Maximum marks for a graded component.
     */
    public MaxMarks(double maxMarks) {
        requireNonNull(maxMarks);
        checkArgument(isValidMarks(maxMarks), MESSAGE_CONSTRAINTS);
        this.maxMarks = maxMarks;
    }

    /**
     * Returns true if a given string is a valid mark.
     */
    public static boolean isValidMarks(double m) {
        return m >= 0.0;
    }

    @Override
    public String toString() {
        return maxMarks.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MaxMarks)) {
            return false;
        }

        MaxMarks otherMark = (MaxMarks) other;
        return maxMarks.equals(otherMark.maxMarks);
    }

    @Override
    public int hashCode() {
        return maxMarks.hashCode();
    }

}
