package chapter_6;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Option<A> {
    @SuppressWarnings("rawtypes")
    public static Option NONE = new None<>();

    public abstract A getOrThrow();

    public abstract A getOrElse(Supplier<A> defaultValue);
    public abstract <B> Option<B> map(Function<A, B> f);
    public <B> Option<B> flatMap(Function<A, Option<B>> f) {
        return map(f).getOrElse(Option::none);
    }

    public Option<A> orElse(Supplier<Option<A>> defaultValue) {
        return map(a -> this).getOrElse(defaultValue);
    }

    Option<A> filter(Function<A, Boolean> f) {
        return flatMap(x -> f.apply(x) ? this : none());
    }
    private static class None<A> extends Option<A> {
        private None() {}

        @Override
        public A getOrThrow() {
            throw new IllegalStateException("get called on None");
        }
        @Override
        public String toString() {
            return "None";
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <B> Option<B> map(Function<A, B> f) {
            return NONE;
        }
    }

    private static class Some<A> extends Option<A> {
        private final A value;

        private Some(A a) {
            value = a;
        }
        @Override
        public A getOrThrow() {
            return this.value;
        }
        @Override
        public String toString() {
            return String.format("Some(%s)", this.value);
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return value;
        }

        @Override
        public <B> Option<B> map(Function<A, B> f) {
            return some(f.apply(value));
        }

    }

    public static <A> Option<A> none() {
        return NONE;
    }

    public static <A> Option<A> some(A a) {
        return new Some<>(a);
    }
}