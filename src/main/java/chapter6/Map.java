package chapter6;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Map<T, U> {
    private final ConcurrentMap<T, U> map = new ConcurrentHashMap<>();

    public static <T, U> Map<T, U> empty() {
        return new Map<>();
    }

    public Map<T, U> add(T t, U u) {
        map.put(t, u);
        return this;
    }

    public Option<U> get(final T t) {
        return this.map.containsKey(t)
                ? Option.some(this.map.get(t))
                : Option.none();
    }

    public Map<T, U> put(T t, U u) {
        return add(t, u);
    }

    public Map<T, U> removeKey(T t) {
        this.map.remove(t);
        return this;
    }
}