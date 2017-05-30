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
