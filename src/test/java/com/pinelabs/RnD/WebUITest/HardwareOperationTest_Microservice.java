package com.pinelabs.RnD.WebUITest;

import com.gargoylesoftware.htmlunit.protocol.about.Handler;
import com.pinelabs.RnD.WebUI.Base.SeleniumUtilities;
import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.ExcelUtility;
import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.ExtentSparkReport;
import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.JSONUtility;
import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.SQLDatabaseUtility;
import com.pinelabs.RnD.WebUI.Constants.DatabaseEnums;
import com.pinelabs.RnD.WebUI.Constants.FilePaths;
import com.pinelabs.RnD.WebUI.Constants.SQLQueries;
import com.pinelabs.RnD.WebUI.POM.CommonPage;
import com.pinelabs.RnD.WebUI.POM.GoogleDemoPage;
import com.pinelabs.RnD.WebUI.POM.HardwareOperationPage_Microservice;
import com.pinelabs.RnD.WebUI.POM.IRISLoginPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;

public class HardwareOperationTest_Microservice extends TestUtils {
	IRISLoginPage LoginPageInstance;
	HardwareOperationPage_Microservice HardwareOperationPageInstance;
    CommonPage commonPage;
    Map<Object, Object> values;
    
  /*  static Logger log = Logger.getLogger("HardwareOperationTest_Microservice"); 
    ConsoleHandler handlerObj = new ConsoleHandler();
    handlerObj.setLevel(Level.ALL);
    log.addHandler(handlerObj);
    log.setLevel(Level.ALL);
    log.setUseParentHandlers(false);
*/
    @BeforeClass
    public void getInstances() {
        commonPage = getCommonPageInstance();
        LoginPageInstance = IRISLoginPage.getInstance();
        HardwareOperationPageInstance = HardwareOperationPage_Microservice.getInstance();
       
    }

   /* @DataProvider(name = "NewData")
    public Object[][] checkExcelUtil() {

        Object[][] values = ExcelUtility.excelFileReading();
        return values;
    }

    @Test(dataProvider = "NewData")
    public void m2() {
        ExcelUtility.excelFileReading();
    }

    @Test(description = "Checking JSON utility")
    public void fetchJsonData() {
        JSONUtility.getValueFromJson(FilePaths.jsonDataFilePath, "name");
    } */

    @Test(description = "This test is to Launch IRIS UI",priority=1)
    public void launchIRISUI() {
   
        extentLogger.assignAuthor("Himanshu").assignCategory("SMOKE").assignDevice("Google chrome");
        LoginPageInstance.initialization("https://d18s925jdugybs.cloudfront.net");
        extentLogger.info("IRIS UI launched success!");
        LoginPageInstance.enterUsername("himanshu.sisodia");
        LoginPageInstance.enterPassword("Swift@123456");
        LoginPageInstance.enterCaptchatxt();
        LoginPageInstance.performLogin();
        
        
        
    }
    @Test(description = "This test is to establish MongoDB Connection",priority=2)
    
    public void establishMongoDBConnection()
    {
    	HardwareOperationPageInstance.getHardwareDetailsFromDB("0821397985","BPCL_CLOUD_URL");
    }
    
    
    @Test(description = "This test is to validate error message on Hardware Operation",priority=3)
    
    public void validateErrorMessage()
    {
    
     HardwareOperationPageInstance.selectIRISMicroservice();
     HardwareOperationPageInstance.enterHardwareID("08213988888");
     HardwareOperationPageInstance.clickOnSearch();
     HardwareOperationPageInstance.validateHardwareID("08213988888");
     extentLogger.info(HardwareOperationPage_Microservice.UIerror);
     extentLogger.info(HardwareOperationPage_Microservice.DBerror);
     Assert.assertEquals(HardwareOperationPage_Microservice.UIerror, HardwareOperationPage_Microservice.DBerror);
    
     
    }
    
 @Test(description = "This test is to perform Hardware Operation",priority=4)
    
    public void performhardwareOperation() throws InterruptedException
    {
	 commonPage.pageRefresh();		
     HardwareOperationPageInstance.selectIRISMicroservice();
     HardwareOperationPageInstance.enterHardwareID("0821397985");
     HardwareOperationPageInstance.clickOnSearch();
     HardwareOperationPageInstance.validateHardwareID("0821397985");
     HardwareOperationPageInstance.validateElement();
     Assert.assertEquals(HardwareOperationPage_Microservice.element, "Parameter name");
     HardwareOperationPageInstance.validateUIParameterValues();
     HardwareOperationPageInstance.setNewParamValue("https://v2.bpclcloud9.com");
     
    
     
    }
    
	/*
	 * @Test(description = "This test is to establish MongoDB Connection") public
	 * void connectionwithMongoDB() {
	 * extentLogger.assignAuthor("Himanshu").assignCategory("SMOKE").
	 * assignDevice("Google chrome");
	 * LoginPageInstance.establishMongoDBConnection();
	 * extentLogger.info("MongoDB Connection establish successfully!"); }
	 */
    
	/*
	 * @Test(description = "This test is to open Global Operation Option") public
	 * void clickGlobalOperation() {
	 * extentLogger.assignAuthor("Himanshu").assignCategory("SMOKE").
	 * assignDevice("Google chrome"); LoginPageInstance.performGlobalOperation();
	 * extentLogger.info("Global Operation clicked successfully!"); }
	 */
    
    

    @AfterMethod
    public void generate(ITestResult result) {
        ExtentSparkReport.generateReport(result); 
    }
    
    @AfterClass
     public void closeConnection()
    {
    	SeleniumUtilities.tearDown();
    }
}

