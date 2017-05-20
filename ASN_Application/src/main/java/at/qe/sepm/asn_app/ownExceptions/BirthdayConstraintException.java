package at.qe.sepm.asn_app.ownExceptions;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 22.04.17.
 */
public class BirthdayConstraintException extends Exception {

    public BirthdayConstraintException() {
        super();
    }

    public BirthdayConstraintException(String message) {
        super(message);
    }

}
