package Variables;

public class IntVariable extends Variable {

    private int value;

    public IntVariable(String name, int value) {
        super(name);
        setValue(value);
    }

    public IntVariable(String name) {
        super(name);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
