package at.qe.sepm.asn_app.ui.constraints;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.PasswordConstraintException;

/**
 * Created by zerus on 13.05.17.
 */
public class PasswordConstraints {

    public static void checkPasswordConstraints(String password) throws PasswordConstraintException {
        if (checkPasswordComplexity(password) == false) {
            throw new PasswordConstraintException("The password doesn't match the criteria.");
        }
    }


    /**
     * Since checkPasswordChildName(String password, Parent parent) is only called for parents there is need of a
     * separate
     * @param password
     * @param parent
     * @throws PasswordConstraintException
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
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     * @param password
     * @return iff all constraints are satisfied.
     * @see <a href="http://stackoverflow.com/questions/3802192/regexp-java-for-password-validation">http://stackoverflow.com/questions/3802192/regexp-java-for-password-validation</a>
     */
    public static boolean checkPasswordComplexity(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(pattern);
    }


    /**
     *
     * @param password
     * @return iff the name of a child of a parent is no substring of the password.
     */
    public static boolean checkPasswordChildName(String password, Parent parent) {
        for (Child child : parent.getListChildren()) {
            if (password.toLowerCase().contains(child.getFirstName().toLowerCase()) ||
                    password.toLowerCase().contains(child.getLastName().toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
