package calculator.simpleCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
    private String[] parsedInput;

    public FileParser(String inputFilePath) throws IOException {
        parseFile(inputFilePath);
    }

    void parseFile(String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String input = reader.readLine();

            if (input == null || input.isEmpty()) {
                parsedInput = new String[0];
                return;
            }

            if (reader.readLine() != null) {
                throw new IllegalArgumentException("File must contain only one line");
            }

            if (!isValidFormat(input)) {
                throw new IllegalArgumentException("Invalid input file format (you can write " +
                        "digits sequence divided with a space, then an operation, digits sequence " +
                        "again and '=' symbol.");
            }

            parsedInput = input.split(" ");
        }
    }

    private boolean isValidFormat(String input) {
        String regex = "((\\d(\\s\\d)*)(\\s[+\\-*/](\\s\\d)+)?(\\s=)?)|((\\d(\\s\\d)*)(\\s[+\\-*/])?)";

        return input.matches(regex);
    }

    public String[] getParsedInput() {
        return parsedInput;
    }
}
