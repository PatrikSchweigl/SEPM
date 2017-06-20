package at.qe.sepm.asn_app.tests.initialize;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.general.Religion;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 15:43 CEST.
 */
public class InitializeUserData {

    public static UserData initialize1() {
        UserData userData = new UserData();
        userData.setBirthday("22/05/1989");
        userData.setEmail("UserDataEmail1@google.com");
        userData.setFirstName("UserDataFirstName1");
        userData.setLastName("UserDataLastName1");
        userData.setImgName("UserDataImgName1");
        userData.setLocation("UserDataLocation1");
        userData.setNotification(true);
        userData.setPassword("passwd");
        userData.setPhoneNumber("157890643");
        userData.setPostcode("6020");
        userData.setReligion(Religion.CHRISTENTUM);
        userData.setStreetName("UserDataStreetName1");
        userData.setUsername("UserDataUsername1");
        userData.setUserRole(UserRole.PARENT);
        return userData;
    }
    
    
    public static UserData initialize2() {
        UserData userData = new UserData();
        userData.setBirthday("07/03/1985");
        userData.setEmail("UserDataEmail2@google.com");
        userData.setFirstName("UserDataFirstName2");
        userData.setLastName("UserDataLastName2");
        userData.setImgName("UserDataImgName2");
        userData.setLocation("UserDataLocation2");
        userData.setNotification(true);
        userData.setPassword("passwd");
        userData.setPhoneNumber("9876543210");
        userData.setPostcode("6020");
        userData.setReligion(Religion.HINDUISMUS);
        userData.setStreetName("UserDataStreetName2");
        userData.setUsername("UserDataUsername2");
        userData.setUserRole(UserRole.EMPLOYEE);
        return userData;
    }
}
