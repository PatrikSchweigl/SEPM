package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.parser.BirthdayParser;
import at.qe.sepm.asn_app.services.ParentService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;

/**
 * Created by zerus on 08.05.17.
 */
public class ParentConstraints {

    private Parent parent;
    private ParentService parentService;

    public ParentConstraints(Parent parent) {
        this.parent = parent;
        parentService = new ParentService();
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


    /**
     * @return true if the parent already exists in the database; false otherwise
     */
    public boolean alreadyExists() {
        Collection<Parent> parents = parentService.getAllParents();
        for (Parent p : parents) {
            if (p.equals(parent)) {
                return true;
            }
        }
        return false;
    }
}
