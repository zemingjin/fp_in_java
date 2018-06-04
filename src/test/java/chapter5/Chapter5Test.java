package chapter5;

import org.junit.Test;

import static chapter5.Chapter5.*;
import static org.junit.Assert.*;


public class Chapter5Test {
    private static final List<Integer> ints = List.list(1, 2, 3, 4, 5);

    @Test
    public void testSum() {
        assertEquals(15, (int)sum(ints));
    }

    @Test
    public void testProd() {
        assertEquals(120, (int)prod(ints));
    }

}