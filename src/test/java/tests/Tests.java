package tests;

import navigator.AnyWebDriver;
import navigator.GoTo;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.operationsPage;
import navigator.Elements;
import navigator.ConnectDisconnectDatabase;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Tests {
    private static final Logger log = Logger.getLogger(AnyWebDriver.class);
    WebDriver webDriver = new AnyWebDriver().getWebDriver();

    @Before
    public void setup(){
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
    public void clean(){
        webDriver.close();
    }

    @Test
    public void connection_condition_must_be_offline_when_database_is_disconnected(){
        log.info("starting test...");
        //ARRANGE
        Elements elements = new Elements(GoTo.MainWebPage(webDriver));
        String EXPECTED_RESULT = "Offline", actualResult;
        //ACT
        elements.conDisconButtonClick();
        actualResult = elements.getDatabaseConditionText();
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT,actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void connection_condition_must_be_online_when_database_is_connected(){
        log.info("starting test...");
        //ARRANGE
        Elements elements = new Elements(GoTo.MainWebPage(webDriver));
        ConnectDisconnectDatabase connectToDatabase = new ConnectDisconnectDatabase(webDriver);
        String EXPECTED_RESULT = "Online", actualResult;
        //ACT
        elements.conDisconButtonClick();
        connectToDatabase.connectToDatabase();
        actualResult = elements.getDatabaseConditionText();
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("assert result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void  vip_count_must_change_when_new_vips_are_adding(){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        Elements elements = new Elements(webDriver);
        String EXPECTED_RESULT = "VIP count: 6", actualResult;
        //ACT
        operations.addNewVip("Vasya","Pupkin","Male","Other");
        operations.addNewVip("Yula","Ivanova","Female","Music");
        operations.addNewVip("Gora","Vilkin","Male","Movie");
        operations.addNewVip("Dusya","Petrova","Female","Science");
        operations.addNewVip("Petya","Sidirov","Male","Sport");
        operations.addNewVip("Genya", "Smirnova","Female","Politics");
        actualResult = elements.getVipCount();
        //ASSERT
        org.junit.Assert.assertEquals("Incorrect VIP count value", EXPECTED_RESULT, actualResult);
        log.info("assert result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void vips_data_must_be_input_without_any_changes() {
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        ArrayList<String> gotVipsFromTable = new ArrayList<String>();
        ArrayList<String> sendVipsListToTable =  new ArrayList<String>();
        sendVipsListToTable.add("Vasya Pupkin Male Other");
        sendVipsListToTable.add("Yula Ivanova Female Music");
        sendVipsListToTable.add("Gora Vilkin Male Movie");
        String EXPECTED_RESULT = "true", actualResult;
        //ACT
        operations.addNewVip("Vasya","Pupkin","Male","Other");
        operations.addNewVip("Yula","Ivanova","Female","Music");
        operations.addNewVip("Gora","Vilkin","Male","Movie");
        gotVipsFromTable=operations.getVipInfoFromTable(2,2);
        actualResult=operations.compareListVips(gotVipsFromTable,sendVipsListToTable);
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void after_successfuly_saving_vips_must_be_popup_with_message(){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        String EXPECTED_RESULT = "true", actualResult;
        operations.addNewVip("Vasya","Pupkin","Male","Other");
        operations.addNewVip("Yula","Ivanova","Female","Music");
        operations.addNewVip("Gora","Vilkin","Male","Movie");
        //ACT
        actualResult = operations.saveEnteredVips();
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void load_button_must_load_defined_list_of_vips_in_table(){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        Elements elements = new Elements(webDriver);
        ArrayList<String> listOfLoadedVips = new ArrayList<String>();
        ArrayList<String> etalonVipsList = new ArrayList<String>();
        etalonVipsList.add("Sylvester Stallone Male Movie");
        etalonVipsList.add("Elvis Presley Male Music");
        etalonVipsList.add("Marie Curie Female Science");
        etalonVipsList.add("Andre Agassi Male Sport");
        etalonVipsList.add("Arnold Schwarzenegger Male Politics");
        etalonVipsList.add("Marilyn Monroe Female Other");
        String EXPECTED_RESULT = "true", actualResult;
        //ACT
        elements.loadButtonClick();
        listOfLoadedVips = operations.getVipInfoFromTable(2,2);
        actualResult = operations.compareListVips(listOfLoadedVips, etalonVipsList);
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void delete_button_must_delets_from_table_chose_vip(){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        ArrayList<String> gotVipsList = new ArrayList<String>();
        ArrayList<String> etalonVipsList = new ArrayList<String>();
        String EXPECTED_RESULT = "true", actualResult;
        etalonVipsList.add("Vasya Pupkin Male Other");
        etalonVipsList.add("Gora Vilkin Male Movie");
        //ACT
        operations.addNewVip("Vasya", "Pupkin", "Male", "Other");
        operations.addNewVip("Yula","Ivanova","Female","Music");
        operations.addNewVip("Gora","Vilkin","Male","Movie");
        operations.deleteVipByNumber(2);
        gotVipsList = operations.getVipInfoFromTable(2,2);
        actualResult = operations.compareListVips(etalonVipsList,gotVipsList);
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void delete_button_must_delets_vip_in_table_where_vips_list_was_loaded(){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        Elements elements = new Elements(webDriver);
        ArrayList<String> gotVipsList = new ArrayList<String>();
        ArrayList<String> etalonVipsList = new ArrayList<String>();
        String EXPECTED_RESULT = "true", actualResult;
        //ACT
        elements.loadButtonClick();
        etalonVipsList = operations.getVipInfoFromTable(2,2);
        operations.deleteVipByNumber(3);
        etalonVipsList.remove(2);
        gotVipsList = operations.getVipInfoFromTable(2,2);
        actualResult = operations.compareListVips(etalonVipsList,gotVipsList);
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void  vip_count_must_change_when_vips_are_deleting(){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        Elements elements = new Elements(webDriver);
        String EXPECTED_RESULT = "VIP count: 4", actualResult;
        //ACT
        elements.loadButtonClick();
        operations.deleteVipByNumber(2);
        operations.deleteVipByNumber(2);
        actualResult = elements.getVipCount();
        //ASSERT
        org.junit.Assert.assertEquals("Incorrect VIP count value", EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void clear_button_must_clears_from_table_all_added_vips(){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        Elements elements = new Elements(webDriver);
        Integer EXPECTED_RESULT = 1, actualResult;
        //ACT
        operations.addNewVip("Vasya", "Pupkin", "Male", "Other");
        operations.addNewVip("Yula","Ivanova","Female","Music");
        operations.addNewVip("Gora","Vilkin","Male","Movie");
        elements.clearButtonClick();
        actualResult = operations.countLineInVipsTable();
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void clear_vips_table_using_delete_button(){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        Integer EXPECTED_RESULT = 1, actualResult;
        //ACT
        operations.addNewVip("Vasya", "Pupkin", "Male", "Other");
        operations.addNewVip("Yula","Ivanova","Female","Music");
        operations.addNewVip("Gora", "Vilkin", "Male", "Movie");
        operations.deleteVipByNumber(1);
        operations.deleteVipByNumber(1);
        operations.deleteVipByNumber(1);
        //ASSERT
        actualResult = operations.countLineInVipsTable();
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void clear_button_must_clears_from_table_all_loaded_vips(){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        Elements elements = new Elements(webDriver);
        Integer EXPECTED_RESULT = 1, actualResult;
        //ACT
        elements.loadButtonClick();
        elements.clearButtonClick();
        actualResult = operations.countLineInVipsTable();
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void add_vip_without_first_name_and_last_name_is_impossible(){
        log.info("starting test...");
        //ARRANGE
        Elements elements = new Elements(GoTo.MainWebPage(webDriver));
        operationsPage operations = new operationsPage(webDriver);
        String EXPECTED_RESULT = "true", actualResult;
        //ACT
        elements.addButtonClick();
        actualResult = operations.popupWindowMassageCheck("Please specify 'First Name' value");
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void add_vip_only_with_first_name_is_impossible(){
        log.info("starting test...");
        //ARRANGE
        Elements elements = new Elements(GoTo.MainWebPage(webDriver));
        operationsPage operations = new operationsPage(webDriver);
        String EXPECTED_RESULT = "true", actualResult;
        //ACT
        elements.sendVipFirstName("Vasya");
        elements.addButtonClick();
        actualResult = operations.popupWindowMassageCheck("Please specify 'Last Name' value");
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void add_vip_only_with_last_name_is_impossible(){
        log.info("starting test...");
        //ARRANGE
        Elements elements = new Elements(GoTo.MainWebPage(webDriver));
        operationsPage operations = new operationsPage(webDriver);
        String EXPECTED_RESULT = "true", actualResult;
        //ACT
        elements.sendVipLastName("Petrov");
        elements.addButtonClick();
        actualResult = operations.popupWindowMassageCheck("Please specify 'First Name' value");
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void delete_button_is_disable_while_vip_table_is_empty (){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        boolean EXPECTED_RESULT = false, actualResult;
        //ACT
        actualResult = operations.getButtonCondition("Delete");
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void save_button_is_disable_while_database_is_disconnected (){
        log.info("starting test...");
        //ARRANGE
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        Elements elements = new Elements(webDriver);
        boolean EXPECTED_RESULT = false, actualResult;
        //ACT
        elements.conDisconButtonClick();
        actualResult = operations.getButtonCondition("Save");
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }

    @Test
    public void load_button_is_disable_while_database_is_disconnected () throws org.openqa.selenium.NoSuchElementException {
        log.info("starting test...");
        //ARRANGE
        Elements elements = new Elements(webDriver);
        operationsPage operations = new operationsPage(GoTo.MainWebPage(webDriver));
        boolean EXPECTED_RESULT = false, actualResult;
        //ACT
        elements.conDisconButtonClick();
        actualResult = operations.getButtonCondition("Load");
        //ASSERT
        org.junit.Assert.assertEquals(EXPECTED_RESULT, actualResult);
        log.info("test result: " + EXPECTED_RESULT + " " + actualResult);
    }
}