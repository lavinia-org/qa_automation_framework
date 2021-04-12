package pages;

import modules.HeaderModule;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

    HeaderModule headerModule = new HeaderModule(driver, log);

    public MyAccountPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public HeaderModule getHeaderModule() {
        return headerModule;
    }
}
