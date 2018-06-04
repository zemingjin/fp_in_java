package chapter7;

import org.junit.Test;

import static org.junit.Assert.*;

public class ToonTest {
    private static Toon mockNoEmail = new Toon("Ming", "Jin");
    private static Toon mockWithEmail = new Toon("Ming", "Jin", "ming.jin@email.com");
    @Test
    public void test() {
        assertEquals("Empty()", mockNoEmail.getEmail().toString());
        assertEquals("Success(ming.jin@email.com)", mockWithEmail.getEmail().toString());
    }

}