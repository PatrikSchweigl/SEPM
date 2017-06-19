package at.qe.sepm.c4f_app.tests.initialize;

import at.qe.sepm.c4f_app.models.Gender;
import at.qe.sepm.c4f_app.models.child.Child;
import at.qe.sepm.c4f_app.models.child.Custody;
import at.qe.sepm.c4f_app.models.child.Sibling;
import at.qe.sepm.c4f_app.models.general.Religion;
import at.qe.sepm.c4f_app.models.referencePerson.Caregiver;
import at.qe.sepm.c4f_app.models.referencePerson.Parent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 14:48 CEST.
 */
public class InitializeChild {

    public static Child initialize() {
        Parent parent1 = InitializeParent.initialize1();
        Parent parent2 = InitializeParent.initialize2();

        Set<String> allergies = new HashSet<>();
        Set<String> foodIntolerances = new HashSet<>();
        Set<Sibling> siblings = new HashSet<>();
        Set<Caregiver> caregivers = new HashSet<>();

        Child child = new Child();
        child.setFirstName("ChildFirstName");
        child.setLastName("ChildLastName");
        child.setBirthday("22/05/2015");
        child.setImgName("ChildImgName");
        child.setGender(Gender.MAENNLICH);
        child.setPrimaryParent(parent1);
        child.setParent2(parent2);
        child.setEmergencyNumber("01234456789");
        //child.setAllergies(allergies);
        //child.setFoodIntolerances(foodIntolerances);
        child.setSiblings(siblings);
        child.setCustody(Custody.BEIDE);
        child.setReligion(Religion.CHRISTENTUM);
        child.setCaregivers(caregivers);

        return child;
    }
}
