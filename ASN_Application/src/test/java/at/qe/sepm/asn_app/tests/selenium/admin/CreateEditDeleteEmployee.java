package at.qe.sepm.asn_app.tests.selenium.admin;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class CreateEditDeleteEmployee {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = InitializeSelenium.initialize();
        baseUrl = InitializeSelenium.BASE_URL;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCreateEditDeleteEmployee() throws Exception {
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[3]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:usernameCreate")).clear();
        driver.findElement(By.id("employeeForm:usernameCreate")).sendKeys("EmployeeUserName");
        driver.findElement(By.id("employeeForm:firstnameCreate")).clear();
        driver.findElement(By.id("employeeForm:firstnameCreate")).sendKeys("EmployeeFirstName");
        driver.findElement(By.id("employeeForm:lastnameCreate")).clear();
        driver.findElement(By.id("employeeForm:lastnameCreate")).sendKeys("EmployeeLastName");
        driver.findElement(By.id("employeeForm:birthdayCreate")).clear();
        driver.findElement(By.id("employeeForm:birthdayCreate")).sendKeys("11/11/1990");
        driver.findElement(By.id("employeeForm:locationCreate")).clear();
        driver.findElement(By.id("employeeForm:locationCreate")).sendKeys("EmployeeLocation");
        driver.findElement(By.id("employeeForm:streetCreate")).clear();
        driver.findElement(By.id("employeeForm:streetCreate")).sendKeys("EmployeeStreetName");
        driver.findElement(By.id("employeeForm:postcodeCreate")).clear();
        driver.findElement(By.id("employeeForm:postcodeCreate")).sendKeys("6020");
        driver.findElement(By.id("employeeForm:phonenumberCreate")).clear();
        driver.findElement(By.id("employeeForm:phonenumberCreate")).sendKeys("343430924832");
        driver.findElement(By.id("employeeForm:emailCreate")).clear();
        driver.findElement(By.id("employeeForm:emailCreate")).sendKeys("employeeemail@google.com");
        driver.findElement(By.id("employeeForm:familystatusCreate_label")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:familystatusCreate_1")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='employeeForm:employeeDataCreate']/table[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:religionCreate_label")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:religionCreate_1")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:roleCreate_label")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:roleCreate_2")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:j_idt173")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("employeeForm:employeeTable:1:j_idt144")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:firstnameEdit")).clear();
        driver.findElement(By.id("employeeForm:firstnameEdit")).sendKeys("EmployeeFirstNameEdit");
        driver.findElement(By.id("employeeForm:lastnameEdit")).clear();
        driver.findElement(By.id("employeeForm:lastnameEdit")).sendKeys("EmployeeLastNameEdit");
        driver.findElement(By.id("employeeForm:birthdayEdit")).clear();
        driver.findElement(By.id("employeeForm:birthdayEdit")).sendKeys("10/101989");
        driver.findElement(By.id("employeeForm:locationEdit")).clear();
        driver.findElement(By.id("employeeForm:locationEdit")).sendKeys("EmployeeLocationEdit");
        driver.findElement(By.id("employeeForm:streetEdit")).clear();
        driver.findElement(By.id("employeeForm:streetEdit")).sendKeys("EmployeeStreetNameEdit");
        driver.findElement(By.id("employeeForm:postcodeEdit")).clear();
        driver.findElement(By.id("employeeForm:postcodeEdit")).sendKeys("2060");
        driver.findElement(By.id("employeeForm:phonenumberEdit")).clear();
        driver.findElement(By.id("employeeForm:phonenumberEdit")).sendKeys("657295839585");
        driver.findElement(By.id("employeeForm:emailEdit")).clear();
        driver.findElement(By.id("employeeForm:emailEdit")).sendKeys("employeeemailedit@google.com");
        driver.findElement(By.id("employeeForm:familyStatus_label")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:familyStatus_2")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:Religion_label")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:Religion_2")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:Role_label")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:Role_2")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:j_idt202")).click();
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:employeeTable:1:j_idt145")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:j_idt224")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[2]/a/i")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
