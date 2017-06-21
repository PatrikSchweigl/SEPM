package at.qe.sepm.asn_app.tests.selenium.employee;

/**
 * Created by Emanuel Striednig <emanuel.striednig@student.uibk.ac.at>
 * on 21.06.2017.
 */

import java.util.concurrent.TimeUnit;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

/**
 * This test logs in as an employee and creates a new lunch.
 * Then its data is edited and finally the new lunch is deleted
 */
public class CreateEditDeleteLunch {
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
        // create lunch
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[6]/a/i")).click();
        driver.findElement(By.id("j_idt118")).click();
        Thread.sleep(1000);
        // fill form
        driver.findElement(By.id("lunchForm:meal")).clear();
        driver.findElement(By.id("lunchForm:meal")).sendKeys("Ananas");
        driver.findElement(By.id("lunchForm:cost")).clear();
        driver.findElement(By.id("lunchForm:cost")).sendKeys("3.5");
        driver.findElement(By.id("lunchForm:date_input")).clear();
        driver.findElement(By.id("lunchForm:date_input")).sendKeys("17/07/2017");
        // save
        driver.findElement(By.id("lunchForm:lunchAddDialog_title")).click();
        Thread.sleep(1000);
        // sort
        driver.findElement(By.id("lunchForm:j_idt156")).click();
        Thread.sleep(1000);
        // edit
        driver.findElement(By.xpath("//th[@id='lunchForm:lunchTable:j_idt135']/span[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("lunchForm:lunchTable:0:j_idt144")).click();
        Thread.sleep(1000);
        // fill form
        driver.findElement(By.id("lunchForm:mealEdit")).clear();
        driver.findElement(By.id("lunchForm:mealEdit")).sendKeys("Ananaskompott");
        driver.findElement(By.id("lunchForm:costEdit")).clear();
        driver.findElement(By.id("lunchForm:costEdit")).sendKeys("4.2");
        driver.findElement(By.id("lunchForm:dateEdit")).click();
        driver.findElement(By.id("lunchForm:dateEdit_input")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("lunchForm:dateEdit_input")).sendKeys("27/07/2017");
        // save
        driver.findElement(By.id("lunchForm:j_idt165")).click();
        Thread.sleep(1000);
        // sort
        driver.findElement(By.id("lunchForm:lunchTable:0:j_idt145")).click();
        Thread.sleep(1000);
        // delete
        driver.findElement(By.xpath("(//button[@id='lunchForm:j_idt147'])[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("lunchForm:lunchTable:j_idt135")).click();
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

