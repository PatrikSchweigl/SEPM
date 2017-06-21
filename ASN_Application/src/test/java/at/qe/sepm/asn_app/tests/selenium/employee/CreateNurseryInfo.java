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
 * This test logs in as an employee and creates a new NurseryInformation.
 */
public class CreateNurseryInfo {
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
		driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
		// navigate
		driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
		driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[9]/a/i")).click();
		// create nursery info
		driver.findElement(By.id("j_idt117")).click();
		Thread.sleep(1000);
		// fill form
		driver.findElement(By.id("infoForm:maxOcc")).clear();
		driver.findElement(By.id("infoForm:maxOcc")).sendKeys("25");
		driver.findElement(By.id("infoForm:originDate_input")).clear();
		driver.findElement(By.id("infoForm:originDate_input")).sendKeys("29.07.2017");
		driver.findElement(By.id("infoForm:bringFrom_input")).clear();
		driver.findElement(By.id("infoForm:bringFrom_input")).sendKeys("07:15");
		driver.findElement(By.id("infoForm:bringTo_input")).clear();
		driver.findElement(By.id("infoForm:bringTo_input")).sendKeys("08:00");
		driver.findElement(By.id("infoForm:pickUpFrom_input")).clear();
		driver.findElement(By.id("infoForm:pickUpFrom_input")).sendKeys("12:15");
		driver.findElement(By.id("infoForm:pickUpTo_input")).clear();
		driver.findElement(By.id("infoForm:pickUpTo_input")).sendKeys("13:00");
		// save
		driver.findElement(By.id("infoForm:j_idt146")).click();
		Thread.sleep(1000);
		// sort
		driver.findElement(By.xpath("//th[@id='infoForm:infoTable:j_idt127']/span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//th[@id='infoForm:infoTable:j_idt127']/span")).click();
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

