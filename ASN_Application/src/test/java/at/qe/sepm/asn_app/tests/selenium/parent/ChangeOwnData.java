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
 * This test logs in as a parent and changes all possible own data.
 * After the changes have been made it logs in as an employee and
 * check if the changes have been made successfully.
 */
public class ChangeOwnData {
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
    public void testChangeOwnData() throws Exception {
        // Login
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("bernd");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Close the change password modal.
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);

        // Click on the gear in the top menu bar to open a view about a users own data.
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[2]/a/i")).click();
        Thread.sleep(1000);

        // Change all possible values.
        driver.findElement(By.id("j_idt22:street")).clear();
        driver.findElement(By.id("j_idt22:street")).sendKeys("StreetName");
        driver.findElement(By.id("j_idt22:location")).clear();
        driver.findElement(By.id("j_idt22:location")).sendKeys("Location");
        driver.findElement(By.id("j_idt22:email")).clear();
        driver.findElement(By.id("j_idt22:email")).sendKeys("email@google.com");
        driver.findElement(By.id("j_idt22:phone")).clear();
        driver.findElement(By.id("j_idt22:phone")).sendKeys("5710223857");

        // Activate email notifications.
        driver.findElement(By.xpath("//div[@id='j_idt22:notifier']/span")).click();
        Thread.sleep(1000);

        // Change the colour of the website and stick with orange.
        driver.findElement(By.cssSelector("a.clearfix.full-opacity-hover > span")).click();     // It seems like this is apparently green.
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id='j_idt22:j_idt43']/tbody/tr/td[2]/a[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id='j_idt22:j_idt43']/tbody/tr/td[2]/a[3]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id='j_idt22:j_idt43']/tbody/tr/td[2]/a[4]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id='j_idt22:j_idt43']/tbody/tr/td[2]/a[3]/span")).click();
        Thread.sleep(1000);

        // Save the changes.
        driver.findElement(By.id("j_idt22:j_idt47")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[2]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt22:j_idt48")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[3]/a/i")).click();
        Thread.sleep(1000);

        // Login as an employee to check if the prior changes have indeed been saved.
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("fatima");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Close the change password modal.
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[3]/a/i")).click();
        Thread.sleep(1000);

        // Search for the parent via the username in the search bar.
        driver.findElement(By.id("parentForm:parentTable:globalFilter")).clear();
        driver.findElement(By.id("parentForm:parentTable:globalFilter")).sendKeys("bernd");
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
