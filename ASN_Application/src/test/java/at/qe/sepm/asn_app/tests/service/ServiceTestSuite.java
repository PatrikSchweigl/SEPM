package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.tests.views.children.ChildServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:04 CEST.
 */
@RunWith(Suite.class)
@SuiteClasses({
        //CaregiverServiceTest.class,
        ChildServiceTest.class,
        CommentServiceTest.class,
        EmployeeServiceTest.class,
        //EnumServiceTest.class,
        LunchServiceTest.class,
        MessageServiceTest.class,
        NurseryInformationServiceTest.class,
        ParentServiceTest.class,
        PictureServiceTest.class,
        PrivateMessageServiceTest.class,
        //RegistrationServiceTest.class,
        TaskServiceTest.class
        })

public class ServiceTestSuite {
}
