package navigator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.log4j.Logger;

public class AnyWebDriver {
    private static final Logger logWarn = Logger.getLogger(AnyWebDriver.class);
    private static final Logger logInfo = Logger.getLogger(AnyWebDriver.class);
    private static final Logger logErr = Logger.getLogger(AnyWebDriver.class);
    public WebDriver getWebDriver (){
        WebDriver driver;
        String anyWebDriver = System.getProperty("anyWebDriver");
        if ("chromedriver".equals(anyWebDriver)){
            try{
                System.setProperty("webdriver.chrome.driver", ":/Program Files (x86)/QA tools/chromedriver.exe");
                logInfo.info("Starting Chrome...");
                driver = new ChromeDriver();
                logInfo.info("Chrome started successfuly.");
                return driver;
            } catch (IllegalStateException e){
                logWarn.warn("Chrome start failed!");
                logInfo.info("Starting FireFox...");
                driver = new FirefoxDriver();
                logInfo.info("FireFox started successfuly.");
                return driver;
            }
        }
        if ("firefox".equals(anyWebDriver)){
            try{
                logInfo.info("Starting FireFox...");
                driver = new FirefoxDriver();
                logInfo.info("FireFox started successfuly.");
                return driver;
            } catch (IllegalStateException e){
                logErr.error("FireFox started failed!");
                return null;
            }
        }
        return null;
    }
}
