package at.qe.sepm.asn_app.tests.selenium.employee;

/**
 * Created by Emanuel Striednig <emanuel.striednig@student.uibk.ac.at>
 * on 20.06.2017.
 */

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * This test logs in as an employee and creates a new parent.
 * Then their data is edited and finally the new parent is deleted
 */
public class CreateEditDeleteParent {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = InitializeSelenium.initialize();
        baseUrl = InitializeSelenium.BASE_URL;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCreateEditDeleteChild() throws Exception {
        // login
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("cheng");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        // close password change dialog
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        // navigate
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        // create parent
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[3]/a/i")).click();
        driver.findElement(By.id("j_idt118")).click();
        Thread.sleep(1000);
        // fill form
        driver.findElement(By.id("parentForm:usernameCreate")).clear();
        driver.findElement(By.id("parentForm:usernameCreate")).sendKeys("aaron");
        driver.findElement(By.id("parentForm:firstnameCreate")).clear();
        driver.findElement(By.id("parentForm:firstnameCreate")).sendKeys("Aaron");
        driver.findElement(By.id("parentForm:lastnameCreate")).clear();
        driver.findElement(By.id("parentForm:lastnameCreate")).sendKeys("Jolibert");
        driver.findElement(By.id("parentForm:streetCreate")).clear();
        driver.findElement(By.id("parentForm:streetCreate")).sendKeys("Straße 1");
        driver.findElement(By.id("parentForm:locationCreate")).clear();
        driver.findElement(By.id("parentForm:locationCreate")).sendKeys("Ort");
        driver.findElement(By.id("parentForm:postcodeCreate")).clear();
        driver.findElement(By.id("parentForm:postcodeCreate")).sendKeys("1234");
        driver.findElement(By.id("parentForm:phonenumberCreate")).clear();
        driver.findElement(By.id("parentForm:phonenumberCreate")).sendKeys("123456789");
        driver.findElement(By.id("parentForm:emailCreate")).clear();
        driver.findElement(By.id("parentForm:emailCreate")).sendKeys("mail@domain.com");
        Thread.sleep(1000);
        // save
        driver.findElement(By.id("parentForm:j_idt191")).click();
        //WebElement element = driver.findElement(By.id("parentForm:j_idt191"));
        //Actions actions = new Actions(driver);
        //actions.moveToElement(element).click().perform();
        Thread.sleep(1000);
        // sort
        driver.findElement(By.cssSelector("span.ui-column-title")).click();
        Thread.sleep(1000);
        // edit
        driver.findElement(By.id("parentForm:parentTable:0:j_idt150")).click();
        Thread.sleep(1000);
        // fill form
        driver.findElement(By.id("parentForm:firstnameEdit")).clear();
        driver.findElement(By.id("parentForm:firstnameEdit")).sendKeys("Aaronus");
        driver.findElement(By.id("parentForm:lastnameEdit")).clear();
        driver.findElement(By.id("parentForm:lastnameEdit")).sendKeys("Jolibertus");
        driver.findElement(By.id("parentForm:streetEdit")).clear();
        driver.findElement(By.id("parentForm:streetEdit")).sendKeys("Straße 2");
        driver.findElement(By.id("parentForm:locationEdit")).clear();
        driver.findElement(By.id("parentForm:locationEdit")).sendKeys("Ortl");
        driver.findElement(By.id("parentForm:postcodeCreate")).clear();
        driver.findElement(By.id("parentForm:postcodeCreate")).sendKeys("12345");
        driver.findElement(By.id("parentForm:phonenumberCreate")).clear();
        driver.findElement(By.id("parentForm:phonenumberCreate")).sendKeys("987654321");
        driver.findElement(By.id("parentForm:emailEdit")).clear();
        driver.findElement(By.id("parentForm:emailEdit")).sendKeys("mail2@server.net");
        // save
        driver.findElement(By.id("parentForm:j_idt211")).click();
        Thread.sleep(1000);
        // delete
        driver.findElement(By.id("parentForm:parentTable:0:j_idt151")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@id='parentForm:j_idt215'])[2]")).click();
        Thread.sleep(1000);
        // logout
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

