package at.qe.sepm.asn_app.tests.selenium.admin;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


/**
 * Log in as the admin, go to the calendar view and create
 * an appointment. Edit the appointment, delete it and then
 * log the admin out.
 */
public class CalendarAppointment {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = InitializeSelenium.initialize();
        baseUrl = InitializeSelenium.BASE_URL;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testCalendar2() throws Exception {
        // Login with the admin.
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Go to the calendar and click on a future week day.
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[2]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='sheduleForm:schedule_container']/div[2]/div/table/tbody/tr/td/div/div/div[5]/div/table/tbody/tr/td")).click();
        Thread.sleep(1000);

        // Set the appointment name.
        driver.findElement(By.id("sheduleForm:title")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:title")).sendKeys("Termin");
        Thread.sleep(1000);

        // Set the begin time.
        driver.findElement(By.id("sheduleForm:from_input")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:from_input")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:from_input")).sendKeys("26/06/2017 08:15");
        driver.findElement(By.xpath("//table[@id='sheduleForm:eventDetails']/tbody/tr[2]/td")).click();
        Thread.sleep(1000);

        // Set the end time.
        driver.findElement(By.id("sheduleForm:to_input")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:to_input")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:to_input")).sendKeys("26/06/2017 10:30");
        driver.findElement(By.xpath("//table[@id='sheduleForm:eventDetails']/tbody/tr/td")).click();
        Thread.sleep(1000);

        // Save the appointment.
        driver.findElement(By.id("sheduleForm:addButton")).click();
        Thread.sleep(1000);

        // Edit the appointment.
        driver.findElement(By.xpath("//div[@id='sheduleForm:schedule_container']/div[2]/div/table/tbody/tr/td/div/div/div[5]/div[2]/table/tbody/tr/td/a/div")).click();
        Thread.sleep(1000);

        // Edit the title.
        driver.findElement(By.id("sheduleForm:title")).clear();
        driver.findElement(By.id("sheduleForm:title")).sendKeys("TerminEdit");

        // Edit the begin time.
        driver.findElement(By.id("sheduleForm:from_input")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:from_input")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:from_input")).sendKeys("26/06/2017 13:47");
        driver.findElement(By.xpath("//table[@id='sheduleForm:eventDetails']/tbody/tr[2]/td")).click();
        Thread.sleep(1000);

        // Edit the end time.
        driver.findElement(By.id("sheduleForm:to_input")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:to_input")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:to_input")).sendKeys("26/06/2017 15:00");
        driver.findElement(By.xpath("//table[@id='sheduleForm:eventDetails']/tbody/tr/td")).click();
        Thread.sleep(1000);

        // Save the changes.
        driver.findElement(By.id("sheduleForm:addButton")).click();
        Thread.sleep(1000);

        // Delete the appointment again.
        driver.findElement(By.xpath("//div[@id='sheduleForm:schedule_container']/div[2]/div/table/tbody/tr/td/div/div/div[5]/div[2]/table/tbody/tr/td/a/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("sheduleForm:deleteButton")).click();
        Thread.sleep(1000);

        // Logout
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
