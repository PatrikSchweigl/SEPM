package at.qe.sepm.asn_app.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.GregorianCalendar;

/**
 * Created by Lukas Aukenthaler on 22.05.2017.
 */
public class DateUtils {
    /**
     * getNextWeek
     *
     * @return array containing LocalDates for the next week (mon-fri)
     */
    @Deprecated
    public static LocalDate[] getNextWeek() {
        LocalDate now = LocalDate.now();

        int x = now.getDayOfWeek().getValue();
        x = 8 - x;
        LocalDate[] ret = new LocalDate[5];
        for (int i = 0; i < 5; i++) {
            //TODO remove holidays
            ret[i] = now.plusDays(x + i);
        }

        return ret;
    }

    /**
     * getCurrentWeek
     *
     * @return array containing LocalDates for the current week (mon-fri)
     */
    @Deprecated
    public static LocalDate[] getCurrentWeek() {
        LocalDate now = LocalDate.now();

        int x = now.getDayOfWeek().getValue();
        x = 1 - x;
        LocalDate[] ret = new LocalDate[5];
        for (int i = 0; i < 5; i++) {
            //TODO remove holidays
            ret[i] = now.plusDays(x + i);
        }

        return ret;
    }

    /**
     * getWeek
     *
     * @param j determines the week you want to retrieve, 0 is the current, 1 is the next one, etc.
     * @return array containing string represantations of LocalDates for the selected week (mon-fri)
     */
    public static Date[] getWeek(int j) {
        LocalDate now = LocalDate.now();

        int x = now.getDayOfWeek().getValue();
        x = 1 + j * 7 - x;
        Date[] ret = new Date[5];
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        for (int i = 0; i < 5; i++) {
            //TODO remove holidays
            ret[i] = Date.from(now.plusDays(x + i).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

            cal.setTime(ret[i]);

            int hrs = cal.get(Calendar.HOUR_OF_DAY) - 6;
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            int milli = cal.get(Calendar.MILLISECOND);

            ret[i].setTime(ret[i].getTime() - (min * 60 * 1000) - (hrs * 60 * 60 * 1000) - (sec * 1000) - milli);
        }

        return ret;
    }

}
