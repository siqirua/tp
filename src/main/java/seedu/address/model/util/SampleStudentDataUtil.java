package seedu.address.model.util;

import seedu.address.model.student.*;
import seedu.address.model.student.model.ReadOnlyStudentBook;
import seedu.address.model.student.model.StudentBook;

public class SampleStudentDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
                new Student(new StudentId("A0000000Y"), new StudentName("ABC"), new StudentEmail("alexyeoh@example.com"),
                        new TutorialGroup("03")),
                new Student(new StudentId("A0000001Y"), new StudentName("DEF"), new StudentEmail("blexyeoh@example" +
                        ".com"),
                        new TutorialGroup("03")),
                new Student(new StudentId("A0000002Y"), new StudentName("GHI"), new StudentEmail("clexyeoh@example" +
                        ".com"),
                        new TutorialGroup("03")),
                new Student(new StudentId("A0000003Y"), new StudentName("JKL"), new StudentEmail("dlexyeoh@example" +
                        ".com"),
                        new TutorialGroup("03")),
                new Student(new StudentId("A0000004Y"), new StudentName("LMN"), new StudentEmail("elexyeoh@example" +
                        ".com"),
                        new TutorialGroup("03")),
                new Student(new StudentId("A0000005Y"), new StudentName("OPQ"), new StudentEmail("flexyeoh@example" +
                        ".com"),
                        new TutorialGroup("03"))
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
