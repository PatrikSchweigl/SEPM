package at.qe.sepm.asn_app.tests.selenium.admin;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 19.06.17 19:54 CEST.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateEditDeleteEmployee.class,
        Messageboard.class,
        SearchSortEmployee.class
})

public class AdminTestSuite {
}
