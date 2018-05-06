package experiment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Dates {
    private static final int DATE = 1;
    private static final int PUBLISH_HOUR = 12;
    private static final int PUBLISH_MINUTE = 0;
    static final DateTimeFormatter DDF = DateTimeFormatter.ofPattern("M/d/yy");
    private static final DateTimeFormatter SDF = DateTimeFormatter.ofPattern("MMddyy");
    static final DateTimeFormatter LDF = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    static LocalDateTime getPublishDate(String[] elements) {
        if (isEvent(elements)) {
            return null;
        }
        return previousDate(LocalDate.parse(elements[DATE], SDF));
    }

    static String getCampaignDate(String[] elements) {
        if (isEvent(elements)) {
            return LocalDate.now().format(LDF);
        }
        return LocalDate.parse(elements[DATE], SDF).format(LDF);
    }

    static String getCampaignDateForDesc(String[] elements) {
        if (isEvent(elements)) {
            return LocalDate.now().format(DDF);
        }
        return LocalDate.parse(elements[DATE], SDF).format(DDF);
    }

    private static boolean isEvent(String[] elements) {
        return elements.length > 0 && elements[0].startsWith("Event");
    }

    private static LocalDateTime previousDate(LocalDate date) {
         return date.minusDays(1).atTime(PUBLISH_HOUR, PUBLISH_MINUTE);
    }
}
