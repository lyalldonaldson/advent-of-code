package uk.co.lyalldonaldson.aoc.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class File {

    public static final Logger LOGGER = LogManager.getLogger(File.class);

    private File() {
        // Do nothing
    }

    /**
     * Finds the file on the classpath and returns the contents as a List of String objects.
     *
     * @param fileName the name of the file to load
     * @return the file contents
     */
    public static List<String> getFileContents(String fileName) throws URISyntaxException, IOException {

        LOGGER.debug("Finding File: {}", fileName);

        // The files are not in the packages so delegate to the parent classloader
        URL inputFileUrl = File.class.getClassLoader().getResource(fileName);

        if (inputFileUrl == null) {
            throw new IllegalStateException("Unable to find file " + fileName);
        }

        LOGGER.debug("File Found - Absolute Path: {}", inputFileUrl.getPath());

        List<String> fileLines = Files.readAllLines(Path.of(inputFileUrl.toURI()));

        LOGGER.debug("File has {} lines", fileLines.size());

        return fileLines;
    }

}
