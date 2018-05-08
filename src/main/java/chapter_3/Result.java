package chapter_3;

public interface Result<T> {
    void bind(Effect<T> success, Effect<String> failure);
    class Success<T> implements Result<T> {
        private final T value;
        Success(T t) {
            value = t;
        }
        @Override
        public void bind(Effect<T> success, Effect<String> failure) {
            success.apply(value);
        }
    }
    class Failure<T> implements Result<T> {
        private final String errorMessage;
        Failure(String s) {
            this.errorMessage = s;
        }
        @Override
        public void bind(Effect<T> success, Effect<String> failure) {
            failure.apply(errorMessage);
        }

        String getMessage() {
            return errorMessage;
        }
    }
}