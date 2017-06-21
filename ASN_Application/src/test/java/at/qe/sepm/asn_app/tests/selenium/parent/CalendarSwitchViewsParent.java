package at.qe.sepm.asn_app.tests.selenium.parent;

import java.util.concurrent.TimeUnit;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;


/**
 * Test the various buttons on the default calendar page of the parent.
 * This test opens and closes the overview about the lunches of the
 * current and next week, swaps the month of the calendar and then
 * changed the view from month to week to day and then back to month.
 * After these operations the user gets logged out again.
 */
public class CalendarSwitchViewsParent {
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
    public void testCalendarSwitchViewsParent() throws Exception {
        // Login as a parent.
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("bernd");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Close the change password modal.
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(2000);

        // Open and close the overview of the lunches.
        driver.findElement(By.xpath("//div[@id='j_idt128:j_idt130']/h3")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='j_idt128:j_idt130']/h3")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='j_idt128:j_idt130']/h3[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='j_idt128:j_idt130']/h3[2]")).click();
        Thread.sleep(1000);

        // Click on the arrows on the calendar to change the month to the previous one
        // then to the next one and then to the current one again.
        driver.findElement(By.xpath("//button[@type='button']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='button']")).click();
        Thread.sleep(1000);

        // Change the view of the calendar from month (default) to week
        // to day and then back to month.
        driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
        Thread.sleep(1000);

        // Logout
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
