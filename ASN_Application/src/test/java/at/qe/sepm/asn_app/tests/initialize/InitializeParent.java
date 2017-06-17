package at.qe.sepm.asn_app.tests.initialize;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 14:49 CEST.
 */
public class InitializeParent {
    
    public static Parent initialize1() {
        Set<Child> children = new HashSet<>();
        Set<Task> tasks = new HashSet<>();

        Parent parent = new Parent();
        parent.setBirthday("22/05/1990");
        parent.setChildren(children);
        parent.setEmail("ParentEmail1@google.com");
        parent.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent.setFirstName("ParentFirstName1");
        parent.setImgName("ParentImgName1");
        parent.setLastName("ParentLastName1");
        parent.setLocation("ParentLocation1");
        parent.setNotification(true);
        parent.setPassword("passwd");
        parent.setPhoneNumber("0123456789");
        parent.setPostcode("6020");
        parent.setReligion(Religion.CHRISTENTUM);
        parent.setStatus(true);
        parent.setStreetName("ParentStreetName1");
        parent.setTasks(tasks);
        parent.setUsername("ParentUsername1");
        parent.setUserRole(UserRole.PARENT);
        
        return parent;
    }


    public static Parent initialize2() {
        Set<Child> children = new HashSet<>();
        Set<Task> tasks = new HashSet<>();

        Parent parent = new Parent();
        parent.setBirthday("07/11/1978");
        parent.setChildren(children);
        parent.setEmail("ParentEmail2@google.com");
        parent.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent.setFirstName("ParentFirstName2");
        parent.setImgName("ParentImgName2");
        parent.setLastName("ParentLastName2");
        parent.setLocation("ParentLocation2");
        parent.setNotification(true);
        parent.setPassword("passwd");
        parent.setPhoneNumber("0123456789");
        parent.setPostcode("6020");
        parent.setReligion(Religion.CHRISTENTUM);
        parent.setStatus(true);
        parent.setStreetName("ParentStreetName2");
        parent.setTasks(tasks);
        parent.setUsername("ParentUsername2");
        parent.setUserRole(UserRole.PARENT);

        return parent;
    }
}
