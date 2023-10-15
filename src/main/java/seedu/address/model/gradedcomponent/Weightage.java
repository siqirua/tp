package seedu.address.model.gradedcomponent;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the weightage of a graded component in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeightage(double)}
 */
public class Weightage {


    public static final String MESSAGE_CONSTRAINTS =
            "Weightage should be an number larger than or equal to 0.";

    public final Double weightage;

    /**
     * Constructs a {@code Weightage}.
     *
     * @param weightage Weightage for a weighted component for the module.
     */
    public Weightage(double weightage) {
        requireNonNull(weightage);
        checkArgument(isValidWeightage(weightage), MESSAGE_CONSTRAINTS);
        this.weightage = weightage;
    }

    /**
     * Returns true if a given string is a valid weightage.
     */
    public static boolean isValidWeightage(double m) {
        return m >= 0.0;
    }

    @Override
    public String toString() {
        return weightage.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Weightage)) {
            return false;
        }

        Weightage otherWeightage = (Weightage) other;
        return weightage.equals(otherWeightage.weightage);
    }

    @Override
    public int hashCode() {
        return weightage.hashCode();
    }

}
