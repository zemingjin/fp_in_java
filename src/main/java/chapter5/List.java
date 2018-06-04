package chapter5;

import common.TailCall;

import java.util.function.Function;

import static common.TailCall.*;

public abstract class List<A> {
    public abstract A head();

    public abstract List<A> tail();

    public abstract boolean isEmpty();

    public abstract List<A> setHead(A h);

    public abstract List<A> drop(int n);

    public abstract List<A> dropWhile(Function<A, Boolean> f);

    public List<A> cons(A a) {
        return new Cons<>(a, this);
    }

    public List<A> concat(List<A> that) {
        return isEmpty()
                ? that
                : new Cons<>(head(), tail().concat(that));
    }

    public List<A> concatViaFoldLeft(List<A> that) {
        return foldLeft(that, b -> b::cons);
    }

    public abstract List<A> init();

    public <B> B foldLeft(B identity, Function<B, Function<A, B>> f) {
        return isEmpty()
                ? identity
                : f.apply(tail().foldLeft(identity, f)).apply(head());
    }

    public <B> B foldLeftViaFoldRight(B identity, Function<B, Function<A, B>> f) {
        return foldRight(identity, x -> y -> f.apply(y).apply(x));
    }

    public  <B> B foldRight(B identity, Function<A, Function<B, B>> f ) {
        return isEmpty()
                ? identity
                : f.apply(head()).apply(tail().foldRight(identity, f));
    }

    public <B> B foldRightViaFoldLeft(B identity, Function<A, Function<B, B>> f) {
        return foldLeft(identity, x -> y -> f.apply(y).apply(x));
    }

    public List<A> reverse() {
        return reverse_(list(), this).eval();
    }

    public List<A> reverseViaFoldLeft() {
        return foldLeft(list(), x -> x::cons);
    }

    private TailCall<List<A>> reverse_(List<A> acc, List<A> list) {
        return list.isEmpty()
                ? ret(acc)
                : sus(() -> reverse_(new Cons<>(list.head(), acc), list.tail()));
    }

    public int length() {
        return foldLeft(0, a -> ignore -> a + 1);
    }

    public <B> List<B> map(Function<A, B> f) {
        return foldRight(list(), h -> t -> new Cons<>(f.apply(h), t));
    }

    public List<A> filter(Function<A, Boolean> f) {
        return foldRight(list(), h -> t -> f.apply(h) ? new Cons<>(h, t) : t);
    }

    public List<A> filterViaFlatMap(Function<A, Boolean> f) {
        return flatMap(a -> f.apply(a) ? list(a) : NIL);
    }

    public <B> List<B> flatMap(Function<A, List<B>> f) {
        return foldRight(list(), h -> t -> f.apply(h).concat(t));
    }

    public static <A> List<A> flatten(List<List<A>> list) {
        return list.flatMap(x -> x);
    }

    @SuppressWarnings("rawtypes")
    public static final List NIL = new Nil<>();

    private List() {
    }

    private static class Nil<A> extends List<A> {
        private Nil() {
        }

        public A head() {
            throw new IllegalStateException("head called en empty list");
        }

        public List<A> tail() {
            throw new IllegalStateException("tail called en empty list");
        }

        public boolean isEmpty() {
            return true;
        }

        public List<A> setHead(A h) {
            throw new IllegalStateException("setHead called on empty list");
        }

        @Override
        public List<A> drop(int n) {
            return this;
        }

        @Override
        public List<A> dropWhile(Function<A, Boolean> f) {
            return this;
        }

        @Override
        public List<A> init() {
            return this;
        }

        @Override
        public String toString() {
            return "Nil";
        }
    }

    private static class Cons<A> extends List<A> {
        private final A head;
        private final List<A> tail;

        private Cons(A head, List<A> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public A head() {
            return head;
        }

        @Override
        public List<A> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public List<A> setHead(A h) {
            return new Cons<>(h, tail);
        }

        @Override
        public String toString() {
            return "(" + head + "," + tail + ")";
        }

        @Override
        public List<A> drop(int n) {
            return (n == 0) ? this : drop_(n).eval();
        }

        private TailCall<List<A>> drop_(int n) {
            return n == 0 || isEmpty()
                    ? ret(this)
                    : sus(() -> ((Cons<A>)tail()).drop_(n - 1));
        }

        @Override
        public List<A> dropWhile(Function<A, Boolean> f) {
            return dropWhile_(f).eval();
        }

        private TailCall<List<A>> dropWhile_(Function<A, Boolean> f) {
            return isEmpty() || !f.apply(head)
                    ? ret(this)
                    : sus(() -> ((Cons<A>)tail).dropWhile_(f));
        }

        @Override
        public List<A> init() {
            return reverse().tail().reverse();
        }

     }

    @SuppressWarnings("unchecked")
    public static <A> List<A> list() {
        return NIL;
    }

    @SafeVarargs
    public static <A> List<A> list(A... a) {
        List<A> n = list();
        for (int i = a.length - 1; i >= 0; i--) {
            n = new Cons<>(a[i], n);
        }
        return n;
    }

}