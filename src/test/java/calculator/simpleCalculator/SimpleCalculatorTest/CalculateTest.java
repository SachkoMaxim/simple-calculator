package calculator.simpleCalculator.SimpleCalculatorTest;

import calculator.simpleCalculator.SimpleCalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculateTest {

    @Test
    void testCalculateWhenNoButtonsPressed() {
        String[] input = {};
        assertEquals(0, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenOneDigitInput() {
        String[] input = {"5"};
        assertEquals(5, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenMultipleDigitsInputWithoutOperationInput() {
        String[] input = {"1", "2"};
        assertEquals(12, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenMultipleDigitsInputAndEqualsInput() {
        String[] input = {"1", "2", "3", "="};
        assertEquals(123, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenEqualsAndAdditionInput() {
        String[] input = {"1", "2", "3", "+", "4", "5", "6", "="};
        assertEquals(579, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenEqualsAndSubtractionInputWithPositiveResult() {
        String[] input = {"1", "2", "3", "-", "2", "3", "="};
        assertEquals(100, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenEqualsAndSubtractionInputWithNegativeResult() {
        String[] input = {"1", "0", "-", "1", "0", "0", "="};
        assertEquals(-90, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenEqualsAndMultiplicationInput() {
        String[] input = {"1", "0", "*", "2", "2", "="};
        assertEquals(220, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenEqualsAndDivisionInput() {
        String[] input = {"8", "8", "/", "4", "="};
        assertEquals(22, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenEqualsAndDivisionInputWithRounding() {
        String[] input = {"1", "7", "/", "4", "="};
        assertEquals(4, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenEqualsAndDivisionInputWithRoundingIsAlwaysToLowerNumber() {
        String[] input = {"9", "/", "1", "0", "="};
        assertEquals(0, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenDivisionInputWithoutEqualsInputAndDivisionByZero() {
        String[] input = {"5", "/", "0"};
        assertEquals(0, SimpleCalculator.calculate(input));
    }

    @Test
    void testThrowsErrorWhenDivisionAndEqualsInputsAndDivisionByZero() {
        String[] input = {"5", "/", "0", "="};
        assertThrows(ArithmeticException.class, () -> SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenOperationInputAndWithoutSecondNumber() {
        String[] input = {"1", "2", "+"};
        assertEquals(12, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenOperationInputWithOneDigitSecondNumberAndWithoutEqualsInput() {
        String[] input = {"1", "2", "+", "4"};
        assertEquals(4, SimpleCalculator.calculate(input));
    }

    @Test
    void testCalculateWhenOperationInputWithMultiplyDigitsSecondNumberWithoutEqualsInput() {
        String[] input = {"1", "2", "+", "4", "5", "6"};
        assertEquals(456, SimpleCalculator.calculate(input));
    }
}
