package com.pinelabs.RnD.WebUITest;

import com.pinelabs.RnD.WebUI.CommonUtilsWebUI.ExtentSparkReport;
import com.pinelabs.RnD.WebUI.POM.CommonPage;
import com.pinelabs.RnD.WebUI.POM.YoutubeDemoPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YoutubeDemo extends TestUtils {
    YoutubeDemoPage youtubeDemoPage;
    CommonPage commonPage;

    @BeforeClass
    public void getInstances() {
        youtubeDemoPage = YoutubeDemoPage.getInstance();
        commonPage = CommonPage.getInstance();


    }

    @Test(description = "Launching youtube.com")
    public void launchYoutube() {
        extentLogger.assignAuthor("Vanshika").assignCategory("SMOKE").assignDevice("Google chrome");
        youtubeDemoPage.initialization("https://www.youtube.com");
        extentLogger.info("Youtube launch success!");
        extentLogger.info("Steps completed!");
    }

    @Test(description = "Searching for Azure Devops")
    public void searchYoutube() {
        extentLogger.assignAuthor("Vanshika").assignCategory("Regression").assignDevice("Google chrome");
        youtubeDemoPage.searchText("Azure Devops");
        extentLogger.info("Searching for text AzureDevops");
        extentLogger.info("Steps completed");
    }

    @AfterMethod
    public void generate(ITestResult result) {
        ExtentSparkReport.generateReport(result);
    }
}
