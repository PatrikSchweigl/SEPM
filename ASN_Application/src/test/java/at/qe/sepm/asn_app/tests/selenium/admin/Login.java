package at.qe.sepm.asn_app.tests.selenium.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

    public static void login(WebDriver driver, String baseUrl) throws InterruptedException {
        driver.get(baseUrl + "/login.xhtml");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("passwd");
        driver.findElement(By.cssSelector("form.login-form > button.login-button")).click();
        Thread.sleep(1000);
    }

}
