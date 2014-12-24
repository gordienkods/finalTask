package navigator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonElementsOperations {
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

    public void conDisconButtonClick(){
        conDiscButton.click();
    }

    public WebElement getDatabaseCondition(){
        return dbCondition;
    }
    public String getDatabaseConditionText(){
        return dbCondition.getText();
    }


    public void sendVipFirstName(String vipFirstName){
        firstNameField.sendKeys(vipFirstName);
    }

    public void sendVipLastName(String vipLastName){
        lastNameField.sendKeys(vipLastName);
    }

    public void addButtonClick(){
        addButton.click();
    }

    public void saveButtonClick(){
        saveButton.click();
    }

    public void deleteButtonClick(){
        deleteButton.click();
    }
    public boolean deleteButtonIsEnabled(){
        return deleteButton.isEnabled();
    }

    public void clearButtonClick(){
        clearButton.click();
    }

    public void loadButtonClick(){
        loadButton.click();
    }

    public void femaleGenderClick(){
        femaleGender.click();
    }

    public void maleGenderClick(){
        maleGender.click();
    }

    public String getVipCount(){
        return vipCount.getText();
    }

    public String successfulAddVipsGetText(){
        return successfulAddVips.getText();
    }

    public WebElement getListOfCategory(){
        return listOfCategory;
    }
}
