package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import Pattern.Patterns;
import Exception.SyntaxErrorException;
import Interpretation.Interpretation;

public class ForLoop {

    // for 100 (first for)
    private final String mainLine;

    public ForLoop(String mainLine) {
        this.mainLine = mainLine;
    }

    public void computeForLoop(String data) throws SyntaxErrorException {

        String number = mainLine.substring(mainLine.indexOf("r") + 1).trim();

        if (number.matches(Patterns.numbersPattern)) {

            int repeat = Integer.parseInt(number);

            for (int i = 0; i < repeat; i++)
                nestedLoop(data);
        } else
            throw new SyntaxErrorException("Your code has syntax error. Please fix the error first and then try again");
    }

    // inner fors
    public static void nestedLoop(String data) throws SyntaxErrorException {

        try (BufferedReader input = new BufferedReader(new StringReader(data))) {

            while (true) {

                String line = input.readLine();

                if (line == null)
                    break;

                if (!line.equals("") && !line.contains("//")) {

                    if (!line.matches(Patterns.forStartingPattern) && !line.matches(Patterns.printPattern)
                            && !line.matches(Patterns.commandsPattern) && !line.matches(Patterns.forEndingPattern))
                        throw new SyntaxErrorException("Your code has syntax error. Please fix the error first and then try again");

                    if (line.matches(Patterns.printPattern)
                            || line.matches(Patterns.commandsPattern))
                        Interpretation.commandsAnalyze(line);

                    if (line.matches(Patterns.forStartingPattern)) {

                        ForLoop forLoop = new ForLoop(line);
                        int countInnerFor = 1;
                        StringBuilder builder = new StringBuilder();

                        while (!line.matches(Patterns.forEndingPattern) || countInnerFor != 0) {

                            line = input.readLine();

                            if (line == null)
                                throw new SyntaxErrorException("Your code has syntax error. Please fix the error first and then try again");
                            if (line.matches(Patterns.forStartingPattern))
                                countInnerFor++;
                            if (line.matches(Patterns.forEndingPattern))
                                countInnerFor--;
                            if (line.matches(Patterns.forEndingPattern) && countInnerFor == 0)
                                break;

                            line = line.trim();
                            builder.append(line).append("\n");
                        }

                        forLoop.computeForLoop(builder.toString());
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}