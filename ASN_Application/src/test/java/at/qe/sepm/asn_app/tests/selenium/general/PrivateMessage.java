package at.qe.sepm.asn_app.tests.selenium.general;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


/**
 * Log in as a parent, choose an employee and write some messages
 * to them. Then log in as that employee, and write some messages
 * back to the parent. The final step is to log back in as the
 * parent and write once more some messages to the employee.
 * After all these steps the parent logs out and the test finishes.
 */
public class PrivateMessage {
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
    public void testPrivateMessage() throws Exception {
        // Login as a parent
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

        // Click on private messages.
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li/a/i")).click();
        Thread.sleep(1000);

        // Choose an employee to write a private message.
        driver.findElement(By.id("j_idt49:j_idt51:1:j_idt55")).click();
        Thread.sleep(1000);

        // Click on the message box and write a message.
        driver.findElement(By.id("form:messageInput")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("form:messageInput")).clear();
        driver.findElement(By.id("form:messageInput")).sendKeys("Hello fatima");
        Thread.sleep(1000);

        // Send the message.
        //WebElement tab = driver.findElement(By.id("form:messageInput"));
        //tab.sendKeys(Keys.TAB);
        driver.findElement(By.id("form:j_idt78")).click();
        Thread.sleep(1000);

        // Write another one.
        driver.findElement(By.id("form:messageInput")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("form:messageInput")).clear();
        driver.findElement(By.id("form:messageInput")).sendKeys("How are you doing?");
        driver.findElement(By.id("form:j_idt78")).click();
        Thread.sleep(1000);

        // Close the private message modal.
        driver.findElement(By.xpath("//div[@id='privateMessageSend']/div/a/span")).click();
        Thread.sleep(1000);

        // Logout
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[3]/a/i")).click();
        Thread.sleep(1000);

        // Log in with the employee to which the parent wrote to.
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("fatima");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Close the change password modal.
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);

        // Click on private messages.
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li/a/i")).click();
        Thread.sleep(1000);

        // Choose a parent to write to.
        driver.findElement(By.id("j_idt49:j_idt59:3:j_idt63")).click();
        Thread.sleep(1000);

        // ?????????
        // Write a message
        driver.findElement(By.id("privateMessageSend_title")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("form:messageInput")).clear();
        driver.findElement(By.id("form:messageInput")).sendKeys("Good day bernd.");
        driver.findElement(By.id("form:j_idt78")).click();
        Thread.sleep(1000);

        // Write another one.
        driver.findElement(By.id("form:messageInput")).clear();
        driver.findElement(By.id("form:messageInput")).sendKeys("I am doing great, thanks. How about yourself?");
        driver.findElement(By.id("form:j_idt78")).click();
        Thread.sleep(1000);

        // Close the private message modal again.
        driver.findElement(By.xpath("//div[@id='privateMessageSend']/div/a/span")).click();
        Thread.sleep(1000);

        // Log out
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[3]/a/i")).click();
        Thread.sleep(1000);

        // Log back in as the parent.
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("bernd");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Close the change password modal.
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);

        // Click on private messages.
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li/a/i")).click();
        Thread.sleep(1000);

        // Choose the employee that we have written to previously.
        driver.findElement(By.id("j_idt49:j_idt51:1:j_idt55")).click();
        Thread.sleep(1000);

        // Write another message.
        driver.findElement(By.id("form:messageInput")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("form:messageInput")).clear();
        driver.findElement(By.id("form:messageInput")).sendKeys("Pretty good.");
        driver.findElement(By.id("form:j_idt78")).click();
        Thread.sleep(1000);

        // Write the last message.
        driver.findElement(By.id("form:messageInput")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("form:messageInput")).clear();
        driver.findElement(By.id("form:messageInput")).sendKeys("I'll catch you later, good bye.");
        driver.findElement(By.id("form:j_idt78")).click();
        Thread.sleep(1000);

        // Close the private message modal.
        driver.findElement(By.xpath("//div[@id='privateMessageSend']/div/a/span")).click();
        Thread.sleep(1000);

        // Log out
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
