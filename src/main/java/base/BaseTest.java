package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;

    @BeforeMethod (alwaysRun = true)
    public void setDriver(ITestContext context) {
        String testName = context.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        checkBrowserOS();
        driver.manage().window().maximize();
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        driver.quit();
        log.info("Driver closed");
    }

    public void openURL(String URL) {
        log.info("Opening page " + URL);
        driver.get(URL);
        log.info("Page opened!");
    }

    /** Get browser name, browser version and OS */
    public  void checkBrowserOS() {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = capabilities.getBrowserName();
        String browserVersion = capabilities.getVersion();
        String os = System.getProperty("os.name");

        log.info("Initializing driver: " + browserName + " " + browserVersion + " on " + os);
    }
}
