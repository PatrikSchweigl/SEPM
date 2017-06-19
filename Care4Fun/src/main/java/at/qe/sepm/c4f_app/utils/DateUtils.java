package at.qe.sepm.c4f_app.utils;

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
    /** getNextWeek
     *
     * @return array containing LocalDates for the next week (mon-fri)
     */
    @Deprecated
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
    @Deprecated
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
    public static Date[] getWeek(int j){
        LocalDate now = LocalDate.now();

        int x = now.getDayOfWeek().getValue();
        x = 1 + j*7 - x;
        Date[] ret = new Date[5];
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        for(int i = 0; i < 5; i++){
            //TODO remove holidays
            ret[i] = Date.from(now.plusDays(x + i).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            //System.err.println(ret[i]);

            cal.setTime(ret[i]);

            int hrs = cal.get(Calendar.HOUR_OF_DAY) - 6;
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            int milli = cal.get(Calendar.MILLISECOND);

            ret[i].setTime(ret[i].getTime()-(min*60*1000)-(hrs*60*60*1000)-(sec*1000)-milli);
        }

        return ret;
    }

    /** primefaceCalendarToStr
     *
     * util function to convert the pattern obtained via primefaces calendar into our pattern
     *
     * @param pfs
     *      primefaces string to convert
     *      a typical string looks like this
     *      WEEKDAY MONTH DAY TIME TIMEZONE YEAR
     * @return
     *      formatted string YYYY/MM/DD
     */

    public static int getLastDay(int month, int year){
        GregorianCalendar cal = new GregorianCalendar();
        switch(month){
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11: return 31;
            case 1: return cal.isLeapYear(year) ? 29 : 28;
            default: return 30;
        }
    }
    public static String primefaceCalendarToStr(String pfs){
        String d = "ERROR", m ="ERROR", y = "ERROR";
        String[] strings = pfs.split(" ");
        String s = strings[0].toLowerCase();
        if(!(s.equals("mon") || (s.equals("tue")) || (s.equals("wed"))||s.equals("thu")||s.equals("fri"))){
            return pfs;
        }
        switch(strings[1].toLowerCase()){
            case "jan" :
                m = "01";
                break;
            case "feb" :
                m = "02";
                break;
            case "mar" :
                m = "03";
                break;
            case "apr" :
                m = "04";
                break;
            case "may" :
                m = "05";
                break;
            case "jun" :
                m = "06";
                break;
            case "jul" :
                m = "07";
                break;
            case "aug" :
                m = "08";
                break;
            case "sep" :
                m = "09";
                break;
            case "oct" :
                m = "10";
                break;
            case "nov" :
                m = "11";
                break;
            case "dec" :
                m = "12";
                break;
            default:
                m ="ERROR";
                break;
        }
        if (strings.length > 2) {
            d = strings[2];
        }
        if (strings.length > 5) {
            y = strings[5];
        }
        return y + "/" + m + "/" + d;
    }
}
