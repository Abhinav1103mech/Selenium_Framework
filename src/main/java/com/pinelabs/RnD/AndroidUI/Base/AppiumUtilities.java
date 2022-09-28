package com.pinelabs.RnD.AndroidUI.Base;

import com.pinelabs.RnD.AndroidUI.Constants.FilePaths;
import com.pinelabs.RnD.CommonUtils.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class AppiumUtilities {
    //    static String Appium_Node_Path = "C:\\Program Files (x86)\\Appium\\node.exe";
//    static String Appium_JS_Path = "C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js";
    static AppiumDriverLocalService service;
    static AppiumServiceBuilder builder;
    private static DesiredCapabilities caps;
    static URL url;
    static AppiumDriver<MobileElement> driver;
    static WebDriverWait wait;

    private static DesiredCapabilities setCapabilities() {
        caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, CommonUtils.readPropertyfile(FilePaths.devicePropertiesPath).getProperty("PLATFORM_NAME"));
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, CommonUtils.readPropertyfile(FilePaths.devicePropertiesPath).getProperty("PLATFORM_VERSION"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, CommonUtils.readPropertyfile(FilePaths.devicePropertiesPath).getProperty("DEVICE_NAME"));
        caps.setCapability(MobileCapabilityType.UDID, CommonUtils.readPropertyfile(FilePaths.devicePropertiesPath).getProperty("UDID"));
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, CommonUtils.readPropertyfile(FilePaths.devicePropertiesPath).getProperty("NEW_COMMAND_TIMEOUT"));
        //  caps.setCapability("–session-override", true);
        //caps.setCapability(MobileCapabilityType.APPLICATION_NAME, "Payments");
        //  caps.setCapability("fullReset", false);
        caps.setCapability("noReset", false);

        // caps.setCapability(“appActivity”,“com.android.settings.Settings”);
        return caps;
    }

