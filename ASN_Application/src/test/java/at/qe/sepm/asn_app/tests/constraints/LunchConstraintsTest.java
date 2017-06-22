package at.qe.sepm.asn_app.tests.constraints;

import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.services.NurseryInformationService;
import at.qe.sepm.asn_app.ui.constraints.LunchConstraints;
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
    @Autowired
    private NurseryInformationService nurseryInformationService;
    private Lunch lunch1;
    private Lunch lunch2;
    private Lunch lunch3;

    private Calendar calendar;


    @Before
    public void initialize() {
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
        calendar.set(2017, Calendar.JULY, 19, 13, 45);
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
        calendar.clear();
        calendar.set(2017, Calendar.JUNE, 13, 12, 0);
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
    public void testCheckIfLunchExists() {
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 4, 12, 0);
        lunch1.setCost(5);
        lunch1.setDate(calendar.getTime());
        lunch1.setMeal("SpaghettiOs");
        assertFalse(lunchConstraints.checkIfLunchExists(lunch1));

        // Create a lunch one day before the initial lunch.
        calendar.set(2017, Calendar.JULY, 3, 23, 59);
        lunch2.setCost(lunch1.getCost());
        lunch2.setDate(calendar.getTime());
        lunch2.setMeal(lunch1.getMeal());
        lunch2 = lunchService.saveLunch(lunch2);

        // Create another lunch one day after the initial lunch.
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 5, 0, 0);
        lunch3.setCost(lunch1.getCost());
        lunch3.setDate(calendar.getTime());
        lunch3.setMeal(lunch1.getMeal());
        lunch3 = lunchService.saveLunch(lunch3);

        // It must still be possible to save a lunch inbetween two other lunches.
        assertFalse(lunchConstraints.checkIfLunchExists(lunch1));

        lunch1 = lunchService.saveLunch(lunch1);
        assertNotNull(lunchService.loadLunch(lunch1.getId()));
        // TODO make another checkIfLunchEcists here for lunch1

        // Delete all lunches again for completenesss.
        lunchService.deleteLunch(lunch1);
        assertNull(lunchService.loadLunch(lunch1.getId()));

        lunchService.deleteLunch(lunch2);
        assertNull(lunchService.loadLunch(lunch2.getId()));

        lunchService.deleteLunch(lunch3);
        assertNull(lunchService.loadLunch(lunch3.getId()));
    }


    /**
     * The time of the nuseryInformation is 2 hours before the lunch because
     * in the NurseryInformationService 2 hours get added (it didn't work
     * otherwise on the views).
     */
    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testCheckIfNurseryInformationExists() {
        calendar.clear();
        calendar.set(2017, Calendar.AUGUST, 11, 12, 0);
        lunch1.setCost(5);
        lunch1.setDate(calendar.getTime());
        lunch1.setMeal("SpaghettiOs");
        lunch1 = lunchService.saveLunch(lunch1);
        assertFalse(lunchConstraints.checkIfNurseryInformationExists(lunch1));


        //calendar.clear();
        calendar.set(2017, Calendar.JULY, 11, 10, 0);
        Date date1 = calendar.getTime();
        lunch1.setDate(date1);

        assertTrue(lunchConstraints.checkIfNurseryInformationExists(lunch1));

        //Delete the lunch again.
        lunchService.deleteLunch(lunch1);
    }


    /**
     * A lunch can't be created in the past.
     */
    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testCheckTimeConstraints1() {
        calendar.clear();
        calendar.set(2017, Calendar.JUNE, 9, 0, 0);
        lunch1.setCost(5);
        lunch1.setDate(calendar.getTime());
        lunch1.setMeal("SpaghettiOs");
        assertFalse(lunchConstraints.checkTimeConstraints(lunch1));
    }


    /**
     * A lunch can't be created for the current week, only future ones.
     */
    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testCheckTimeConstraints2() {
        calendar.clear();
        calendar.set(2017, Calendar.JUNE, 16, 0, 0);
        lunch1.setDate(calendar.getTime());
        assertFalse(lunchConstraints.checkTimeConstraints(lunch1));
    }


    /**
     * A lunch can't be created for the current week, only future ones.
     */
    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testCheckTimeConstraints3() {
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 3, 0, 0);
        lunch1.setDate(calendar.getTime());
        assertTrue(lunchConstraints.checkTimeConstraints(lunch1));
    }


    @After
    public void cleanUp() {
        lunch1 = null;
        lunch2 = null;
        lunch3 = null;
    }
}
