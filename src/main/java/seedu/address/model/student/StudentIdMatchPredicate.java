package seedu.address.model.student;

import seedu.address.commons.util.ToStringBuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code StudentId} matches any of the Student IDs given.
 */
public class StudentIdMatchPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public StudentIdMatchPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.getStudentId().toString().equalsIgnoreCase(keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.student.StudentIdMatchPredicate)) {
            return false;
        }

        seedu.address.model.student.StudentIdMatchPredicate otherStudentIdMatchPredicate = (seedu.address.model.student.StudentIdMatchPredicate) other;
        return keywords.equals(otherStudentIdMatchPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}





