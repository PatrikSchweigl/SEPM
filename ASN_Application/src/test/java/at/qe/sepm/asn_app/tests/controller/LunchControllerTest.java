package at.qe.sepm.asn_app.tests.controller;

import at.qe.sepm.asn_app.models.nursery.Lunch;
import at.qe.sepm.asn_app.services.LunchService;
import at.qe.sepm.asn_app.ui.controllers.LunchController;
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
import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 06.06.17 10:45 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LunchControllerTest {

    @Autowired
    private LunchController lunchController;
    @Autowired
    private LunchService lunchService;
    private Lunch lunch;


    @Before
    public void initialize() {
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        calendar.clear();
        calendar.set(2017, Calendar.JULY, 21, 13, 45);
        Date date = calendar.getTime();
        Set<Long> childrenIds = new HashSet<>();

        lunch = new Lunch();
        lunch.setCost(5);
        lunch.setDate(date);
        lunch.setMeal("SpaghettiOs");
        lunch.setChildrenIds(childrenIds);
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        FacesContext context = ContextMocker.mockFacesContext();
        RequestContext requestContext = ContextMocker.mockRequestContext();
        try {
            // Save the lunch object in the database.
            lunchController.setLunch(lunch);
            lunch = lunchController.doSaveLunch();

            // Check if the values have changed since the parent was saved.
            Lunch other = lunchService.loadLunch(lunch.getId());
            assertTrue(lunch.equals(other));

            // Delete the lunch again
            lunchController.setLunchEdit(lunch);
            lunchController.doDeleteLunch();
            other = lunchService.loadLunch(lunch.getId());
            assertFalse(lunch.equals(other));
            assertNull(other);
        }
        finally {
            context.release();
            requestContext.release();
        }
    }


    @After
    public void cleanUp() {
        lunch = null;
    }
}
