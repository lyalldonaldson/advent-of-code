package uk.co.lyalldonaldson.aoc.day2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.lyalldonaldson.aoc.utility.File;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

public class Day2Application {

    private static final Logger LOGGER = LogManager.getLogger(Day2Application.class);

    private static final String FILE_NAME = "day2-input.txt";

    public static void main(String[] args) throws URISyntaxException, IOException {

        List<String> submarineCommands = File.getFileContents(FILE_NAME);

        Day2Application day2Application = new Day2Application();

        int part1Answer = day2Application.calculatePosition(submarineCommands);

        LOGGER.info("Answer for Part 1: " + part1Answer);

        int part2Answer = day2Application.calculatePositionWithAim(submarineCommands);

        LOGGER.info("Answer for Part 2: " + part2Answer);

    }


    public int calculatePosition(List<String> submarineCommands) {

        int horizontalPosition = 0;
        int depth = 0;

        for (String submarineCommand : submarineCommands) {
            int spacePosition = submarineCommand.indexOf(" ");

            String command = submarineCommand.substring(0, spacePosition).trim().toUpperCase(Locale.ROOT);
            int movementUnits = Integer.parseInt(submarineCommand.substring(spacePosition).trim());

            switch (SubmarineCommands.valueOf(command)) {
                case UP -> depth -= movementUnits;
                case DOWN -> depth += movementUnits;
                case FORWARD -> horizontalPosition += movementUnits;
            }
        }

        return horizontalPosition * depth;
    }

    public int calculatePositionWithAim(List<String> submarineCommands) {

        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        for (String submarineCommand : submarineCommands) {
            int spacePosition = submarineCommand.indexOf(" ");

            String command = submarineCommand.substring(0, spacePosition).trim().toUpperCase(Locale.ROOT);
            int movementUnits = Integer.parseInt(submarineCommand.substring(spacePosition).trim());

            switch (SubmarineCommands.valueOf(command)) {
                case UP -> aim -= movementUnits;
                case DOWN -> aim += movementUnits;
                case FORWARD -> {
                    horizontalPosition += movementUnits;
                    depth += aim * movementUnits;
                }
            }
        }

        return horizontalPosition * depth;
    }
}
