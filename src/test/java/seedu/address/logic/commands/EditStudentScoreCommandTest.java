package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.studentscore.StudentScore;

import static org.junit.jupiter.api.Assertions.*;

class EditStudentScoreCommandTest {

    @Test
    public void execute_notParsableIndex_failure() {
        StudentScore studentScore;
    }

    @Test
    public void execute_zeroOrLessIndex_failure() {
    }

    @Test
    public void execute_validIndex_success() {
    }

    @Test
    public void execute_invalidPrefixToEdit_failure() {
    }

    @Test
    public void execute_indexMoreThanListSize_failure() {
    }

    @Test
    public void execute_validEditScore_success() {
    }

    @Test
    public void execute_scoreAboveMaxMark_failure() { //?????
    }

    @Test
    public void execute_negativeScore_failure() {
    }

    @Test
    public void execute_editScoreWithNoValue_failure() {
    }

    @Test
    public void execute_scoreNotParsable_failure() {
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
    public void execute_invalidTagName_failure() { //invalid regex
    }

    @Test
    public void execute_editTagWithNoValue_failure() {
    }

    @Test
    public void execute_validMultipleTags_success() {
    }

    @Test
    public void execute_duplicateTags_failure() { //not handled yet (error message)
    }

    @Test
    public void execute_multipleTagsWithOneEmptyValue_failure() {
    }

    // More test on edit component dll

    @Test
    public void execute_validMultipleEditComponents_success() {
    }

    @Test
    public void execute_multipleEditComponentsWithOneInvalidInput_failure() { //??????
    }

    @Test
    public void execute() {
    }

    @Test
    public void execute_() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    void testToString() {
    }
}