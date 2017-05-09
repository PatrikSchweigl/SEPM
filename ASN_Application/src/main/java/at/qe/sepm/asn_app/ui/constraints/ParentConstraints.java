package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.parser.BirthdayParser;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by zerus on 08.05.17.
 */
public class ParentConstraints {

    private Parent parent;

    public ParentConstraints(Parent parent) {
        this.parent = parent;
    }

    public void checkBirthdayConstraints() throws BirthdayConstraintException {
        long dateNow = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long birthday = BirthdayParser.parseBirthdayToLong(parent.getBirthday());
        int ageDays = (int)((dateNow-birthday)/60/60/24);
        System.out.println("date now: " + dateNow);
        System.out.println("birthday: " + birthday);
        System.out.println("age in days: " + ageDays);

        // Check if the parent is younger than 14 years.
        if (ageDays < 14*365) {
            throw new BirthdayConstraintException("Too young");
        }

        // Check if the parent is older than 99 years.
        if (ageDays > (99*365)) {
            throw new BirthdayConstraintException("Too old");
        }
    }
}
