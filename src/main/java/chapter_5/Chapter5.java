package chapter_5;

public class Chapter5 {
    public static Integer sum(List<Integer> ints) {
        return ints.foldLeft(0, a -> b -> a + b);
    }

    public static Integer prod(List<Integer> ints) {
        return ints.foldLeft(1, a -> b -> a * b);
    }
}
