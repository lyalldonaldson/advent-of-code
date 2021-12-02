package uk.co.lyalldonaldson.aoc.day2;

/**
 * Password rules for the Sled Rental company.
 */
public class SledRentalPasswordRule implements PasswordRule {

    private char character;
    private int minimumCount;
    private int maximumCount;

    public SledRentalPasswordRule(char character, int minimumCount, int maximumCount) {
        this.character = character;
        this.minimumCount = minimumCount;
        this.maximumCount = maximumCount;
    }

    @Override
    public boolean validPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        long numberOfCharacters = password.chars().filter(foundCharacter -> foundCharacter == character).count();

        return numberOfCharacters >= minimumCount && numberOfCharacters <= maximumCount;
    }
}
