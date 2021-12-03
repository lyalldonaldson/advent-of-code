package uk.co.lyalldonaldson.aoc.day2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Day2ApplicationTest {

    Day2Application day2Application;

    @BeforeEach
    void setUp() {
        day2Application = new Day2Application();
    }

    @AfterEach
    void tearDown() {
        day2Application = null;
    }

    @Test
    void exceptionThrownForUnknownCommand() {
        assertThrows(IllegalArgumentException.class, () -> day2Application.calculatePosition(List.of("blah 10")));
    }

    @Test
    void noChangeInDepthShouldEqualZero() {
        int answer = day2Application.calculatePosition(List.of("forward 10", "down 10", "up 10"));

        assertEquals(0, answer);
    }

    @Test
    void answerReturnedForPosition() {
        int answer = day2Application.calculatePosition(getValues());

        assertEquals(150, answer);
    }

    @Test
    void answerReturnedForPositionWithAim() {
        int answer = day2Application.calculatePositionWithAim(getValues());

        assertEquals(900, answer);
    }

    private List<String> getValues() {
        return List.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
    }
}