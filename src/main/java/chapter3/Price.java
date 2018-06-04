package chapter3;

public class Price {
    public static final Price ZERO = new Price(0);

    final double value;

    Price(double value) {
        this.value = value;
    }

    public Price add(Price that) {
        return new Price(this.value + that.value);
    }

    public Price mult(int count) {
        return new Price(this.value * count);
    }

}
