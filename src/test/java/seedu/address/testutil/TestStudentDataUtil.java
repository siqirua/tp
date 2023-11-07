package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;
import seedu.address.model.student.StudentEmail;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.student.model.ReadOnlyStudentBook;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating StudentBook with sample data.
 */
public class TestStudentDataUtil {

    private static List<StudentScore> scoreList = new ArrayList<>();

    private static Set<Tag> tags = new HashSet<>();

    public static ArrayList<Student> getTestStudents() {
        Student[] students = new Student[]{
            new Student(new StudentId("A0000000Y"), new StudentName("Lionel Messi"),
                new StudentEmail("alexyeoh@example.com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000001Y"), new StudentName("Ah Beng"), new StudentEmail("blexyeoh@example"
                + ".com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000002Y"), new StudentName("Paul McCartney"), new StudentEmail("clexyeoh@example"
                + ".com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000003Y"), new StudentName("Li Ming"), new StudentEmail("dlexyeoh@example"
                + ".com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000004Y"), new StudentName("Catherine"),
                new StudentEmail("elexyeoh@example.com"),
                new TutorialGroup("T03"), scoreList, tags),
            new Student(new StudentId("A0000005Y"), new StudentName("Goodman"), new StudentEmail("flexyeoh@example"
                + ".com"),
                new TutorialGroup("G03"), scoreList, tags)
        };
        return new ArrayList<>(List.of(students));
    }

    public static ArrayList<Student> getTestStudentsAfterAdding() {
        ArrayList<Student> newStudents = getTestStudents();
        newStudents.add(new Student(new StudentId("A1234567U"), new StudentName("Newbie"),
                new StudentEmail("newbie@gmail.com"),
                new TutorialGroup("T05"), scoreList, tags));
        return newStudents;
    }

    public static ArrayList<Student> getTestStudentsAfterDeleting() {
        ArrayList<Student> newStudents = getTestStudents();
        newStudents.remove(1);
        return newStudents;
    }

    public static ArrayList<Student> getTestStudentsWithScore() {
        float markToBeAdded = 0;
        ArrayList<Student> newStudents = new ArrayList<>();
        if (!getTestStudents().isEmpty()) {
            Collections.copy(newStudents, getTestStudents());
        }
        for (Student stu : newStudents) {
            stu.addScore(new StudentScore(stu.getStudentId(), new GcName("Midterm"),
                    markToBeAdded));
            markToBeAdded += newStudents.size() > 1 ? (float) 100 / (newStudents.size() - 1) : 0;
        }
        return newStudents;
    }

    public static ArrayList<Student> getTestStudentsWithZeroScore() {
        float markToBeAdded = 0;
        ArrayList<Student> newStudents = new ArrayList<>();
        if (!getTestStudents().isEmpty()) {
            Collections.copy(newStudents, getTestStudents());
        }
        for (Student stu : newStudents) {
            stu.addScore(new StudentScore(stu.getStudentId(), new GcName("Midterm"),
                    0));
        }
        return newStudents;
    }

    public static ReadOnlyStudentBook getTestStudentBook(String selectedStu) {
        StudentBook sampleStu = new StudentBook();
        ArrayList<Student> stuToBeAdded;
        switch (selectedStu) {
        case "add":
            stuToBeAdded = getTestStudentsAfterAdding();
            break;
        case "delete":
            stuToBeAdded = getTestStudentsAfterDeleting();
            break;
        case "create":
            stuToBeAdded = getTestStudents();
            break;
        case "score":
            stuToBeAdded = getTestStudentsWithScore();
            break;
        case "zeroScore":
            stuToBeAdded = getTestStudentsWithZeroScore();
            break;
        default:
            stuToBeAdded = new ArrayList<>();
        }
        for (Student testStu: stuToBeAdded) {
            sampleStu.addStudent(testStu);
        }
        return sampleStu;
    }
}
