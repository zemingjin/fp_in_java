package chapter_3;

public interface Effect<T> {
    void apply(T t);
}