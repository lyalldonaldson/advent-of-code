package uk.co.lyalldonaldson.aoc.day1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.lyalldonaldson.aoc.day1.Day1Application;
import uk.co.lyalldonaldson.aoc.day1.NumberSumValues;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Day1ApplicationTest {

    private Day1Application day1Application;

    @BeforeEach
    void setUp() {
        day1Application = new Day1Application();
    }

    @AfterEach
    void tearDown() {
        day1Application = null;
    }

    @Test
    @DisplayName("Exception Thrown When No Answer Found")
    void exceptionThrownWhenNoAnswerFound() {
        Exception exception = assertThrows(IllegalStateException.class, () ->
                day1Application.calculateAnswer(500, getValues(), NumberSumValues.TWO));
        assertEquals("Unable to find an answer :(", exception.getMessage());
    }

    @Test
    @DisplayName("Answer returned when looking for sum of two values")
    void answerReturnedWhenLookingForSumOfTwoValues() {
        int answer = day1Application.calculateAnswer(6, getValues(), NumberSumValues.TWO);
        assertEquals(8, answer);
    }

    @Test
    @DisplayName("Answer returned when looking for sum of three values")
    void answerReturnedWhenLookingForSumOfThreeValues() {
        int answer = day1Application.calculateAnswer(9, getValues(), NumberSumValues.THREE);
        assertEquals(24, answer);
    }


    private List<String> getValues() {
        List<String> values = new ArrayList<>();
        values.add("2");
        values.add("3");
        values.add("4");
        values.add("5");

        return values;
    }

}