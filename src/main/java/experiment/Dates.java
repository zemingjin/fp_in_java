package experiment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.function.Supplier;

class Dates {
    private static final int DATE = 1;
    private static final int PUBLISH_HOUR = 12;
    private static final int PUBLISH_MINUTE = 0;
    private static final DateTimeFormatter SDF = DateTimeFormatter.ofPattern("MMddyy");
    static final DateTimeFormatter DDF = DateTimeFormatter.ofPattern("M/d/yy");
    static final DateTimeFormatter LDF = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    static LocalDateTime getPublishDate(String[] elements) {
        return getDate(elements, () -> null, Dates::previousDate);
    }

    static String getChallengeDate(String[] elements) {
        return getDate(elements, () -> LocalDate.now().format(LDF), LDF::format);
    }

    static String getChallengeDateForDesc(String[] elements) {
        return getDate(elements, () -> LocalDate.now().format(DDF), DDF::format);
    }

    private static <A> A getDate(String[] elements, Supplier<A> eventDate, Function<LocalDate, A> otherDate) {
        if (isEvent(elements)) {
            return eventDate.get();
        }
        return otherDate.apply(LocalDate.parse(elements[DATE], SDF));
    }

    private static boolean isEvent(String[] elements) {
        return elements.length > 0 && elements[0].startsWith("Event");
    }

    private static LocalDateTime previousDate(LocalDate date) {
         return date.minusDays(1).atTime(PUBLISH_HOUR, PUBLISH_MINUTE);
    }
}
