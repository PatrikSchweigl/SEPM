package at.qe.sepm.asn_app.parser;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by zerus on 09.05.17.
 */
public class BirthdayParser {
    
    /**
     * The birthday String gets parsed to a LocalDateTime of which then the seconds until now are calculated and
     * returned as a <code>long</code>.
     * @param birthday The birthday to be converted to seconds.
     * @return The amount of seconds from birthday to now.
     */
    public static long parseBirthdayToLong(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime birthdayTmp = LocalDateTime.parse(birthday + " 00:00", formatter);
        return birthdayTmp.toEpochSecond(ZoneOffset.UTC);
    }

}
