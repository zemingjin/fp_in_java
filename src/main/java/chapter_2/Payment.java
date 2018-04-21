package chapter_2;

import chapter_1.CreditCard;
import model.Tuple;

import model.List;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
    public Payment combine(Payment other) {
        return combine(this, other);
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

    private Double cos = this.<Double, Double, Double>higherCompose()
            .apply(z -> Math.PI / 2 - z).apply(Math::sin).apply(2.0);

    // Exercise 2.7
    <A, B, C> Function<B, C> partialA(A a, Function<A, Function<B, C>> f) {
        return f.apply(a);
    }

    // Exercise 2.8
    <A, B, C> Function<A, C> partialB(B b, Function<A, Function<B, C>> f) {
        return (A a) -> f.apply(a).apply(b);
    }

    // Exercise 2.9
    <A, B, C, D> Function<A, Function<B, Function<C, Function<D, String>>>> f() {
        return a -> b -> c -> d -> String.format("%s, %s, %s, %s", a, b, c, d);
    }
    // Exercise 2.10
    <A, B, C> Function<A, Function<B, C>> curry(Function<Tuple<A, B>, C> f) {
        return a -> b -> f.apply(new Tuple<>(a, b));
    }
    // Exercise 2.11
    public static <T, U, V> Function<U, Function<T, V>> reverseArgs(Function<T, Function<U, V>> f) {
        return u -> t -> f.apply(t).apply(u);
    }

    final Function<Integer, Integer> factorial = a -> a <= 1 ? 1 : a * this.factorial.apply(a - 1);

}