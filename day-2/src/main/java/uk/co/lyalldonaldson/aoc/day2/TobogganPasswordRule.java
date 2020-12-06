package uk.co.lyalldonaldson.aoc.day2;

/**
 * Password rules for the Toboggan Rental Company.
 */
public class TobogganPasswordRule implements PasswordRule {

    private final char character;
    private final int firstPosition;
    private final int secondPosition;

    public TobogganPasswordRule(char character, int firstPosition, int secondPosition) {
        this.character = character;
        // Java is ZERO based indexed, move the position back by 1
        this.firstPosition = firstPosition - 1;
        this.secondPosition = secondPosition - 1;
    }

    @Override
    public boolean validPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        boolean characterAtFirstPosition = password.charAt(firstPosition) == character;
        boolean characterAtSecondPosition = password.charAt(secondPosition) == character;

        return characterAtFirstPosition ^ characterAtSecondPosition;
    }
}
