package uk.co.lyalldonaldson.aoc.day2;

public class PasswordRule {

    private char character;
    private int minimumCount;
    private int maximumCount;

    public PasswordRule setCharacter(char character) {
        this.character = character;

        return this;
    }

    public PasswordRule setMinimumCount(int minimumCount) {
        this.minimumCount = minimumCount;

        return this;
    }

    public PasswordRule setMaximumCount(int maximumCount) {
        this.maximumCount = maximumCount;

        return this;
    }

    /**
     * Checks if the supplied password is valid based on the rules. Blank passwords are assumed to be invalid by default.
     *
     * @param password the password to check if it's valid
     * @return <code>true</code> if the password passes the rules, <code>false</code> if not
     */
    public boolean validPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        long numberOfCharacters = password.chars().filter(foundCharacter -> foundCharacter == character).count();

        return numberOfCharacters >= minimumCount && numberOfCharacters <= maximumCount;
    }
}
