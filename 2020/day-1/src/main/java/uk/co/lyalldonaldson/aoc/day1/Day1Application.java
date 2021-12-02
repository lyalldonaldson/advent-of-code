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

    private static final int SUM_TARGET = 15;
    private static final String FILE_NAME = "day1-input.txt";

    public static void main(String[] args) throws IOException, URISyntaxException {

        Day1Application day1Application = new Day1Application();

        List<String> inputLines = day1Application.getFileContents(FILE_NAME);

        int answerForTwoValues = day1Application.calculateAnswer(SUM_TARGET, inputLines, NumberSumValues.TWO);

        LOGGER.info("Answer for two values: {}", answerForTwoValues);

        int answerForThreeValues = day1Application.calculateAnswer(SUM_TARGET, inputLines, NumberSumValues.THREE);

        LOGGER.info("Answer for three values: {}", answerForThreeValues);
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
     * @param target          the number that numberSumValues of the lines must add up to
     * @param input           the list of numbers to check
     * @param numberSumValues the number of values from the input to add together to match the target
     * @return the product of the numberSumValues numbers that add up to the target
     */
    public int calculateAnswer(int target, List<String> input, NumberSumValues numberSumValues) {

        for (int firstLoopCounter = 0; firstLoopCounter < input.size(); firstLoopCounter++) {

            int firstValue = Integer.parseInt(input.get(firstLoopCounter));

            for (int secondLoopCounter = 0; secondLoopCounter < input.size(); secondLoopCounter++) {

                // We're looking for different values from the list that add up to the target
                if (firstLoopCounter == secondLoopCounter) {
                    continue;
                }

                int secondValue = Integer.parseInt(input.get(secondLoopCounter));

                if (numberSumValues == NumberSumValues.TWO && firstValue + secondValue == target) {
                    LOGGER.debug("Found a match: {} + {} = {}", firstValue, secondValue, target);

                    return firstValue * secondValue;
                } else if (numberSumValues == NumberSumValues.THREE) {
                    for (int thirdLoopCounter = 0; thirdLoopCounter < input.size(); thirdLoopCounter++) {

                        // We're looking for different values from the list that add up to the target
                        if (firstLoopCounter == thirdLoopCounter || secondLoopCounter == thirdLoopCounter) {
                            continue;
                        }

                        int thirdValue = Integer.parseInt(input.get(thirdLoopCounter));
                        if (firstValue + secondValue + thirdValue == target) {
                            LOGGER.debug("Found a match: {} + {} + {} = {}", firstValue, secondValue, thirdValue, target);

                            return firstValue * secondValue * thirdValue;
                        }
                    }
                }
            }
        }

        throw new IllegalStateException("Unable to find an answer :(");
    }

}