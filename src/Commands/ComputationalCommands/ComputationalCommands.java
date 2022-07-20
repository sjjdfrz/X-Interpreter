package Commands.ComputationalCommands;

import Variables.*;
import Pattern.Patterns;

import java.util.ArrayList;

public class ComputationalCommands {

    private String operator;
    private int indexOperator;
    private String mainOperand;
    private int indexMainOperand;
    private String firstOperand;
    private int indexFirstOperand;
    private String secondOperand;
    private int indexSecondOperand;
    private boolean findVariable1 = false;
    private boolean findVariable2 = false;
    private boolean findVariable3 = false;
    /*
    sum = x + y
    sum : mainOperand
    x : firstOperand
    + : operator
    y : secondOperand
     */


    public ComputationalCommands(String operator) {
        setOperator(operator);
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getIndexOperator() {
        return indexOperator;
    }

    public void setIndexOperator(int indexOperator) {
        this.indexOperator = indexOperator;
    }

    public String getMainOperand() {
        return mainOperand;
    }

    public void setMainOperand(String mainOperand) {
        this.mainOperand = mainOperand;
    }

    public int getIndexMainOperand() {
        return indexMainOperand;
    }

    public void setIndexMainOperand(int indexMainOperand) {
        this.indexMainOperand = indexMainOperand;
    }

    public String getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(String firstOperand) {
        this.firstOperand = firstOperand;
    }

    public int getIndexFirstOperand() {
        return indexFirstOperand;
    }

    public void setIndexFirstOperand(int indexFirstOperand) {
        this.indexFirstOperand = indexFirstOperand;
    }

    public String getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(String secondOperand) {
        this.secondOperand = secondOperand;
    }

    public int getIndexSecondOperand() {
        return indexSecondOperand;
    }

    public void setIndexSecondOperand(int indexSecondOperand) {
        this.indexSecondOperand = indexSecondOperand;
    }

    public boolean isFindVariable1() {
        return findVariable1;
    }

    public boolean isFindVariable2() {
        return findVariable2;
    }

    public boolean isFindVariable3() {
        return findVariable3;
    }


    public final void compute(String line, ArrayList<Variable> variables) {

        setIndexOperator(line.indexOf(getOperator()));
        setMainOperand(line.substring(0, line.indexOf("=")).trim());
        setFirstOperand(line.substring(line.indexOf("=") + 1, getIndexOperator()).trim());
        setSecondOperand(line.substring(getIndexOperator() + 1).trim());

        // example : sum = 2 + 6
        if (getFirstOperand().matches(Patterns.numbersPattern) && getSecondOperand().matches(Patterns.numbersPattern))
            compute1(variables);
        // example : sum = x + y
        if (!getFirstOperand().matches(Patterns.numbersPattern) && !getSecondOperand().matches(Patterns.numbersPattern))
            compute2(variables);
        // example : sum = 2 + x
        if (getFirstOperand().matches(Patterns.numbersPattern) && !getSecondOperand().matches(Patterns.numbersPattern))
            compute3(variables);
        // example : sum = x + 2
        if (!getFirstOperand().matches(Patterns.numbersPattern) && getSecondOperand().matches(Patterns.numbersPattern))
            compute4(variables);
    }


    // compute1-2-3-4 for find the location of variables in arraylist
    public float compute1(ArrayList<Variable> variables) {

        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getName().equals(getMainOperand())) {
                findVariable1 = true;
                setIndexMainOperand(i);
            }
        }

        return 0;
    }

    public float compute2(ArrayList<Variable> variables) {

        for (int i = 0; i < variables.size(); i++) {

            if (variables.get(i).getName().equals(getMainOperand())) {
                setIndexMainOperand(i);
                findVariable1 = true;

            }
            if (variables.get(i).getName().equals(getFirstOperand())) {
                setIndexFirstOperand(i);
                findVariable2 = true;

            }
            if (variables.get(i).getName().equals(getSecondOperand())) {
                setIndexSecondOperand(i);
                findVariable3 = true;

            }
        }
        return 0;
    }

    public float compute3(ArrayList<Variable> variables) {

        for (int i = 0; i < variables.size(); i++) {

            if (variables.get(i).getName().equals(getMainOperand())) {
                setIndexMainOperand(i);
                findVariable1 = true;
            }
            if (variables.get(i).getName().equals(getSecondOperand())) {
                setIndexSecondOperand(i);
                findVariable3 = true;
            }
        }
        return 0;
    }

    public float compute4(ArrayList<Variable> variables) {

        for (int i = 0; i < variables.size(); i++) {

            if (variables.get(i).getName().equals(getMainOperand())) {
                setIndexMainOperand(i);
                findVariable1 = true;
            }
            if (variables.get(i).getName().equals(getFirstOperand())) {
                setIndexFirstOperand(i);
                findVariable2 = true;
            }
        }
        return 0;
    }
}