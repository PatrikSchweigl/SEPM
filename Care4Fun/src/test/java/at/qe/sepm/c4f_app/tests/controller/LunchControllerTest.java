package at.qe.sepm.c4f_app.tests.controller;

import at.qe.sepm.c4f_app.models.nursery.Lunch;
import at.qe.sepm.c4f_app.services.LunchService;
import at.qe.sepm.c4f_app.ui.controllers.LunchController;
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

import java.util.Date;

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
        Date date = new Date();
        date.setYear(2017);
        date.setMonth(6);
        date.setDate(19);

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
        lunchController.setLunch(lunch);
        lunch = lunchController.doSaveLunch();

        // Check if the values have changed since the parent was saved.
        Lunch other = lunchService.loadLunch(lunch.getId());
        assertTrue(lunch.equals(other));

        // Delete the lunch again
        lunchController.setLunchEdit2(lunch);
        lunchController.doDeleteLunch();
        other = lunchService.loadLunch(lunch.getId());
        assertFalse(lunch.equals(other));
        assertNull(other);
    }


    @After
    public void cleanUp() {
        lunch = null;
    }
}
