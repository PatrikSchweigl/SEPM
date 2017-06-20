package at.qe.sepm.asn_app.tests.selenium.admin;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CalendarSwitchViews {
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
    //driver.findElement(By.id("j_idt117:j_idt118")).click();
    driver.findElement(By.id("j_idt118:j_idt119")).click();
    Thread.sleep(1000);
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _self | 30000]]
    //driver.findElement(By.xpath("//div[@id='j_idt120']/h3[2]")).click();
    driver.findElement(By.xpath("//div[@id='j_idt121']/h3[2]")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("j_idt117:j_idt118")).click();
    driver.findElement(By.id("j_idt118:j_idt119")).click();
    Thread.sleep(1000);
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _self | 30000]]
    //driver.findElement(By.id("j_idt117:j_idt119")).click();
    driver.findElement(By.id("j_idt118:j_idt120")).click();
    Thread.sleep(1000);
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _self | 30000]]
    //driver.findElement(By.xpath("//div[@id='j_idt119']/h3[2]")).click();
    driver.findElement(By.xpath("//div[@id='j_idt120']/h3[2]")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("j_idt117")).click();
    driver.findElement(By.id("j_idt118")).click();
    Thread.sleep(1000);
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _self | 30000]]
    //driver.findElement(By.id("j_idt117:j_idt120")).click();
    driver.findElement(By.id("j_idt118:j_idt121")).click();
    Thread.sleep(1000);
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _self | 30000]]
    //driver.findElement(By.xpath("//div[@id='j_idt120']/h3[2]")).click();
    driver.findElement(By.xpath("//div[@id='j_idt121']/h3[2]")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("j_idt117:j_idt118")).click();
    driver.findElement(By.id("j_idt118:j_idt119")).click();
    Thread.sleep(1000);
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _self | 30000]]
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
