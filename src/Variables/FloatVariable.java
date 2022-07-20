package Variables;

public class FloatVariable extends Variable {

    private float value;

    public FloatVariable(String name, float value) {
        super(name);
        setValue(value);
    }

    public FloatVariable(String name) {
        super(name);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
