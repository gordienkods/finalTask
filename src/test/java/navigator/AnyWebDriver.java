package navigator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.log4j.Logger;

public class AnyWebDriver {
    private static final Logger log = Logger.getLogger(AnyWebDriver.class);

    public WebDriver getWebDriver (){
        WebDriver driver;
        String anyWebDriver = System.getProperty("anyWebDriver");
        if ("chromedriver".equals(anyWebDriver)){
            try{
                System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/QA tools/chromedriver.exe");
                log.info("Starting Chrome...");
                driver = new ChromeDriver();
                log.info("Chrome started successfuly.");
                return driver;
            } catch (IllegalStateException e){
                log.warn("Chrome start failed!");
                log.info("Starting FireFox...");
                driver = new FirefoxDriver();
                log.info("FireFox started successfuly.");
                return driver;
            }
        }
        if ("firefox".equals(anyWebDriver)){
            try{
                log.info("Starting FireFox...");
                driver = new FirefoxDriver();
                log.info("FireFox started successfuly.");
                return driver;
            } catch (IllegalStateException e){
                log.error("FireFox started failed!");
                return null;
            }
        }
        log.error("Incorrect webdriver settings! Browser start failed!");
        return null;
    }
}
