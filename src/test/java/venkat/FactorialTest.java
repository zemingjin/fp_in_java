package venkat;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactorialTest {
    @Test
    public void testFactorial() {
        assertEquals(Factorial.factorialTailRec(2), 2);
        assertEquals(Factorial.factorialTailRec(5), 120);
        assertEquals(Factorial.factorialTailRec(20000), 0);
    }

}