package at.qe.sepm.asn_app.tests.selenium.general;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Messageboard {
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
    public void testMessageboard() throws Exception {
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[5]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("messages:messageInput")).clear();
        driver.findElement(By.id("messages:messageInput")).sendKeys("Hello world =].");
        driver.findElement(By.id("messages:button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[2]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("fatima");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[11]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("messages:messageInput")).clear();
        driver.findElement(By.id("messages:messageInput")).sendKeys("Greetings traveller");
        driver.findElement(By.id("messages:button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[3]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("bernd");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[4]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("j_idt118")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("messages:messageInput")).clear();
        driver.findElement(By.id("messages:messageInput")).sendKeys("We require more minerals!");
        driver.findElement(By.id("messages:button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[3]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[5]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='messages:j_idt121:2:j_idt125']/span/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("messages:j_idt133")).click();
        driver.findElement(By.id("messages:j_idt132")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[2]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("fatima");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[11]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='messages:j_idt121:1:j_idt125']/span/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("messages:j_idt132")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[3]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("bernd");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[4]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='messages:j_idt121:0:j_idt125']/span/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("messages:j_idt132")).click();
        Thread.sleep(1000);
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
