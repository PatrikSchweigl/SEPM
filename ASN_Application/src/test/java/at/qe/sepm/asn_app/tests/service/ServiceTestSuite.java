package at.qe.sepm.asn_app.tests.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:04 CEST.
 */
@RunWith(Suite.class)
@SuiteClasses({
        AuditLogServiceTest.class,
        CaregiverServiceTest.class,
        ChildServiceTest.class,
        CommentServiceTest.class,
        EmployeeServiceTest.class,
        LunchServiceTest.class,
        MessageServiceTest.class,
        NurseryInformationServiceTest.class,
        ParentServiceTest.class,
        PictureServiceTest.class,
        PrivateMessageServiceTest.class,
        RegistrationServiceTest.class,
        SiblingServiceTest.class,
        TaskServiceTest.class,
        UserServiceTest.class,
})

public class ServiceTestSuite {
}
