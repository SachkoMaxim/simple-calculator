package calculator.simpleCalculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileParserTest {
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary file
        tempFile = Files.createTempFile("testFile", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the temporary file
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testGetEmptyStringIfFileIsEmpty() throws IOException {
        writeTempFile("");

        FileParser parser = new FileParser(tempFile.toString());

        String[] expected = {};
        assertArrayEquals(expected, parser.getParsedInput());
    }

    @Test
    void testZeroLengthStringIfFileIsEmpty() throws IOException {
        writeTempFile("");

        FileParser parser = new FileParser(tempFile.toString());

        assertEquals(0, parser.getParsedInput().length);
    }

    @Test
    void testGetOneStringElementIfFileHasOneDigit() throws IOException {
        writeTempFile("5");

        FileParser parser = new FileParser(tempFile.toString());

        String[] expected = {"5"};
        assertArrayEquals(expected, parser.getParsedInput());
    }

    @Test
    void testGetManyStringElementsIfFileHasNumber() throws IOException {
        writeTempFile("1 2");

        FileParser parser = new FileParser(tempFile.toString());

        String[] expected = {"1", "2"};
        assertArrayEquals(expected, parser.getParsedInput());
    }

    @Test
    void testGetEvenMoreStringElementsIfFileHasBiggerNumber() throws IOException {
        writeTempFile("1 2 3 4");

        FileParser parser = new FileParser(tempFile.toString());

        String[] expected = {"1", "2", "3", "4"};
        assertArrayEquals(expected, parser.getParsedInput());
    }

    @ParameterizedTest
    @CsvSource({
            "'7 +', '7,+'",
            "'1 2 3 +', '1,2,3,+'",
            "'1 2 3 -', '1,2,3,-'",
            "'1 2 3 *', '1,2,3,*'",
            "'1 2 3 /', '1,2,3,/'",
    })
    void testGetStringElementsIfFileHasNumberAndOperation(String fileContent, String expectedOutput) throws IOException {
        writeTempFile(fileContent);

        FileParser parser = new FileParser(tempFile.toString());

        String[] expected = expectedOutput.split(",");
        assertArrayEquals(expected, parser.getParsedInput());
    }

    @ParameterizedTest
    @CsvSource({
            "'1 =', '1,='",
            "'1 2 3 =', '1,2,3,='",
    })
    void testGetStringElementsIfFileHasNumberAndEqualsSign(String fileContent, String expectedOutput) throws IOException {
        writeTempFile(fileContent);

        FileParser parser = new FileParser(tempFile.toString());

        String[] expected = expectedOutput.split(",");
        assertArrayEquals(expected, parser.getParsedInput());
    }

    @ParameterizedTest
    @CsvSource({
            "'7 + 8', '7,+,8'",
            "'7 + 4 5 6', '7,+,4,5,6'",
            "'1 2 3 + 8', '1,2,3,+,8'",
            "'1 2 3 + 4 5 6', '1,2,3,+,4,5,6'",
            "'1 2 3 - 4 5 6', '1,2,3,-,4,5,6'",
            "'1 2 3 * 4 5 6', '1,2,3,*,4,5,6'",
            "'1 2 3 / 4 5 6', '1,2,3,/,4,5,6'",
    })
    void testGetStringElementsIfFileHasNumberOperationAndNumber(String fileContent, String expectedOutput) throws IOException {
        writeTempFile(fileContent);

        FileParser parser = new FileParser(tempFile.toString());

        String[] expected = expectedOutput.split(",");
        assertArrayEquals(expected, parser.getParsedInput());
    }

    @ParameterizedTest
    @CsvSource({
            "'7 + 8 =', '7,+,8,='",
            "'7 + 4 5 6 =', '7,+,4,5,6,='",
            "'1 2 3 + 8 =', '1,2,3,+,8,='",
            "'1 2 3 + 4 5 6 =', '1,2,3,+,4,5,6,='",
            "'1 2 3 - 4 5 6 =', '1,2,3,-,4,5,6,='",
            "'1 2 3 * 4 5 6 =', '1,2,3,*,4,5,6,='",
            "'1 2 3 / 4 5 6 =', '1,2,3,/,4,5,6,='",
    })
    void testGetStringElementsIfFileHasFullExpression(String fileContent, String expectedOutput) throws IOException {
        writeTempFile(fileContent);

        FileParser parser = new FileParser(tempFile.toString());

        String[] expected = expectedOutput.split(",");
        assertArrayEquals(expected, parser.getParsedInput());
    }

    @ParameterizedTest
    @CsvSource({
            "'1 +\n\n'",
            "'1 +\n2 ='",
            "'1 +\n 2 ='",
    })
    void testThrowsErrorIfFileHasMoreThanOneLine(String fileContent) {
        writeTempFile(fileContent);

        assertThrows(IllegalArgumentException.class, () -> new FileParser(tempFile.toString()));
    }

    @ParameterizedTest
    @CsvSource({
            "' 1'",
            "'1 '",
            "12",
            "1  2",
            "1 23",
            "1 2  3",
            "1+",
            "1  +",
            "1 2 3+",
            "1 2 3  +",
            "1 2 3 +4",
            "1 2 3 +  4",
            "1 2 3 + 45",
            "1 2 3 + 4  5",
            "1 2 3 + 4 5=",
            "1 2 3 + 4 5  =",
            "123+45=",
            "123 + 45 =",
    })
    void testThrowsErrorIfFileHasIncorrectSpacePlacement(String fileContent) {
        writeTempFile(fileContent);

        assertThrows(IllegalArgumentException.class, () -> new FileParser(tempFile.toString()));
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 - =",
            "- 3 4 =",
            "- =",
            "-",
            "=",
            "- =",
    })
    void testThrowsErrorIfFileHasMissingNumbers(String fileContent) {
        writeTempFile(fileContent);

        assertThrows(IllegalArgumentException.class, () -> new FileParser(tempFile.toString()));
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 * 3 ?",
            "1 2 * a =",
            "a 2 * 3 =",
            "1 2 _ 3 =",
            "one + two =",
    })
    void testThrowsErrorIfFileHasInvalidSymbols(String fileContent) {
        writeTempFile(fileContent);

        assertThrows(IllegalArgumentException.class, () -> new FileParser(tempFile.toString()));
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 / 3 =4",
            "1 2 / 3 = 4",
            "1 2 / 3 =a",
            "1 2 / 3 = a",
            "1 2 / 3 =+",
            "1 2 / 3 = +",
            "1 2 / 3 ==",
            "1 2 / 3 = =",
            "'1 2 / 3 = '",
    })
    void testThrowsErrorIfFileHasSomethingAfterEqualsSign(String fileContent) {
        writeTempFile(fileContent);

        assertThrows(IllegalArgumentException.class, () -> new FileParser(tempFile.toString()));
    }

    private void writeTempFile(String content) {
        try {
            Files.writeString(tempFile, content);
        } catch (IOException e) {
            fail("Failed to write to temporary file: " + e.getMessage());
        }
    }
}
