package Commands;

import Variables.*;

public class Assignment {

    public static void assignment(Variable variable, int value) {
        if (variable instanceof IntVariable)
            ((IntVariable) variable).setValue(value);
        else if (variable instanceof FloatVariable)
            ((FloatVariable) variable).setValue(value);
    }

    public static void assignment(Variable variable, float value) {
        if (variable instanceof IntVariable)
            ((IntVariable) variable).setValue((int) value);
        else if (variable instanceof FloatVariable)
            ((FloatVariable) variable).setValue(value);
    }
}
