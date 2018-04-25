package chapter_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class CollectionUtilities {
    public static <T> List<T> list() {
        return Collections.emptyList();
    }
    public static <T> List<T> list(T t) {
        return Collections.singletonList(t);
    }
    public static <T> List<T> list(List<T> ts) {
        return Collections.unmodifiableList(ts);
    }
    @SafeVarargs
    public static <T> List<T> list(T... t) {
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));
    }

    // Exercise 3.4
    static <T> T head(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("head of empty list");
        }
        return list.get(0);
    }

    static <T> List<T> tail(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("head of empty list");
        }
        List<T> copy = copy(list);
        copy.remove((0));
        return Collections.unmodifiableList(copy);
    }

    static <T> List<T> append(List<T> list, T t) {
        List<T> ts = copy(list);
        ts.add(t);
        return Collections.unmodifiableList(ts);
    }

    private static <T> List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

    // Exercise 3.6
    static <T, R> R foldLeft(List<T> list, R acc, Function<R, Function<T, R>> f) {
        if (!list.isEmpty()) {
            acc = foldLeft(tail(list), f.apply(acc).apply(head(list)), f);
        }
        return acc;
    }

    // Exercise 3.8
    static <T, R> R foldRight(List<T> list, R acc, Function<T, Function<R, R>> f) {
        return list.isEmpty() ? acc
                : f.apply(head(list)).apply(foldRight(tail(list), acc, f));
    }

    // Exercise 3.9
    static <T> List<T> reverse(List<T> list) {
        return foldRight(list, Collections.emptyList(), x -> y -> append(y, x));
    }

    static <T> List<T> reverseViLeftFold(List<T> list) {
        return foldLeft(list, list(), x -> y -> foldLeft(x, list(y), a -> b -> append(a, b)));
    }

    // Exercise 3.10
    static <T, U> List<U> mapViaFoldLeft(List<T> list, Function<T, U> f) {
        return foldLeft(list, list(), x -> y -> append(x, f.apply(y)));
    }

    static <T, U> List<U> mapViaFoldRight(List<T> list, Function<T, U> f) {
        return foldRight(reverse(list), list(), x -> y -> append(y, f.apply(x)));
    }

    static List<Integer> range(int start, int end) {
        return unfold(Collections.emptyList(), start, a -> a + 1, a -> a < end);
    }

    private static <T> List<T> unfold(List<T> acc, T seed, Function<T, T> f, Function<T, Boolean> p) {
        if (p.apply(seed)) {
            return unfold(append(acc, seed), f.apply(seed), f, p);
        }
        return acc;
    }
}
