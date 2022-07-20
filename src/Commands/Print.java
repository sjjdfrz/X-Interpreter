package Commands;

import java.util.ArrayList;

import Pattern.Patterns;
import Graphic.MainFrame;
import Variables.*;
import javafx.scene.control.Alert;

public class Print {


    public Print(String line, ArrayList<Variable> variables) {

        print(line, variables);

    }


    public int print(String line, ArrayList<Variable> variables) {

        // print x ---> x is mainOperand
        String mainOperand = line.substring(5).trim();

        // example : print 5 -- print 2.6
        if (mainOperand.matches(Patterns.numbersPattern)) {

            MainFrame.getOutputTextArea().appendText(mainOperand + "\n");
            return mainOperand.length();

        }

        // example : print x
        if (mainOperand.matches(Patterns.namesPattern)) {

            int indexMainOperand = 0;
            boolean find = false;
            // find the variable in array
            for (int i = 0; i < variables.size(); i++) {

                if (variables.get(i).getName().equals(mainOperand)) {
                    find = true;
                    indexMainOperand = i;
                }
            }

            if (find) {

                // x is int variable
                if (variables.get(indexMainOperand) instanceof IntVariable) {
                    MainFrame.getOutputTextArea().appendText(((IntVariable) variables.get(indexMainOperand)).getValue() + "\n");
                    return String.valueOf(((IntVariable) variables.get(indexMainOperand)).getValue()).length();
                }
                // x is float variable
                if (variables.get(indexMainOperand) instanceof FloatVariable) {
                    MainFrame.getOutputTextArea().appendText(((FloatVariable) variables.get(indexMainOperand)).getValue() + "\n");
                    return String.valueOf(((FloatVariable) variables.get(indexMainOperand)).getValue()).length();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Syntax  Error");
                alert.setContentText("Your code has syntax error. Please fix the error first and then try again");
                alert.showAndWait();
            }
        }
        return 0;
    }
}