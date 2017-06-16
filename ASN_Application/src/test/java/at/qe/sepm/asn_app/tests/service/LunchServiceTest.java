package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.tests.controller.ContextMocker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:00 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LunchServiceTest {

    @Autowired
    private LunchService lunchService;
    private Lunch lunch;


    @Before
    public void initialize() {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, Calendar.APRIL, 23, 13, 45);
        Date date = calendar.getTime();

        lunch = new Lunch();
        lunch.setCost(5);
        lunch.setDate(date);
        lunch.setMeal("SpaghettiOs");
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save the lunch object in the database.
        lunch = lunchService.saveLunch(lunch);

        // Check if the values have changed since the parent was saved.
        Lunch other = lunchService.loadLunch(lunch.getId());
        assertTrue(lunch.equals(other));

        // Delete the lunch again
        lunchService.deleteLunch(lunch);
        other = lunchService.loadLunch(lunch.getId());
        assertFalse(lunch.equals(other));
        assertNull(other);
    }


    /**
     * This test is just for completeness to make sure
     * that everything works and is covered 100%.
     */
    @Test
    public void testSetterGetter() {
        // Initialize attributes
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 21, 16, 47);

        double cost = 2;
        Date date = calendar.getTime();
        String meal = "Meal";

        // Set attributes
        lunch.setCost(cost);
        lunch.setDate(date);
        lunch.setMeal(meal);

        // Compare all attributes with getters.
        assertEquals(cost, lunch.getCost(), 0.1);
        assertEquals(date, lunch.getDate());
        assertEquals(meal, lunch.getMeal());
    }


    @After
    public void cleanUp() {
        lunch = null;
    }
}