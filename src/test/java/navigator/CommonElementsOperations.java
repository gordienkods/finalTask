package navigator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonElementsOperations {
    private static final Logger log = Logger.getLogger(AnyWebDriver.class);
    private WebDriver webDriver;
    public CommonElementsOperations (WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "FirstName") WebElement firstNameField;
    @FindBy (id = "LastName") WebElement lastNameField;
    @FindBy (xpath = "//input[@value='female']") WebElement femaleGender;
    @FindBy (xpath = "//input[@value='male']") WebElement maleGender;
    @FindBy (id = "Add") WebElement addButton;
    @FindBy (id = "Category") WebElement listOfCategory;
    @FindBy (id = "count") WebElement vipCount;
    @FindBy (id = "Save") WebElement saveButton;
    @FindBy (id = "alertTextOK") WebElement successfulAddVips;
    @FindBy (id = "Load") WebElement loadButton;
    @FindBy (id = "Delete") WebElement deleteButton;
    @FindBy (id = "Clear") WebElement clearButton;
    @FindBy (id = "connect") WebElement conDiscButton;
    @FindBy (id = "connection") WebElement dbCondition;

    public void conDisconButtonClick() {
        try{
            conDiscButton.click();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Connect/Disconnect button' not found");
            throw e;
        }
    }

    public WebElement getDatabaseCondition(){
        try{
            return dbCondition;
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Database condition' not found");
            throw e;
        }
    }

    public String getDatabaseConditionText(){
        try{
            return dbCondition.getText();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Database condition' not found");
            throw e;
        }
    }

    public void sendVipFirstName(String vipFirstName){
        try{
            firstNameField.sendKeys(vipFirstName);
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Field First name' not found");
            throw e;
        }
    }

    public void sendVipLastName(String vipLastName){
        try{
            lastNameField.sendKeys(vipLastName);
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Field Last name' not found");
            throw e;
        }
    }

    public void addButtonClick() throws org.openqa.selenium.NoSuchElementException {
        try{
            addButton.click();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Add button' not found");
            throw e;
        }
    }

    public void saveButtonClick(){
        try{
            saveButton.click();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Save button' not found");
            throw e;
        }
    }

    public void deleteButtonClick(){
        try{
            deleteButton.click();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Delete button' not found");
            throw e;
        }
    }

    public boolean deleteButtonIsEnabled(){
        try{
            return deleteButton.isEnabled();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Delete button' not found");
            throw e;
        }
    }

    public boolean loadButtonIsEnabled(){
        try{
            return loadButton.isEnabled();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Load button' not found");
            throw e;
        }
    }

    public boolean saveButtonIsEnabled(){
        try{
            return saveButton.isEnabled();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Save button' not found");
            throw e;
        }
    }

    public void clearButtonClick(){
        try{
            clearButton.click();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Clear button' not found");
            throw e;
        }
    }

    public void loadButtonClick(){
        try{
            loadButton.click();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Load button' not found");
            throw e;
        }
    }

    public void femaleGenderClick(){
        try{
            femaleGender.click();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Female radio' not found");
            throw e;
        }
    }

    public void maleGenderClick(){
        try{
            maleGender.click();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Male radio' not found");
            throw e;
        }
    }

    public String getVipCount(){
        try{
            return vipCount.getText();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Vip count' not found");
            throw e;
        }
    }

    public String successfulAddVipsGetText(){
        try{
            return successfulAddVips.getText();
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Successful string' not found");
            throw e;
        }
    }

    public WebElement getListOfCategory(){
        try{
            return listOfCategory;
        } catch (org.openqa.selenium.NoSuchElementException e){
            log.error("Element 'Category list' not found");
            throw e;
        }
    }
}
