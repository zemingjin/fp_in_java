package chapter_6;

import chapter_5.List;
import static chapter_6.Option.*;

import java.util.function.Function;

public class Chapter6 {
    public static <A extends Comparable<A>> Function<List<A>, Option<A>> max() {
        return xs -> xs.isEmpty()
                ? NONE
                : some(xs.foldLeft(xs.head(), x -> y -> x.compareTo(y) > 0 ? x : y));
    }


    static Function<List<Double>, Double> sum = ds -> ds.foldLeft(0.0, a -> b -> a + b);

    static Function<List<Double>, Option<Double>> mean =
            ds -> ds.isEmpty()
                    ? none()
                    : some(sum.apply(ds) / ds.length());

    static Function<List<Double>, Option<Double>> variance =
            ds -> mean.apply(ds).flatMap(m -> mean.apply(ds.map(x -> Math.pow(x - m, 2))));
}
