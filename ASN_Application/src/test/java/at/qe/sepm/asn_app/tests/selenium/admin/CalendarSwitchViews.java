package at.qe.sepm.asn_app.tests.selenium.admin;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class CalendarSwitchViews {
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
    public void testCalendar() throws Exception {
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[2]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:j_idt119")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='j_idt121']/h3[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:j_idt119")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:j_idt120")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='j_idt120']/h3[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:j_idt121")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='j_idt121']/h3[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118:j_idt119")).click();
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
