package at.qe.sepm.asn_app.tests.selenium.employee;

/**
 * Created by Emanuel Striednig <emanuel.striednig@student.uibk.ac.at>
 * on 20.06.2017.
 */

import java.util.concurrent.TimeUnit;

import at.qe.sepm.asn_app.tests.selenium.InitializeSelenium;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class CreateEditDeleteChild {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = InitializeSelenium.initialize();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCreateEditDeleteChild() throws Exception {
		driver.get(baseUrl + "/login.xhtml");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("cheng");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("passwd");
		driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
		driver.findElement(By.xpath("//div[@id='dialogForm:dialogTest']/div/a/span")).click();
		driver.findElement(By.xpath("//div[@id='content2']/aside/section/ul/li[2]/a/i")).click();
		driver.findElement(By.id("j_idt117")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='childForm:parent']/div[3]/table/tbody/tr[3]/td[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("childForm:firstName")).clear();
		driver.findElement(By.id("childForm:firstName")).sendKeys("Abuelita");
		driver.findElement(By.id("childForm:lastName")).clear();
		driver.findElement(By.id("childForm:lastName")).sendKeys("Dolorosa");
		driver.findElement(By.id("childForm:birthDay")).clear();
		driver.findElement(By.id("childForm:birthDay")).sendKeys("01/01/2016");
		driver.findElement(By.id("childForm:emgNum")).clear();
		driver.findElement(By.id("childForm:emgNum")).sendKeys("1234656789");
		driver.findElement(By.xpath("//div[@id='childForm:genderCreate']/div[3]/span")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("childForm:genderCreate_1")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("childForm:j_idt192")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//th[@id='childForm:childTable:j_idt134']/span[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("childForm:childTable:0:j_idt141")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("childForm:firstname")).clear();
		driver.findElement(By.id("childForm:firstname")).sendKeys("Abuela");
		driver.findElement(By.id("childForm:lastname")).clear();
		driver.findElement(By.id("childForm:lastname")).sendKeys("Dolores");
		driver.findElement(By.id("childForm:birthday")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("childForm:birthday")).clear();
		driver.findElement(By.id("childForm:birthday")).sendKeys("03/03/2016");
		WebElement toClear = driver.findElement(By.id("childForm:emergencyNumber"));
		for(int i = 0; i<15; i++)
			toClear.sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.id("childForm:emergencyNumber")).sendKeys("987654321");
		driver.findElement(By.id("childForm:j_idt153")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("childForm:childTable:0:j_idt142")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("childForm:j_idt195")).click();
		Thread.sleep(1000);
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

