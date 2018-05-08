package chapter_7;

import common.Result;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Map<T, U> {
    private final ConcurrentMap<T, U> map = new ConcurrentHashMap<>();

    public static <T, U> Map<T, U> empty() {
        return new Map<>();
    }

    public static <T, U> Map<T, U> add(Map<T, U> m, T t, U u) {
        m.map.put(t, u);
        return m;
    }

    public Result<U> get(final T key) {
        return this.map.containsKey(key) ? Result.success(this.map.get(key)) : Result.empty();
    }

    public Map<T, U> put(T key, U value) {
        return add(this, key, value);
    }

    public Map<T, U> removeKey(T key) {
        map.remove(key);
        return this;
    }
}
