package uk.co.lyalldonaldson.aoc.day2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SledRentalPasswordRuleTest {

    private SledRentalPasswordRule passwordRule;

    @AfterEach
    void tearDown() {
        passwordRule = null;
    }

    @Test
    @DisplayName("False returned when NULL passed in for the password")
    void nullPasswordReturnsFalse() {
        passwordRule = new SledRentalPasswordRule('a', 1, 3);

        assertFalse(passwordRule.validPassword(null));
    }

    @Test
    @DisplayName("False returned when blank is passed in for the password")
    void blankPasswordReturnsFalse() {
        passwordRule = new SledRentalPasswordRule('a', 1, 3);

        assertFalse(passwordRule.validPassword("   "));
    }

    @Test
    @DisplayName("False returned for too many characters")
    void tooManyCharactersInPasswordReturnsFalse() {
        passwordRule = new SledRentalPasswordRule('h', 1, 3);

        assertFalse(passwordRule.validPassword("hhhhh"));
    }

    @Test
    @DisplayName("False returned for too few characters")
    void tooFewCharactersInPasswordReturnsFalse() {
        passwordRule = new SledRentalPasswordRule('h', 2, 3);

        assertFalse(passwordRule.validPassword("h"));
    }

    @Test
    @DisplayName("False returned for no characters")
    void noRequiredCharactersInPasswordReturnsFalse() {
        passwordRule = new SledRentalPasswordRule('q', 1, 3);

        assertFalse(passwordRule.validPassword("hello world"));
    }

    @Test
    @DisplayName("True returned for valid password")
    void validPasswordReturnsTrue() {
        passwordRule = new SledRentalPasswordRule('l', 1, 3);

        assertTrue(passwordRule.validPassword("hello world"));
    }

}