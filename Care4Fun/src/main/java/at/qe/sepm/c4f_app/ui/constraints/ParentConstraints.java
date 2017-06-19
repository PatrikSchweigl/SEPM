package at.qe.sepm.c4f_app.ui.constraints;

import at.qe.sepm.c4f_app.models.referencePerson.Parent;
import at.qe.sepm.c4f_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.c4f_app.parser.BirthdayParser;
import at.qe.sepm.c4f_app.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 08.05.17.
 */
@Component
@Scope("application")
public class ParentConstraints {

    @Autowired
    private ParentService parentService;

    /**
     * The main method of ParentConstraints. From here on all constraints
     * get checked.
     *
     * @param parent the parent of which the constraints are to be checked.
     * @return true iff no constraints are violated.
     */
    public boolean checkConstraints(Parent parent) throws BirthdayConstraintException {
        if(!checkBirthdayConstraints(parent)) {
            throw new BirthdayConstraintException();
        }
        return true;
    }


    /**
     * Checks if a parent is between 14 and 99 years old.
     *
     * @param parent the parent of which the birthday is too be checked.
     * @return <code>true</code> iff the parent is between 14 and 99 years old; <code>false</code> otherwise.
     */
    public boolean checkBirthdayConstraints(Parent parent)  {
        long dateNow = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long birthday = BirthdayParser.parseBirthdayToLong(parent.getBirthday());
        int ageDays = (int)((dateNow-birthday)/60/60/24);
        System.out.println("date now: " + dateNow);
        System.out.println("birthday: " + birthday);
        System.out.println("age in days: " + ageDays);

        // Check if the parent is younger than 14 years.
        if (ageDays < 14*365) {
            System.out.println("Too young");
            return false;
        }

        // Check if the parent is older than 99 years.
        if (ageDays > (99*365)) {
            System.out.println("Too old");
            return false;
        }
        return true;
    }


    /**
     * @param parent check if this parent already exists in the database
     * @return <code>true</code> if the parent already exists in the database; <code>false</code> otherwise
     */
    public boolean alreadyExists(Parent parent) {
        Collection<Parent> parents = parentService.getAllParents();
        for (Parent p : parents) {
            if (p.equals(parent)) {
                return true;
            }
        }
        return false;
    }
}
