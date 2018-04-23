package venkat.memoization;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RodCutterBasicTest {
    private final List<Integer> priceValues = Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
    private final RodCutterBasic rodCutter = new RodCutterBasic(priceValues);

    @Test
    public void testMaxProfit() {
        assertEquals(10, rodCutter.maxProfit(5));
        assertEquals(44, rodCutter.maxProfit(22));
    }

}