package at.qe.sepm.asn_app.tests.selenium;

import at.qe.sepm.asn_app.tests.selenium.admin.AdminTestSuite;
import at.qe.sepm.asn_app.tests.selenium.employee.EmployeeTestSuite;
import at.qe.sepm.asn_app.tests.selenium.general.GeneralTestSuite;
import at.qe.sepm.asn_app.tests.selenium.parent.ParentTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 20.06.17 12:09 CEST.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdminTestSuite.class,
        EmployeeTestSuite.class,
        GeneralTestSuite.class,
        ParentTestSuite.class
})

public class SeleniumMasterTestSuite {
}
