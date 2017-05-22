package at.qe.sepm.asn_app.utils;

import java.time.LocalDate;

/**
 * Created by Lukas Aukenthaler on 22.05.2017.
 */
public class DateUtils {
    /** getNextWeek
     *
     * @return array containing LocalDates for the next week (mon-fri)
     */
    public static LocalDate[] getNextWeek(){
        LocalDate now = LocalDate.now();
        //TODO remove holidays
        int x = now.getDayOfWeek().getValue();
        x = 8 - x;
        LocalDate[] ret = new LocalDate[5];
        for(int i = 0; i < 5; i++){
            ret[i] = now.plusDays(x + i);
        }

        return ret;
    }

}
