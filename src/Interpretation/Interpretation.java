package Interpretation;

import Commands.Assignment;
import Commands.ForLoop;
import Commands.Print;
import Commands.ComputationalCommands.*;
import Pattern.Patterns;
import Variables.FloatVariable;
import Variables.IntVariable;
import Variables.Variable;
import Exception.SyntaxErrorException;

import java.io.*;
import java.util.ArrayList;


public class Interpretation {

    // an array for locate variables
    private static final ArrayList<Variable> variables = new ArrayList<>();

    // read txt file line by line
    public static void fileReader(String path) throws SyntaxErrorException {

        File file = new File(path);
        boolean commandFlag = false;
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {

            while (true) {

                String line = input.readLine();

                if (line == null)
                    break;

                if (line.contains("%%")) {
                    commandFlag = true;
                    line = input.readLine();
                }

                if (!commandFlag && !line.equals("") && !line.contains("//"))
                    if (line.matches(Patterns.variableDefinitionPattern))
                        variableDefinition(line);
                    else
                        throw new SyntaxErrorException("Your code has syntax error. Please fix the error first and then try again");


                if (commandFlag && !line.equals("") && !line.contains("//")) {


                    if (!line.matches(Patterns.forStartingPattern) && !line.matches(Patterns.printPattern)
                            && !line.matches(Patterns.commandsPattern) && !line.matches(Patterns.forEndingPattern))
                        throw new SyntaxErrorException("Your code has syntax error. Please fix the error first and then try again");

                    if (line.matches(Patterns.printPattern)
                            || line.matches(Patterns.commandsPattern))
                        commandsAnalyze(line);

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void variableDefinition(String line) {

        if (line.contains("int"))
            intVariableDefinition(line);

        else if (line.contains("float"))
            floatVariableDefinition(line);
    }

    public static void intVariableDefinition(String line) {

        // example : int x = 20
        if (line.contains("=")) {

            String name = line.substring(line.indexOf("t") + 1, line.indexOf("=")).trim();
            String value = line.substring(line.indexOf("=") + 1).trim();
            int value1;

            if (value.contains("."))
                value1 = (int) Float.parseFloat(value);
            else
                value1 = Integer.parseInt(value);

            variables.add(new IntVariable(name, value1));
        }

        // example : int x
        else {
            String name = line.substring(line.indexOf("t") + 1).trim();
            variables.add(new IntVariable(name));
        }
    }

    public static void floatVariableDefinition(String line) {

        if (line.contains("=")) {

            String name = line.substring(line.indexOf("t") + 1, line.indexOf("=")).trim();
            String value = line.substring(line.indexOf("=") + 1).trim();
            float value1;

            if (value.contains("."))
                value1 = Float.parseFloat(value);
            else
                value1 = Integer.parseInt(value);

            variables.add(new FloatVariable(name, value1));
        } else {

            String name = line.substring(line.indexOf("t") + 1).trim();
            variables.add(new FloatVariable(name));
        }
    }


    public static void commandsAnalyze(String line) throws SyntaxErrorException {

        if (line.contains("print"))
            new Print(line, variables);

        if (line.contains("=")) {

            // example : sum = x + y
            if (line.contains("+") || line.contains("-") || line.contains("*") || line.contains("/"))
                computationalCommands(line);

                // sum = 2
                // sum = x
            else
                assignmentCommands(line);
        }
    }

    public static void computationalCommands(String line) {

        ComputationalCommands computationalCommands;

        if (line.contains("+")) {
            computationalCommands = new Addition("+");
            computationalCommands.compute(line, variables);
        }
        if (line.contains("-")) {
            computationalCommands = new Subrtraction("-");
            computationalCommands.compute(line, variables);
        }
        if (line.contains("*")) {
            computationalCommands = new Multiplication("*");
            computationalCommands.compute(line, variables);
        }
        if (line.contains("/")) {
            computationalCommands = new Division("/");
            computationalCommands.compute(line, variables);
        }

    }

    public static void assignmentCommands(String line) throws SyntaxErrorException {

        String mainOperand = line.substring(0, line.indexOf("=")).trim();
        String firstOperand = line.substring(line.indexOf("=") + 1).trim();
        int indexMainOperand = 0;
        int indexFirstOperand = 0;


        // sum = 2
        if (firstOperand.matches(Patterns.numbersPattern)) {

            boolean findVariable = false;

            for (int i = 0; i < variables.size(); i++) {
                if (variables.get(i).getName().equals(mainOperand)) {
                    indexMainOperand = i;
                    findVariable = true;
                }
            }

            if (findVariable) {

                if (firstOperand.contains("."))
                    Assignment.assignment(variables.get(indexMainOperand), Float.parseFloat(firstOperand));
                else
                    Assignment.assignment(variables.get(indexMainOperand), Integer.parseInt(firstOperand));
            } else
                throw new SyntaxErrorException("This variable is not defined");
        }

        // sum = x
        else {

            boolean findVariable1 = false;
            boolean findVariable2 = false;
            for (int i = 0; i < variables.size(); i++) {

                if (variables.get(i).getName().equals(mainOperand)) {
                    indexMainOperand = i;
                    findVariable1 = true;

                }
                if (variables.get(i).getName().equals(firstOperand)) {
                    indexFirstOperand = i;
                    findVariable2 = true;
                }
            }

            if (findVariable1 && findVariable2) {

                if (variables.get(indexFirstOperand) instanceof FloatVariable)
                    Assignment.assignment(variables.get(indexMainOperand), ((FloatVariable) variables.get(indexFirstOperand)).getValue());
                else if (variables.get(indexFirstOperand) instanceof IntVariable)
                    Assignment.assignment(variables.get(indexMainOperand), ((IntVariable) variables.get(indexFirstOperand)).getValue());
            } else
                throw new SyntaxErrorException("This variable is not defined");
        }
    }
}