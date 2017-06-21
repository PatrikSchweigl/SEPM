package at.qe.sepm.asn_app.tests.selenium.admin;

import java.util.concurrent.TimeUnit;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;


public class LoginLogout {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = InitializeSelenium.initialize();
        baseUrl = "http://192.168.33.10:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testLoginLogout() throws Exception {
        Login.login(driver, baseUrl);
        driver.findElement(By.cssSelector("a.sidebar-toggle")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("a.sidebar-toggle")).click();
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
