package at.qe.sepm.asn_app.tests.selenium.employee;

/**
 * Created by Emanuel Striednig <emanuel.striednig@student.uibk.ac.at>
 * on 22.06.2017.
 */

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * This test logs in as an employee and tests all calendar views
 * and clicks all navigation buttons.
 */
public class CalendarSwitchViews {
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
		Thread.sleep(1000);
		// close password change dialog
		driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
		Thread.sleep(1000);
		// navigate
		driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[5]/a/i")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("j_idt118:j_idt119")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-e")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("span.ui-icon.ui-icon-triangle-1-s")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='j_idt121']/h3[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("j_idt118:j_idt119")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("j_idt118:j_idt120")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='j_idt120']/h3[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='j_idt120']/h3")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='j_idt120']/h3[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("j_idt118")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("j_idt118:j_idt121")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='j_idt121']/h3[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='j_idt121']/h3")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='j_idt121']/h3[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("j_idt118:j_idt119")).click();
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

