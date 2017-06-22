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
 * This test logs in as an employee and creates a new task for a parent.
 * Then it marks the task as completed and deletes it again.
 */
public class CreateEditDeleteTask {
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
		// click day
		driver.findElement(By.xpath("//div[@id='sheduleForm:schedule_container']/div[2]/div/table/tbody/tr/td/div/div/div[5]/div/table/tbody/tr/td[5]")).click();
		Thread.sleep(1000);
		// add task
		driver.findElement(By.id("sheduleForm:reciever")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("sheduleForm:title")).clear();
		driver.findElement(By.id("sheduleForm:title")).sendKeys("Testaufgabe");
		driver.findElement(By.xpath("//div[@id='sheduleForm:advanced']/div[3]/table/tbody/tr[3]/td[2]")).click();
		// save
		driver.findElement(By.id("sheduleForm:addButton")).click();
		Thread.sleep(3000);
		// navigate
		driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[7]/a/i")).click();
		Thread.sleep(1000);
		// set task completed
		driver.findElement(By.xpath("//div[@id='taskForm:taskTable:0:taskStatus']/span[2]")).click();
		Thread.sleep(1000);
		// navigate
		driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[5]/a/i")).click();
		Thread.sleep(1000);
		// delete task
		driver.findElement(By.xpath("//div[@id='sheduleForm:schedule_container']/div[2]/div/table/tbody/tr/td/div/div/div[5]/div[2]/table/tbody/tr/td[5]/a/div/span[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("sheduleForm:deleteButton")).click();
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

