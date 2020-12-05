package uk.co.lyalldonaldson.aoc.day1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1Application {

    private static final Logger LOGGER = LogManager.getLogger(Day1Application.class);

    private static final int SUM_TARGET = 2020;
    private static final String FILE_NAME = "day1-input.txt";

    public static void main(String[] args) throws IOException, URISyntaxException {

        Day1Application day1Application = new Day1Application();

        List<String> inputLines = day1Application.getFileContents(FILE_NAME);

        int answer = day1Application.calculateAnswer(SUM_TARGET, inputLines);

        LOGGER.info("Answer: {}", answer);
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
     * Finds the values from the list that add up to the target and returns the product of those values.
     *
     * @param target the number that 2 of the lines must add up to
     * @param input  the list of numbers to check
     * @return the product of the 2 numbers that add up to the target
     */
    private int calculateAnswer(int target, List<String> input) {

        for (int firstLoopCounter = 0; firstLoopCounter < input.size(); firstLoopCounter++) {

            int firstValue = Integer.parseInt(input.get(firstLoopCounter));

            for (int secondLoopCounter = 0; secondLoopCounter < input.size(); secondLoopCounter++) {

                // We're looking for the two different values from the list that add up to the target
                if (firstLoopCounter == secondLoopCounter) {
                    continue;
                }

                int secondValue = Integer.parseInt(input.get(secondLoopCounter));

                if (firstValue + secondValue == target) {
                    LOGGER.debug("Found a match: {} + {} = {}", firstValue, secondValue, target);

                    return firstValue * secondValue;
                }
            }
        }

        throw new IllegalStateException("Unable to find an answer :(");
    }

}