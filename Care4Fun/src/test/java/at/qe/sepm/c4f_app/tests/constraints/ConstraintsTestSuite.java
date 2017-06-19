package at.qe.sepm.c4f_app.tests.constraints;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 15.06.17 12:56 CEST.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ChildConstraintsTest.class,
        LunchConstraintsTest.class,
        ParentConstraintsTest.class,
        PasswordConstraintsTest.class
        })

public class ConstraintsTestSuite {
}
