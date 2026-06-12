package edu.gwu.csci6212;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WildcardPatternMatchingTest {

    // -----------------------------------------------------------------------
    // Null-argument validation
    // -----------------------------------------------------------------------

    @Test
    void rejectsNullText() {
        assertThrows(IllegalArgumentException.class,
                () -> WildcardPatternMatching.matches(null, "abc"));
    }

    @Test
    void rejectsNullPattern() {
        assertThrows(IllegalArgumentException.class,
                () -> WildcardPatternMatching.matches("abc", null));
    }

    // -----------------------------------------------------------------------
    // Empty string / empty pattern edge cases
    // -----------------------------------------------------------------------

    @Test
    void emptyPatternMatchesEmptyString() {
        assertTrue(WildcardPatternMatching.matches("", ""));
    }

    @Test
    void emptyPatternDoesNotMatchNonEmptyString() {
        assertFalse(WildcardPatternMatching.matches("a", ""));
    }

    @Test
    void allStarsPatternMatchesEmptyString() {
        assertTrue(WildcardPatternMatching.matches("", "***"));
    }

    @Test
    void allStarsPatternMatchesAnyString() {
        assertTrue(WildcardPatternMatching.matches("anything goes here", "***"));
    }

    // -----------------------------------------------------------------------
    // Assignment examples
    // -----------------------------------------------------------------------

    @Test
    void assignmentExample1_xStarsY_matches() {
        // ("xyxzzxy", "x***y") → true
        assertTrue(WildcardPatternMatching.matches("xyxzzxy", "x***y"));
    }

    @Test
    void assignmentExample2_xStarsX_doesNotMatch() {
        // ("xyxzzxy", "x***x") → false
        assertFalse(WildcardPatternMatching.matches("xyxzzxy", "x***x"));
    }

    @Test
    void assignmentExample3_xStarsXQuestionMark_matches() {
        // ("xyxzzxy", "x***x?") → true
        assertTrue(WildcardPatternMatching.matches("xyxzzxy", "x***x?"));
    }

    // -----------------------------------------------------------------------
    // '?' wildcard behaviour
    // -----------------------------------------------------------------------

    @Test
    void questionMarkMatchesExactlyOneCharacter() {
        assertTrue(WildcardPatternMatching.matches("a", "?"));
    }

    @Test
    void questionMarkDoesNotMatchEmpty() {
        assertFalse(WildcardPatternMatching.matches("", "?"));
    }

    @Test
    void questionMarkDoesNotMatchTwoCharacters() {
        assertFalse(WildcardPatternMatching.matches("ab", "?"));
    }

    @Test
    void questionMarkInMiddleMatchesAnyChar() {
        assertTrue(WildcardPatternMatching.matches("abc", "a?c"));
    }

    // -----------------------------------------------------------------------
    // Exact-match (no wildcards)
    // -----------------------------------------------------------------------

    @Test
    void exactMatchWithNoWildcards() {
        assertTrue(WildcardPatternMatching.matches("hello", "hello"));
    }

    @Test
    void noMatchWithNoWildcards() {
        assertFalse(WildcardPatternMatching.matches("hello", "world"));
    }

    @Test
    void patternLongerThanTextNoWildcards() {
        assertFalse(WildcardPatternMatching.matches("hi", "hix"));
    }

    @Test
    void textLongerThanPatternNoWildcards() {
        assertFalse(WildcardPatternMatching.matches("hix", "hi"));
    }
}
