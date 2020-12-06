package uk.co.lyalldonaldson.aoc.day2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordRuleTest {

    private PasswordRule passwordRule;

    @BeforeEach
    void setUp() {
        passwordRule = new PasswordRule();
    }

    @AfterEach
    void tearDown() {
        passwordRule = null;
    }

    @Test
    @DisplayName("False returned when NULL passed in for the password")
    void nullPasswordReturnsFalse() {
        assertFalse(passwordRule.validPassword(null));
    }

    @Test
    @DisplayName("False returned when blank is passed in for the password")
    void blankPasswordReturnsFalse() {
        assertFalse(passwordRule.validPassword("   "));
    }

    @Test
    @DisplayName("False returned for too many characters")
    void tooManyCharactersInPasswordReturnsFalse() {
        passwordRule = passwordRule.setCharacter('h').setMinimumCount(1).setMaximumCount(3);

        assertFalse(passwordRule.validPassword("hhhhh"));
    }

    @Test
    @DisplayName("False returned for too few characters")
    void tooFewCharactersInPasswordReturnsFalse() {
        passwordRule = passwordRule.setCharacter('h').setMinimumCount(2).setMaximumCount(3);

        assertFalse(passwordRule.validPassword("h"));
    }

    @Test
    @DisplayName("False returned for no characters")
    void noRequiredCharactersInPasswordReturnsFalse() {
        passwordRule = passwordRule.setCharacter('q').setMinimumCount(2).setMaximumCount(3);

        assertFalse(passwordRule.validPassword("hello world"));
    }

    @Test
    @DisplayName("True returned for valid password")
    void validPasswordReturnsTrue() {
        passwordRule = passwordRule.setCharacter('l').setMinimumCount(1).setMaximumCount(3);

        assertTrue(passwordRule.validPassword("hello world"));
    }

}