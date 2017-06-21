package at.qe.sepm.asn_app.tests.selenium.parent;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


/**
 * This test logs in as a parent and signs up a child for the next week
 * including lunch with the fast sign-up button on the calendar view.
 */
public class CalendarLunchFastSignUp {
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
    public void testCalendarSwitchViewsParent2() throws Exception {
        // Login with a parent
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("bernd");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);

        // Click on the fast sign-up button.
        driver.findElement(By.id("j_idt118:j_idt120")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:massBox']/div[3]/table/tbody/tr/td[2]")).click();
        Thread.sleep(1000);

        // General sign-up.
        // Click the first two checkboxes twice to show that the can be un-assigned.
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt11']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt11']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt21']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt21']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt31']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt41']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt51']/div[2]/span")).click();
        Thread.sleep(1000);

        // Do the same for the lunch.
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt12']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt12']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt22']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt22']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt32']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt42']/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:opt52']/div[2]/span")).click();
        Thread.sleep(1000);

        // Save the changes made.
        driver.findElement(By.id("sheduleForm:j_idt242")).click();
        Thread.sleep(1000);

        // Since the page refresehs and we are on the main page of the parent we need to
        // close the password change modal again.
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);

        // Logout.
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[3]/a/i")).click();
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
