package navigator;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ConnectDisconnectDatabase {
    private WebDriver webDriver;

    public ConnectDisconnectDatabase (WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void getWebPage(){
        webDriver.get("http://www.ranorex.com/web-testing-examples/vip/");
    }

    public void connectToDatabase(){
        CommonElementsOperations elements = new CommonElementsOperations(webDriver);
        String parentWindowHandle = webDriver.getWindowHandle();
        String popupWindowHandle = null;
        Set<String> windowSet = null;
        Iterator iterator = null;

        elements.conDisconButtonClick();
        elements.conDisconButtonClick();
        windowSet = webDriver.getWindowHandles();
        iterator = windowSet.iterator();
        while(iterator.hasNext()) {
            popupWindowHandle = iterator.next().toString();
            if(parentWindowHandle.equals(popupWindowHandle) == false){
                webDriver.switchTo().window(popupWindowHandle);
                webDriver.findElement(By.xpath("//button[text()='OK']")).click();
            }
        }

        webDriver.switchTo().window(parentWindowHandle);
        Wait<WebDriver> wait = new WebDriverWait(webDriver, 10, 500);
        wait.until(ExpectedConditions.textToBePresentInElement(elements.getDatabaseCondition(), "Online"));
    }
}
