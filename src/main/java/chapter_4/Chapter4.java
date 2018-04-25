package chapter_4;

import chapter_3.CollectionUtilities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static chapter_4.TailCall.ret;
import static chapter_4.TailCall.sus;
import static chapter_3.CollectionUtilities.*;


class Chapter4 extends CollectionUtilities {
    static Function<Integer, Function<Integer, TailCall<Integer>>> add =
            a -> b -> b == 0
                    ? ret(a)
                    : sus(() -> Chapter4.add.apply(a + 1).apply(b - 1));

    static TailCall<Integer> addRec(int x, int y) {
        return y == 0
                ? ret(x)
                : sus(() -> addRec(x + 1, y - 1));
    }

    static Integer sumTail(List<Integer> list, Integer acc) {
        return list.isEmpty() ? acc : sumTail(tail(list), acc + head(list));
    }

    static String fibonacci(int number) {
        return fib_(BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(number)).eval().toString();
    }

    private static TailCall<BigInteger> fib_(BigInteger acc1, BigInteger acc2, BigInteger x) {
        if (x.equals(BigInteger.ZERO)) {
            return ret(BigInteger.ZERO);
        } else if (x.equals(BigInteger.ONE)) {
            return ret(acc1.add(acc2));
        }
        return sus(() -> fib_(acc2, acc1.add(acc2), x.subtract(BigInteger.ONE)));
    }

    static <T, U> U foldLeft(List<T> list, final U acc, Function<U, Function<T, U>> f) {
        return foldLeft_(list, acc, f).eval();
    }

    private static <T, U> TailCall<U> foldLeft_(List<T> list, U acc, Function<U, Function<T, U>> f) {
        return list.isEmpty()
                ? ret(acc)
                : sus(() -> foldLeft_(tail(list), f.apply(acc).apply(head(list)), f));
    }

    static <T, U> U foldRight(List<T> list, U acc, Function<T, Function<U, U>> f) {
        return list.isEmpty()
                ? acc
                : foldRight(tail(list), f.apply(head(list)).apply(acc), f);
    }

    static <T> Function<T, T> composeAll(List<Function<T, T>> list) {
        return x -> foldLeft(list, x, a -> b -> b.apply(a));
    }

    static <T> Function<T, T> composeAllViaFoldLeft(List<Function<T, T>> list) {
        return x -> foldLeft(reverse(list), x, a -> b -> b.apply(a));
    }

    static <T> Function<T, T> composeAllViaFoldRighty(List<Function<T, T>> list) {
        return x -> foldRight(list, x, a -> a::apply);
    }
}

