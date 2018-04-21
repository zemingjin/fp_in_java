package model;

public class List<T> {
    java.util.List<T> buf = new java.util.LinkedList<>();
    public void add(T v) {
        buf.add(v);
    }
}
