package seedu.modulight.model.studentscore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_COMMENT_AMY;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_COMMENT_JAMES;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_GCNAME_AMY;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_GCNAME_JAMES;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_SID_AMY;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_SID_JAMES;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_TAG_AMY;
import static seedu.modulight.logic.commands.CommandStudentScoreTestUtil.VALID_TAG_JAMES;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_TG_AMY;
import static seedu.modulight.logic.commands.CommandTestUtil.VALID_TG_JAMES;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modulight.model.gradedcomponent.GcMatchPredicate;
import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.MaxMarks;
import seedu.modulight.model.gradedcomponent.Weightage;
import seedu.modulight.model.student.Student;
import seedu.modulight.model.student.StudentMatchPredicate;
import seedu.modulight.testutil.StudentBuilder;
import seedu.modulight.testutil.StudentScoreBuilder;


class ScoreMatchPredicateTest {
    private final List<String> sidList = List.of(VALID_SID_AMY);
    private final List<String> nameList = List.of(VALID_NAME_AMY);
    private final List<String> tutorialGroupList = List.of(VALID_TG_AMY);
    private final List<String> tagList = List.of(VALID_TAG_AMY);
    private final List<String> commentList = List.of(VALID_COMMENT_AMY);
    private final List<String> gcNameList = List.of(VALID_GCNAME_AMY);
    private final List<String> emailList = List.of("amyWho@gmail.com");

    private final List<String> anotherSidList = List.of(VALID_SID_JAMES);
    private final List<String> anotherNameList = List.of("James");
    private final List<String> anotherTutorialGroupList = List.of("G31");
    private final List<String> anotherTagList = List.of(VALID_TAG_JAMES);
    private final List<String> anotherCommentList = List.of(VALID_COMMENT_JAMES);
    private final List<String> anotherGcNameList = List.of(VALID_GCNAME_JAMES);
    private final List<String> anotherEmailList = List.of("jamesCook@gmail.com");

    private final List<String> emptyList = List.of();

