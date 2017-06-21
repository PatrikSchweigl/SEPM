package at.qe.sepm.asn_app.tests.selenium.admin;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class CreateAndDeleteEmployee {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = InitializeSelenium.initialize();
        baseUrl = "http://192.168.33.10:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCreateAndDeleteEmployee() throws Exception {
        Login.login(driver, baseUrl);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[3]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt117")).click();
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
        driver.findElement(By.id("employeeForm:j_idt172")).click();
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:employeeTable:1:j_idt144")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("employeeForm:j_idt205")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[2]/a/i")).click();
        Thread.sleep(1000);
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
