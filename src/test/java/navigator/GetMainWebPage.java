package navigator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class GetMainWebPage {
    private static final Logger log = Logger.getLogger(AnyWebDriver.class);
    public WebDriver goToWebPage (WebDriver webDriver){
        webDriver.get("http://www.ranorex.com/web-testing-examples/vip/");
        return webDriver;
    }
}
