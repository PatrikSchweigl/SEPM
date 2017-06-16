package at.qe.sepm.asn_app.tests.service;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.models.employee.Status;
import at.qe.sepm.asn_app.models.employee.WorkRole;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.services.EmployeeService;
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

import static org.junit.Assert.*;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 14.06.17 11:58 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;
    private Employee employee;


    @Before
    public void initialize() throws InterruptedException {
        employee = new Employee();
        employee.setBirthday("23/11/1990");
        employee.setEmail("EmployeeEmail@google.com");
        employee.setFamilyStatus(FamilyStatus.VERWITTWED);
        employee.setFirstName("EmployeeFirstName");
        employee.setImgName("EmployeeImgName");
        employee.setLastName("EmployeeLastName");
        employee.setLocation("EmpoyeeLocation");
        employee.setNotification(false);
        employee.setPassword("passwd");
        employee.setPhoneNumber("437589234502");
        employee.setPostcode("6020");
        employee.setReligion(Religion.ISLAM);
        employee.setStreetName("EmployeeStreetName");
        employee.setUsername("EmployeeUsername");
        employee.setUserRole(UserRole.EMPLOYEE);
        employee.setWorkingState(Status.ABWESEND);
        employee.setWorkRole(WorkRole.PRAKTIKANT);
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save the employee in the database.
        employeeService.saveEmployee(employee);

        // Check if the values have changed since the parent was saved.
        Employee other = employeeService.loadEmployee(employee.getUsername());
        assertTrue(employee.equals(other));

        // Delete the parent again
        employeeService.deleteEmployee(employee);
        other = employeeService.loadEmployee(employee.getUsername());
        assertFalse(employee.equals(other));
        assertNull(other);
    }


    /**
     * This test is just for completeness to make sure
     * that everything works and is covered 100%.
     */
    @Test
    public void testSetterGetter() {

        // Initialize attributes
        String birthday = "23/11/1990";
        String eMail = "EmployeeEmail@google.com";
        FamilyStatus familyStatus = FamilyStatus.VERWITTWED;
        String firstName = "EmployeeFirstName";
        String imgName = "EmployeeImgName";
        String lastName = "EmployeeLastName";
        String location = "EmployeeLocation";
        boolean notification = false;
        String password = "passwd";
        String phoneNumber = "437589234502";
        String postcode = "6020";
        Religion religion = Religion.ISLAM;
        String streetName = "EmployeeStreetName";
        String username = "EmployeeUsername";
        UserRole userRole = UserRole.EMPLOYEE;
        Status workingState = Status.ABWESEND;
        WorkRole workRole = WorkRole.PRAKTIKANT;

        // Set all attributes
        employee.setBirthday(birthday);
        employee.setEmail(eMail);
        employee.setFamilyStatus(familyStatus);
        employee.setFirstName(firstName);
        employee.setImgName(imgName);
        employee.setLastName(lastName);
        employee.setLocation(location);
        employee.setNotification(notification);
        employee.setPassword(password);
        employee.setPhoneNumber(phoneNumber);
        employee.setPostcode(postcode);
        employee.setReligion(religion);
        employee.setStreetName(streetName);
        employee.setUsername(username);
        employee.setUserRole(userRole);
        employee.setWorkingState(workingState);
        employee.setWorkRole(workRole);

        assertEquals(birthday, employee.getBirthday());
        assertEquals(eMail, employee.getEmail());
        assertEquals(familyStatus, employee.getFamilyStatus());
        assertEquals(firstName, employee.getFirstName());
        assertEquals(imgName, employee.getImgName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(location, employee.getLocation());
        assertEquals(notification, employee.isNotification());
        assertEquals(password, employee.getPassword());
        assertEquals(phoneNumber, employee.getPhoneNumber());
        assertEquals(postcode, employee.getPostcode());
        assertEquals(religion, employee.getReligion());
        assertEquals(streetName, employee.getStreetName());
        assertEquals(username, employee.getUsername());
        assertEquals(userRole, employee.getUserRole());
        assertEquals(workingState, employee.getWorkingState());
        assertEquals(workRole, employee.getWorkRole());
    }


    @After
    public void cleanUp() {
        employee = null;
    }
}
