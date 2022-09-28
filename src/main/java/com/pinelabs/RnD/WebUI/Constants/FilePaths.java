package com.pinelabs.RnD.WebUI.Constants;

import java.io.File;

public class FilePaths {
    public static final String resourceFolderPath = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    public static final String webUIUserConfig = resourceFolderPath + "CommonProperties" + File.separator + "WebUIUserConfig.properties";
    public static final String screenshotsPath = System.getProperty("user.dir") + File.separator + "extentReportsOutput" + File.separator + "screenshots" + File.separator;
    public static final String googleDemoProperties = resourceFolderPath + "WebUILocatorsRepo" + File.separator + "GoogleDemo.properties";
    public static final String extenthtmlReportPath = System.getProperty("user.dir") + File.separator + "extentReportsOutput" + File.separator + "htmlReports" + File.separator;
    public static final String extentReportProperties = resourceFolderPath + "CommonProperties" + File.separator + "ExtentReports.properties";
    public static final String youTubeDemoProperties = resourceFolderPath + "WebUILocatorsRepo" + File.separator + "YoutubeDemo.properties";
    public static final String jsonDataFilePath = resourceFolderPath + "TestData" + File.separator + "JSONData.json";
    public static final String excelDataFile = resourceFolderPath + "TestData";
    public static final String excelDataProperties = resourceFolderPath + "CommonProperties" + File.separator + "ExcelSheet.properties";
    public static final String iRISLoginPageProperties = resourceFolderPath + "WebUILocatorsRepo" + File.separator + "IRISLoginPage.properties";
    public static final String dbPropertiesPath= resourceFolderPath + "Database" + File.separator + "db.properties";
    public static final String hardwareOperationProperties = resourceFolderPath + "WebUILocatorsRepo" + File.separator + "HardwareOperation_Microservice.properties";
}
