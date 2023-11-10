package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentEmail;
import seedu.address.model.student.StudentGrade;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_ID = "A0000000W";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TG = "T00";
    public static final String DEFAULT_GRADE = "";

    private StudentId sid;
    private StudentName name;
    private StudentEmail email;
    private TutorialGroup tg;
    private List<StudentScore> scoreList;

    private Set<Tag> tags;
    private float totalScore;
    private StudentGrade studentGrade;


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public StudentBuilder() {
        sid = new StudentId(DEFAULT_ID);
        name = new StudentName(DEFAULT_NAME);
        email = new StudentEmail(DEFAULT_EMAIL);
        tg = new TutorialGroup(DEFAULT_TG);
        scoreList = new ArrayList<>();
        tags = new HashSet<>();
        studentGrade = new StudentGrade(DEFAULT_GRADE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        sid = studentToCopy.getStudentId();
        name = studentToCopy.getName();
        email = studentToCopy.getEmail();
        tg = studentToCopy.getTutorial();
        tags = new HashSet<>(studentToCopy.getTags());
        scoreList = new ArrayList<>(studentToCopy.getScores());
        studentGrade = studentToCopy.getStudentGrade();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, tags);
    }

}
