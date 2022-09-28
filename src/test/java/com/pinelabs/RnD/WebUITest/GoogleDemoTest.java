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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GoogleDemoTest extends TestUtils {
    GoogleDemoPage googleDemoPageInstance;
    CommonPage commonPage;
    Map<Object, Object> values;

    @BeforeClass
    public void getInstances() {
        commonPage = getCommonPageInstance();
        googleDemoPageInstance = GoogleDemoPage.getInstance();
    }

	/*
	 * @DataProvider(name = "NewData") public Object[][] checkExcelUtil() {
	 * 
	 * Object[][] values = ExcelUtility.excelFileReading(); return values; }
	 * 
	 * @Test(dataProvider = "NewData") public void m2() {
	 * ExcelUtility.excelFileReading(); }
	 * 
	 * @Test(description = "Checking JSON utility") public void fetchJsonData() {
	 * JSONUtility.getValueFromJson(FilePaths.jsonDataFilePath, "name"); }
	 * 
	 * @Test(description = "This test is to Launch google.com") public void
	 * launchGoogle() { SQLDatabaseUtility.setUPConnection(); values = new
	 * HashMap<>(); extentLogger.assignAuthor("Abhijeet").assignCategory("SMOKE").
	 * assignDevice("Google chrome");
	 * googleDemoPageInstance.initialization("httpss://www.google.com");
	 * extentLogger.info("Google launched success!");
	 * values.put(DatabaseEnums.ColumnNames.Column_Name, "MID");
	 * values.put(DatabaseEnums.ColumnNames.Value, "1234");
	 * values.put(DatabaseEnums.ColumnNames.TableName, "TableName");
	 * SQLDatabaseUtility.executeSelectquery(SQLQueries.selectQueryWithWhereClause,
	 * values); }
	 * 
	 * @Test(description = "This test is to Searching for stephan Hawkins", priority
	 * = 1) public void searchGoogle() {
	 * extentLogger.assignAuthor("Abhijeet").assignCategory("SMOKE").
	 * assignDevice("Google chrome");
	 * googleDemoPageInstance.searchText("Stephan Hawkins");
	 * extentLogger.info("Searching for text Stephan Hawkins"); }
	 */
    

    @Test(description = "abc")
    public void launchpcui() {
     googleDemoPageInstance.initialization("https://192.168.100.192:8050/LoginPage.aspx");
        googleDemoPageInstance.captchatxt();
    }

    @AfterMethod
    public void generate(ITestResult result) {
        ExtentSparkReport.generateReport(result);
    }
}
