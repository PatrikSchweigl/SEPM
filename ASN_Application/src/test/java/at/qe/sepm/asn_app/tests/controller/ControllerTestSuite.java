package at.qe.sepm.asn_app.tests.controller;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 12.06.17 10:57 CEST.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        NurseryInformationControllerTest.class,
        PictureControllerTest.class })

public class ControllerTestSuite {

}