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

    public static void main(String[] args) throws IOException, URISyntaxException {

        Day2Application day2Application = new Day2Application();

        List<String> inputLines = day2Application.getFileContents(FILE_NAME);

        LOGGER.info("There are {} valid passwords", day2Application.calculateNumberOfValidPasswords(inputLines));
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
    public int calculateNumberOfValidPasswords(List<String> inputLines) {

        int numValidPasswords = 0;

        for (String inputLine : inputLines) {

            String[] lineParts = inputLine.split(" ");

            PasswordRule passwordRule = new PasswordRule();

            parseMinimumAndMaximumValues(lineParts[0], passwordRule);
            parseCharacter(lineParts[1], passwordRule);

            if (passwordRule.validPassword(lineParts[2])) {
                numValidPasswords++;
            }

        }

        return numValidPasswords;
    }

    private void parseMinimumAndMaximumValues(String minAndMax, PasswordRule passwordRule) {
        String[] minAndMaxParts = minAndMax.split("-");

        passwordRule.setMinimumCount(Integer.parseInt(minAndMaxParts[0]));
        passwordRule.setMaximumCount(Integer.parseInt(minAndMaxParts[1]));
    }

    private void parseCharacter(String character, PasswordRule passwordRule) {
        passwordRule.setCharacter(character.charAt(0));
    }

}