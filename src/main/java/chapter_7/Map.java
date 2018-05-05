package chapter_7;

import common.Result;

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

    public Result<U> get(final T t) {
        return map.containsKey(t)
                ? Result.success(map.get(t))
                : Result.empty();
    }

    public Map<T, U> put(T t, U u) {
        return add(t, u);
    }

    public Map<T, U> removeKey(T t) {
        map.remove(t);
        return this;
    }
}