    @Test
    public void test_containsGcNames_returnsTrue() {
        // empty keyword
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withGcName(VALID_GCNAME_AMY).build()));

        // Single keyword
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, emptyList, gcNameList);
        assertTrue(predicate.test(new StudentScoreBuilder().withGcName(VALID_GCNAME_AMY).build()));

        predicate = new ScoreMatchPredicate(gcNameList);
        assertTrue(predicate.test(new StudentScoreBuilder().withGcName(VALID_GCNAME_AMY).build()));

        // Exact Multiple keywords
        List<String> multipleGcName = List.of(VALID_GCNAME_JAMES, VALID_GCNAME_AMY);
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, emptyList, multipleGcName);
        assertTrue(predicate.test(new StudentScoreBuilder()
                .withGcName(VALID_GCNAME_AMY).withGcName(VALID_GCNAME_JAMES).build()));

        // multiple keyword with a non-matching keyword
        multipleGcName = List.of(VALID_GCNAME_JAMES, VALID_GCNAME_AMY);
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, emptyList, multipleGcName);
        assertTrue(predicate.test(new StudentScoreBuilder()
                .withGcName(VALID_GCNAME_AMY).build()));

    }

    @Test
    public void test_noMatchForGcNames_returnsFalse() {
        // Single non-matching keyword
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, emptyList, gcNameList);
        assertFalse(predicate.test(new StudentScoreBuilder().withGcName(VALID_GCNAME_JAMES).build()));

    }

    @Test
    public void test_containsSidKeywords_returnsTrue() {
        // Single keyword
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(sidList,
                emptyList, emptyList, emptyList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withStudentId(VALID_SID_AMY).build()));

        // Multiple keywords
        List<String> newSidList = List.of(VALID_SID_JAMES, VALID_SID_AMY);
        predicate = new ScoreMatchPredicate(newSidList, emptyList,
                emptyList, emptyList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withStudentId(VALID_SID_AMY).build()));

        // Zero keyword
        predicate = new ScoreMatchPredicate(emptyList, emptyList,
                emptyList, emptyList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withStudentId(VALID_SID_AMY).build()));

        // Using incomplete student ID
        newSidList = List.of(VALID_SID_AMY.substring(0, 2));
        predicate = new ScoreMatchPredicate(newSidList,
                emptyList, emptyList, emptyList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withStudentId(VALID_SID_AMY).build()));

        newSidList = List.of(VALID_SID_AMY.substring(2, 5));
        predicate = new ScoreMatchPredicate(newSidList,
                emptyList, emptyList, emptyList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withStudentId(VALID_SID_AMY).build()));

        // Lowercase studentId
        newSidList = List.of(VALID_SID_AMY.toLowerCase());
        predicate = new ScoreMatchPredicate(newSidList,
                emptyList, emptyList, emptyList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withStudentId(VALID_SID_AMY).build()));


    }

    @Test
    public void test_noMatchForSidKeywords_returnsFalse() {
        // No match keyword
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(sidList,
                emptyList, emptyList, emptyList, emptyList, emptyList);
        assertFalse(predicate.test(new StudentScoreBuilder().withStudentId(VALID_SID_JAMES).build()));

        // Using Non-matching incomplete student ID
        List<String> newSidList = List.of(VALID_SID_JAMES.substring(0, 5));
        predicate = new ScoreMatchPredicate(newSidList,
                emptyList, emptyList, emptyList, emptyList, emptyList);
        assertFalse(predicate.test(new StudentScoreBuilder().withStudentId(VALID_SID_AMY).build()));

    }

    @Test
    public void test_containsTagKeywords_returnsTrue() {
        // Single keyword
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, tagList, emptyList, emptyList);
        StudentScore test = new StudentScoreBuilder().withTags(VALID_TAG_AMY).build();
        assertTrue(predicate.test(new StudentScoreBuilder().withTags(VALID_TAG_AMY).build()));

        // Multiple keywords
        List<String> newTagList = List.of(VALID_TAG_JAMES, VALID_TAG_AMY);
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, newTagList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withTags(VALID_TAG_AMY, VALID_TAG_JAMES).build()));

        // Multiple identical keywords
        newTagList = List.of(VALID_TAG_AMY, VALID_TAG_AMY);
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, newTagList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withTags(VALID_TAG_AMY).build()));

        // matching and non-matching keywords
        newTagList = List.of(VALID_TAG_JAMES, VALID_TAG_AMY);
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, newTagList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withTags(VALID_TAG_AMY).build()));

        // zero keywords
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withTags(VALID_TAG_AMY).build()));

    }

    @Test
    public void test_noMatchForTagKeywords_returnsFalse() {

        // No matching keyword
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, tagList, emptyList, emptyList);
        assertFalse(predicate.test(new StudentScoreBuilder().withTags(VALID_TAG_JAMES).build()));

        // non-matching case keywords
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, tagList, emptyList, emptyList);
        assertFalse(predicate.test(new StudentScoreBuilder().withTags(VALID_TAG_AMY.toUpperCase()).build()));

    }

    @Test
    public void test_containsTutorialGroupKeywords_returnsTrue() {
        // Single keyword
        StudentMatchPredicate studentMatchPredicate = new StudentMatchPredicate(emptyList,
                emptyList, emptyList, List.of(VALID_TG_AMY), emptyList);
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(studentMatchPredicate);

        Student student = new StudentBuilder().withId(VALID_SID_AMY).withTg(VALID_TG_AMY).build();
        StudentScore studentScore = new StudentScoreBuilder()
                .withStudentId(VALID_SID_AMY).withStudent(student).build();
        assertTrue(predicate.test(studentScore));

        // zero keyword
        studentMatchPredicate = new StudentMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, emptyList);
        predicate = new ScoreMatchPredicate(studentMatchPredicate);

        student = new StudentBuilder().withId(VALID_SID_AMY).withTg(VALID_TG_AMY).build();
        studentScore = new StudentScoreBuilder()
                .withStudentId(VALID_SID_AMY).withStudent(student).build();
        assertTrue(predicate.test(studentScore));

        // Multiple keywords with a non-matching keyword
        studentMatchPredicate = new StudentMatchPredicate(emptyList,
                emptyList, emptyList, List.of(VALID_TG_AMY, VALID_SID_JAMES), emptyList);
        predicate = new ScoreMatchPredicate(studentMatchPredicate);

        student = new StudentBuilder().withId(VALID_SID_AMY).withTg(VALID_TG_AMY).build();
        studentScore = new StudentScoreBuilder()
                .withStudentId(VALID_SID_AMY).withStudent(student).build();
        assertTrue(predicate.test(studentScore));

    }

    @Test
    public void test_noMatchingTutorialGroupKeywords_returnsFalse() {
        // non-matching keywords
        StudentMatchPredicate studentMatchPredicate = new StudentMatchPredicate(emptyList,
                emptyList, emptyList, List.of(VALID_TG_AMY), emptyList);
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(studentMatchPredicate);

        Student student = new StudentBuilder().withId(VALID_SID_JAMES).withTg(VALID_TG_JAMES).build();
        StudentScore studentScore = new StudentScoreBuilder()
                .withStudentId(VALID_SID_AMY).withStudent(student).build();
        assertFalse(predicate.test(studentScore));

        // incomplete keyword
        studentMatchPredicate = new StudentMatchPredicate(emptyList,
                emptyList, emptyList, List.of(VALID_TG_AMY.substring(0, 1)), emptyList);
        predicate = new ScoreMatchPredicate(studentMatchPredicate);

        student = new StudentBuilder().withId(VALID_SID_AMY).withTg(VALID_TG_AMY).build();
        studentScore = new StudentScoreBuilder()
                .withStudentId(VALID_SID_AMY).withStudent(student).build();
        assertFalse(predicate.test(studentScore));
    }

    @Test
    public void test_containsCommentKeywords_returnTrue() {
        // single keyword
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, commentList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withComment(VALID_COMMENT_AMY).build()));

        // zero keyword
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, emptyList, emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withComment(VALID_COMMENT_AMY).build()));

        // multiple keywords
        predicate = new ScoreMatchPredicate(emptyList,
                emptyList, emptyList, emptyList, List.of(VALID_COMMENT_JAMES, VALID_COMMENT_AMY), emptyList);
        assertTrue(predicate.test(new StudentScoreBuilder().withComment(VALID_COMMENT_AMY).build()));

    }

    @Test
    public void test_multipleTypeKeywords_returnTrue() {
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(sidList, emptyList,
                emptyList, tagList, List.of(VALID_COMMENT_JAMES, VALID_COMMENT_AMY), gcNameList);
        assertTrue(predicate.test(new StudentScoreBuilder()
                .withComment(VALID_COMMENT_AMY).withStudentId(VALID_SID_AMY)
                .withTags(VALID_TAG_AMY).withGcName(VALID_GCNAME_AMY).build()));

    }

    @Test
    public void test_singleMatchMultipleTypeKeywords_returnFalse() {
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(sidList, emptyList,
                emptyList, tagList, List.of(VALID_COMMENT_JAMES, VALID_COMMENT_AMY), gcNameList);
        assertFalse(predicate.test(new StudentScoreBuilder()
                .withComment(VALID_COMMENT_AMY).withStudentId(VALID_SID_JAMES)
                .withTags(VALID_TAG_JAMES).withGcName(VALID_GCNAME_JAMES).build()));
    }

    @Test
    public void test_gcMatchPredicate_returnTrue() {
        GcMatchPredicate gcMatchPredicate = new GcMatchPredicate(gcNameList);
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(gcMatchPredicate);

        GradedComponent gradedComponent =
                new GradedComponent(new GcName(VALID_GCNAME_AMY), new MaxMarks(100), new Weightage(30));
        StudentScore studentScore = new StudentScoreBuilder()
                .withStudentId(VALID_SID_AMY).withGc(gradedComponent).build();
        assertTrue(predicate.test(studentScore));
    }

    @Test
    public void testEquals() {
        ScoreMatchPredicate scoreMatchPredicate = new ScoreMatchPredicate(sidList, nameList,
                tutorialGroupList, tagList, commentList, gcNameList);
        // Same values -> return true
        ScoreMatchPredicate scoreMatchPredicateCopy = new ScoreMatchPredicate(sidList, nameList,
                tutorialGroupList, tagList, commentList, gcNameList);
        assertTrue(scoreMatchPredicate.equals(scoreMatchPredicateCopy));

        // Same object -> return true
        assertTrue(scoreMatchPredicate.equals(scoreMatchPredicate));

        // null -> return false
        assertFalse(scoreMatchPredicate.equals(null));

        // Different Type -> return false
        assertFalse(scoreMatchPredicate.equals("Score!"));

        // Different scoreMatchPredicate -> return false
        ScoreMatchPredicate anotherScoreMatchPredicate = new ScoreMatchPredicate(anotherSidList, anotherNameList,
                anotherTutorialGroupList, anotherTagList, anotherCommentList, anotherGcNameList);
        assertFalse(scoreMatchPredicate.equals(anotherScoreMatchPredicate));

        // Different Student Id, same all other attributes -> return False
        anotherScoreMatchPredicate = new ScoreMatchPredicate(anotherSidList, nameList,
                tutorialGroupList, tagList, commentList, gcNameList);
        assertFalse(scoreMatchPredicate.equals(anotherScoreMatchPredicate));

        // Different Name, same all other attributes -> return False
        anotherScoreMatchPredicate = new ScoreMatchPredicate(sidList, anotherNameList,
                tutorialGroupList, tagList, commentList, gcNameList);
        assertFalse(scoreMatchPredicate.equals(anotherScoreMatchPredicate));

        // Different gcName, same all other attributes -> return false
        anotherScoreMatchPredicate = new ScoreMatchPredicate(sidList, nameList,
                tutorialGroupList, tagList, commentList, anotherGcNameList);
        assertFalse(scoreMatchPredicate.equals(anotherScoreMatchPredicate));


        // Different Comment, same all other attributes -> return false
        anotherScoreMatchPredicate = new ScoreMatchPredicate(sidList, nameList,
                tutorialGroupList, tagList, anotherCommentList, gcNameList);
        assertFalse(scoreMatchPredicate.equals(anotherScoreMatchPredicate));

        // Different Tags, same all other attributes -> return false
        anotherScoreMatchPredicate = new ScoreMatchPredicate(sidList, nameList,
                tutorialGroupList, anotherTagList, commentList, gcNameList);
        assertFalse(scoreMatchPredicate.equals(anotherScoreMatchPredicate));

        // Different Tutorial Groups, same all other attributes -> return false
        anotherScoreMatchPredicate = new ScoreMatchPredicate(sidList, nameList,
                anotherTutorialGroupList, tagList, commentList, gcNameList);
        assertFalse(scoreMatchPredicate.equals(anotherScoreMatchPredicate));

        // Different studentMatchPredicate, same all other attributes -> return false
        StudentMatchPredicate studentMatchPredicate = new StudentMatchPredicate(sidList,
                emailList, nameList, tagList, tutorialGroupList);
        StudentMatchPredicate anotherStudentMatchPredicate = new StudentMatchPredicate(anotherSidList,
                anotherEmailList, anotherNameList, anotherTagList, anotherTutorialGroupList);

        scoreMatchPredicate = new ScoreMatchPredicate(studentMatchPredicate);
        anotherScoreMatchPredicate = new ScoreMatchPredicate(anotherStudentMatchPredicate);
        assertFalse(scoreMatchPredicate.equals(anotherScoreMatchPredicate));


        // Different gcMatchPredicate -> return false
        GcMatchPredicate gcMatchPredicate = new GcMatchPredicate(gcNameList);
        GcMatchPredicate anotherGcMatchPredicate = new GcMatchPredicate(anotherGcNameList);

        scoreMatchPredicate = new ScoreMatchPredicate(gcMatchPredicate);
        anotherScoreMatchPredicate = new ScoreMatchPredicate(anotherGcMatchPredicate);
        assertFalse(scoreMatchPredicate.equals(anotherScoreMatchPredicate));

    }

    @Test
    public void testToString() {
        ScoreMatchPredicate predicate = new ScoreMatchPredicate(sidList, emptyList,
                emptyList, tagList, List.of(VALID_COMMENT_JAMES, VALID_COMMENT_AMY), gcNameList);

        assertEquals(predicate.toString(), "Find student score by given criteria");

    }
}
