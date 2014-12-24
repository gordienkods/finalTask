package navigator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.log4j.Logger;

public class AnyWebDriver {

    public WebDriver getWebDriver (){
        WebDriver driver;
        String anyWebDriver = System.getProperty("anyWebDriver");
        if ("chromedriver".equals(anyWebDriver)){
            try{
                System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/QA tools/chromedriver.exe");
                driver = new ChromeDriver();
                return driver;
            } catch (IllegalStateException e){
                driver = new FirefoxDriver();
                return driver;
            }
        }
        if ("firefox".equals(anyWebDriver)){
            try{
                driver = new FirefoxDriver();
                return driver;
            } catch (IllegalStateException e){
                return null;
            }
        }
        return null;
    }
}
