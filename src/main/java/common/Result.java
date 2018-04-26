package common;

import chapter_6.Option;

import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Result<V> implements Serializable {
    private Result() {
    }
    public abstract V getOrElse(final V defaultValue);
    public abstract V getOrElse(final Supplier<V> defaultValue);
    public abstract <U> Result<U> map(Function<V, U> f);
    public abstract <U> Result<U> flatMap(Function<V, Result<U>> f);
    public abstract Option<V> toOption();
    public abstract Result<V> filter(Function<V, Boolean> f);

    public boolean exists(Function<V, Boolean> p) {
        return map(p).getOrElse(false);
    }

    private static class Failure<V> extends Result<V> {
        private static final Result EMPTY = new Empty();
        private final RuntimeException exception;

        private static class Empty<V> extends Result<V> {
            Empty() {
                super();
            }
            @Override
            public V getOrElse(final V defaultValue) {
                return defaultValue;
            }
            @Override
            public <U> Result<U> map(Function<V, U> f) {
                return empty();
            }
            @Override
            public <U> Result<U> flatMap(Function<V, Result<U>> f) {
                return empty();
            }
            @Override
            public String toString() {
                return "Empty()";
            }
            @Override
            public V getOrElse(Supplier<V> defaultValue) {
                return defaultValue.get();
            }

            @Override
            public Option<V> toOption() {
                return Option.none();
            }

            @Override
            public Result<V> filter(Function<V, Boolean> f) {
                return Result.empty();
            }
        }

        private Failure(String message) {
            super();
            this.exception = new IllegalStateException(message);
        }

        private Failure(RuntimeException e) {
            super();
            this.exception = e;
        }

        private Failure(Exception e) {
            super();
            this.exception = new IllegalStateException(e.getMessage(), e);
        }

        @Override
        public String toString() {
            return String.format("Failure(%s)", exception.getMessage());
        }

        @Override
        public V getOrElse(V defaultValue) {
            return defaultValue;
        }

        @Override
        public V getOrElse(Supplier<V> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <U> Result<U> map(Function<V, U> f) {
            return failure(exception);
        }

        @Override
        public <U> Result<U> flatMap(Function<V, Result<U>> f) {
            return failure(exception);
        }

        @Override
        public Option<V> toOption() {
            return Option.none();
        }

        @Override
        public Result<V> filter(Function<V, Boolean> f) {
            return Result.empty();
        }
    }

    private static class Success<V> extends Result<V> {
        private final V value;

        private Success(V value) {
            super();
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Success(%s)", value.toString());
        }

        @Override
        public V getOrElse(V defaultValue) {
            return value;
        }

        @Override
        public V getOrElse(Supplier<V> defaultValue) {
            return value;
        }

        @Override
        public <U> Result<U> map(Function<V, U> f) {
            try {
                return success(f.apply(value));
            } catch (Exception e) {
                return failure(e);
            }
        }

        @Override
        public <U> Result<U> flatMap(Function<V, Result<U>> f) {
            try {
                return f.apply(value);
            } catch (Exception e) {
                return failure(e.getMessage());
            }
        }

        @Override
        public Option<V> toOption() {
            return Option.some(value);
        }

        @Override
        public Result<V> filter(Function<V, Boolean> f) {
            return flatMap(x -> f.apply(x) ? this : failure("Condition not matched"));
        }
    }

    public static <V> Result<V> failure(String message) {
        return new Failure<>(message);
    }

    public static <V> Result<V> failure(Exception e) {
        return new Failure<V>(e);
    }

    public static <V> Result<V> failure(RuntimeException e) {
        return new Failure<V>(e);
    }

    public static <V> Result<V> success(V value) {
        return new Success<>(value);
    }

    @SuppressWarnings("unchecked")
    public static <V> Result<V> empty() {
        return Failure.EMPTY;
    }
}