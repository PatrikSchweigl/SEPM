package at.qe.sepm.asn_app.tests.selenium.admin;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import at.qe.sepm.asn_app.tests.selenium.admin.Login;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateAndDeleteEmployee {
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
  public void testCreateAndDeleteEmployee() throws Exception {
    //driver.get(baseUrl + "/admin/mainpage_admin.xhtml");
    Login.login(driver, baseUrl);
    driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[3]/a/i")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt117")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:usernameCreate")).clear();
    driver.findElement(By.id("employeeForm:usernameCreate")).sendKeys("EmployeeUserName");
    driver.findElement(By.id("employeeForm:firstnameCreate")).clear();
    driver.findElement(By.id("employeeForm:firstnameCreate")).sendKeys("EmployeeFirstName");
    driver.findElement(By.id("employeeForm:lastnameCreate")).clear();
    driver.findElement(By.id("employeeForm:lastnameCreate")).sendKeys("EmployeeLastName");
    driver.findElement(By.id("employeeForm:birthdayCreate")).clear();
    driver.findElement(By.id("employeeForm:birthdayCreate")).sendKeys("11/11/1990");
    driver.findElement(By.id("employeeForm:locationCreate")).clear();
    driver.findElement(By.id("employeeForm:locationCreate")).sendKeys("EmployeeLocation");
    driver.findElement(By.id("employeeForm:streetCreate")).clear();
    driver.findElement(By.id("employeeForm:streetCreate")).sendKeys("EmployeeStreetName");
    driver.findElement(By.id("employeeForm:postcodeCreate")).clear();
    driver.findElement(By.id("employeeForm:postcodeCreate")).sendKeys("6020");
    driver.findElement(By.id("employeeForm:phonenumberCreate")).clear();
    driver.findElement(By.id("employeeForm:phonenumberCreate")).sendKeys("343430924832");
    driver.findElement(By.id("employeeForm:emailCreate")).clear();
    driver.findElement(By.id("employeeForm:emailCreate")).sendKeys("employeeemail@google.com");
    driver.findElement(By.id("employeeForm:familystatusCreate_label")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:familystatusCreate_1")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='employeeForm:employeeDataCreate']/table[3]")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:religionCreate_label")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:religionCreate_1")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:roleCreate_label")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:roleCreate_2")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:j_idt172")).click();
    Thread.sleep(1000);
    driver.navigate().refresh();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:employeeTable:1:j_idt144")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("employeeForm:j_idt205")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[2]/a/i")).click();
    Thread.sleep(1000);
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
