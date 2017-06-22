package at.qe.sepm.asn_app.tests.selenium.admin;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class SearchSortEmployee {
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

}
