package venkat;

import venkat.tailcall.TailCall;

import java.math.BigInteger;

import static venkat.tailcall.TailCalls.call;
import static venkat.tailcall.TailCalls.done;

public class BigFactorial {
    public static BigInteger decrement(final BigInteger number) {
        return number.subtract(BigInteger.ONE);
    }

    public static BigInteger multiply(final BigInteger first, final BigInteger second) {
        return first.multiply(second);
    }

    public static BigInteger factorial(final BigInteger number) {
        return factorialTailRec(BigInteger.ONE, number).invoke();
    }

    final static BigInteger ONE = BigInteger.ONE;
    final static BigInteger FIVE = new BigInteger("5");
    final static BigInteger TWENTYK = new BigInteger("20000");

    private static TailCall<BigInteger> factorialTailRec(final BigInteger factorial, final BigInteger number) {
        if (number.equals(BigInteger.ONE))
            return done(factorial);
        else
            return call(() -> factorialTailRec(multiply(factorial, number), decrement(number)));
    }
}