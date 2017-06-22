package at.qe.sepm.asn_app.tests.selenium.parent;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


/**
 *
 */
public class AddCaregiver {
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
    public void testAddCaregiver() throws Exception {
        // Login
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("bernd");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Close the change password modal.
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);

        // Go to the children view.
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li/a/i")).click();
        Thread.sleep(1000);

        // Click on the button to add a caregiver.
        driver.findElement(By.id("childForm:j_idt150")).click();
        Thread.sleep(1000);

        // Choose the first child in the drop down menu
        driver.findElement(By.xpath("//div[@id='childForm:childList']/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:childList_0")).click();
        Thread.sleep(1000);

        // Set all data for the caregiver.
        driver.findElement(By.id("childForm:firstName")).clear();
        driver.findElement(By.id("childForm:firstName")).sendKeys("FirstName");
        driver.findElement(By.id("childForm:lastName")).clear();
        driver.findElement(By.id("childForm:lastName")).sendKeys("LastName");
        driver.findElement(By.id("childForm:phone")).clear();
        driver.findElement(By.id("childForm:phone")).sendKeys("2374824789");

        // Save the caregiver.
        driver.findElement(By.id("childForm:j_idt186")).click();
        Thread.sleep(1000);

        // Search for the caregiver in the search bar.
        driver.findElement(By.id("childForm:caregiverTable:globalFilter")).clear();
        driver.findElement(By.id("childForm:caregiverTable:globalFilter")).sendKeys("FirstName");
        Thread.sleep(1000);
        //driver.navigate().refresh();
        //Thread.sleep(1000);

        // Click on the caregiver to show all data.
        //driver.findElement(By.id("childForm:caregiverTable:0:j_idt163")).click();
        //Thread.sleep(1000);

        // Close the caregiver again
        //driver.findElement(By.xpath("//div[@id='childForm:caregiverEditDialog']/div/a/span")).click();
        //Thread.sleep(1000);

        // Clear the search bar again.
        driver.findElement(By.id("childForm:caregiverTable:globalFilter")).clear();
        driver.findElement(By.id("childForm:caregiverTable:globalFilter")).sendKeys("");
        Thread.sleep(1000);

        // Sort by first name.
        driver.findElement(By.id("childForm:caregiverTable:j_idt154")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:caregiverTable:j_idt154")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:caregiverTable:j_idt154")).click();
        Thread.sleep(1000);

        // Sort by last name.
        driver.findElement(By.id("childForm:caregiverTable:j_idt156")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:caregiverTable:j_idt156")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:caregiverTable:j_idt156")).click();
        Thread.sleep(1000);

        // Sort by telephone number.
        driver.findElement(By.xpath("//th[@id='childForm:caregiverTable:j_idt158']/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//th[@id='childForm:caregiverTable:j_idt158']/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//th[@id='childForm:caregiverTable:j_idt158']/span")).click();
        Thread.sleep(1000);

        // Sort by child.
        driver.findElement(By.xpath("//th[@id='childForm:caregiverTable:j_idt160']/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//th[@id='childForm:caregiverTable:j_idt160']/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//th[@id='childForm:caregiverTable:j_idt160']/span")).click();
        Thread.sleep(1000);

        // Logout
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
