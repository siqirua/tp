package seedu.address.testutil;

import seedu.address.model.student.*;
import seedu.address.model.student.model.ReadOnlyStudentBook;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Contains utility methods for populating StudentBook with sample data.
 */
public class TestStudentDataUtil {

    private static List<StudentScore> scoreList = new ArrayList<>();

    private static Set<Tag> tags = new HashSet<>();

    public static Student[] getSampleStudents() {
        return new Student[]{
            new Student(new StudentId("A0000000Y"), new StudentName("ABC"),
                new StudentEmail("alexyeoh@example.com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000001Y"), new StudentName("DEF"), new StudentEmail("blexyeoh@example"
                + ".com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000002Y"), new StudentName("GHI"), new StudentEmail("clexyeoh@example"
                + ".com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000003Y"), new StudentName("JKL"), new StudentEmail("dlexyeoh@example"
                + ".com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000004Y"), new StudentName("LMN"), new StudentEmail("elexyeoh@example"
                + ".com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000005Y"), new StudentName("OPQ"), new StudentEmail("flexyeoh@example"
                + ".com"),
                new TutorialGroup("G03"), scoreList, tags)
        };
    }

    public static ReadOnlyStudentBook getSampleStudentBook() {
        StudentBook sampleAb = new StudentBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }
}
