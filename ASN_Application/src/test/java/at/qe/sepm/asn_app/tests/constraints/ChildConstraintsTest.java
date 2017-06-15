package at.qe.sepm.asn_app.tests.constraints;

import at.qe.sepm.asn_app.models.Gender;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Custody;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.models.nursery.Task;
import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import at.qe.sepm.asn_app.ownExceptions.BirthdayConstraintException;
import at.qe.sepm.asn_app.ownExceptions.ParentConstraintException;
import at.qe.sepm.asn_app.ownExceptions.SiblingConstraintException;
import at.qe.sepm.asn_app.tests.views.children.ChildServiceTest;
import at.qe.sepm.asn_app.ui.constraints.ChildConstraints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:51 CEST.
 */
public class ChildConstraintsTest {

    private Child child1;
    private Child child2;
    private Child child3;
    private Child child4;
    private Child child5;

    private Sibling sibling1;
    private Sibling sibling2;
    private Sibling sibling3;

    private Parent parent1;
    private Parent parent2;
    private Parent parent3;
    private Parent parent4;
    private Parent parent5;

    // TODO Check if a parent exists in the nursery
    // TODO check naming constraints (e.g numbers etc. )

    /**
     * To make sure that every test works with the same starting values all generally
     * needed attributes get initialized before each test. After each test all attributes
     * get set to null again in {@link ChildServiceTest#cleanUp()}.
     */
    @Before
    public void initialize() {
        // Having a '0' in front of the month could maybe be a problem because usually a 0 in front of a number means oct-numbers
        String allergies1 = "";
        String foodIntolerances1 = "";
        Set<Sibling> siblings1 = new HashSet<>();
        Set<Caregiver> caregivers1 = new HashSet<>();
        child1 = new Child();
        child1.setFirstName("ChildConstraintsFirstName1");
        child1.setLastName("ChildConstraintsLastName1");
        child1.setBirthday("03/05/2015");
        child1.setImgName("ChildConstraintsImgName1");
        child1.setGender(Gender.MAENNLICH);
        child1.setPrimaryParent(parent1);
        child1.setParent2(parent2);
        child1.setEmergencyNumber("478139232");
        child1.setAllergies(allergies1);
        child1.setFoodIntolerances(foodIntolerances1);
        child1.setSiblings(siblings1);
        child1.setCustody(Custody.BEIDE);
        child1.setReligion(Religion.CHRISTENTUM);
        child1.setCaregivers(caregivers1);

        String allergies2 = "";
        String foodIntolerances2 = "";
        Set<Sibling> siblings2 = new HashSet<>();
        Set<Caregiver> caregivers2 = new HashSet<>();
        child2 = new Child();
        child2.setFirstName("ChildConstraintsFirstName2");
        child2.setLastName("ChildConstraintsLastName2");
        child2.setBirthday("04/04/2014");
        child2.setImgName("ChildConstraintsImgName2");
        child2.setGender(Gender.WEIBLICH);
        child2.setPrimaryParent(parent3);
        child2.setParent2(parent4);
        child2.setEmergencyNumber("3184901840");
        child2.setAllergies(allergies2);
        child2.setFoodIntolerances(foodIntolerances2);
        child2.setSiblings(siblings2);
        child2.setCustody(Custody.BEIDE);
        child2.setReligion(Religion.BUDDHISMUS);
        child2.setCaregivers(caregivers2);

        // Same as child1
        child3 = new Child();
        child3.setFirstName("ChildConstraintsFirstName1");
        child3.setLastName("ChildConstraintsLastName1");
        child3.setBirthday("03/05/2015");
        child3.setImgName("ChildConstraintsImgName1");
        child3.setGender(Gender.MAENNLICH);
        child3.setPrimaryParent(parent1);
        child3.setParent2(parent2);
        child3.setEmergencyNumber("478139232");
        child3.setAllergies(allergies1);
        child3.setFoodIntolerances(foodIntolerances1);
        child3.setSiblings(siblings1);
        child3.setCustody(Custody.BEIDE);
        child3.setReligion(Religion.HINDUISMUS);
        child3.setCaregivers(caregivers1);

        String allergies4 = "";
        String foodIntolerances4 = "";
        Set<Sibling> siblings4 = new HashSet<>();
        Set<Caregiver> caregivers4 = new HashSet<>();
        child4 = new Child();
        child4.setFirstName("ChildConstraintsFirstName4");
        child4.setLastName("ChildConstraintsLastName4");
        child4.setBirthday("21/02/2017");
        child4.setImgName("ChildConstraintsImgName4");
        child4.setGender(Gender.WEIBLICH);
        child4.setPrimaryParent(parent3);
        child4.setParent2(parent5);
        child4.setEmergencyNumber("3184901840");
        child4.setAllergies(allergies4);
        child4.setFoodIntolerances(foodIntolerances4);
        child4.setSiblings(siblings4);
        child4.setCustody(Custody.MUTTER);
        child4.setReligion(Religion.ISLAM);
        child4.setCaregivers(caregivers4);

        String allergies5 = "";
        String foodIntolerances5 = "";
        Set<Sibling> siblings5 = new HashSet<>();
        Set<Caregiver> caregivers5 = new HashSet<>();
        child5 = new Child();
        child5.setFirstName("ChildConstraintsFirstName5");
        child5.setLastName("ChildConstraintsLastName5");
        child5.setBirthday("03/05/2015");
        child5.setImgName("ChildConstraintsImgName5");
        child5.setGender(Gender.WEIBLICH);
        child5.setPrimaryParent(parent4);
        child5.setParent2(parent5);
        child5.setEmergencyNumber("14551133086");
        child5.setAllergies(allergies5);
        child5.setFoodIntolerances(foodIntolerances5);
        child5.setSiblings(siblings5);
        child5.setCustody(Custody.MUTTER);
        child5.setReligion(Religion.ISLAM);
        child5.setCaregivers(caregivers5);

        sibling1 = new Sibling();
        sibling1.setFirstName(child1.getFirstName());
        sibling1.setLastName(child1.getLastName());
        sibling1.setBirthday(child1.getBirthday());

        sibling2 = new Sibling();
        sibling2.setFirstName(child2.getFirstName());
        sibling2.setLastName(child2.getLastName());
        sibling2.setBirthday(child2.getBirthday());

        sibling3 = new Sibling();
        sibling3.setFirstName(child3.getFirstName());
        sibling3.setLastName(child3.getLastName());
        sibling3.setBirthday(child3.getBirthday());

        // These variables are needed to initialize the parents.
        Set<Child> parentChildren1 = new HashSet<>();
        Set<Child> parentChildren2 = new HashSet<>();
        Set<Child> parentChildren3 = new HashSet<>();
        //Set<Child> parentChildren4 = new HashSet<>();
        Set<Child> parentChildren5 = new HashSet<>();
        Set<Task> parentTasks1 = new HashSet<>();
        Set<Task> parentTasks2 = new HashSet<>();
        Set<Task> parentTasks3 = new HashSet<>();
        //Set<Task> parentTasks4 = new HashSet<>();
        Set<Task> parentTasks5 = new HashSet<>();

        parent1 = new Parent();
        parent1.setUsername("");
        parent1.setPassword("passwd");
        parent1.setFirstName("ParentFirstName1");
        parent1.setLastName("ParentLastName1");
        parent1.setLocation("ParentLocation1");
        parent1.setStreetName("ParentStreetName1");
        parent1.setPostcode("6020");
        parent1.setBirthday("24/05/1980");
        parent1.setEmail("ParentEmail1@google.com");
        parent1.setImgName("ParentImgName1");
        parent1.setUserRole(UserRole.PARENT);
        parent1.setReligion(Religion.CHRISTENTUM);
        parent1.setPhoneNumber("13435155566");
        parent1.setNotification(true);
        parent1.setChildren(parentChildren1);
        parent1.setTasks(parentTasks1);
        parent1.setFamilyStatus(FamilyStatus.VERHEIRATET);
        parent1.setStatus(true);

        parent2 = new Parent();
        parent2.setUsername("");
        parent2.setPassword("passwd");
        parent2.setFirstName("ParentFirstName2");
        parent2.setLastName("ParentLastName2");
        parent2.setLocation("ParentLocation2");
        parent2.setStreetName("ParentStreetName2");
        parent2.setPostcode("6020");
        parent2.setBirthday("11/11/2003");
        parent2.setEmail("ParentEmail2@google.com");
        parent2.setImgName("ParentImgName2");
        parent2.setUserRole(UserRole.PARENT);
        parent2.setReligion(Religion.ATHEISMUS);
        parent2.setPhoneNumber("58259603434");
        parent2.setNotification(true);
        parent2.setChildren(parentChildren2);
        parent2.setTasks(parentTasks2);
        parent2.setFamilyStatus(FamilyStatus.GESCHIEDEN);
        parent2.setStatus(true);

        parent3 = new Parent();
        parent3.setUsername("");
        parent3.setPassword("passwd");
        parent3.setFirstName("ParentFirstName3");
        parent3.setLastName("ParentLastName3");
        parent3.setLocation("ParentLocation3");
        parent3.setStreetName("ParentStreetName3");
        parent3.setPostcode("6020");
        parent3.setBirthday("30/04/1918");
        parent3.setEmail("ParentEmail3@google.com");
        parent3.setImgName("ParentImgName3");
        parent3.setUserRole(UserRole.PARENT);
        parent3.setReligion(Religion.CHRISTENTUM);
        parent3.setPhoneNumber("2489602376");
        parent3.setNotification(true);
        parent3.setChildren(parentChildren3);
        parent3.setTasks(parentTasks3);
        parent3.setFamilyStatus(FamilyStatus.GESCHIEDEN);
        parent3.setStatus(true);

        // Identical to parent3
        parent4 = new Parent();
        parent4.setUsername("");
        parent4.setPassword("passwd");
        parent4.setFirstName("ParentFirstName3");
        parent4.setLastName("ParentLastName3");
        parent4.setLocation("ParentLocation3");
        parent4.setStreetName("ParentStreetName3");
        parent4.setPostcode("6020");
        parent4.setBirthday("30/04/1918");
        parent4.setEmail("ParentEmail3@google.com");
        parent4.setImgName("ParentImgName3");
        parent4.setUserRole(UserRole.PARENT);
        parent4.setReligion(Religion.CHRISTENTUM);
        parent4.setPhoneNumber("2489602376");
        parent4.setNotification(true);
        parent4.setChildren(parentChildren3);
        parent4.setTasks(parentTasks3);
        parent4.setFamilyStatus(FamilyStatus.GESCHIEDEN);
        parent4.setStatus(true);

        parent5 = new Parent();
        parent5.setUsername("");
        parent5.setPassword("passwd");
        parent5.setFirstName("ParentFirstName5");
        parent5.setLastName("ParentLastName5");
        parent5.setLocation("ParentLocation5");
        parent5.setStreetName("ParentStreetName5");
        parent5.setPostcode("6020");
        parent5.setBirthday("30/04/1918");
        parent5.setEmail("ParentEmail5@google.com");
        parent5.setImgName("ParentImgName5");
        parent5.setUserRole(UserRole.PARENT);
        parent5.setReligion(Religion.JUDENTUM);
        parent5.setPhoneNumber("713450973107");
        parent5.setNotification(true);
        parent5.setChildren(parentChildren5);
        parent5.setTasks(parentTasks5);
        parent5.setFamilyStatus(FamilyStatus.EINGETRAGENE_PARTNERSCHAFT);
        parent5.setStatus(true);
    }


