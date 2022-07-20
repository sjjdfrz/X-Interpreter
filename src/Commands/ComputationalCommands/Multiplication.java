package Commands.ComputationalCommands;

import Commands.Assignment;
import Variables.*;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Multiplication extends ComputationalCommands {

    public Multiplication(String operator) {
        super(operator);
    }

    public float compute1(ArrayList<Variable> variables) {

        super.compute1(variables);

        if (isFindVariable1()) {

            if (!getFirstOperand().contains(".") && !getSecondOperand().contains(".")) {
                Assignment.assignment(variables.get(getIndexMainOperand()), Integer.parseInt(getFirstOperand()) * Integer.parseInt(getSecondOperand()));
                return Integer.parseInt(getFirstOperand()) * Integer.parseInt(getSecondOperand());
            }

            if (getFirstOperand().contains(".") && getSecondOperand().contains(".")) {
                Assignment.assignment(variables.get(getIndexMainOperand()), Float.parseFloat(getFirstOperand()) * Float.parseFloat(getSecondOperand()));
                return Float.parseFloat(getFirstOperand()) * Float.parseFloat(getSecondOperand());
            }

            if (!getFirstOperand().contains(".") && getSecondOperand().contains(".")) {
                Assignment.assignment(variables.get(getIndexMainOperand()), Integer.parseInt(getFirstOperand()) * Float.parseFloat(getSecondOperand()));
                return Integer.parseInt(getFirstOperand()) * Float.parseFloat(getSecondOperand());
            }

            if (getFirstOperand().contains(".") && !getSecondOperand().contains(".")) {
                Assignment.assignment(variables.get(getIndexMainOperand()), Float.parseFloat(getFirstOperand()) * Integer.parseInt(getSecondOperand()));
                return Float.parseFloat(getFirstOperand()) * Integer.parseInt(getSecondOperand());
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Syntax  Error");
            alert.setContentText("Your code has syntax error. Please fix the error first and then try again");
            alert.showAndWait();
        }


        return 0;
    }


    public float compute2(ArrayList<Variable> variables) {

        super.compute2(variables);

        if (isFindVariable1() && isFindVariable2() && isFindVariable3()) {

            if (variables.get(getIndexFirstOperand()) instanceof IntVariable && variables.get(getIndexSecondOperand()) instanceof IntVariable) {
                Assignment.assignment(variables.get(getIndexMainOperand()), ((IntVariable) variables.get(getIndexFirstOperand())).getValue() * ((IntVariable) variables.get(getIndexSecondOperand())).getValue());
                return ((IntVariable) variables.get(getIndexFirstOperand())).getValue() * ((IntVariable) variables.get(getIndexSecondOperand())).getValue();
            }

            if (variables.get(getIndexFirstOperand()) instanceof FloatVariable && variables.get(getIndexSecondOperand()) instanceof FloatVariable) {
                Assignment.assignment(variables.get(getIndexMainOperand()), ((FloatVariable) variables.get(getIndexFirstOperand())).getValue() * ((FloatVariable) variables.get(getIndexSecondOperand())).getValue());
                return ((FloatVariable) variables.get(getIndexFirstOperand())).getValue() * ((FloatVariable) variables.get(getIndexSecondOperand())).getValue();
            }

            if (variables.get(getIndexFirstOperand()) instanceof IntVariable && variables.get(getIndexSecondOperand()) instanceof FloatVariable) {
                Assignment.assignment(variables.get(getIndexMainOperand()), ((IntVariable) variables.get(getIndexFirstOperand())).getValue() * ((FloatVariable) variables.get(getIndexSecondOperand())).getValue());
                return ((IntVariable) variables.get(getIndexFirstOperand())).getValue() * ((FloatVariable) variables.get(getIndexSecondOperand())).getValue();
            }

            if (variables.get(getIndexFirstOperand()) instanceof FloatVariable && variables.get(getIndexSecondOperand()) instanceof IntVariable) {
                Assignment.assignment(variables.get(getIndexMainOperand()), ((FloatVariable) variables.get(getIndexFirstOperand())).getValue() * ((IntVariable) variables.get(getIndexSecondOperand())).getValue());
                return ((FloatVariable) variables.get(getIndexFirstOperand())).getValue() * ((IntVariable) variables.get(getIndexSecondOperand())).getValue();
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Syntax  Error");
            alert.setContentText("Your code has syntax error. Please fix the error first and then try again");
            alert.showAndWait();
        }


        return 0;
    }


    public float compute3(ArrayList<Variable> variables) {

        super.compute3(variables);

        if (isFindVariable1() && isFindVariable3()) {

            if (!getFirstOperand().contains(".") && variables.get(getIndexSecondOperand()) instanceof IntVariable) {
                Assignment.assignment(variables.get(getIndexMainOperand()), Integer.parseInt(getFirstOperand()) * ((IntVariable) variables.get(getIndexSecondOperand())).getValue());
                return Integer.parseInt(getFirstOperand()) * ((IntVariable) variables.get(getIndexSecondOperand())).getValue();
            }

            if (getFirstOperand().contains(".") && variables.get(getIndexSecondOperand()) instanceof FloatVariable) {
                Assignment.assignment(variables.get(getIndexMainOperand()), Float.parseFloat(getFirstOperand()) * ((FloatVariable) variables.get(getIndexSecondOperand())).getValue());
                return Float.parseFloat(getFirstOperand()) * ((FloatVariable) variables.get(getIndexSecondOperand())).getValue();
            }

            if (!getFirstOperand().contains(".") && variables.get(getIndexSecondOperand()) instanceof FloatVariable) {
                Assignment.assignment(variables.get(getIndexMainOperand()), Integer.parseInt(getFirstOperand()) * ((FloatVariable) variables.get(getIndexSecondOperand())).getValue());
                return Integer.parseInt(getFirstOperand()) * ((FloatVariable) variables.get(getIndexSecondOperand())).getValue();
            }

            if (getFirstOperand().contains(".") && variables.get(getIndexSecondOperand()) instanceof IntVariable) {
                Assignment.assignment(variables.get(getIndexMainOperand()), Float.parseFloat(getFirstOperand()) * ((IntVariable) variables.get(getIndexSecondOperand())).getValue());
                return Float.parseFloat(getFirstOperand()) * ((IntVariable) variables.get(getIndexSecondOperand())).getValue();
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Syntax  Error");
            alert.setContentText("Your code has syntax error. Please fix the error first and then try again");
            alert.showAndWait();
        }

        return 0;
    }


    public float compute4(ArrayList<Variable> variables) {

        super.compute4(variables);

        if (isFindVariable1() && isFindVariable2()) {

            if (variables.get(getIndexFirstOperand()) instanceof IntVariable && !getSecondOperand().contains(".")) {
                Assignment.assignment(variables.get(getIndexMainOperand()), ((IntVariable) variables.get(getIndexFirstOperand())).getValue() * Integer.parseInt(getSecondOperand()));
                return ((IntVariable) variables.get(getIndexFirstOperand())).getValue() * Integer.parseInt(getSecondOperand());
            }

            if (variables.get(getIndexFirstOperand()) instanceof FloatVariable && getSecondOperand().contains(".")) {
                Assignment.assignment(variables.get(getIndexMainOperand()), ((FloatVariable) variables.get(getIndexFirstOperand())).getValue() * Float.parseFloat(getSecondOperand()));
                return ((FloatVariable) variables.get(getIndexFirstOperand())).getValue() * Float.parseFloat(getSecondOperand());
            }
            if (variables.get(getIndexFirstOperand()) instanceof IntVariable && getSecondOperand().contains(".")) {
                Assignment.assignment(variables.get(getIndexMainOperand()), ((IntVariable) variables.get(getIndexFirstOperand())).getValue() * Float.parseFloat(getSecondOperand()));
                return ((IntVariable) variables.get(getIndexFirstOperand())).getValue() * Float.parseFloat(getSecondOperand());
            }

            if (variables.get(getIndexFirstOperand()) instanceof FloatVariable && !getSecondOperand().contains(".")) {
                Assignment.assignment(variables.get(getIndexMainOperand()), ((FloatVariable) variables.get(getIndexFirstOperand())).getValue() * Integer.parseInt(getSecondOperand()));
                return ((FloatVariable) variables.get(getIndexFirstOperand())).getValue() * Integer.parseInt(getSecondOperand());
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Syntax  Error");
            alert.setContentText("Your code has syntax error. Please fix the error first and then try again");
            alert.showAndWait();
        }
        return 0;
    }
}