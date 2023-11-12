package seedu.modulight.testutil;

import static seedu.modulight.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_EMAIL_JAMES;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_NAME_JAMES;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_SID_AMY;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_SID_JAMES;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_TAG_MAKEUP_EXAM;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_TAG_TA;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_TG_AMY;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_TG_JAMES;
import static seedu.modulight.testutil.TestGcDataUtil.getTestGradedComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;
import seedu.modulight.model.student.Student;
import seedu.modulight.model.student.model.StudentBook;
import seedu.modulight.model.studentscore.StudentScore;
import seedu.modulight.model.studentscore.model.StudentScoreBook;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withId("A0000000Y").withName("Alice Pauline")
            .withEmail("alice@example.com").withTg("T01").withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withId("A0000001Y").withName("Benson Meier")
            .withEmail("johnd@example.com").withTg("T01").withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withId("A0000002Y").withName("Carl Kurz").withTg("T01")
            .withEmail("heinz@example.com").build();
    public static final Student DANIEL = new StudentBuilder().withId("A0000003Y").withName("Daniel Meier")
            .withEmail("cornelia@example.com").withTg("T02").withTags("plagiarism").build();
    public static final Student ELLE = new StudentBuilder().withId("A0000004Y").withName("Elle Meyer")
            .withEmail("werner@example.com").withTg("T02").build();
    public static final Student FIONA = new StudentBuilder().withId("A0000005Y").withName("Fiona Kunz")
            .withEmail("lydia@example.com").withTg("T02").build();
    public static final Student GEORGE = new StudentBuilder().withId("A0000006Y").withName("George Best")
            .withEmail("anna@example.com").withTg("T02").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withId("A0000007Y").withName("Hoon Meier")
            .withEmail("stefan@example.com").withTg("T03").build();
    public static final Student IDA = new StudentBuilder().withId("A0000008Y").withName("Ida Mueller")
            .withEmail("hans@example.com").withTg("T03").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withId(VALID_SID_AMY)
            .withTg(VALID_TG_AMY).withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_TA).build();
    public static final Student JAMES = new StudentBuilder().withName(VALID_NAME_JAMES).withId(VALID_SID_JAMES)
            .withTg(VALID_TG_JAMES).withEmail(VALID_EMAIL_JAMES).withTags(VALID_TAG_MAKEUP_EXAM)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Students.
     */
    public static StudentBook getTypicalStudentBook() {
        StudentBook sb = new StudentBook();
        for (Student student : getTypicalStudents()) {
            sb.addStudent(student);
        }
        return sb;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<GradedComponent> getTypicalComponents() {
        return new ArrayList<>(Arrays.asList(getTestGradedComponents()));
    }

    public static GradedComponentBook getTypicalComponentBook() {
        GradedComponentBook cb = new GradedComponentBook();
        for (GradedComponent gc : getTypicalComponents()) {
            cb.addGradedComponent(gc);
        }
        return cb;
    }

    public static StudentScoreBook getTypicalScoreBook() {
        StudentScoreBook sb = new StudentScoreBook();
        for (StudentScore ss : getTypicalStudentScores()) {
            sb.addStudentScore(ss);
        }
        return sb;
    }

    public static List<StudentScore> getTypicalStudentScores() {
        float score = 0;
        List<StudentScore> scores = new ArrayList<>();
        List<Student> students = getTypicalStudents();
        List<GradedComponent> components = getTypicalComponents();
        for (GradedComponent gc : components) {
            for (Student s : students) {
                scores.add(new StudentScore(s.getStudentId(), gc.getName(), score));
                score++;
            }
        }
        return scores;
    }
}
