package at.qe.sepm.asn_app.tests.selenium.admin;

import java.util.concurrent.TimeUnit;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class AuditLog {
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
    public void testAuditLog() throws Exception {
        // Login
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Navigate to the AuditLog.
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[4]/a/i")).click();
        Thread.sleep(1000);

        // Change the list size from 10 to 25 and then 50.
        driver.findElement(By.id("j_idt118:auditLogTable:j_id2")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("j_idt118:auditLogTable:j_id2"))).selectByVisibleText("25");
        driver.findElement(By.cssSelector("option[value=\"25\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:j_id2")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("j_idt118:auditLogTable:j_id2"))).selectByVisibleText("50");
        driver.findElement(By.cssSelector("option[value=\"50\"]")).click();
        Thread.sleep(1000);

        // Search for different keywords in the search bar.
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).clear();
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).sendKeys("task");
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).clear();
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).sendKeys("message");
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).clear();
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).sendKeys("created");
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).clear();
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).sendKeys("deleted");
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).clear();
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).sendKeys("admin");
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).clear();
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).sendKeys("bernd");
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).clear();
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).sendKeys("fatima");
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).clear();
        driver.findElement(By.id("j_idt118:auditLogTable:globalFilter")).sendKeys("");
        Thread.sleep(1000);

        // Reload the page to refreshen the list.
        driver.navigate().refresh();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("j_idt118:auditLogTable:j_id2"))).selectByVisibleText("50");
        driver.findElement(By.cssSelector("option[value=\"50\"]")).click();
        Thread.sleep(1000);

        // Sort the AuditLog with all options.
        driver.findElement(By.id("j_idt118:auditLogTable:j_idt134")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:j_idt134")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:j_idt134")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:j_idt136")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:j_idt136")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:j_idt136")).click();
        //Thread.sleep(1000);
        //driver.findElement(By.xpath("//tbody[@id='j_idt118:auditLogTable_data']/tr/td[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:j_idt138")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:j_idt138")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:j_idt138")).click();
        Thread.sleep(1000);

        // Export the AuditLog.
        driver.findElement(By.id("j_idt118:auditLogTable:excel")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:pdf")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:csv")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:auditLogTable:print")).click();
        Thread.sleep(1000);

        // Logout again.
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
