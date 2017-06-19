package at.qe.sepm.c4f_app.tests.initialize;

import at.qe.sepm.c4f_app.models.child.Child;
import at.qe.sepm.c4f_app.models.referencePerson.Caregiver;
import at.qe.sepm.c4f_app.models.referencePerson.Relationship;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 18.06.17 18:25 CEST.
 */
public class InitializeCaregiver {

    public static Caregiver initialize1() {
        Child child = InitializeChild.initialize();

        Caregiver caregiver = new Caregiver();
        caregiver.setChild(child);
        caregiver.setEligible(true);
        caregiver.setFirstName("CaregiverFirstName1");
        caregiver.setLastName("CaregiverLastName1");
        caregiver.setRelationship(Relationship.AUNT_UNCLE);
        caregiver.setImgName("CaregiverImgName1");
        caregiver.setPhoneNumber("2758091735");

        return caregiver;
    }
}
