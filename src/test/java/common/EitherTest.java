package common;

import org.junit.Test;

import static org.junit.Assert.*;

public class EitherTest {
    private static Either<String, Integer> mockLeft = Either.left("Error");
    private static Either<String, Integer> mockRight = Either.right(5);

    @Test
    public void test() {
        assertEquals((Integer)1, mockLeft.getOrElse(() -> 1));
        assertEquals((Integer)5, mockRight.getOrElse(() -> 1));
        assertEquals("Left(Error)", mockLeft.toString());
        assertEquals("Right(5)", mockRight.toString());
        assertEquals("Left(Error)", mockLeft.map(x -> x + "_new").toString());
        assertEquals("Right(10)", mockRight.map(x -> x * 2).toString());
        assertEquals("Left(Error)", mockLeft.flatMap(x -> Either.left(x + "_new")).toString());
        assertEquals("Right(10)", mockRight.flatMap(x -> Either.right(x * 2)).toString());
        assertEquals("Left(New Error)", mockLeft.orElse(() -> Either.left("New Error")).toString());
        assertEquals("Right(5)", mockRight.orElse(() -> Either.right(10)).toString());
        assertEquals("10", mockLeft.getOrElse(() -> 10).toString());
        assertEquals("5", mockRight.getOrElse(() -> 10).toString());
    }

}