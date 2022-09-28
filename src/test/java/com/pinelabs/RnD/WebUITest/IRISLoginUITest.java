package com.pinelabs.RnD.WebUITest;

import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.ExcelUtility;
import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.ExtentSparkReport;
import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.JSONUtility;
import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.SQLDatabaseUtility;
import com.pinelabs.RnD.WebUI.Constants.DatabaseEnums;
import com.pinelabs.RnD.WebUI.Constants.FilePaths;
import com.pinelabs.RnD.WebUI.Constants.SQLQueries;
import com.pinelabs.RnD.WebUI.POM.CommonPage;
import com.pinelabs.RnD.WebUI.POM.GoogleDemoPage;
import com.pinelabs.RnD.WebUI.POM.IRISLoginPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class IRISLoginUITest extends TestUtils {
    IRISLoginPage LoginPageInstance;
    CommonPage commonPage;
    Map<Object, Object> values;

    @BeforeClass
    public void getInstances() {
        commonPage = getCommonPageInstance();
        LoginPageInstance = IRISLoginPage.getInstance();
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

    @Test(description = "This test is to Launch IRIS UI")
    public void launchIRISUI() {
   
        extentLogger.assignAuthor("Himanshu").assignCategory("SMOKE").assignDevice("Google chrome");
        LoginPageInstance.initialization("http://pleadevirisui.s3-website.ap-south-1.amazonaws.com/");
        extentLogger.info("IRIS UI launched success!");
        LoginPageInstance.enterUsername("himanshu.sisodia");
        LoginPageInstance.enterPassword("Swift@123456");
        LoginPageInstance.enterCaptchatxt();
        LoginPageInstance.performLogin();
       
     
        
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
}

