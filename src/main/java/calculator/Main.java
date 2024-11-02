package calculator;

import java.util.Arrays;

import calculator.simpleCalculator.*;

public class Main {
    public static void main(String[] args) {
        try {
            InputCmdReader cmdReader = new InputCmdReader(args);

            System.out.println("Reading input keys from " + cmdReader.getInputFilePath() + "...");
            FileParser parser = new FileParser(cmdReader.getInputFilePath());

            System.out.println("\nInput keys are:\n" + Arrays.toString(parser.getParsedInput()));

            int result = SimpleCalculator.calculate(parser.getParsedInput());

            System.out.println("\nWriting result to " + cmdReader.getOutputFilePath() + "...");
            FileWriter.writeToFile(result, cmdReader.getOutputFilePath());

            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}