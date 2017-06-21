package at.qe.sepm.asn_app.tests.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 19.06.17 11:48 CEST.
 */
public class InitializeSelenium {
    public static final String BASE_URL = "http://192.168.33.10:8080/";


    public static WebDriver initialize() {
        WebDriver driver;
        String browser = "chrome";   // use this to change browser

        switch (browser) {
            case "gecko":
                System.setProperty("webdriver.gecko.driver", "geckodriver");
                driver = new FirefoxDriver();
                break;
            case "geckoWin":
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                driver = new ChromeDriver();
                break;
            case "chromeWin":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            default:
                driver = null;
        }

        driver.manage().window().maximize();
        return driver;
    }
}
