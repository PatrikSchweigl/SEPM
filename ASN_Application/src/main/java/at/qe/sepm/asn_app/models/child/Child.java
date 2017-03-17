package at.qe.sepm.asn_app.models.child;


import at.qe.sepm.asn_app.models.parent.Parent;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by zerus on 17.03.17.
 */
public class Child {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private int profilePicture;   // tmp, I don't know what data type profilePicture should be.
    private List<String> furtherRemarks;
    private Parent father;
    private Parent mother;
    // private EmergencyContact emergencyContact;
    private List<String> listAllergies;
    private List<String> listFoodIntolerances;
    private List<Sibling> listSiblings;
    private boolean isRegistered;
}
