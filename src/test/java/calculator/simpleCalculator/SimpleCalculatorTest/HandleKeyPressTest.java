package calculator.simpleCalculator.SimpleCalculatorTest;

import calculator.simpleCalculator.CalculatorState;
import calculator.simpleCalculator.SimpleCalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandleKeyPressTest {

    private CalculatorState state;

    @BeforeEach
    void setUp() {
        state = new CalculatorState();
    }

    @Test
    void testHandleKeyWhenOneDigitPressed() {
        SimpleCalculator.handleKeyPress(state, "5");
        assertEquals(5, state.screen);
        assertFalse(state.startNewNumber);
    }

    @Test
    void testHandleKeyWhenSecondDigitPressed() {
        state.startNewNumber = false;
        state.screen = 5;
        SimpleCalculator.handleKeyPress(state, "2");
        assertEquals(52, state.screen);
    }

    @Test
    void testHandleKeyWhenPressedTwoDigits() {
        SimpleCalculator.handleKeyPress(state, "1");
        SimpleCalculator.handleKeyPress(state, "2");
        assertEquals(12, state.screen);
        assertFalse(state.startNewNumber);
    }

    @Test
    void testHandleKeyWhenOperationPressed() {
        state.screen = 7;
        SimpleCalculator.handleKeyPress(state, "/");
        assertEquals('/', state.op);
        assertTrue(state.startNewNumber);
        assertEquals(7, state.firstNumber);
    }

    @Test
    void testHandleKeyWhenEqualsPressed() {
        state.screen = 17;
        SimpleCalculator.handleKeyPress(state, "=");
        assertEquals(17, state.screen);
    }

    @Test
    void testHandleKeyWhenEqualsPressedForAddition() {
        state.firstNumber = 17;
        state.screen = 3;
        state.op = '+';
        SimpleCalculator.handleKeyPress(state, "=");
        assertEquals(20, state.screen);
    }

    @Test
    void testHandleKeyWhenEqualsPressedForSubtraction() {
        state.firstNumber = 7;
        state.screen = 4;
        state.op = '-';
        SimpleCalculator.handleKeyPress(state, "=");
        assertEquals(3, state.screen);
    }

    @Test
    void testHandleKeyWhenEqualsPressedForSubtractionWithNegativeResult() {
        state.firstNumber = 7;
        state.screen = 23;
        state.op = '-';
        SimpleCalculator.handleKeyPress(state, "=");
        assertEquals(-16, state.screen);
    }

    @Test
    void testHandleKeyWhenEqualsPressedForMultiplication() {
        state.firstNumber = 6;
        state.screen = 7;
        state.op = '*';
        SimpleCalculator.handleKeyPress(state, "=");
        assertEquals(42, state.screen);
    }

    @Test
    void testHandleKeyWhenEqualsPressedForDivision() {
        state.firstNumber = 21;
        state.screen = 7;
        state.op = '/';
        SimpleCalculator.handleKeyPress(state, "=");
        assertEquals(3, state.screen);
    }

    @Test
    void testHandleKeyWhenEqualsPressedForDivisionWithRounding() {
        state.firstNumber = 100;
        state.screen = 33;
        state.op = '/';
        SimpleCalculator.handleKeyPress(state, "=");
        assertEquals(3, state.screen);
    }

    @Test
    void testHandleKeyWhenEqualsPressedForDivisionWithRoundingIsAlwaysToLowerNumber() {
        state.firstNumber = 9;
        state.screen = 10;
        state.op = '/';
        SimpleCalculator.handleKeyPress(state, "=");
        assertEquals(0, state.screen);
    }

    @Test
    void testThrowErrorIfDivisionByZero() {
        state.firstNumber = 5;
        state.screen = 0;
        state.op = '/';
        assertThrows(ArithmeticException.class, () -> SimpleCalculator.handleKeyPress(state, "="));
    }
}
