package uk.co.lyalldonaldson.aoc.day1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Day1ApplicationTest {

    Day1Application day1Application;

    @BeforeEach
    void setUp() {
        day1Application = new Day1Application();
    }

    @AfterEach
    void tearDown() {
        day1Application = null;
    }

    @Test
    void exceptionThrownForOnlyOneReading() {
        Exception exception = assertThrows(IllegalStateException.class, () ->
                day1Application.howQuicklyDepthIncreasing(List.of(234)));

        assertEquals("Can't calculate how quickly the depth is increasing with only one reading!", exception.getMessage());
    }

    @Test
    void noChangeInDepthReadingsShouldEqualZero() {
        int answer = day1Application.howQuicklyDepthIncreasing(List.of(109, 109, 109, 109));

        assertEquals(0, answer);
    }

    @Test
    void answerReturnedForSimpleCount() {
        int answer = day1Application.howQuicklyDepthIncreasing(getValues());

        assertEquals(7, answer);
    }

    @Test
    void answerReturnedForSlidingWindow() {
        int answer = day1Application.howQuicklyDepthIncreasingSlidingWindow(getValues());

        assertEquals(5, answer);
    }

    private List<Integer> getValues() {
        return List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);
    }
}