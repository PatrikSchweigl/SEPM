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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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


    @After
    public void cleanUp() {
        employee = null;
    }
}
