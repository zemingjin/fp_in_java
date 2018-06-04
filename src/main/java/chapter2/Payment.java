package chapter2;

import chapter1.CreditCard;

import java.util.function.Function;

public class Payment {
    public final CreditCard cc;
    public final int amount;

    public Payment() {
        this(null, 0);
    }

    public Payment(CreditCard cc, int amount) {
        this.cc = cc;
        this.amount = amount;
    }

    Function<Integer, Integer> triple = n -> n * 3;
    Function<Integer, Integer> square = n -> n * n;

    // Exercise 2.2
    static Function<Integer, Integer> compose(Function<Integer, Integer> f1,
                                              Function<Integer, Integer> f2) {
        return n -> f1.apply(f2.apply(n));
    }

    public static Payment combine(Payment payment1, Payment payment2) {
        if (payment1.cc.equals(payment2.cc)) {
            return new Payment(payment1.cc, payment1.amount + payment2.amount);
        } else {
            throw new IllegalStateException(
                    "Can't combine payments to different cards");
        }
    }

    // Exercise 2.3
    Function<Integer, Function<Integer, Integer>> add = a -> b -> a + b;

    // Exercise 2.4
    Function<Function<Integer, Integer>,
            Function<Function<Integer, Integer>,
                    Function<Integer, Integer>>> compose = x -> y -> z -> x.apply(y.apply(z));

    // Exercise 2.5
    <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
        return (Function<U, V> f) -> (Function<T, U > g) -> (T x) -> f.apply(g.apply(x));
    }

    // Exercise 2.6
    <T, U, V> Function<Function<T, U>, Function<Function<U, V>, Function<T, V>>> higherAndThen() {
        return (Function<T, U> f) -> (Function<U, V> g) -> x -> g.apply(f.apply(x));
    }

    // Exercise 2.9
    <A, B, C, D> Function<A, Function<B, Function<C, Function<D, String>>>> f() {
        return a -> b -> c -> d -> String.format("%s, %s, %s, %s", a, b, c, d);
    }
    final Function<Integer, Integer> factorial = a -> a <= 1 ? 1 : a * this.factorial.apply(a - 1);

}