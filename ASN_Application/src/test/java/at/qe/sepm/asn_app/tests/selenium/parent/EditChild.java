package at.qe.sepm.asn_app.tests.selenium.parent;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


public class EditChild {
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
    public void testEditChild() throws Exception {
        // Login as an employee to create a child.
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("fatima");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Close the change password modal.
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);

        // Go to the children view.
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[2]/a/i")).click();
        Thread.sleep(1000);

        // Click on the button to add a child.
        driver.findElement(By.id("j_idt117")).click();
        Thread.sleep(1000);

        // Choose a parent from the list.
        driver.findElement(By.xpath("//div[@id='childForm:parent']/div[3]/table/tbody/tr[3]/td[2]")).click();
        //driver.findElement(By.name("bernd")).click();
        Thread.sleep(1000);

        // Set all data for the child.
        driver.findElement(By.id("childForm:firstName")).clear();
        driver.findElement(By.id("childForm:firstName")).sendKeys("AaChildFirstName");
        driver.findElement(By.id("childForm:lastName")).clear();
        driver.findElement(By.id("childForm:lastName")).sendKeys("AaChildLastName");
        driver.findElement(By.id("childForm:birthDay")).clear();
        driver.findElement(By.id("childForm:birthDay")).sendKeys("11/04/2015");
        driver.findElement(By.id("childForm:imgname")).clear();
        driver.findElement(By.id("childForm:imgname")).sendKeys("AaChildImgName");
        driver.findElement(By.id("childForm:emgNum")).clear();
        driver.findElement(By.id("childForm:emgNum")).sendKeys("7324892349");

        // Choose a gender
        driver.findElement(By.xpath("//div[@id='childForm:genderCreate']/div[3]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:genderCreate_2")).click();
        Thread.sleep(1000);

        // Save the child.
        //driver.findElement(By.xpath("//div[@id='childForm:childDataAdd']/table[2]/tbody/tr/td")).click();
        driver.findElement(By.id("childForm:j_idt192")).click();
        Thread.sleep(1000);

        // Log the employee out again.
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[3]/a/i")).click();
        Thread.sleep(1000);

        // Log in as a parent to edit the created child.
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

        // Set the amount of visible children on the view from 10 to 25 to 50.
        new Select(driver.findElement(By.id("childForm:childTable:j_id2"))).selectByVisibleText("25");
        new Select(driver.findElement(By.id("childForm:childTable:j_id2"))).selectByVisibleText("50");
        driver.findElement(By.cssSelector("option[value=\"50\"]")).click();
        Thread.sleep(1000);

        // Sort the children in descending order.
        driver.findElement(By.id("childForm:childTable:j_idt119")).click();
        Thread.sleep(1000);

        // Click on the edit button of the first child.
        driver.findElement(By.id("childForm:childTable:0:j_idt128")).click();
        Thread.sleep(1000);

        // Overwrite the old data of the child with new one.
        driver.findElement(By.id("childForm:firstname")).clear();
        driver.findElement(By.id("childForm:firstname")).sendKeys("AaChildFirstNameEdit");
        driver.findElement(By.xpath("//div[@id='childForm:childDataEdit']/table/tbody/tr[2]/td")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:lastname")).clear();
        driver.findElement(By.id("childForm:lastname")).sendKeys("AaChildLastNameEdit");
        driver.findElement(By.id("childForm:birthday")).clear();
        driver.findElement(By.id("childForm:birthday")).sendKeys("12/04/2015");
        driver.findElement(By.id("childForm:emergencyNumber")).clear();
        driver.findElement(By.id("childForm:emergencyNumber")).sendKeys("7324892349");
        driver.findElement(By.id("childForm:allergies")).clear();
        driver.findElement(By.id("childForm:allergies")).sendKeys("AaAllergies");
        driver.findElement(By.id("childForm:intolerance")).clear();
        driver.findElement(By.id("childForm:intolerance")).sendKeys("AaIntolerances");

        // Add a Sibling for the child.
        driver.findElement(By.id("childForm:j_idt146")).click();
        Thread.sleep(1000);

        // Set all data for the sibling.
        driver.findElement(By.id("childForm:firstN")).clear();
        driver.findElement(By.id("childForm:firstN")).sendKeys("AaSiblingFirstName");
        driver.findElement(By.id("childForm:lastN")).clear();
        driver.findElement(By.id("childForm:lastN")).sendKeys("AaSiblingLastName");
        driver.findElement(By.id("childForm:birth")).clear();
        driver.findElement(By.id("childForm:birth")).sendKeys("27/08/2016");

        // Save the sibling.
        driver.findElement(By.id("childForm:j_idt175")).click();
        Thread.sleep(1000);

        // Save the child.
        driver.findElement(By.id("childForm:j_idt145")).click();
        Thread.sleep(1000);

        // Sort
        driver.findElement(By.id("childForm:childTable:j_idt121")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:childTable:j_idt121")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:childTable:j_idt121")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:childTable:j_idt123")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:childTable:j_idt123")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:childTable:j_idt123")).click();
        Thread.sleep(1000);

        // Log the parent out again.
        driver.findElement(By.xpath("//div[@id='header']/div/header/nav/div/ul/li[3]/a/i")).click();
        Thread.sleep(1000);

        // Log in as an employee again to delete the child.
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("fatima");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);

        // Close the change password modal.
        driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
        Thread.sleep(1000);

        // Change to the children view.
        driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[2]/a/i")).click();
        Thread.sleep(1000);
        //driver.findElement(By.linkText("Kinder")).click();

        // Sort to find the first child (should be the auto-generated child).
        driver.findElement(By.id("childForm:childTable:j_idt134")).click();
        Thread.sleep(1000);

        // Click on the first element.
        driver.findElement(By.id("childForm:childTable:0:j_idt141")).click();
        Thread.sleep(1000);

        // Search for the child in the search bar.
        /*driver.findElement(By.id("childForm:childTable:globxalFilter")).clear();
        driver.findElement(By.id("childForm:childTable:globalFilter")).sendKeys("ChildFirstName");

        // Click on the first element (should be only left to assure no errors).
        driver.findElement(By.id("childForm:childTable:0:j_idt141")).click();
        Thread.sleep(1000);
        */

        // Close the show child modal again.
        driver.findElement(By.xpath("//div[@id='childForm:childEditDialog']/div/a/span")).click();
        Thread.sleep(1000);

        // Delete the child.
        driver.findElement(By.id("childForm:childTable:0:j_idt142")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("childForm:j_idt195")).click();
        Thread.sleep(1000);

        // Search for the child again. The results should be empty now.
        driver.findElement(By.id("childForm:childTable:globalFilter")).clear();
        driver.findElement(By.id("childForm:childTable:globalFilter")).sendKeys("ChildFirstName");
        driver.findElement(By.id("childForm:childTable:globalFilter")).clear();
        driver.findElement(By.id("childForm:childTable:globalFilter")).sendKeys("");

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
