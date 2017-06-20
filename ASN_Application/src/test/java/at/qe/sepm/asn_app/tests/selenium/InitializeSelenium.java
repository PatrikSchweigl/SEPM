package at.qe.sepm.asn_app.tests.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 19.06.17 11:48 CEST.
 */
public class InitializeSelenium {

    public static WebDriver initialize() {
        String browser = "chrome";   // use this to change browser
        switch(browser) {
            case "gecko":
                System.setProperty("webdriver.gecko.driver", "geckodriver");
                return new FirefoxDriver();
            case "geckoWin":
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                return new FirefoxDriver();
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                return new ChromeDriver();
            case "chromeWin":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                return new ChromeDriver();
            default: return null;
        }
    }
}
