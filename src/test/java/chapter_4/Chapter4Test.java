package chapter_4;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import static chapter_3.CollectionUtilities.*;

import static org.junit.Assert.*;

public class Chapter4Test {
    private static final List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void testAddRec() {
        assertEquals(10003, (int)Chapter4.addRec(10000, 3).eval());
        assertEquals(1000003, (int)Chapter4.addRec(3, 1000000).eval());
        assertEquals(10003, (int)Chapter4.add.apply(10000).apply(3).eval());
        assertEquals(1000003, (int)Chapter4.add.apply(3).apply( 1000000).eval());
    }

    @Test
    public void testSumTail() {
        assertEquals(15, (int)Chapter4.sumTail(ints, 0));
    }

    @Test
    public void testFibonacci() {
        assertEquals("1", Chapter4.fibonacci(1));
        assertEquals("2", Chapter4.fibonacci(3));
        assertEquals("55", Chapter4.fibonacci(10));
        assertEquals("354224848179261915075", Chapter4.fibonacci(100));
    }

    @Test
    public void testFoldLeft() {
        assertEquals("[([([([([([] + 1)] + 2)] + 3)] + 4)] + 5)]",
                     Chapter4.foldLeft(ints, Collections.emptyList(), a -> b -> Collections.singletonList(addAS(a, b))).toString());
    }

    @Test
    public void testFoldRight() {
        assertEquals("[(5 + [(4 + [(3 + [(2 + [(1 + [])])])])])]",
                     Chapter4.foldRight(ints, Collections.emptyList(), a -> b -> Collections.singletonList(addAS(a, b))).toString());
    }

    @Test
    public void testComposeAll() {
        Function<Integer, Integer> add = y -> y + 1;
        assertEquals(500, (int)Chapter4.composeAll(mapViaFoldLeft(range(0, 500), x -> add)).apply(0));
        assertEquals(500, (int)Chapter4.composeAllViaFoldLeft(mapViaFoldLeft(range(0, 500), x -> add)).apply(0));
        assertEquals(500, (int)Chapter4.composeAllViaFoldRighty(mapViaFoldLeft(range(0, 500), x -> add)).apply(0));
    }

    @Test
    public void testFibo() {
        assertEquals("0, 1, 1, 2, 3, 5", Chapter4.fibo(5));
        assertEquals("0, 1, 1, 2, 3, 5", Chapter4.fiboViaRec(5));
        assertEquals("0, 1, 1, 2, 3, 5", Chapter4.fiboCorecursive(5));
    }

    @Test
    public void testMemoization() {
        Map<Integer, Integer> cache = new ConcurrentHashMap<>();
        Function<Integer, Integer> doubleValue = x -> cache.computeIfAbsent(x, y -> y * 2);

        assertNotNull(doubleValue);
    }

    private <T, U> String addAS(T x, U y) {
        return "(" + x + " + " + y +")";
    }

}