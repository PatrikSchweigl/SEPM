package at.qe.sepm.asn_app.tests.database;

import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.models.employee.Status;
import at.qe.sepm.asn_app.models.employee.WorkRole;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.services.EmployeeService;
import at.qe.sepm.asn_app.ui.controllers.EmployeeController;
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
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 04.06.17 13:21 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private EmployeeService employeeService;
    private Employee employee;


    @Before
    public void initialize() throws InterruptedException {
        employee = new Employee("", "passwd", "EmployeeFirstName1", "EmployeeLastName1", "EmployeeLocation1",
                                "EmployeeStreetName1", "6020", "23/11/1990", "EmployeeEmail1@google.com",
                                "EmployeeImgName1", UserRole.EMPLOYEE, Religion.ATHEISMUS, "0123456789",
                                FamilyStatus.LEDIG, Status.ANWESEND, WorkRole.PAEDAGOGE);
    }


    @DirtiesContext
    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void test1() {
        // Save the employee in the database.
        employeeController.setEmployee2(employee);
        employeeController.doSaveEmployee();

        // Check if the values have changed since the parent was saved.
        Employee other = employeeService.loadEmployee(employee.getUsername());
        assertTrue(employee.equals(other));

        // Delete the parent again
        employeeController.setEmployeeEdit2(employee);
        employeeController.doDeleteEmployee();
        other = employeeService.loadEmployee(employee.getUsername());
        assertFalse(employee.equals(other));
        assertNull(other);
    }


    @After
    public void cleanUp() {
        employee = null;
    }

}
