package experiment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class Dates {
    static final DateFormat DDF = new SimpleDateFormat("M/d/yy");
    private static final DateFormat SDF = new SimpleDateFormat("MMddyy");
    static final DateFormat LDF = new SimpleDateFormat("MMMM d, yyyy");
    private static final int DATE = 1;
    private static final int PUBLISH_TIME = 12;
    private static final int DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    static Date getPublishDate(String[] elements) throws ParseException {
        if (isEvent(elements)) {
            return null;
        }
        return previousDate(SDF.parse(elements[DATE]));
    }

    static String getChallengeDate(String[] elements) throws ParseException {
        if (isEvent(elements)) {
            return LDF.format(new Date());
        }
        return LDF.format(SDF.parse(elements[DATE]));
    }

    static String getChallengeDateForDesc(String[] elements) throws ParseException {
        if (isEvent(elements)) {
            return DDF.format(new Date());
        }
        return DDF.format(SDF.parse(elements[DATE]));
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
