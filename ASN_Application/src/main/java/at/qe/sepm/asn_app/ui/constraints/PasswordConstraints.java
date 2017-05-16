package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.PasswordConstraintException;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at> on 13.05.17.
 *
 * All of the constraints contained in PaswordConstraints get checked in PasswordConstraintsTest.
 * Parent passwords are handled differently because for them there is also a constraint that
 * the name of one of their children must not be a substring of their password.
 */
public class PasswordConstraints {

    /**
     * This method gets called for all password who are not associated with a Parent. It calls all necessary
     * password constraints methods and throws a PasswordConstraintException if a constraint is violated.
     * @param password The password to be validated.
     * @throws PasswordConstraintException if a password constraint is violated.
     */
    public static void checkPasswordConstraints(String password) throws PasswordConstraintException {
        if (checkPasswordComplexity(password) == false) {
            throw new PasswordConstraintException("The password doesn't match the criteria.");
        }
    }


    /**
     * Since checkPasswordChildName(String password) is only called for non-parents there is need of a
     * separate method in which the password constraints for parents are validated.
     * @param password The password to be validated.
     * @param parent The parent of which the names of their children get validated.
     * @throws PasswordConstraintException if a password constraint is violated.
     */
    public static void checkPasswordConstraintsParent(String password, Parent parent) throws PasswordConstraintException {
        if (checkPasswordComplexity(password) == false) {
            throw new PasswordConstraintException("The password doesn't match the criteria.");
        }
        else if(!checkPasswordChildName(password, parent)) {
            throw new PasswordConstraintException("The name of a parents own child is not allowed as a substring in the password.");
        }
    }


    /**
     * Validates various password constraints:
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     * @param password The password to be validated.
     * @return iff all constraints are satisfied.
     * @see <a href="http://stackoverflow.com/questions/3802192/regexp-java-for-password-validation">http://stackoverflow.com/questions/3802192/regexp-java-for-password-validation</a>
     */
    public static boolean checkPasswordComplexity(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(pattern);
    }


    /**
     * To add a tad more security to the password it is not allowed for a parent to have a password which contains
     * the name of one of their children as a substring.
     * @param password The password to be validated.
     * @param parent The parent of which the names of their children get validated.
     * @return iff the name of a child of a parent is no substring of the password.
     */
    public static boolean checkPasswordChildName(String password, Parent parent) {
        for (Child child : parent.getChildren()) {
            if (password.toLowerCase().contains(child.getFirstName().toLowerCase()) ||
                    password.toLowerCase().contains(child.getLastName().toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
