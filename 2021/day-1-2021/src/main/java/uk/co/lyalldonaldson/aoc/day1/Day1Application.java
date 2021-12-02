package uk.co.lyalldonaldson.aoc.day1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.lyalldonaldson.aoc.utility.File;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

public class Day1Application {

    private static final Logger LOGGER = LogManager.getLogger(Day1Application.class);

    private static final String FILE_NAME = "day1-input.txt";

    public static void main(String[] args) throws URISyntaxException, IOException {

        List<String> fileContents = File.getFileContents(FILE_NAME);

        List<Integer> depthReadings = fileContents.stream().map(Integer::valueOf).collect(Collectors.toList());

        Day1Application day1Application = new Day1Application();

        int part1Answer = day1Application.howQuicklyDepthIncreasing(depthReadings);

        LOGGER.info("Answer for Part 1: " + part1Answer);

        int part2Answer = day1Application.howQuicklyDepthIncreasingSlidingWindow(depthReadings);

        LOGGER.info("Answer for Part 2: " + part2Answer);

    }

    public int howQuicklyDepthIncreasing(List<Integer> depthReadings) {
        if (depthReadings == null || depthReadings.size() <= 1) {
            throw new IllegalStateException("Can't calculate how quickly the depth is increasing with only one reading!");
        }

        int depthIncreases = 0;

        for (int i = 1; i < depthReadings.size(); i++) {
            if (depthReadings.get(i) > depthReadings.get(i - 1)) {
                depthIncreases++;
            }
        }

        return depthIncreases;
    }

    public int howQuicklyDepthIncreasingSlidingWindow(List<Integer> depthReadings) {
        if (depthReadings == null || depthReadings.size() <= 1) {
            throw new IllegalStateException("Can't calculate how quickly the depth is increasing with only one reading!");
        }

        int depthIncreases = 0;

        for (int i = 0; i < depthReadings.size() - 3; i++) {

            int previousWindowHeight = depthReadings.get(i) + depthReadings.get(i + 1) + depthReadings.get(i + 2);
            int windowHeight = depthReadings.get(i + 1) + depthReadings.get(i + 2) + depthReadings.get(i + 3);

            if (windowHeight > previousWindowHeight) {
                depthIncreases++;
            }

        }

        return depthIncreases;
    }

}
