package at.qe.sepm.asn_app.tests.selenium.parent;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 20.06.17 19:15 CEST.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalendarLunchFastSignUp.class,
        CalendarSwitchViewsParent.class,
        ChangeOwnData.class
})

public class ParentTestSuite {
}
