package venkat;

import org.junit.Test;

import static org.junit.Assert.*;

public class BigFactorialTest {
    @Test
    public void testBigFactorial() {
        assertEquals("8192063202",
                     BigFactorial.factorial(BigFactorial.TWENTYK).toString().substring(1, 11));
    }

}