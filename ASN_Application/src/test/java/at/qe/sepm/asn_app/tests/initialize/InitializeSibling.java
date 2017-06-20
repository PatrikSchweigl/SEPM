package at.qe.sepm.asn_app.tests.initialize;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 17.06.17 17:18 CEST.
 */
public class InitializeSibling {

    public static Sibling initialize1() {
        Sibling sibling = new Sibling();
        sibling.setBirthday("07/03/2015");
        sibling.setFirstName("SiblingFirstName1");
        sibling.setLastName("SiblingLastName1");
        Child child = InitializeChild.initialize();
        sibling.setChild(child);
        return sibling;
    }
}
