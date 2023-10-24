package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAX_MARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.gradedcomponent.MaxMarks;
import seedu.address.model.gradedcomponent.Weightage;
import seedu.address.model.gradedcomponent.model.GradedComponentBook;
import seedu.address.model.student.StudentId;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.studentscore.model.StudentScoreBook;

/**
 * Edits the details of an existing gradedComponent in the address book.
 */
public class EditGradedComponentCommand extends Command {

    public static final String COMMAND_WORD = "editComp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the gradedComponent identified "
            + "by the index number used in the displayed gradedComponent list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPONENT_NAME + "COMPONENT_NAME] "
            + "[" + PREFIX_MAX_MARKS + "MAX_MARKS] "
            + "[" + PREFIX_WEIGHTAGE + "WEIGHTAGE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMPONENT_NAME + "Midterm Project "
            + PREFIX_WEIGHTAGE + "20";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited GradedComponent: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_GRADED_COMPONENT = "This gradedComponent already "
            + "exists in the address book.";

    private final Index index;
    private final EditGradedComponentDescriptor editGradedComponentDescriptor;

    /**
     * @param index of the gradedComponent in the filtered gradedComponent list to edit
     * @param editGradedComponentDescriptor details to edit the gradedComponent with
     */
    public EditGradedComponentCommand(Index index, EditGradedComponentDescriptor editGradedComponentDescriptor) {
        requireNonNull(index);
        requireNonNull(editGradedComponentDescriptor);

        this.index = index;
        this.editGradedComponentDescriptor = new EditGradedComponentDescriptor(editGradedComponentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<GradedComponent> lastShownList = model.getFilteredGradedComponentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        GradedComponent gradedComponentToEdit = lastShownList.get(index.getZeroBased());
        GradedComponent editedGradedComponent = createEditedGradedComponent(gradedComponentToEdit,
                editGradedComponentDescriptor);

        GradedComponentBook gModel = model.getGradedComponentBook();

        if (!gradedComponentToEdit.isSameGc(editedGradedComponent)
                && gModel.hasGradedComponent(editedGradedComponent)) {
            throw new CommandException(MESSAGE_DUPLICATE_GRADED_COMPONENT);
        }

        StudentScoreBook ssb = model.getStudentScoreBook();
        List<StudentScore> ssList = ssb.getStudentScoreList();


        for (int i = 0; i < ssList.size(); i++) {
            StudentScore sc = ssList.get(i);
            if (sc.getGcName().equals(gradedComponentToEdit.getName())) {
                EditStudentScoreCommand.EditStudentScoreDescriptor newDescriptor =
                        new EditStudentScoreCommand.EditStudentScoreDescriptor();
                newDescriptor.setGcName(editedGradedComponent.getName());
                new EditStudentScoreCommand(Index.fromZeroBased(i), newDescriptor).execute(model);
            }
        }

        gModel.setGradedComponent(gradedComponentToEdit, editedGradedComponent);

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.formatGradedComponent(editedGradedComponent)));
    }

    /**
     * Creates and returns a {@code GradedComponent} with the details of {@code gradedComponentToEdit}
     * edited with {@code editGradedComponentDescriptor}.
     */
    private static GradedComponent createEditedGradedComponent(GradedComponent gradedComponentToEdit,
                   EditGradedComponentDescriptor editGradedComponentDescriptor) {
        assert gradedComponentToEdit != null;

        GcName updatedName = editGradedComponentDescriptor.getGcName()
                .orElse(gradedComponentToEdit.getName());
        MaxMarks updatedMaxMarks = editGradedComponentDescriptor.getMaxMarks()
                .orElse(gradedComponentToEdit.getMaxMarks());
        Weightage updatedWeightage = editGradedComponentDescriptor.getWeightage()
                .orElse(gradedComponentToEdit.getWeightage());

        return new GradedComponent(updatedName, updatedMaxMarks, updatedWeightage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditGradedComponentCommand)) {
            return false;
        }

        EditGradedComponentCommand otherEditCommand = (EditGradedComponentCommand) other;
        return index.equals(otherEditCommand.index)
                && editGradedComponentDescriptor.equals(otherEditCommand.editGradedComponentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editGradedComponentDescriptor", editGradedComponentDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the gradedComponent with. Each non-empty field value will replace the
     * corresponding field value of the gradedComponent.
     */
    public static class EditGradedComponentDescriptor {
        private GcName name;
        private MaxMarks maxMarks;
        private Weightage weightage;

        public EditGradedComponentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditGradedComponentDescriptor(EditGradedComponentDescriptor toCopy) {
            setGcName(toCopy.name);
            setMaxMarks(toCopy.maxMarks);
            setWeightage(toCopy.weightage);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, maxMarks, weightage);
        }

        public void setGcName(GcName name) {
            this.name = name;
        }

        public Optional<GcName> getGcName() {
            return Optional.ofNullable(name);
        }

        public void setMaxMarks(MaxMarks maxMarks) {
            this.maxMarks = maxMarks;
        }

        public Optional<MaxMarks> getMaxMarks() {
            return Optional.ofNullable(maxMarks);
        }

        public void setWeightage(Weightage weightage) {
            this.weightage = weightage;
        }

        public Optional<Weightage> getWeightage() {
            return Optional.ofNullable(weightage);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGradedComponentDescriptor)) {
                return false;
            }

            EditGradedComponentDescriptor otherEditGradedComponentDescriptor = (EditGradedComponentDescriptor) other;
            return Objects.equals(name, otherEditGradedComponentDescriptor.name)
                    && Objects.equals(weightage, otherEditGradedComponentDescriptor.weightage)
                    && Objects.equals(maxMarks, otherEditGradedComponentDescriptor.maxMarks);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("max marks", maxMarks)
                    .add("weightage", weightage)
                    .toString();
        }
    }
}
