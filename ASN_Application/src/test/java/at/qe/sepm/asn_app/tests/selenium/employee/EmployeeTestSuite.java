package at.qe.sepm.asn_app.tests.selenium.employee;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 21.06.17 11:42 CEST.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		CalendarSwitchViews.class,
		CreateAndDeleteHoliday.class,
        CreateEditDeleteChild.class,
        CreateEditDeleteLunch.class,
        CreateEditDeleteParent.class,
		CreateEditDeleteTask.class,
		CreateNurseryInfo.class
})

public class EmployeeTestSuite {
}
