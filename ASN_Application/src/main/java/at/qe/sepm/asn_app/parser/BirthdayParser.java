package at.qe.sepm.asn_app.parser;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by zerus on 09.05.17.
 */
public class BirthdayParser {

    public static long parseBirthdayToLong(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime birthdayTmp = LocalDateTime.parse(birthday + " 00:00", formatter);
        return birthdayTmp.toEpochSecond(ZoneOffset.UTC);
    }

}
