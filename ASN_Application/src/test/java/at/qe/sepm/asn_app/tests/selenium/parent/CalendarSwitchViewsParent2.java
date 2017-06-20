package at.qe.sepm.asn_app.tests.selenium.parent;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CalendarSwitchViewsParent2 {
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
  public void testCalendarSwitchViewsParent2() throws Exception {
    driver.get(baseUrl + "/login.xhtml");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("bernd");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("passwd");
    driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt118:j_idt120")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:massBox']/div[3]/table/tbody/tr/td[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:massBox']/div[3]/table/tbody/tr[2]/td[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:massBox']/div[3]/table/tbody/tr/td[2]")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("sheduleForm:massCheck:0_clone")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:opt11']/div[2]/span")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:opt11']/div[2]/span")).click();
    //Thread.sleep(1000);
    //driver.findElement(By.id("sheduleForm:massCheck:2_clone")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:opt21']/div[2]/span")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:opt21']/div[2]/span")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("sheduleForm:massCheck:4_clone")).click();
    //Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:opt31']/div[2]/span")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:opt41']/div[2]/span")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='sheduleForm:opt51']/div[2]/span")).click();
    Thread.sleep(1000);


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

    driver.findElement(By.id("sheduleForm:j_idt242")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
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
