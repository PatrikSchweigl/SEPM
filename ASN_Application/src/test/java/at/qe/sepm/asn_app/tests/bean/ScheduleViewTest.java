package at.qe.sepm.asn_app.tests.bean;

import at.qe.sepm.asn_app.ui.beans.ScheduleView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 22.06.17 16:19 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ScheduleViewTest {

    @Autowired
    private ScheduleView scheduleView;

    @Before
    public void initialize() {

    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "bernd", authorities = {"PARENT"})
    public void testTest() {
        scheduleView.test();
    }


    @After
    public void cleanUp() {
    }
}
