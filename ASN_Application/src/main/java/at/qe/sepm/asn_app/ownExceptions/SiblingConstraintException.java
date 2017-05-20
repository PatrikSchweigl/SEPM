package at.qe.sepm.asn_app.ownExceptions;


/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 22.04.17.
 */
public class SiblingConstraintException extends Exception {

    public SiblingConstraintException() {
        super();
    }

    public SiblingConstraintException(String message) {
        super(message);
    }

}
