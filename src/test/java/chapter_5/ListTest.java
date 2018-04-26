package chapter_5;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {
    private static final List<Integer> ints = List.list(1, 2, 3, 4, 5);

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

}