package common;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Either<E, A> {
    public abstract <B> Either<E, B> map(Function<A, B> f);
    public abstract <B> Either<E, B> flatMap(Function<A, Either<E, B>> f);
    public abstract Either<E, A> orElse(Supplier<Either<E, A>> a);
    public abstract A getOrElse(Supplier<A> defaultValue);

    private static class Left<E, A> extends Either<E, A> {
        private final E value;

        private Left(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Left(%s)", value);
        }

        @Override
        public <B> Either<E, B> map(Function<A, B> f) {
            return new Left<>(value);
        }

        @Override
        public <B> Either<E, B> flatMap(Function<A, Either<E, B>> f) {
            return new Left<>(value);
        }

        @Override
        public Either<E, A> orElse(Supplier<Either<E, A>> a) {
            return a.get();
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return defaultValue.get();
        }
    }

    private static class Right<E, A> extends Either<E, A> {
        private final A value;

        private Right(A value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Right(%s)", value);
        }

        @Override
        public <B> Either<E, B> map(Function<A, B> f) {
            return new Right<>(f.apply(value));
        }

        @Override
        public <B> Either<E, B> flatMap(Function<A, Either<E, B>> f) {
            return f.apply(value);
        }

        @Override
        public Either<E, A> orElse(Supplier<Either<E, A>> a) {
            return this;
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return value;
        }
    }

    public static <T, U> Either<T, U> left(T value) {
        return new Left<>(value);
    }

    public static <T, U> Either<T, U> right(U value) {
        return new Right<>(value);
    }

 }
