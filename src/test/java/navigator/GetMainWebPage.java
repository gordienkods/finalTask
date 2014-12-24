package navigator;

import org.openqa.selenium.WebDriver;

public class GetMainWebPage {

    public WebDriver goToWebPage (WebDriver webDriver){
        webDriver.get("http://www.ranorex.com/web-testing-examples/vip/");
        return webDriver;
    }
}
