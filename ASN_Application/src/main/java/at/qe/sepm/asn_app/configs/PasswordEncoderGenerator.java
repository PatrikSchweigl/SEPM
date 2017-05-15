package at.qe.sepm.asn_app.configs;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 22.03.2017
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
    //just to get a given password hashed+salted and to check whether
    //the hashed+salted password is different after each encode() call
    public static void main(String[] args) {

        int i = 0;
        while (i < 10) {
            String password = "passwd";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            System.out.println(hashedPassword);
            i++;
        }

    }
}