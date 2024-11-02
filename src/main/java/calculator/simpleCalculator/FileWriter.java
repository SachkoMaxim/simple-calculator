package calculator.simpleCalculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriter {
    public static void writeToFile(int result, String outputFilePath) throws IOException {
        String content = generateContent(result);
        Files.write(Paths.get(outputFilePath), content.getBytes());
    }

    public static String generateContent(int result) {
        return Integer.toString(result);
    }
}
