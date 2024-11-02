package calculator.simpleCalculator;

public class InputCmdReader {

    private String inputFilePath;
    private String outputFilePath = "result.txt";

    public InputCmdReader(String[] args) {
        parseArgs(args);
    }

    private void parseArgs(String[] args) {
        if (args.length > 4) {
            throw new IllegalArgumentException("Too many arguments.");
        }

        for (int i = 0; i < args.length; i++) {
            if ("-input".equals(args[i])) {
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    inputFilePath = args[++i].trim();
                } else {
                    throw new IllegalArgumentException("No file path specified after \033[3m-input\033[0m.");
                }
            } else if ("-output".equals(args[i])) {
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    outputFilePath = args[++i].trim();
                }
            }
        }

        if (inputFilePath == null) {
            throw new IllegalArgumentException("You need to provide \033[3m-input <file_path>\033[0m because input file is required.");
        }
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }
}