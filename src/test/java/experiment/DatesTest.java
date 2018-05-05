package experiment;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class DatesTest {
    private static final String[] mockElements = {"abc", "123118"};
    private static final String[] mockEvent = {"Event", "123118"};
    private static final String today_l = Dates.LDF.format(new Date());
    private static final String today_s = Dates.DDF.format(new Date());

    @Test
    public void testGetPublishDate() throws ParseException {
        assertEquals("Sun Dec 30 12:00:00 CST 2018", Dates.getPublishDate(mockElements).toString());
        assertNull(Dates.getPublishDate(mockEvent));
    }

    @Test
    public void testGetChallengeDate() throws ParseException {
        assertEquals("December 31, 2018", Dates.getChallengeDate(mockElements));
        assertEquals(today_l, Dates.getChallengeDate(mockEvent));
    }

    @Test
    public void testGetChallengeDateForDesc() throws ParseException {
        assertEquals("12/31/18", Dates.getChallengeDateForDesc(mockElements));
        assertEquals(today_s, Dates.getChallengeDateForDesc(mockEvent));
    }
}