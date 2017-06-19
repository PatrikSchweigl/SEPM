package at.qe.sepm.asn_app.tests.selenium2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Bernd Menia <bernd.menia@student.uibk.ac.at>
 * on 19.06.17 11:48 CEST.
 */
public class InitializeSelenium {

    public static WebDriver initialize() {
        // Set the path to the firefox and chrome driver.
        //System.setProperty("webdriver.gecko.driver", "geckodriver");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        //return new FirefoxDriver();
        return new ChromeDriver();
    }
}
