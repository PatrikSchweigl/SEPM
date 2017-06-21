package at.qe.sepm.asn_app.tests;

import at.qe.sepm.asn_app.tests.constraints.ConstraintsTestSuite;
import at.qe.sepm.asn_app.tests.service.ServiceTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 15.06.17 18:03 CEST.
 * <p>
 * This test suite calls all other test suites.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConstraintsTestSuite.class,
        ServiceTestSuite.class
})

public class MasterTestSuite {
}
