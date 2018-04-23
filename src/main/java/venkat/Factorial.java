package venkat;

import venkat.tailcall.TailCall;

import static venkat.tailcall.TailCalls.*;

public class Factorial {
    public static int factorialTailRec(final int number) {
        return factorialTailRec(1, number).invoke();
    }

    private static TailCall<Integer> factorialTailRec(final int factorial, final int number) {
        if (number == 1)
            return done(factorial);
        else
            return call(() -> factorialTailRec(factorial * number, number - 1));
    }
}
