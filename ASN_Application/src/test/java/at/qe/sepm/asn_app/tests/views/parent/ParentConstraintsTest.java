package at.qe.sepm.asn_app.tests.views.parent;

import at.qe.sepm.asn_app.models.child.Child;
import at.qe.sepm.asn_app.models.child.Sibling;
import at.qe.sepm.asn_app.models.referencePerson.Parent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by zerus on 08.05.17.
 */
public class ParentConstraintsTest {

    private Child child1;
    private Child child2;
    private Child child3;
    private Child child4;
    private Child child5;
    private ArrayList<Child> listChildren;
    private Sibling sibling1;
    private Sibling sibling2;
    private Sibling sibling3;
    private ArrayList<Sibling> listSiblings;
    private Parent parent1;
    private Parent parent2;
    private Parent parent3;
    private Parent parent4;
    private Parent parent5;
    private ArrayList<Parent> listParents;


    /**
     * Initialize every attribute with static values.
     */
    @Before
    public void initialize() {

    }


    @Test
    public void test() {

    }


    /**
     * Set every attribute to null so it is assured that every test works with clean attributes.
     */
    @After
    public void cleanUp() {
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
    }

}
