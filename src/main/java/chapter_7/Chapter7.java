package chapter_7;

import chapter_5.List;
import common.Either;

import java.util.function.Function;

public class Chapter7 {
    <A extends Comparable<A>> Function<List<A>, Either<String, A>> max() {
        return xs -> xs.isEmpty()
                ? Either.left("max called on an empty list")
                : Either.right(xs.foldLeft(xs.head(), x -> y -> x.compareTo(y) < 0 ?
                x : y));
    }
}

