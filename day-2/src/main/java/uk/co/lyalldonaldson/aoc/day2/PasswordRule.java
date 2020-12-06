package uk.co.lyalldonaldson.aoc.day2;

public interface PasswordRule {

    /**
     * Checks if the supplied password is valid based on the rules. Blank passwords are assumed to be invalid by default.
     *
     * @param password the password to check if it's valid
     * @return <code>true</code> if the password passes the rules, <code>false</code> if not
     */
    boolean validPassword(String password);
}
