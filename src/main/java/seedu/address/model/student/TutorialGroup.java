package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
public class TutorialGroup {
    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial groups should only contain a capital letter and a 2 digit number, and it should not be blank";

    public static final String VALIDATION_REGEX = "^[A-Z]\\\\d{2}";

    private final String groupName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid tutorial group.
     */
    public TutorialGroup(String name) {
        requireNonNull(name);
        checkArgument(isValidTutorial(name), MESSAGE_CONSTRAINTS);
        groupName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTutorial(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return groupName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.student.TutorialGroup)) {
            return false;
        }

        seedu.address.model.student.TutorialGroup otherName = (seedu.address.model.student.TutorialGroup) other;
        return groupName.equals(otherName.groupName);
    }

    @Override
    public int hashCode() {
        return groupName.hashCode();
    }
}
