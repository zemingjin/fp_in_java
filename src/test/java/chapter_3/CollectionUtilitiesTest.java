package chapter_3;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionUtilitiesTest {
    private List<String> list = Arrays.asList("a", "b", "c", "d");
    private List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void testHeadTail() {
        assertEquals("a", CollectionUtilities.head(list));
        assertEquals("[b, c, d]", CollectionUtilities.tail(list).toString());
    }

    @Test
    public void testAppend() {
        assertEquals("[a, b, c, d, e]", CollectionUtilities.append(list, "e").toString());
    }

    @Test
    public void testReverse() {
        assertEquals("[d, c, b, a]", CollectionUtilities.reverse(list).toString());
        assertEquals("[d, c, b, a]", CollectionUtilities.reverseViLeftFold(list).toString());
    }

    @Test
    public void testFoldLeft() {
        assertEquals(15, (int)CollectionUtilities.foldLeft(ints, 0, x -> y -> x + y));
        assertEquals(120, (int)CollectionUtilities.foldLeft(ints, 1, x -> y -> x * y));
        assertEquals("(((((0 + 1) + 2) + 3) + 4) + 5)", CollectionUtilities.foldLeft(ints, "0", x -> y -> addAS(x, y)));
    }

    @Test
    public void testFoldRight() {
        assertEquals("(1 + (2 + (3 + (4 + (5 + 0)))))", CollectionUtilities.foldRight(ints, "0", x -> y -> addAS(x, y)));
    }

    private <T, U> String addAS(T x, U y) {
        return "(" + x + " + " + y +")";
    }

    @Test
    public void testMapVia() {
        assertEquals("[2, 4, 6, 8, 10]", CollectionUtilities.mapViaFoldLeft(ints, x -> x * 2).toString());
        assertEquals("[2, 4, 6, 8, 10]", CollectionUtilities.mapViaFoldRight(ints, x -> x * 2).toString());
    }

    @Test
    public void testRange() {
        assertEquals("[0, 1, 2, 3, 4]", CollectionUtilities.<Integer>range(0, 5).toString());
    }

}