package at.qe.sepm.asn_app.tests.database;

import at.qe.sepm.asn_app.models.UserData;
import at.qe.sepm.asn_app.models.UserRole;
import at.qe.sepm.asn_app.models.employee.Employee;
import at.qe.sepm.asn_app.models.employee.Status;
import at.qe.sepm.asn_app.models.employee.WorkRole;
import at.qe.sepm.asn_app.models.general.FamilyStatus;
import at.qe.sepm.asn_app.models.general.Religion;
import at.qe.sepm.asn_app.services.EmployeeService;
import at.qe.sepm.asn_app.services.UserService;
import at.qe.sepm.asn_app.ui.controllers.EmployeeController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 04.06.17 13:21 CEST.
 */
@Component
@Scope("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration
@WebAppConfiguration
public class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    private Employee employee;
    //@Autowired
    //AuthenticationManager authenticationManager;
    private MockMvc mvc;
    private UserData admin;
    SecurityContextRepository repository = new HttpSessionSecurityContextRepository();
    @Autowired
    private Filter springSecurityFilterChain;


    @Before
    public void initialize() throws InterruptedException {
        admin = userService.loadUser("admin");

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .addFilters(springSecurityFilterChain)
                .build();

        employee = new Employee("", "passwd", "EmployeeFirstName1", "EmployeeLastName1", "EmployeeLocation1",
                                "EmployeeStreetName1", "6020", "23/11/1990", "EmployeeEmail1@google.com",
                                "EmployeeImgName1", UserRole.EMPLOYEE, Religion.ATHEISMUS, "0123456789",
                                FamilyStatus.LEDIG, Status.ANWESEND, WorkRole.PAEDAGOGE);
    }


    @Test
    public void testValidUserWithInvalidRoleFails() {
        mvc = standaloneSetup(EmployeeController.class)
                .addFilters(springSecurityFilterChain)
                .setViewResolvers(viewResolver())
                .build();
    }


    @Test
    public void withUserRequestPostProcessor() throws Exception {
        mvc.perform(get("/admin").with(user("admin").roles("USER","ADMIN")));
    }


    @WithMockUser(roles="ADMIN")
    @Test
    public void withMockUser() throws Exception {
        //RequestBuilder request = get("http://localhost:8080/admin/employeepage_admin.xhtml")
        //                              .with(user("admin").roles("ADMIN"));

        //UserDetails userAdmin;
        //RequestBuilder request = get("http://localhost:8080/admin/employeepage_admin.xhtml")
        RequestBuilder request = get("http://localhost:8080/login.xhtml")
                                    .with(user("admin").roles("ADMIN"));
        //mvc.perform(request).andExpect(status().isUnauthorized());
        mvc.perform(request);
        //mvc.perform(get("/admin"));

        //login(request.buildRequest(ServletContext.));

        // Save the employee in the database.
        employeeController.setEmployee2(employee);
        employeeController.doSaveEmployee();
    }


    @Test
    public void test1() {
        // Save the employee in the database.
        employeeController.setEmployee2(employee);
        employeeController.doSaveEmployee();

        // Check if the values have changed since the parent was saved.
        Employee other = employeeService.loadEmployee(employee.getUsername());
        assertTrue(employee.equals(other));

        // Delete the parent again
        employeeController.doDeleteEmployee();
        other = employeeService.loadEmployee(employee.getUsername());
        assertFalse(employee.equals(other));
        assertNull(other);
    }


    @After
    public void cleanUp() {
        employee = null;
    }


    private void login(SecurityContext securityContext, HttpServletRequest request) {
        HttpServletResponse response = new MockHttpServletResponse();

        HttpRequestResponseHolder requestResponseHolder =
                new HttpRequestResponseHolder(request, response);
        repository.loadContext(requestResponseHolder);

        request = requestResponseHolder.getRequest();
        response = requestResponseHolder.getResponse();

        repository.saveContext(securityContext, request, response);
    }


    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/views");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
