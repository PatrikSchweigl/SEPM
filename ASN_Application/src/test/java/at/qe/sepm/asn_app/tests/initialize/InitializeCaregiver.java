package at.qe.sepm.asn_app.tests.initialize;

import at.qe.sepm.asn_app.models.referencePerson.Caregiver;
import at.qe.sepm.asn_app.models.referencePerson.Relationship;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 22.06.17 15:51 CEST.
 */
public class InitializeCaregiver {

    public static Caregiver initialize1() {
        Caregiver caregiver = new Caregiver();
        caregiver.setEligible(true);
        caregiver.setFirstName("CaregiverFirstName1");
        caregiver.setLastName("CaregiverLastName1");
        caregiver.setRelationship(Relationship.AUNT_UNCLE);
        caregiver.setImgName("emptypicture.png");
        caregiver.setPhoneNumber("0123456789");
        caregiver.setChild(InitializeChild.initialize());
        return caregiver;
    }
}
