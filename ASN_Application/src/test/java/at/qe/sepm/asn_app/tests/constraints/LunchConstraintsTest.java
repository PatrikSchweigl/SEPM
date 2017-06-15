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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

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
    @Autowired
    private LunchService lunchService;
    private Lunch lunch1;
    private Lunch lunch2;
    private Lunch lunch3;

    private Calendar calendar;


    @Before
    public void initialize() {
        //lunchConstraints = new LunchConstraints();

        calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        lunch1 = new Lunch();
        lunch2 = new Lunch();
        lunch3 = new Lunch();
    }


    /**
     * Test a lunch without any violation.
     */
    @Test
    public void checkLunchConstraints() {
        calendar.set(2017, 5, 19, 13, 45);
        lunch1.setCost(5);
        lunch1.setDate(calendar.getTime());
        lunch1.setMeal("SpaghettiOs");
        assertTrue(lunchConstraints.checkLunchConstraints(lunch1));
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

        lunch1.setCost(5);
        lunch1.setDate(date);
        lunch1.setMeal("SpaghettiOs");
        assertFalse(lunchConstraints.checkTimeConstraints(lunch1));
    }


    /**
     * Save two lunches in the database and check if it is possible to save a lunch between those days.
     */
    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testCheckIfLunchExists1() {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, 6, 4, 12, 0);
        lunch1.setCost(5);
        lunch1.setDate(calendar.getTime());
        lunch1.setMeal("SpaghettiOs");
        assertFalse(lunchConstraints.checkIfLunchExists(lunch1));

        // Create a lunch one day before the initial lunch.
        calendar.clear();
        calendar.set(2017, 6, 3, 23, 59);
        lunch2.setCost(lunch1.getCost());
        lunch2.setDate(calendar.getTime());
        lunch2.setMeal(lunch1.getMeal());
        lunch2 = lunchService.saveLunch(lunch2);

        // Create another lunch one day after the initial lunch.
        calendar.clear();
        calendar.set(2017, 6, 5, 0, 0);
        lunch3.setCost(lunch1.getCost());
        lunch3.setDate(calendar.getTime());
        lunch3.setMeal(lunch1.getMeal());
        lunch3 = lunchService.saveLunch(lunch3);

        // It must still be possible to save a lunch inbetween two other lunches.
        assertFalse(lunchConstraints.checkIfLunchExists(lunch1));

        lunch1 = lunchService.saveLunch(lunch1);
        assertNotNull(lunchService.loadLunch(lunch1.getId()));

        // Delete all lunches again for completenesss.
        lunchService.deleteLunch(lunch1);
        assertNull(lunchService.loadLunch(lunch1.getId()));

        lunchService.deleteLunch(lunch2);
        assertNull(lunchService.loadLunch(lunch2.getId()));

        lunchService.deleteLunch(lunch3);
        assertNull(lunchService.loadLunch(lunch3.getId()));
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
        lunch1 = null;
        lunch2 = null;
        lunch3 = null;
    }
}
