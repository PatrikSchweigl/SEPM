package at.qe.sepm.asn_app.tests.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 12.06.17 10:57 CEST.
 */
@RunWith(Suite.class)
@SuiteClasses({
        CaregiverControllerTest.class,
        ChildControllerTest.class,
        CommentControllerTest.class,
        EmployeeControllerTest.class,
        EnumControllerTest.class,
        LunchControllerTest.class,
        MessageControllerTest.class,
        NurseryInformationControllerTest.class,
        ParentControllerTest.class,
        PictureControllerTest.class
})

public class ControllerTestSuite {
}