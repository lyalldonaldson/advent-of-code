package uk.co.lyalldonaldson.aoc.day2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TobogganPasswordRuleTest {

    private TobogganPasswordRule passwordRule;

    @AfterEach
    void tearDown() {
        passwordRule = null;
    }

    @Test
    @DisplayName("False returned when NULL passed in for the password")
    void nullPasswordReturnsFalse() {
        passwordRule = new TobogganPasswordRule('a', 1, 3);

        assertFalse(passwordRule.validPassword(null));
    }

    @Test
    @DisplayName("False returned when blank is passed in for the password")
    void blankPasswordReturnsFalse() {
        passwordRule = new TobogganPasswordRule('a', 1, 3);

        assertFalse(passwordRule.validPassword("   "));
    }

    @Test
    @DisplayName("False returned when both positions have the character")
    void bothPositionsHaveCharacterReturnsFalse() {
        passwordRule = new TobogganPasswordRule('c', 2, 9);

        assertFalse(passwordRule.validPassword("ccccccccc"));
    }

    @Test
    @DisplayName("False returned when no position has the character")
    void noCharacterAtPositionPasswordReturnsFalse() {
        passwordRule = new TobogganPasswordRule('b', 1, 3);

        assertFalse(passwordRule.validPassword("cdefg"));
    }

    @Test
    @DisplayName("True returned for valid password")
    void validPasswordReturnsTrue() {
        passwordRule = new TobogganPasswordRule('a', 1, 3);

        assertTrue(passwordRule.validPassword("abcde"));
    }

}