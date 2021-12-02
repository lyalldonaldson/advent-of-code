package uk.co.lyalldonaldson.aoc.day2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2Application {

    private static final Logger LOGGER = LogManager.getLogger(Day2Application.class);

    private static final String FILE_NAME = "day2-input.txt";
    private static final String SLED_RENTAL = "SLED_RENTAL";
    private static final String TOBOGGAN = "TOBOGGAN";


    public static void main(String[] args) throws IOException, URISyntaxException {

        Day2Application day2Application = new Day2Application();

        List<String> inputLines = day2Application.getFileContents(FILE_NAME);

        LOGGER.info("There are {} valid passwords for {}", day2Application.calculateNumberOfValidPasswords(inputLines, SLED_RENTAL), SLED_RENTAL);

        LOGGER.info("There are {} valid passwords for {}", day2Application.calculateNumberOfValidPasswords(inputLines, TOBOGGAN), TOBOGGAN);
    }

    /**
     * Finds the file on the classpath and returns the contents as a List of String objects.
     *
     * @param fileName the name of the file to load
     * @return the file contents
     */
    private List<String> getFileContents(String fileName) throws URISyntaxException, IOException {

        LOGGER.debug("Finding File: {}", fileName);

        // The files are not in the packages so delegate to the parent classloader
        URL inputFileUrl = this.getClass().getClassLoader().getResource(fileName);

        if (inputFileUrl == null) {
            throw new IllegalStateException("Unable to find file " + fileName);
        }

        LOGGER.debug("File Found - Absolute Path: {}", inputFileUrl.getPath());

        List<String> fileLines = Files.readAllLines(Path.of(inputFileUrl.toURI()));

        LOGGER.debug("File has {} lines", fileLines.size());

        return fileLines;
    }

    /**
     * Determines how many passwords are valid.
     *
     * @param inputLines the passwords and rules to check
     * @return the number of passwords that are valid
     */
    public int calculateNumberOfValidPasswords(List<String> inputLines, String company) {

        LOGGER.debug("Calculating valid passwords using {} rules", company);

        int numValidPasswords = 0;

        for (String inputLine : inputLines) {

            String[] lineParts = inputLine.split(" ");

            PasswordRule passwordRule = buildPasswordRule(lineParts, company);

            if (passwordRule.validPassword(lineParts[2])) {
                numValidPasswords++;
            }

        }

        return numValidPasswords;
    }

    /**
     * Constructs the Password Rule from a line in the file
     *
     * @param lineParts the parts of the line
     * @return the PasswordRule
     */
    private PasswordRule buildPasswordRule(String[] lineParts, String company) {

        char ruleCharacter = parseCharacter(lineParts[1]);
        int firstNumber = parseFirstNumberValue(lineParts[0]);
        int secondNumber = parseSecondNumberValue(lineParts[0]);

        PasswordRule passwordRule;

        switch (company) {
            case "SLED_RENTAL" -> passwordRule = new SledRentalPasswordRule(ruleCharacter, firstNumber, secondNumber);
            case "TOBOGGAN" -> passwordRule = new TobogganPasswordRule(ruleCharacter, firstNumber, secondNumber);
            default -> throw new IllegalArgumentException("No Password rules for " + company);
        }

        return passwordRule;
    }

    /**
     * Extracts the first number value.
     *
     * @param numberRange the string from the password file
     */
    private int parseFirstNumberValue(String numberRange) {
        String[] numberRangeParts = numberRange.split("-");

        return Integer.parseInt(numberRangeParts[0]);
    }

    /**
     * Extracts the second number value.
     *
     * @param numberRange the string from the password file
     */
    private int parseSecondNumberValue(String numberRange) {
        String[] numberRangeParts = numberRange.split("-");

        return Integer.parseInt(numberRangeParts[1]);
    }

    /**
     * Extracts the character at the first position of the string
     *
     * @param character the character string from the file
     */
    private char parseCharacter(String character) {
        return character.charAt(0);
    }

}