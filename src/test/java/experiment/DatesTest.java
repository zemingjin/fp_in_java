package experiment;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class DatesTest {
    private static final String[] mockElements = {"abc", "", "", "123118"};
    private static final String[] mockEvent = {"Event", "", "", "123118"};
    private static final String today_l = Dates.LDF.format(LocalDate.now());
    private static final String today_s = Dates.DDF.format(LocalDate.now());

    @Test
    public void testGetPublishDate() {
        Optional.ofNullable(Dates.getPublishDate(mockElements))
                .ifPresent(d -> assertEquals("December 30, 2018", Dates.LDF.format(d)));
        assertNull(Dates.getPublishDate(mockEvent));
    }

    @Test
    public void testCampaignDateDate() {
        assertEquals("December 31, 2018", Dates.getCampaignDate(mockElements));
        assertEquals(today_l, Dates.getCampaignDate(mockEvent));
    }

    @Test
    public void testCampaignDateForDesc() {
        assertEquals("12/31/18", Dates.getCampaignDateForDesc(mockElements));
        assertEquals(today_s, Dates.getCampaignDateForDesc(mockEvent));
    }
}