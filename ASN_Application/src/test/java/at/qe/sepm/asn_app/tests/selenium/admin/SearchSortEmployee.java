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

public class SearchSortEmployee {
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
  public void testSearchSortEmployee() throws Exception {
    driver.get(baseUrl + "/login.xhtml");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("passwd");
    driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:globalFilter")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:globalFilter")).sendKeys("a");
    Thread.sleep(1000);
    driver.navigate().refresh();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:globalFilter")).clear();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:globalFilter")).sendKeys("b");
    Thread.sleep(1000);
    driver.navigate().refresh();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:globalFilter")).clear();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:globalFilter")).sendKeys("c");
    Thread.sleep(1000);
    driver.navigate().refresh();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:globalFilter")).clear();
    Thread.sleep(1000);
    //driver.findElement(By.id("employeeForm:employeeTable:j_idt134")).click();
    driver.findElement(By.id("employeeForm:employeeTable:j_idt135")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("employeeForm:employeeTable:j_idt134")).click();
    driver.findElement(By.id("employeeForm:employeeTable:j_idt135")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("employeeForm:employeeTable:j_idt136")).click();
    driver.findElement(By.id("employeeForm:employeeTable:j_idt137")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("employeeForm:employeeTable:j_idt136")).click();
    driver.findElement(By.id("employeeForm:employeeTable:j_idt137")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("employeeForm:employeeTable:j_idt138")).click();
    driver.findElement(By.id("employeeForm:employeeTable:j_idt139")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("employeeForm:employeeTable:j_idt138")).click();
    driver.findElement(By.id("employeeForm:employeeTable:j_idt139")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("employeeForm:employeeTable:j_idt140")).click();
    driver.findElement(By.id("employeeForm:employeeTable:j_idt141")).click();
    Thread.sleep(1000);
    //driver.findElement(By.id("employeeForm:employeeTable:j_idt140")).click();
    driver.findElement(By.id("employeeForm:employeeTable:j_idt141")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:excel")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:pdf")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:csv")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:print")).click();
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