////    public static AppiumServiceBuilder launchAppium() {
////        builder = new AppiumServiceBuilder();
////        builder.withCapabilities(caps);
////        builder.usingPort(4723);
////        builder.withIPAddress("0.0.0.0");
////        //  builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
////        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
////        builder.usingDriverExecutable(new File((Appium_Node_Path)));
////        builder.withAppiumJS(new File(Appium_JS_Path));
////        return builder;
////    }
////
////    public static String appiumStart() {
////        // builder = launchAppium();
////        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
////                usingPort(2856).usingDriverExecutable(new File(Appium_Node_Path)).
////                withAppiumJS(new File(Appium_JS_Path)));
////        service = AppiumDriverLocalService.buildService(builder);
////        service.start();
////        try {
////            Thread.sleep(25000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
////        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
////        appiumServiceBuilder.usingPort(4723)
////                .withIPAddress("0.0.0.0").withCapabilities(caps)
////                .withAppiumJS(new File(Appium_JS_Path))
////                .usingDriverExecutable(new File(Appium_Node_Path))
////                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
////                .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, "4824")
////                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
////            //    .withArgument(GeneralServerFlag.CONFIGURATION_FILE, nodeConfigFilePath)
////                .withLogFile(new File(System.getProperty("user.dir") + "/target/resources/appium_server_logs" + Thread.currentThread().getId()));
////        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(appiumServiceBuilder);
////        service.start();
//        service_url = service.getUrl().toString();
//        System.out.println("Service URL is " + service_url);
//
//        return service_url;
//    }

    public static void appiumStop() throws Exception {
        service.stop();

    }

    public static void setUpConnection() {
        caps = setCapabilities();
        // service_url = appiumStart();
        try {
            url = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AndroidDriver<>(url, caps);
        wait = new WebDriverWait(driver, 30);
        System.out.println("Connection with Appium is established");
    }


    public MobileElement getElement(String locator) {
        MobileElement element = null;
        String[] loc = getLocatorTypeAndValue(locator);
        String locatorType = loc[0].substring(1).toUpperCase();
        //System.out.println(locatorType);
        String locatorValue = loc[1];
        //System.out.println(locatorValue);

        switch (locatorType) {
            case "ID":
                element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
                break;
            case "CLASSNAME":
                element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
                break;
            case "XPATH":
                element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
                break;
            case "ACCESSIBILITYID":
            case "ANDROIDUIAUTOMATOR":
            case "iOS UI Automation":
            case "Android View Tag":
            default:
                throw new NoSuchElementException("Please check the locator");
        }
        return element;
    }

    public List<WebElement> getElements(String locator) {
        List<WebElement> element = null;
        String[] loc = getLocatorTypeAndValue(locator);
        String locatorType = loc[0].substring(1).toUpperCase();
        String locatorValue = loc[1];
        switch (locatorType) {
            case "ID":
                element = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
                break;
            case "CLASSNAME":
            case "XPATH":
                element = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
                break;
            case "ACCESSIBILITYID":
                driver.findElementsByAccessibilityId(locatorValue);
                break;
            case "ANDROIDUIAUTOMATOR":
            case "iOS UI Automation":
            case "Android View Tag":
            default:
                throw new NoSuchElementException("Please check the locator");
        }
        return element;

    }

    public int getElementsSize(String locator) {

        return getElements(locator).size();
    }

    private String[] getLocatorTypeAndValue(String locator) {
        String[] str = locator.split("\\]: ", 0);
        return str;
    }

    public void clickOnElement(String locator) {

        if (isElementDisplayed(locator)) {
            getElement(locator).click();
        } else
            throw new NoSuchElementException("Element not found . Please check locator!" + locator);
        //   System.out.println("Clicked on " + locator);
    }

    public void clickUsingJS(String locator) {
        WebElement element = getElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", "//*[@text='UPI']");
    }

    public String getElementText(String locator) {
        return getElement(locator).getText();
    }

    public void setElementText(String locator, String value) {
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
    }


    public void getsize() {
        Dimension dimension = driver.manage().window().getSize();
        System.out.println(dimension);
    }

    public void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();

        int start_x = (int) (dimension.getWidth() * 0.5);
        int start_y = (int) (dimension.getHeight() * 0.5);
        int end_x = (int) (dimension.getWidth() * 0.2);
        int end_y = (int) (dimension.getHeight() * 0.2);
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(start_x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(end_x, end_y)).release().perform();
    }


    public String getAttribute(String locator, String attributeName) {

        return getElement(locator).getAttribute(attributeName);
    }

    public boolean isElementPresent(String locator, String attributeName) {
        try {
            if (getAttribute(locator, attributeName).equalsIgnoreCase("true"))
                System.out.println("Attribute " + attributeName + " is true");
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public boolean isRadioButtonSelected(String locator, String attributeName) {
        if (getAttribute(locator, attributeName).equalsIgnoreCase("true"))
            return true;
        return false;

    }

    public boolean isElementDisplayed(String locator) {
        try {
            if (getElement(locator).isDisplayed()) {
                return true;
            }

        } catch (Exception nse) {
            return false;
        }
        return true;
    }

    public boolean isElementSelected(String locator) {
        if (getElement(locator).isSelected())
            return true;
        return false;

    }

    public boolean isElementEnabled(String locator) {
        if (getElement(locator).isEnabled())
            return true;
        return false;
    }

    public void navigateBack() {
        driver.navigate().back();
    } //9899293631


    private BufferedImage generateImage(MobileElement locator, File screenshot) throws IOException {
        BufferedImage fullImage = ImageIO.read(screenshot);
        Point imageLocation = locator.getLocation();

        int qrCodeImageWidth = locator.getSize().getWidth();
        int qrCodeImageHeight = locator.getSize().getHeight();

        int pointXPosition = imageLocation.getX();
        int pointYPosition = imageLocation.getY();

        BufferedImage qrCodeImage = fullImage.getSubimage(pointXPosition, pointYPosition, qrCodeImageWidth, qrCodeImageHeight);
        ImageIO.write(qrCodeImage, "png", screenshot);

        return qrCodeImage;
    }

//    public String decodeQr(String locator) {
//        MobileElement qrCodeElement = getElement(locator);
//        File screenshot = driver.getScreenshotAs(OutputType.FILE);
//        String content = null;
//        try {
//            content = decodeQRCode(generateImage(qrCodeElement, screenshot));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("content = " + content);
//        return content;
//    }

//    private static String decodeQRCode(BufferedImage qrCodeImage) throws NotFoundException {
//        Result result = null;
//        LuminanceSource source = new BufferedImageLuminanceSource(qrCodeImage);
//        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//        try {
//            result = new MultiFormatReader().decode(bitmap);
//        } catch (com.google.zxing.NotFoundException e) {
//            e.printStackTrace();
//        }
//        String qrtext = result.getText();
//        System.out.println("QR code text is : " + qrtext);
//        return qrtext;
//    }

    public static void closeAppInstace(String appName) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.terminateApp("payments");
    }

    public static String getScreenshot(ITestResult result) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-h-m-s");
        System.out.println("Date is " + dateFormat.format(date));
        String methodName = result.getMethod().getMethodName() + "_";
        String name = "FailedMethod" + methodName + dateFormat.format(date) + ".png";
        System.out.println("Name of the string is " + name);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = FilePaths.extentReportScreenshot + result.getName() + ".png";
        File dest = new File(path);
        try {
            FileUtils.copyFile(screenshotFile, dest);
        } catch (IOException e) {
            System.out.println("Could not capture screenshot" + e.getMessage());
        }
        return path;
    }

    public void closeApp(String appPackageName) {
        driver.terminateApp(appPackageName);
    }

    public void tearDown() {
        driver.quit();
    }
}
