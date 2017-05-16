package at.qe.sepm.asn_app.ownExceptions;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at> on 13.05.17.
 */
public class PasswordConstraintException extends Exception {

    public PasswordConstraintException() {
        super();
    }

    public PasswordConstraintException(String message) {
        super(message);
    }

}
