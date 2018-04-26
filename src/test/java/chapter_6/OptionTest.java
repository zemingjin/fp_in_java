package chapter_6;

import org.junit.Test;

import static chapter_6.Option.*;
import static org.junit.Assert.*;

public class OptionTest {
    private static final Option<Integer> opt = some(1);
    @Test
    public void testFlatMap() {
        assertEquals("Some('1')", opt.flatMap(a -> some("'" + a.toString() + "'")).toString());
    }

    @Test
    public void testOrElse() {
        assertEquals("Some(1)", opt.orElse(() -> some(2)).toString());
        assertEquals("Some(2)", none().orElse(() -> some(2)).toString());
    }

    @Test
    public void testFilter() {
        assertEquals("Some(1)", opt.filter(a -> a > 0).toString());
        assertEquals("None", opt.filter(a -> a == 0).toString());
    }
}