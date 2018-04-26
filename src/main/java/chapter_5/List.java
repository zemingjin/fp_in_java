package chapter_5;

import chapter_4.TailCall;

import java.util.function.Function;

import static chapter_4.TailCall.*;

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
    @SuppressWarnings("rawtypes")
    public static final List NIL = new Nil<>();
    private List() {}
    private static class Nil<A> extends List<A> {
        @Override
        public List<A> drop(int n) {
            return this;
        }

        private Nil() {}
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
        public List<A> dropWhile(Function<A, Boolean> f) {
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
        public A head() {
            return head;
        }
        public List<A> tail() {
            return tail;
        }
        public boolean isEmpty() {
            return false;
        }
        public List<A> setHead(A h) {
            return new Cons<>(h, tail);
        }
        @Override
        public String toString() {
            return "(" + head + "," + tail + ")";
        }

        @Override
        public List<A> drop(int n) {
            return (n == 0)
                    ? this
                   : drop_(n).eval();
         }

         private TailCall<List<A>> drop_(int n) {
            return n == 0 || isEmpty()
                   ? ret(this)
                   : sus(() -> ((Cons<A>)tail).drop_(n - 1));
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

    public static <A> List<A> setHead(List<A> list, A h) {
        if (list.isEmpty()) {
            throw new IllegalStateException("setHead called on an empty list");
        }
        return new Cons<>(h, list.tail());
    }
}