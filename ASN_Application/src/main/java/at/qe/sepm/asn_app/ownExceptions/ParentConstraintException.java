package at.qe.sepm.asn_app.ownExceptions;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 26.04.17.
 */
public class ParentConstraintException extends Exception {

    public ParentConstraintException() {
        super();
    }

    public ParentConstraintException(String message) {
        super(message);
    }

}
