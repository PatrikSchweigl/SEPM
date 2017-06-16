package at.qe.sepm.asn_app.tests.constraints;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.ParentConstraintException;
import at.qe.sepm.asn_app.repositories.ParentRepository;
import at.qe.sepm.asn_app.services.ParentService;
import at.qe.sepm.asn_app.ui.constraints.ParentConstraints;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 08.05.17.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParentConstraintsTest {

    private Child child;
    //private Child child2;
    //private Child child3;
    //private Child child4;
    //private Child child5;
    //private ArrayList<Child> listChildren;
    //private Sibling sibling1;
    //private Sibling sibling2;
    //private Sibling sibling3;
    //private ArrayList<Sibling> listSiblings;
    private Parent parent;
    //private ArrayList<Parent> listParents;
    //private ParentConstraints parentConstraints;
    //private ParentService parentService;

    @Autowired
    ParentRepository parentRepository;
    @Autowired
    ParentService parentService;
    @Autowired
    ParentConstraints parentConstraints;

    /**
     * Initialize every attribute with static values.
     */
    @Before
    public void initialize() {
        Set<Child> children = new HashSet<>();
        //Set<Child> parentListChildren2 = new HashSet<>();
        //Set<Child> parentListChildren3 = new HashSet<>();
        Set<Task> tasks = new HashSet<>();
        //Set<Task> parentListTasks2 = new HashSet<>();
        //Set<Task> parentListTasks3 = new HashSet<>();

        parent = new Parent();
        parent.setUsername("");
        parent.setPassword("passwd");
        parent.setFirstName("ParentFirstName1");
        parent.setLastName("ParentLastName1");
        parent.setLocation("ParentLocation1");
        parent.setStreetName("ParentStreetName1");
        parent.setPostcode("6020");
        parent.setBirthday("24/05/1980");
        parent.setEmail("ParentEmail1@google.com");
        parent.setImgName("ParentImgName1");
        parent.setUserRole(UserRole.PARENT);
        parent.setReligion(Religion.ISLAM);
        parent.setPhoneNumber("4839014056");
        parent.setNotification(true);
        parent.setChildren(children);
        parent.setTasks(tasks);
        parent.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent.setStatus(true);

        // Having a '0' in front of the month could maybe be a problem because usually a 0 in front of a number means oct-numbers
        //listParents = new ArrayList<>();
        /*
        String username, String password, String firstName,
                  String lastName, String location, String streetName,
                  String postcode, String birthday, String email,
                  UserRole userRole, String imgName, Set<Child> children,
                  Set<Task> tasks, FamilyStatus familyStatus, boolean status
         */
        //listParents.add(parent1 = new Parent("4", "passwd", "ParentFirstName1", "ParentLastName1", "ParentLocation1",
                                                //"ParentStreetName1", "ParentPostcode1", "24/05/1980","ParentEmail1@google.com",
                                                //UserRole.PARENT, "ParentImageName1", parentListChildren1, parentListTasks1,
                //FamilyStatus.VERHEIRATET, true));
        //listParents.add(parent1 = new Parent("", "ParentUserName1", "ParentFirstName1", "ParentLastName1", "ParentLocation1", "ParentStreetName1", "ParentPostcode1", UserRole.PARENT, "ParentImgName1", parentListChildren1, parentListTasks1, FamilyStatus.VERHEIRATET, true, "24/05/1980"));
        //listParents.add(parent2 = new Parent("", "ParentUserName2", "ParentFirstName2", "ParentLastName2", "ParentLocation2", "ParentStreetName2", "ParentPostcode2", UserRole.PARENT, "ParentImgName2", parentListChildren2, parentListTasks2, FamilyStatus.GESCHIEDEN, true, "11/11/2003"));  // Too young
        //listParents.add(parent3 = new Parent("", "ParentUserName3", "ParentFirstName3", "ParentLastName3", "ParentLocation3", "ParentStreetName3", "ParentPostcode3", UserRole.PARENT, "ParentImgName3", parentListChildren3, parentListTasks3, FamilyStatus.LEDIG, true, "30/04/1918"));   // Too old
        //listParents.add(parent4 = new Parent("", "ParentUserName3", "ParentFirstName3", "ParentLastName3", "ParentLocation3", "ParentStreetName3", "ParentPostcode3", UserRole.PARENT, "ParentImgName3", parentListChildren3, parentListTasks3, FamilyStatus.LEDIG, true, "30/04/1918"));   // Same as parent3
        //parentService = new ParentService();

        /*
        listChildren = new ArrayList<>();
        listChildren.add(child1 = new Child("FirstName1", "LastName1", "03/05/2015", "ImageName1", Gender.MAENNLICH, parent1));
        listChildren.add(child2 = new Child("FirstName2", "LastName2", "04/04/2014", "ImageName2", Gender.WEIBLICH, parent1));     // Too old
        listChildren.add(child3 = new Child("FirstName1", "LastName1", "03/05/2015", "ImageName3", Gender.MAENNLICH, parent2));
        listChildren.add(child4 = new Child("FirstName4", "LastName4", "21/02/2017", "ImageName4", Gender.WEIBLICH, parent2));    // Too young
        listChildren.add(child5 = new Child("FirstName5", "LastName5", "03/05/2015", "ImageName5", Gender.MAENNLICH, parent3));*/
    }


    /**
     * No constraint violation
     */
    @Test
    public void checkBirthdayConstraintsTest1() throws BirthdayConstraintException {
        ParentConstraints.checkBirthdayConstraints(parent);
    }


    /**
     * Check for the violation of the constraint that a parent must not be younger than 14 years.
     *
     * @throws BirthdayConstraintException if the birthday constraints have been violated.
     */
    @Test
    public void checkBirthdayConstraintsTest2() throws BirthdayConstraintException {
        assertFalse(ParentConstraints.checkBirthdayConstraints(parent));
    }


    /**
     * Check for the violation of the constraint that a parent must not be older than 99 years.
     *
     * @throws BirthdayConstraintException if the birthday constraints have been violated. 
     */
    @Test
    public void checkBirthdayConstraintsTest3() throws BirthdayConstraintException {
        assertFalse(ParentConstraints.checkBirthdayConstraints(parent));
    }


    @Test
    public void alreadyExistsTest() {
        //parentService.saveParent(parent1);
        //parent3 = parentRepository.save(parent3);
        //parentRepository.save(parent3);
        //parentRepository.save(parent);
        //assertTrue(ParentConstraints.alreadyExists(parent1));
        assertTrue(parentConstraints.alreadyExists(parent));
    }


    /**
     * Set every attribute to null so it is assured that every test works with clean attributes.
     */
    @After
    public void cleanUp() {
        /*
        for (Child c : listChildren) {
            c = null;
        }
        listChildren = null;

        for (Sibling s : listSiblings) {
            s = null;
        }
        listSiblings = null;

        for (Parent p : listParents) {
            p = null;
        }
        listParents = null;
        */

        //parentConstraints = null;
        //parentService = null;
    }
}
