package page;

import navigator.AnyWebDriver;
import navigator.CommonElementsOperations;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.util.Iterator;
import java.util.Set;

public class IncorrectOperations {
    private static final Logger log = Logger.getLogger(AnyWebDriver.class);
    private WebDriver webDriver;

    public IncorrectOperations (WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String popupWindowMassageCheck (String expectedMassage){
        CommonElementsOperations elements = new CommonElementsOperations(webDriver);
        String parentWindowHandle = webDriver.getWindowHandle();
        String popupWindowHandle = null;
        String actualMassage = null;
        Set<String> windowSet = null;
        Iterator iterator = null;

        windowSet = webDriver.getWindowHandles();
        iterator = windowSet.iterator();
        while(iterator.hasNext()) {
            popupWindowHandle = iterator.next().toString();
            if(!parentWindowHandle.equals(popupWindowHandle)){
                webDriver.switchTo().window(popupWindowHandle);
                actualMassage = elements.successfulAddVipsGetText();
                webDriver.findElement(By.xpath("//button[text()='OK']")).click();
            }
        }
        webDriver.switchTo().window(parentWindowHandle);
        if (actualMassage.equals(expectedMassage)) {
            log.info("Popup window massage is " + actualMassage);
            return "true";
        }
        return "Unexpected massage in popup window.";
    }

    public boolean getButtonCondition (String buttonName){
        CommonElementsOperations elements = new CommonElementsOperations(webDriver);
        boolean buttonCondition = false;
        if ("Delete".equals(buttonName)){
            buttonCondition = elements.deleteButtonIsEnabled();
        }
        if ("Load".equals(buttonName)){
            buttonCondition = elements.deleteButtonIsEnabled();
        }
        if ("Save".equals(buttonName)){
            buttonCondition = elements.deleteButtonIsEnabled();
        }
        log.info("Condition of button " + buttonName + " is " + buttonCondition);
        return buttonCondition;
    }
}
