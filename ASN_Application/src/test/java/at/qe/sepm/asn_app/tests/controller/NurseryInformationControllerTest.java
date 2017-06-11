package at.qe.sepm.asn_app.tests.controller;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.services.NurseryInformationService;
import at.qe.sepm.asn_app.ui.controllers.NurseryInformationController;
import org.junit.After;
import org.junit.Before;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 10.06.17 18:23 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NurseryInformationControllerTest {

    @Autowired
    private NurseryInformationController nurseryInformationController;
    @Autowired
    private NurseryInformationService nurseryInformationService;
    private NurseryInformation nurseryInformation;

    @Before
    public void initialize() {
        int originDateYear = 2017;
        int originDateMonth = 5;
        int originDateDay = 27;

        Calendar calendar = GregorianCalendar.getInstance();

        calendar.clear();
        calendar.set(2017, 6, 11, 0, 0);
        Date todaysDate = calendar.getTime();

        calendar.clear();
        calendar.set(originDateYear, originDateMonth, originDateDay, 0, 0);
        Date originDate = calendar.getTime();

        calendar.clear();
        calendar.set(originDateYear, originDateMonth, originDateDay, 7, 30);
        Date bringStart = calendar.getTime();

        calendar.clear();
        calendar.set(originDateYear, originDateMonth, originDateDay, 10, 0);
        Date bringEnd = calendar.getTime();

        calendar.clear();
        calendar.set(originDateYear, originDateMonth, originDateDay, 14, 0);
        Date pickUpStart = calendar.getTime();

        calendar.clear();
        calendar.set(originDateYear, originDateMonth, originDateDay, 16, 30);
        Date pickUpEnd = calendar.getTime();

        nurseryInformation = new NurseryInformation();
        nurseryInformation.setBringStart(bringStart);
        nurseryInformation.setBringEnd(bringEnd);
        nurseryInformation.setPickUpStart(pickUpStart);
        nurseryInformation.setPickUpEnd(pickUpEnd);
        nurseryInformation.setMaxOccupancy(21);
        nurseryInformation.setTodaysDate(todaysDate);
        nurseryInformation.setOriginDate(originDate);
    }


    @Test
    public void test() {
        // Save the nursery information in the database.
        //nurseryInformationController.setNurseryInformation2(nurseryInformation);
        //nurseryInformation = nurseryInformationController.doSaveNurseryInformation();
        nurseryInformation = nurseryInformationService.saveNurseryInformation(nurseryInformation);

        // Check if the values have changed since the nurseryInformation was saved.
        NurseryInformation other = nurseryInformationService.loadNurseryInformation(nurseryInformation.getId());
        assertTrue(nurseryInformation.equals(other));

        // Delete the nurseryInformation again.
        //nurseryInformationController.doDeleteNurseryInformation();
        nurseryInformationService.deleteNurseryInformation(nurseryInformation);
        other = nurseryInformationService.loadNurseryInformation(nurseryInformation.getId());
        assertFalse(nurseryInformation.equals(other));
        assertNull(other);
    }


    @After
    public void cleanUp() {
        nurseryInformation = null;
    }
}
