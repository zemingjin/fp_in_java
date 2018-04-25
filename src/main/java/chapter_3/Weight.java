package chapter_3;

public class Weight {
    public static final Weight ZERO = new Weight(0);
    public final double value;

    public Weight(double value) {
        this.value = value;
    }

    public Weight mult(int count) {
        return new Weight(value * count);
    }

    Weight add(Weight that) {
        return new Weight(value + that.value);
    }
}