    /**
     * Test birthdayConstraints for no violations.
     */
    @Test
    public void checkBirthdayConstraints1() throws BirthdayConstraintException {
        assertTrue(ChildConstraints.checkBirthdayConstraints(child1));
    }


    /**
     * Test the birthday constraints for a child that is too young.
     */
    @Test
    public void checkBirthdayConstraints2() throws BirthdayConstraintException {
        assertFalse(ChildConstraints.checkBirthdayConstraints(child4));
    }


    /**
     * Test the birthday constraints for a child that is too old.
     */
    @Test
    public void checkBirthdayConstraints3() throws BirthdayConstraintException {
        assertFalse(ChildConstraints.checkBirthdayConstraints(child2));
    }


    /**
     * Test parents constraints without any violation.
     */
    @Test
    public void checkParentsConstraints1() throws ParentConstraintException {
        //child1.setParent1(parent1);
        child1.setPrimaryParent(parent1);
        child1.setParent2(parent2);
        ChildConstraints.checkParentsConstraints(child1);
    }


    /**
     * It is not allowed to register children in the nursery if not at least one parent is registered beforehand.
     */
    @Test
    public void checkParentsConstraints2() throws ParentConstraintException {
        assertFalse(ChildConstraints.checkParentsConstraints(child1));
    }


