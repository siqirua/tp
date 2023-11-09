package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditStudentScoreDescriptorBuilder;

import static seedu.address.logic.commands.CommandStudentScoreTestUtil.*;
import static seedu.address.testutil.TestGcDataUtil.getTestGcBook;
import static seedu.address.testutil.TestStudentScoreDataUtil.getSampleStudentScoreBook;
import static seedu.address.testutil.TestStudentDataUtil.getTestStudentBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCORE;

class EditStudentScoreCommandTest {

    private Model model = new ModelManager(getTestStudentBook("create"),
            getSampleStudentScoreBook("twoScores"),
            getTestGcBook("create"), new UserPrefs());

    @Test
    public void execute_indexMoreThanListSize_failure() {
        boolean useFiltered = true;
        int size = model.getFilteredStudentScoreList().size();
        Index outOfBoundIndex = Index.fromZeroBased(size);
        EditStudentScoreCommand.EditStudentScoreDescriptor descriptor =
                new EditStudentScoreDescriptorBuilder().build();
        EditStudentScoreCommand editStudentScoreCommand = new EditStudentScoreCommand(
                outOfBoundIndex, descriptor, useFiltered);

        assertCommandFailure(editStudentScoreCommand, model,
                EditStudentScoreCommand.MESSAGE_INVALID_SCORE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validEditScore_success() {
        boolean useFiltered = true;
        EditStudentScoreCommand.EditStudentScoreDescriptor descriptor =
                new EditStudentScoreDescriptorBuilder().withScore(VALID_SCORE_JAMES).build();
        EditStudentScoreCommand editStudentScoreCommand = new EditStudentScoreCommand(
                INDEX_FIRST_SCORE, descriptor, useFiltered);

        //Model modelAfter = model.getFilteredStudentScoreList();

        //assertCommandSuccess(editStudentScoreCommand, model, EditStudentScoreCommand.SCORE_VALUE_NOT_VALID);
    }

    @Test
    public void execute_negativeScore_failure() {
        boolean useFiltered = true;
        EditStudentScoreCommand.EditStudentScoreDescriptor descriptor =
                new EditStudentScoreDescriptorBuilder().withScore(-2).build();
        EditStudentScoreCommand editStudentScoreCommand = new EditStudentScoreCommand(
                INDEX_FIRST_SCORE, descriptor, useFiltered);

        assertCommandFailure(editStudentScoreCommand, model, EditStudentScoreCommand.SCORE_VALUE_NOT_VALID);
    }

    @Test
    public void execute_validEditComment_success() {
    }

    @Test
    public void execute_editCommentWithNoValue_success() {
    }

    @Test
    public void execute_validEditTag_success() {
    }


    @Test
    public void execute_validMultipleTags_success() {
    }

    @Test
    public void execute_duplicateTags_success() { //not handled yet (error message)
    }

    @Test
    public void execute_inValidEditTag_failure() {
//        boolean useFiltered = true;
//        EditStudentScoreCommand.EditStudentScoreDescriptor descriptor =
//                new EditStudentScoreDescriptorBuilder().withTags(INVALID_TAG_DESC).build();
//        EditStudentScoreCommand editStudentScoreCommand = new EditStudentScoreCommand(
//                INDEX_FIRST_SCORE, descriptor, useFiltered);
//
//        assertCommandFailure(editStudentScoreCommand, model, Tag.MESSAGE_CONSTRAINTS);
    }

    // More test on edit component dll

    @Test
    public void execute_validMultipleEditComponents_success() {
    }

    @Test
    public void createEditedStudentScore_validInput_success() {

    }

    @Test
    public void createEditedStudentScore_invalidDescriptor_failure() {

    }

    @Test
    public void createEditedStudentScore_invalidStudentScoreToEdit_failure() {

    }


    @Test
    public void testEquals() {
    }

    @Test
    void testToString() {
    }

    @Test
    public void editStudentDescriptor_equals() {

    }

    @Test
    public void editStudentDescriptor_toString() {

    }
}