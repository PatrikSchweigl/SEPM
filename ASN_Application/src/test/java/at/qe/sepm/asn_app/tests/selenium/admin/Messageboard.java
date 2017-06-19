package at.qe.sepm.asn_app.tests.selenium.admin;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Messageboard {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = InitializeSelenium.initialize();
    baseUrl = "http://192.168.33.10:8080/";
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
    driver.findElement(By.id("j_idt117")).click();
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
    driver.findElement(By.id("j_idt117")).click();
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
    driver.findElement(By.linkText("Pinnwand")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt117")).click();
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
    driver.findElement(By.xpath("//a[@id='messages:j_idt120:2:j_idt124']/span/i")).click();
    Thread.sleep(1000);
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
    driver.findElement(By.xpath("//a[@id='messages:j_idt120:1:j_idt124']/span/i")).click();
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
    driver.findElement(By.xpath("//a[@id='messages:j_idt120:0:j_idt124']/span/i")).click();
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

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
