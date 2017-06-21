package at.qe.sepm.asn_app.tests.constraints;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import javax.imageio.spi.RegisterableService;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 15.06.17 12:56 CEST.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ChildConstraintsTest.class,
        LunchConstraintsTest.class,
        ParentConstraintsTest.class,
        PasswordConstraintsTest.class,
        RegistrationConstraintsTest.class,
        UserConstraintsTest.class
})

public class ConstraintsTestSuite {
}
