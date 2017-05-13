package at.qe.sepm.asn_app.ui.constraints;

/**
 * Created by zerus on 13.05.17.
 */
public class PasswordConstraints {

    public static void checkPasswordConstraints(String password) {
        try {
            if (checkPasswordLength(password) == false) {
                throw new Exception();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkPasswordLength(String password) {
        if (password.length() < 8 || password.length() > 21) {
            return false;
        }
        return true;
    }
}
