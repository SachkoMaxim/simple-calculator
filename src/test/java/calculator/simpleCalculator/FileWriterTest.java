package calculator.simpleCalculator;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileWriterTest {

    @Test
    void testWriteToFileIfDataIsCorrect() {
        int result = 5;

        String expectedResult = "5";
        String actualContent = FileWriter.generateContent(result);

        assertEquals(expectedResult, actualContent);
    }

    @Test
    void testWriteToFileIfDataHasMoreThanOneDigit() {
        int result = 57;

        String expectedResult = "57";
        String actualContent = FileWriter.generateContent(result);

        assertEquals(expectedResult, actualContent);
    }

    @Test
    void testWriteToFileIfDataIsNegative() {
        int result = -5;

        String expectedResult = "-5";
        String actualContent = FileWriter.generateContent(result);

        assertEquals(expectedResult, actualContent);
    }

    @Test
    void testWriteToFileZeroIfDataIsZero() {
        int result = 0;

        String expectedResult = "0";
        String actualContent = FileWriter.generateContent(result);

        assertEquals(expectedResult, actualContent);
    }

    @Test
    void testShowIOExceptionIfPathIsNotReachable() {
        int result = 5;
        String invalidOutputPath = "/invalid_path/output.txt";

        IOException exception = assertThrows(IOException.class, () -> {
            FileWriter.writeToFile(result, invalidOutputPath);
        });

        assertNotNull(exception);
    }
}