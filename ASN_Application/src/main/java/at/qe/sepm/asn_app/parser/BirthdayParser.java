package at.qe.sepm.asn_app.parser;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by zerus on 09.05.17.
 *
 * BirthdayParser holds a method in which a birthday represented as a String gets parsed to the amount of second from
 * the birthday to now.
 * @see at.qe.sepm.asn_app.ui.constraints.ChildConstraints
 * @see at.qe.sepm.asn_app.ui.constraints.ParentConstraints
 */
public class BirthdayParser {

    /**
     * The birthday String gets parsed to a LocalDateTime of which then the seconds until now are calculated and
     * returned as a <code>long</code>.
     * The String has to be casted to a LocalDateTime because it is not possible to get second, milliseconds etc. out
     * of LocalDate.
     * @param birthday The birthday to be converted to seconds.
     * @return The amount of seconds from birthday to now.
     * @see DateTimeFormatter
     * @see LocalDateTime
     */
    public static long parseBirthdayToLong(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime birthdayTmp = LocalDateTime.parse(birthday + " 00:00", formatter);
        return birthdayTmp.toEpochSecond(ZoneOffset.UTC);
    }
}
