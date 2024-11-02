package calculator.simpleCalculator;

import java.util.List;

public class SimpleCalculator {

    public static void handleKeyPress(CalculatorState state, String key) {
        List<String> operationKeys = List.of("+", "-", "*", "/");
        if (key.matches("[0-9]")) {
            int digit = Integer.parseInt(key);
            if (state.startNewNumber) {
                state.screen = digit;
                state.startNewNumber = false;
            } else {
                state.screen = state.screen * 10 + digit;
            }
        } else if (operationKeys.contains(key)) {
            state.op = key.charAt(0);
            state.firstNumber = state.screen;
            state.startNewNumber = true;
        } else if (key.equals("=")) {
            switch (state.op) {
                case '+':
                    state.screen = state.firstNumber + state.screen;
                    break;
                case '-':
                    state.screen = state.firstNumber - state.screen;
                    break;
                case '*':
                    state.screen = state.firstNumber * state.screen;
                    break;
                case '/':
                    if (state.screen != 0) {
                        state.screen = state.firstNumber / state.screen;
                    } else {
                        throw new ArithmeticException("Division by zero is not allowed.");
                    }
                    break;
            }
            state.startNewNumber = true;
        }
    }

    public static int calculate(String[] keys) {
        CalculatorState state = new CalculatorState();
        for (String key : keys) {
            handleKeyPress(state, key);
        }
        return state.screen;
    }
}
