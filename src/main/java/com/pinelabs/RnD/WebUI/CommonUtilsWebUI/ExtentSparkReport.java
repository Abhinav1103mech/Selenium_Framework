package com.pinelabs.RnD.WebUI.CommonUtilsWebUI;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pinelabs.RnD.WebUI.Base.SeleniumUtilities;
import com.pinelabs.RnD.WebUI.Constants.FilePaths;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentSparkReport {
    public static ExtentReports extent;
    public static ExtentSparkReporter spark;
    public static ExtentTest test, extentLogger;

    public static void initialise() {
        extent = new ExtentReports();
        System.out.println("html Path is " + FilePaths.extenthtmlReportPath + generateDynamicName() + ".html");
        spark = new ExtentSparkReporter(FilePaths.extenthtmlReportPath + generateDynamicName() + ".html");
        setConfig();
    }

    public static void setConfig() {
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("ParagonAutomation_OneOfAKind");
        spark.config().setReportName("AutomationReport_" +
                CommonUtils.readPropertyfile(FilePaths.extentReportProperties).getProperty("ProjectName") + "_" +
                CommonUtils.readPropertyfile(FilePaths.extentReportProperties).getProperty("TeamName"));
        spark.config().setOfflineMode(true);
        extent.attachReporter(spark);
        setSystemInfo();
    }

    public static void setSystemInfo() {
        try {
            extent.setSystemInfo("SYSTEM NAME", InetAddress.getLocalHost().getHostName());
            extent.setSystemInfo("BROWSER NAME", CommonUtils.readPropertyfile(FilePaths.webUIUserConfig).getProperty("Browser"));
            extent.setSystemInfo("OS Name", CommonUtils.readPropertyfile(FilePaths.webUIUserConfig).getProperty("OSName"));
            extent.setSystemInfo("BROWSER VERSION", CommonUtils.readPropertyfile(FilePaths.webUIUserConfig).getProperty("BrowserVersion"));
            extent.setSystemInfo("APPLICATION URL", CommonUtils.readPropertyfile(FilePaths.webUIUserConfig).getProperty("ApplicationURL"));
            extent.setSystemInfo("ENVIRONMENT", CommonUtils.readPropertyfile(FilePaths.webUIUserConfig).getProperty("Environment"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setMethod(Method m, Test result) {
        test = extent.createTest(getClass().getSimpleName() + " : " + m.getName() + "()");
        extentLogger = test.createNode(result.description());
    }

    public static void generateReport(ITestResult result) {

        if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getThrowable().getMessage(), ExtentColor.YELLOW));
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String screenPath = SeleniumUtilities.getScreenshot(result);
            extentLogger.log(Status.FAIL, MarkupHelper.createCodeBlock(result.getThrowable().getMessage()));
            extentLogger.fail("PFB screenshot of failed case :  " + "",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenPath).build());
        }
        extent.flush();

    }


    public static String generateDynamicName() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h-m-s-ms");
        Date date = new Date();
        return dateFormat.format(date);
    }


}
