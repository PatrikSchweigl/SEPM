package at.qe.sepm.c4f_app.tests;

import at.qe.sepm.c4f_app.tests.constraints.ConstraintsTestSuite;
import at.qe.sepm.c4f_app.tests.service.ServiceTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 15.06.17 18:03 CEST.
 *
 * This test suite calls all other test suites.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConstraintsTestSuite.class,
        ServiceTestSuite.class
})

public class MasterTestSuite {
}
