/*
 * package com.pinelabs.RnD.CommonUtils;
 * 
 * 
 * import com.pinelabs.RnD.AndroidUI.Base.AppiumUtilities; import
 * com.pinelabs.RnD.AndroidUI.Constants.FilePaths; import
 * com.aventstack.extentreports.ExtentReports; import
 * com.aventstack.extentreports.ExtentTest; import
 * com.aventstack.extentreports.MediaEntityBuilder; import
 * com.aventstack.extentreports.Status; import
 * com.aventstack.extentreports.markuputils.ExtentColor; import
 * com.aventstack.extentreports.markuputils.MarkupHelper;
 * 
 * import com.aventstack.extentreports.reporter.configuration.Theme; import
 * org.testng.ITestResult;
 * 
 * import java.io.IOException; import java.text.DateFormat; import
 * java.text.SimpleDateFormat; import java.util.Date; import
 * java.util.Properties;
 * 
 * public class ExtentReport { static ExtentReports extentReports; public static
 * ExtentTest extentTestLogger; static String method; static ExtentHtmlReporter
 * htmlReporter; static Properties extentReportProperties;
 * 
 * 
 * public static String generateDynamicName() { DateFormat dateFormat = new
 * SimpleDateFormat("dd-MM-yyyy h-m-s-ms"); Date date = new Date(); return
 * dateFormat.format(date); }
 * 
 * public static void initialiseReport() { extentReports = new ExtentReports();
 * htmlReporter = new ExtentHtmlReporter(FilePaths.extentReportPath +
 * generateDynamicName() + ".html");
 * htmlReporter.config().setChartVisibilityOnOpen(true);
 * htmlReporter.config().setDocumentTitle("UPI Modernization");
 * htmlReporter.config().setReportName("Automation Test Report");
 * htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
 * htmlReporter.config().setTheme(Theme.STANDARD); htmlReporter.config().
 * setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
 * extentReports.attachReporter(htmlReporter); }
 * 
 * 
 * 
 * public static void generateReport(ITestResult result) {
 * 
 * method = result.getMethod().getMethodName();
 * System.out.println("Method name is " + method); extentTestLogger =
 * extentReports.createTest(result.getMethod().getMethodName(),
 * result.getMethod().getDescription()); if (result.getStatus() ==
 * ITestResult.FAILURE) { extentTestLogger.log(Status.FAIL,
 * MarkupHelper.createLabel( "Test case failed due to : " +
 * result.getThrowable().fillInStackTrace() +result.getName()+" FAILED ",
 * ExtentColor.RED)); String screenPath = AppiumUtilities.getScreenshot(result);
 * try { extentTestLogger.fail(result.getThrowable().getMessage(),
 * MediaEntityBuilder.createScreenCaptureFromPath(screenPath).build()); } catch
 * (IOException e) { e.printStackTrace(); } } extentReports.flush(); } }
 */