    /**
     * Check the violation of the constraint that a child may not have the same parent twice.
     */
    @Test
    public void checkParentsConstraints3() throws ParentConstraintException {
        //child1.setParent1(parent1);
        child1.setPrimaryParent(parent1);
        child1.setParent2(parent1);
        assertFalse(ChildConstraints.checkParentsConstraints(child1));
    }


    /**
     * Test siblingsConstraints for no violation
     */
    @Test
    public void checkSiblingsConstraints1() throws SiblingConstraintException {
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling2);
        child1.setSiblings(siblingSet);
        assertTrue(ChildConstraints.checkSiblingsConstraints(child1));
    }


    /**
     * Test the violation of the constraint that a child can't be a sibling of itself.
     */
    @Test
    public void checkSiblingsConstraints2() throws SiblingConstraintException {
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling3);
        child1.setSiblings(siblingSet);
        assertFalse(ChildConstraints.checkSiblingsConstraints(child1));
    }


    /**
     * Test the violation of the constraint that a child can't have the same sibling twice or more.
     */
    @Test
    public void checkSiblingsConstraints3() throws SiblingConstraintException {
        Set<Sibling> siblingSet = new HashSet<>();
        siblingSet.add(sibling1);
        siblingSet.add(sibling3);
        child5.setSiblings(siblingSet);
        assertFalse(ChildConstraints.checkSiblingsConstraints(child5));
    }


    @After
    public void cleanUp() {
        child1 = null;
        child2 = null;
        child3 = null;
        child4 = null;
        child5 = null;

        parent1 = null;
        parent2 = null;
        parent3 = null;
        parent4 = null;
        parent5 = null;

        sibling1 = null;
        sibling2 = null;
        sibling3 = null;
    }
}
