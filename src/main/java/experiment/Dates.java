package experiment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

class Dates {
    static final DateFormat DDF = new SimpleDateFormat("M/d/yy");
    private static final DateFormat SDF = new SimpleDateFormat("MMddyy");
    static final DateFormat LDF = new SimpleDateFormat("MMMM d, yyyy");
    private static final int DATE = 1;
    private static final int PUBLISH_TIME = 12;
    private static final int DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    static Date getPublishDate(String[] elements) throws ParseException {
        return getDate(elements, () -> null, Dates::previousDate);
    }

    static String getChallengeDate(String[] elements) throws ParseException {
        return getDate(elements, () -> LDF.format(new Date()), LDF::format);
    }

    static String getChallengeDateForDesc(String[] elements) throws ParseException {
        return getDate(elements, () -> DDF.format(new Date()), DDF::format);
    }

    private static <A> A getDate(String[] elements, Supplier<A> eventDate, Function<Date, A> otherDate)
            throws ParseException {
        if (isEvent(elements)) {
            return eventDate.get();
        }
        return otherDate.apply(SDF.parse(elements[DATE]));
    }

    private static boolean isEvent(String[] elements) {
        return elements.length > 0 && elements[0].startsWith("Event");
    }

    private static Date previousDate(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date(date.getTime() - DAY_IN_MILLIS));
        calendar.set(Calendar.HOUR_OF_DAY, PUBLISH_TIME);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
