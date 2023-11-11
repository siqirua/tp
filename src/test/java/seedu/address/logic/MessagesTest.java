package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.gradedcomponent.MaxMarks;
import seedu.address.model.gradedcomponent.Weightage;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentEmail;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.studentscore.StudentScore;

import java.util.ArrayList;
import java.util.HashSet;

public class MessagesTest {
    @Test
    public void error_duplicatePrefixes_success() {
        assertEquals(Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_NAME),
                "Multiple values specified for the following single-valued field(s): n/");
    }
    @Test
    public void format_student_success() {
        Student testStu = new Student(new StudentId("A0123456U"), new StudentName("Test Stu"),
                new StudentEmail("xyz@nus.edu.sg"), new TutorialGroup("T01"), new ArrayList<>(),
                new HashSet<>());
        String expectedOutput = "A0123456U; Name: Test Stu; Email: xyz@nus.edu.sg; Tutorial Group: T01";
        assertEquals(Messages.format(testStu), expectedOutput);
    }

    @Test
    public void format_studentScore_success() {
        StudentScore testStuScore = new StudentScore(new StudentId("A0123456U"), new GcName("Midterm"), 40);
        String expectedOutput = "; Student ID: A0123456U; Graded component name: Midterm; Score: 40.0";
        assertEquals(Messages.formatStudentScore(testStuScore), expectedOutput);
    }

    @Test
    public void format_gradedComponent_success() {
        GradedComponent testGc = new GradedComponent(new GcName("Midterm"), new MaxMarks(100), new Weightage(20));
        String expectedOutput = "Midterm; Maximum Marks: 100.0; Weightage: 20.0";
        assertEquals(Messages.formatGradedComponent(testGc), expectedOutput);
    }
}
