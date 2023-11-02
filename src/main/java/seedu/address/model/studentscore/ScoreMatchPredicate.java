package seedu.address.model.studentscore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.student.Student;
import seedu.address.model.student.StudentMatchPredicate;

/**
 * Tests that a {@code Student}'s attributes matches any of the attributes given.
 */
public class ScoreMatchPredicate implements Predicate<StudentScore> {
    private final Predicate<Student> studentMatchPredicate;
    private final List<String> tagKeywords;

    private final List<String> gcKeywords;

    /**
     * Constructs a predicate to search for certain students.
     *
     * @param studentMatchPredicate the student match predicate
     * @param tagKeywords           The keywords for Student tag (can be empty)
     * @param gcKeywords            the gc keywords
     */
    public ScoreMatchPredicate(Predicate<Student> studentMatchPredicate, List<String> tagKeywords,
                               List<String> gcKeywords) {
        this.studentMatchPredicate = studentMatchPredicate;
        this.tagKeywords = tagKeywords;
        this.gcKeywords = gcKeywords;
    }

    public ScoreMatchPredicate(List<String> gcKeywords) {
        List<String> empty = new ArrayList<>();
        this.studentMatchPredicate = new StudentMatchPredicate(empty, empty, empty, empty, empty);
        this.tagKeywords = new ArrayList<>();
        this.gcKeywords = gcKeywords;
    }
    @Override
    public boolean test(StudentScore score) {
        boolean tagMatch = tagKeywords.isEmpty() || score.getTags().stream()
                .anyMatch(tag -> tagKeywords.contains(tag.tagName));
        boolean studentTagMatch = tagKeywords.isEmpty() || score.getStudent().getTags().stream()
                .anyMatch(tag -> tagKeywords.contains(tag.tagName));
        boolean gcMatch = gcKeywords.isEmpty() || gcKeywords.stream()
                .anyMatch(keyword -> score.getGcName().toString().toLowerCase().contains(keyword.toLowerCase()));
        return (tagMatch && gcMatch && studentTagMatch) && studentMatchPredicate.test(score.getStudent());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScoreMatchPredicate)) {
            return false;
        }

        ScoreMatchPredicate otherStudentIdMatchPredicate =
                (ScoreMatchPredicate) other;
        return studentMatchPredicate.equals(otherStudentIdMatchPredicate.studentMatchPredicate)
                && tagKeywords.equals(otherStudentIdMatchPredicate.tagKeywords)
                && gcKeywords.equals(otherStudentIdMatchPredicate.gcKeywords);
    }

    @Override
    public String toString() {
        return "Find student scores by given criteria";
    }
}

