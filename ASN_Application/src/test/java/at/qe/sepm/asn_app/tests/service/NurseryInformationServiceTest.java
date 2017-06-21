package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.services.NurseryInformationService;
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
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 12:08 CEST.
 */

@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NurseryInformationServiceTest {

    @Autowired
    private NurseryInformationService nurseryInformationService;
    private NurseryInformation nurseryInformation;
    private Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));


    @Before
    public void initialize() {
        int originDateYear = 2018;
        int originDateMonth = Calendar.APRIL;
        int originDateDay = 27;

        calendar.clear();
        calendar.set(2018, Calendar.JULY, 11, 0, 0);
        Date todaysDate = calendar.getTime();

        calendar.clear();
        calendar.set(originDateYear, originDateMonth, originDateDay);
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

        int currentOccupancy = 7;
        int maxOccupancy = 21;

        nurseryInformation = new NurseryInformation();
        nurseryInformation.setBringStart(bringStart);
        nurseryInformation.setBringEnd(bringEnd);
        nurseryInformation.setCurrentOccupancy(currentOccupancy);
        nurseryInformation.setCurrentDate(todaysDate);
        nurseryInformation.setPickUpStart(pickUpStart);
        nurseryInformation.setPickUpEnd(pickUpEnd);
        nurseryInformation.setMaxOccupancy(maxOccupancy);
        nurseryInformation.setTodaysDate(todaysDate);
        nurseryInformation.setOriginDate(originDate);

        // Getters are tested here to avoid code duplication.
        assertEquals(bringStart, nurseryInformation.getBringStart());
        assertEquals(bringEnd, nurseryInformation.getBringEnd());
        assertEquals(currentOccupancy, nurseryInformation.getCurrentOccupancy());
        assertEquals(todaysDate, nurseryInformation.getCurrentDate());
        assertEquals(pickUpStart, nurseryInformation.getPickUpStart());
        assertEquals(pickUpEnd, nurseryInformation.getPickUpEnd());
        assertEquals(maxOccupancy, nurseryInformation.getMaxOccupancy());
        assertEquals(todaysDate, nurseryInformation.getTodaysDate());
        assertEquals(originDate, nurseryInformation.getOriginDate());
    }


    @Test
    public void test() {
        // Save the nurseryInformation in the database.
        nurseryInformation = nurseryInformationService.saveNurseryInformation(nurseryInformation);

        // Check if the values have changed since the nurseryInformation was saved.
        NurseryInformation other = nurseryInformationService.loadNurseryInformation(nurseryInformation.getId());
        assertTrue(nurseryInformation.equals(other));

        // Delete the nurseryInformation again.
        nurseryInformationService.deleteNurseryInformation(nurseryInformation);
        other = nurseryInformationService.loadNurseryInformation(nurseryInformation.getId());
        assertFalse(nurseryInformation.equals(other));
        assertNull(other);
    }


    @Test
    public void testFurtherMethods() {
        // Test isNew()
        assertFalse(nurseryInformation.isNew());

        // Test getFormattedOriginDate
        assertNotEquals("", nurseryInformation.getFormattedOriginDate());


        // Test getBringDuration
        assertNotEquals("", nurseryInformation.getBringDurationNew());


        // Test getPickUpDuration
        assertNotEquals("", nurseryInformation.getPickUpDurationNew());


        // Test toString()
        assertNotEquals("", nurseryInformation.toString());

    }


    @After
    public void cleanUp() {
        calendar = null;
        nurseryInformation = null;
    }
}
