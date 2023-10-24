package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentEmail;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.studentscore.model.StudentScoreBook;
import seedu.address.model.tag.Tag;


/**
 * Edits the details of an existing Student in the address book.
 */

public class EditStudentCommand extends Command {

    public static final String COMMAND_WORD = "editStu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_STUDENT_ID + "STUDENT_ID] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in ModuleLight.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the Student in the filtered Student list to edit
     * @param editStudentDescriptor details to edit the Student with
     */
    public EditStudentCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        StudentBook sModel = model.getStudentBook();
        if (!studentToEdit.isSameStudent(editedStudent) && sModel.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        StudentScoreBook ssb = model.getStudentScoreBook();
        List<StudentScore> ssList = ssb.getStudentScoreList();
        sModel.setStudent(studentToEdit, editedStudent);

        for (int i = 0; i < ssList.size(); i++) {
            StudentScore sc = ssList.get(i);
            if (sc.getStudentId().equals(studentToEdit.getStudentId())) {
                EditStudentScoreCommand.EditStudentScoreDescriptor newDescriptor =
                        new EditStudentScoreCommand.EditStudentScoreDescriptor();
                newDescriptor.setStudentId(editedStudent.getStudentId());
                StudentId sid = sc.getStudentId();
                GcName gcName = sc.getGcName();
                new EditStudentScoreCommand(Index.fromZeroBased(i), newDescriptor).execute(model);
            }
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedStudent)));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code StudentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;
        StudentId updatedId = editStudentDescriptor.getId().orElse(studentToEdit.getStudentId());
        StudentName updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        StudentEmail updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        TutorialGroup updatedTutorialGroup = editStudentDescriptor.getTutorialGroup()
                        .orElse(studentToEdit.getTutorial());
        Set<Tag> updatedTags = editStudentDescriptor.getTags().orElse(studentToEdit.getTags());
        List<StudentScore> updatedScores = studentToEdit.getScores();

        return new Student(updatedId, updatedName, updatedEmail, updatedTutorialGroup, updatedScores, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        EditStudentCommand otherEditCommand = (EditStudentCommand) other;
        return index.equals(otherEditCommand.index)
                && editStudentDescriptor.equals(otherEditCommand.editStudentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editStudentDescriptor", editStudentDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the Student with. Each non-empty field value will replace the
     * corresponding field value of the Student.
     */
    public static class EditStudentDescriptor {
        private StudentId sid;
        private StudentName name;
        private StudentEmail email;
        private Set<Tag> tags;
        private TutorialGroup tg;
        private List<StudentScore> scores;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setId(toCopy.sid);
            setName(toCopy.name);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
            setScores(toCopy.scores);
            setTutorialGroup(toCopy.tg);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(sid, name, email, tags, scores, tg);
        }

        public void setId(StudentId sid) {
            this.sid = sid;
        }

        public Optional<StudentId> getId() {
            return Optional.ofNullable(sid);
        }

        public void setName(StudentName name) {
            this.name = name;
        }

        public Optional<StudentName> getName() {
            return Optional.ofNullable(name);
        }


        public void setEmail(StudentEmail email) {
            this.email = email;
        }

        public Optional<StudentEmail> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setTutorialGroup(TutorialGroup tg) {
            this.tg = tg;
        }

        public Optional<TutorialGroup> getTutorialGroup() {
            return Optional.ofNullable(tg);
        }

        public void setScores(List<StudentScore> scores) {
            this.scores = (scores != null) ? new ArrayList<>(scores) : null;
        }

        public Optional<List<StudentScore>> getScores() {
            return (scores != null) ? Optional.of(Collections.unmodifiableList(scores)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            EditStudentDescriptor otherEditStudentDescriptor = (EditStudentDescriptor) other;
            return Objects.equals(name, otherEditStudentDescriptor.name)
                    && Objects.equals(sid, otherEditStudentDescriptor.sid)
                    && Objects.equals(email, otherEditStudentDescriptor.email)
                    && Objects.equals(tg, otherEditStudentDescriptor.tg)
                    && Objects.equals(scores, otherEditStudentDescriptor.scores)
                    && Objects.equals(tags, otherEditStudentDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("student ID", sid)
                    .add("name", name)
                    .add("email", email)
                    .add("tutorial group", tg)
                    .add("score", scores)
                    .add("tags", tags)
                    .toString();
        }
    }
}

