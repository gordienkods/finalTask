package page;

import navigator.AnyWebDriver;
import navigator.CommonElementsOperations;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class CorrectOperations {
    private static final Logger log = Logger.getLogger(AnyWebDriver.class);
    private WebDriver webDriver;
    private ArrayList<String> vipsList = new ArrayList<String>();
    public CorrectOperations (WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void addNewVip (String firstName, String lastName, String gender, String category){
        CommonElementsOperations elements = new CommonElementsOperations(webDriver);
        Select select = new Select(elements.getListOfCategory());
        elements.sendVipFirstName(firstName);
        elements.sendVipLastName(lastName);
        if ("Female".equals(gender)) { elements.femaleGenderClick(); }
        if ("Male".equals(gender)) { elements.maleGenderClick();}
        if ("Other".equals(category)) {
            select.selectByValue("Other");
            vipsList.add(firstName+" "+lastName+" "+gender+" "+ category);
        }
        if ("Music".equals(category)) {
            select.selectByValue("Music");
            vipsList.add(firstName+" "+lastName+" "+gender+" "+ category);
        }
        if ("Movie".equals(category)) {
            select.selectByValue("Movie");
            vipsList.add(firstName+" "+lastName+" "+gender+" "+ category);
        }
        if ("Science".equals(category)) {
            select.selectByValue("Science");
            vipsList.add(firstName+" "+lastName+" "+gender+" "+ category);
        }
        if ("Sport".equals(category)) {
            select.selectByValue("Sport");
            vipsList.add(firstName+" "+lastName+" "+gender+" "+ category);
        }
        if ("Politics".equals(category)) {
            select.selectByValue("Politics");
            vipsList.add(firstName+" "+lastName+" "+gender+" "+ category);
        }
        elements.addButtonClick();
        log.info("New VIP: " + firstName+" "+lastName+" "+gender+" "+category+" successful added...");
    }

    public ArrayList<String> getVipInfoFromTable(int i, int j) {
        ArrayList<String> allVipData = new ArrayList<String>();
        String vipInfoFromTableString = null;
        Integer countLinesInVipsTable = 1;
        Integer countColumnsInVipsTable = 1;
        do{
            try{
                webDriver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr["+countLinesInVipsTable+"]"));

            }catch (org.openqa.selenium.NoSuchElementException e){
                break;
            }
            countLinesInVipsTable++;
        }while (true);
        do{
            try{
                webDriver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[1]/td[" + countColumnsInVipsTable + "]"));

            }catch (org.openqa.selenium.NoSuchElementException e){
                break;
            }
            countColumnsInVipsTable++;
        }while (true);
        for (int a = i; a < countLinesInVipsTable; a++) {
            vipInfoFromTableString = "";
            for (int b = j; b < countColumnsInVipsTable; b++) {

                vipInfoFromTableString = vipInfoFromTableString + webDriver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[" + a + "]/td[" + b + "]")).getText();
                if (b < countColumnsInVipsTable - 1 ) {vipInfoFromTableString = vipInfoFromTableString + " "; }
            }
            allVipData.add(vipInfoFromTableString);
            log.info("VIP data has read from table: " + vipInfoFromTableString);
        }
        return allVipData;
    }

    public String saveEnteredVips(){
        String parentWindowHandle = webDriver.getWindowHandle();
        CommonElementsOperations elements = new CommonElementsOperations(webDriver);
        String popupWindowHandle = null;
        String successSaveVips = null;
        Set<String> windowSet = null;
        Iterator iterator = null;

        elements.saveButtonClick();
        windowSet = webDriver.getWindowHandles();
        iterator = windowSet.iterator();
        while(iterator.hasNext()) {
            popupWindowHandle = iterator.next().toString();
            if(!parentWindowHandle.equals(popupWindowHandle)){
                webDriver.switchTo().window(popupWindowHandle);
                successSaveVips = elements.successfulAddVipsGetText();
                webDriver.findElement(By.xpath("//button[text()='OK']")).click();
            }
        }
        webDriver.switchTo().window(parentWindowHandle);
        if (successSaveVips.contains("VIP(s) stored sucessfully")) {
            log.info("Successfuly saved entered VIPs...");
            return "true";
        }
        return "Massage about successful added VIPs didn't find";
    }

    public String compareListVips(ArrayList<String> list1, ArrayList<String> list2 ){
        for (int i=0; i<list1.size(); i++){
            if (!list1.get(i).equals(list2.get(i)))
            {
                return "Gave value: " + list1.get(i) + " is different from got value: " + list2.get(i);
            }
        }
        log.info("VIPs list are same");
        return "true";
    }

    public void deleteVipByNumber (Integer vipLineNumber){
        CommonElementsOperations elements = new CommonElementsOperations(webDriver);
        webDriver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[" + (vipLineNumber+1) + "]/td/input[@id='VIP']")).click();
        elements.deleteButtonClick();
        log.info("VIP number " + (vipLineNumber+1) + " deleted");
    }

    public Integer countLineInVipsTable (){
        Integer countLineInVipsTable = 1;
        do{
            try{
                webDriver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr["+countLineInVipsTable+"]"));

            }catch (org.openqa.selenium.NoSuchElementException e){
                log.info("Count of lines in VIPs table is " + (countLineInVipsTable-1) );
                return countLineInVipsTable-1;
            }
            countLineInVipsTable++;
        }while (true);
    }
}