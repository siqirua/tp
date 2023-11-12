package seedu.address.model.studentscore;

import org.junit.jupiter.api.Test;
import seedu.address.model.gradedcomponent.GcMatchPredicate;
import seedu.address.model.student.StudentMatchPredicate;
import seedu.address.testutil.StudentScoreBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandStudentScoreTestUtil.*;

class ScoreMatchPredicateTest {
    private final List<String> sidList = List.of(VALID_SID_AMY);
    private final List<String> nameList = List.of("Amy");
    private final List<String> tutorialGroupList = List.of("T23");
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
        // Single keyword

        // Multiple keywords

        //
    }

    @Test
    public void test_noMatchForGcNames_returnsFalse() {
        // Single keyword

        // Multiple keywords

        //
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

        // zero keyword

        // Multiple keywords

        // Multiple identical keywords

        // matching and non-matching keywords
    }

    @Test
    public void test_noMatchingTutorialGroupKeywords_returnsFalse() {
        // non-matching keywords

        // incomplete keyword
    }

    @Test
    public void test_containsCommentKeywords_returnTrue() {
        // single keyword

        // zero keyword

        // multiple keywords

    }

    @Test
    public void test_multipleTypeKeywords_returnTrue() {
    }

    @Test
    public void test_noMatchMultipleTypeKeywords_returnTrue() {
    }

    @Test
    public void test_containsStudentMatchPredicate() {
    }

    @Test
    public void test_containsGcMatchPredicate() {
    }

    @Test
    public void test_containsNameKeywords_returnsTrue() {
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

    }
}