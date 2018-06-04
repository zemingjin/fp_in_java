package chapter5;

import org.junit.Test;

import static chapter5.List.NIL;
import static org.junit.Assert.*;

public class ListTest {
    private static final List<Integer> ints = List.list(1, 2, 3, 4, 5);
    private static final List<Integer> ints2 = List.list(6, 7, 8);
    private static final List<List<Integer>> nested = List.list(List.list(1, 2, 3), List.list(4, 5, 6));

    @Test
    public void testCons() {
        assertEquals("(0,(1,(2,(3,(4,(5,Nil))))))", ints.cons(0).toString());
        assertEquals("(0,(2,(3,(4,(5,Nil)))))", ints.setHead(0).toString());
    }

    @Test
    public void testDrop() {
        assertEquals("(3,(4,(5,Nil)))", ints.drop(2).toString());
        assertEquals("(3,(4,(5,Nil)))", ints.dropWhile(a -> a < 3).toString());
    }

    @Test
    public void testConcat() {
        assertEquals("(1,(2,(3,(4,(5,(6,(7,(8,Nil))))))))", ints.concat(ints2).toString());
        assertEquals("(1,(2,(3,(4,(5,(6,(7,(8,Nil))))))))", ints.concatViaFoldLeft(ints2).toString());
    }

    @Test
    public void testInit() {
        assertEquals("(1,(2,(3,(4,Nil))))", ints.init().toString());
    }

    @Test
    public void testFoldRight() {
        assertEquals("(1,(2,(3,(4,(5,Nil)))))", ints.foldRight(NIL, a -> b -> b.cons(a)).toString());
        assertEquals("(1,(2,(3,(4,(5,Nil)))))", ints.foldRightViaFoldLeft(NIL, a -> b -> b.cons(a)).toString());
    }

    @Test
    public void testLengh() {
        assertEquals(5, ints.length());
    }

    @Test
    public void testFoldLeft() {
        assertEquals("(1,(2,(3,(4,(5,Nil)))))", ints.foldLeft(NIL, b -> a -> b.cons(a)).toString());
        assertEquals("(1,(2,(3,(4,(5,Nil)))))", ints.foldLeftViaFoldRight(NIL, b -> a -> b.cons(a)).toString());
    }

    @Test
    public void testReverse() {
        assertEquals("(5,(4,(3,(2,(1,Nil)))))", ints.reverse().toString());
//        assertEquals("(5,(4,(3,(2,(1,Nil)))))", ints.reverseViaFoldLeft().toString());
    }

    @Test
    public void testFlatten() {
        assertEquals("(1,(2,(3,(4,(5,(6,Nil))))))", List.flatten(nested).toString());
    }

    @Test
    public void testMap() {
        assertEquals("(2,(4,(6,(8,(10,Nil)))))", ints.map(a -> a * 2).toString());
        assertEquals("(1,(2,(3,(4,(5,Nil)))))", ints.map(a -> a.toString()).toString());
    }

    @Test
    public void testFilter() {
        assertEquals("(1,(2,Nil))", ints.filter(a -> a < 3).toString());
        assertEquals("(1,(2,Nil))", ints.filterViaFlatMap(a -> a < 3).toString());
    }

    @Test
    public void testFlatMap() {
        assertEquals("(1,(-1,(2,(-2,(3,(-3,(4,(-4,(5,(-5,Nil))))))))))", ints.flatMap(a -> List.list(a, -a)).toString());
    }
}