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

        int x = now.getDayOfWeek().getValue();
        x = 8 - x;
        LocalDate[] ret = new LocalDate[5];
        for(int i = 0; i < 5; i++){
            //TODO remove holidays
            ret[i] = now.plusDays(x + i);
        }

        return ret;
    }
    /** getCurrentWeek
     *
     * @return array containing LocalDates for the current week (mon-fri)
     */
    public static LocalDate[] getCurrentWeek(){
        LocalDate now = LocalDate.now();

        int x = now.getDayOfWeek().getValue();
        x = 1 - x;
        LocalDate[] ret = new LocalDate[5];
        for(int i = 0; i < 5; i++){
            //TODO remove holidays
            ret[i] = now.plusDays(x + i);
        }

        return ret;
    }

    /** getWeek
     * @param j
     *  determines the week you want to retrieve, 0 is the current, 1 is the next one, etc.
     * @return array containing string represantations of LocalDates for the selected week (mon-fri)
     */
    public static String[] getWeekToString(int j){
        LocalDate now = LocalDate.now();

        int x = now.getDayOfWeek().getValue();
        x = 1 + j*7 - x;
        String[] ret = new String[5];
        for(int i = 0; i < 5; i++){
            //TODO remove holidays
            ret[i] = now.plusDays(x + i).toString();
            System.err.println(ret[i]);
        }

        return ret;
    }

}
