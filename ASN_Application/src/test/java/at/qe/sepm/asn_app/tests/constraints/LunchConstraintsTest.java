package at.qe.sepm.asn_app.tests.constraints;

import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.ui.constraints.LunchConstraints;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 14:54 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LunchConstraintsTest {

    @Autowired
    private LunchConstraints lunchConstraints;
    private Lunch lunch;

    private Calendar calendar;


    @Before
    public void initialize() {
        //lunchConstraints = new LunchConstraints();

        calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        lunch = new Lunch();
    }


    /**
     * Test a lunch without any violation.
     */
    @Test
    public void test1() {
        calendar.set(2017, 5, 19, 13, 45);
        lunch.setCost(5);
        lunch.setDate(calendar.getTime());
        lunch.setMeal("SpaghettiOs");
        assertTrue(lunchConstraints.checkLunchConstraints(lunch));
    }


    /**
     * A lunch must not be created in the past
     */
    @Test
    public void dateInPast() {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, 5, 13, 12, 0);
        Date date = calendar.getTime();

        lunch.setCost(5);
        lunch.setDate(date);
        lunch.setMeal("SpaghettiOs");
        assertFalse(lunchConstraints.checkTimeConstraints(lunch));
    }


    /**
     * It is only possible to
     */
    /*
    @Test
    public void dateInFarFuture() {
        calendar.set(2017, 5, 13, 12, 0);
        Date date = calendar.getTime();

        lunch2 = new Lunch();
        lunch2.setCost(5);
        lunch2.setDate(date);
        lunch2.setMeal("SpaghettiOs");
        assertFalse(lunchConstraints.checkTimeConstraints(lunch2));
    }
    */


    @After
    public void cleanUp() {
        lunch = null;
        //lunch2 = null;
        //lunch3 = null;
        //lunch4 = null;
        //lunch5 = null;
    }
}
