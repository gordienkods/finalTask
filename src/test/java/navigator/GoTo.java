package navigator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class GoTo {
    private static final Logger log = Logger.getLogger(AnyWebDriver.class);
    public static WebDriver MainWebPage (WebDriver webDriver){
        webDriver.get("http://www.ranorex.com/web-testing-examples/vip/");
        return webDriver;
    }
}
