package common;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResultTest {
    private static Result<Integer> mockFailure = Result.failure(new Exception("error"));
    private static Result<Integer> mockSuccess = Result.success(5);

    @Test
    public void testFailure() {
        assertEquals("Failure(error)", mockFailure.toString());
        assertEquals("10", mockFailure.getOrElse(10).toString());
        assertEquals("10", mockFailure.getOrElse(() -> 10).toString());
        assertEquals("Failure(error)", mockFailure.flatMap(d -> Result.success(10)).toString());
        assertEquals("Success(10)", mockFailure.orElse(() -> Result.success(10)).toString());
    }

    @Test
    public void testSuccess() {
        assertEquals("Success(5)", mockSuccess.toString());
        assertEquals("5", mockSuccess.getOrElse(10).toString());
        assertEquals("5", mockSuccess.getOrElse(() -> 10).toString());
        assertEquals("Success(10)", mockSuccess.flatMap(d -> Result.success(10)).toString());
        assertEquals("Success(5)", mockSuccess.orElse(() -> Result.success(10)).toString());
    }
}