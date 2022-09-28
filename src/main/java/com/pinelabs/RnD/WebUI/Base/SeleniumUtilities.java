package com.pinelabs.RnD.WebUI.Base;


import com.pinelabs.RnD.CommonUtils.CommonUtils;
import com.pinelabs.RnD.WebUI.Constants.FilePaths;
import io.cucumber.messages.types.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class SeleniumUtilities {

    static WebDriver driver;
    public static WebDriverWait wait;
    static Logger log = Logger.getLogger(SeleniumUtilities.class);
    static Properties userConfig;

    /**
     * Use this method to get the BrowserName set by the User in the
     * config.properties file.
     *
     * @return A string representation of the BrowserName.
     * @throws IOException
     */


    private String browserName() {
        userConfig = CommonUtils.readPropertyfile(FilePaths.webUIUserConfig);
        return userConfig.getProperty("Browser");
    }

    /**
     * Use this method to get the Test URL set by the User in the
     * config.properties file.
     *
     * @return A string representation of the Test URL.
     * @throws IOException
     */
    private String getTestURL(String url) {
        if (userConfig == null) {
            userConfig = CommonUtils.readPropertyfile(url);
            return userConfig.getProperty("ApplicationURL");
        } else {
            return userConfig.getProperty("ApplicationURL");
        }
    }


    /**
     * Use this method to capture the screenshot of the browser window and save
     * the .png file in the Screenshots folder. FileName Format : << >>
     *
     * @param scenario The object containing the result of the test.
     * @return A string representation of the file path to the captured
     * screenshot(.png) file
     * <p>
     * Pending 1. Rename the screenshot file. 2. Read the screenshot
     * filetype from config file and handle in the code.
     */
    public static String getAShot(Scenario scenario) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        log.info("Date is " + dateFormat.format(date));

        String name = scenario.getId().replace(";", "_") + dateFormat.format(date) + ".png";

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver);
        String path = FilePaths.screenshotsPath + File.separator + "Screenshots" + File.separator + name;
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(path));
        } catch (IOException e) {
            log.error("Could not capture screenshot" + e.getMessage());

        }
        return path;
    }

    public String getScreenshotOfElement(String locator) {

        WebElement ele = getElement(locator);
        if (verifyElementIsClickable(ele))

            ele.click();

        Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, ele);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-h-m-s");

        log.info("Date is " + dateFormat.format(date));
        String name = dateFormat.format(date) + ".png";
        String path = FilePaths.screenshotsPath + File.separator + "Screenshots" + File.separator + name;

        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(path));
        } catch (IOException e) {
            log.info("Cannot capture the screenshot because: " + e.getMessage());
        }

        return path;
    }

    /**
     * Use this method to capture the screenshot of the browser window and save
     * the .png file in the Screenshots folder. FileName Format : << >>
     *
     * @param result The object containing the result of the test.
     * @return A string representation of the file path to the captured
     * screenshot(.png) file
     * <p>
     * Pending 1. Rename the screenshot file. 2. Read the screenshot
     * filetype from config file and handle in the code.
     */
    public static String getScreenshot(ITestResult result) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-h-m-s");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        String path = FilePaths.screenshotsPath + result.getName() + dateFormat.format(date) + ".png";
        File dest = new File(path);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            log.error("Could not capture screenshot" + e.getMessage());

        }
        return path;
    }


    /**
     * Use this method to launch browser with the specific capabilities and
     * options as defined in the config.properties file.
     *
     * @throws IOException -Pending Add the config file property BrowserName along with
     *                     the possible values.
     *                     <p>
     *                     Pending 1. Reading the waitTime from Config file. 2. Adding
     *                     the headless chrome and headless firefox code 3.
     *                     WebDriverManager class
     */
    public void initialization(String url) {
        //String url = getTestURL(CommonUtils.readPropertyfile(FilePaths.webUIUserConfig).getProperty("ApplicationURL"));
        String browser = browserName();
        Capabilities cap = null;
        CommonUtils.writePropertyFile("OSName", System.getProperty("os.name").toLowerCase(), FilePaths.webUIUserConfig);
        if (browser.equalsIgnoreCase("chrome") || browser.isEmpty() || browser.equalsIgnoreCase("Google chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Internet Explorer") || browser.equalsIgnoreCase("IE")
                || browser.equalsIgnoreCase("InternetExplorer")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("FireFox") || browser.equalsIgnoreCase("FF")) {
            WebDriverManager.iedriver().setup();
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("Microsoft Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("headless chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1200x600");
            driver = new ChromeDriver();
            cap = ((RemoteWebDriver) driver).getCapabilities();
//            ChromeDriverService service = new ChromeDriverService.Builder()
//                    .usingAnyFreePort()
//                    .withVerbose(true)
//                    .build();
//            try {
//                service.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        try {
            driver.get(url);
        } catch (Exception e) {
            log.info("Please enter the URL in WebUIUserConfig.properties file ");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, 20);
        cap = ((RemoteWebDriver) driver).getCapabilities();
        CommonUtils.writePropertyFile("BrowserVersion", cap.getVersion(), FilePaths.webUIUserConfig);
    }


    /**
     * Use this method to click on an element on the current page.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     */
    public void clickOnElement(String locator) {
        WebElement ele = getElement(locator);
        if (verifyElementIsClickable(ele))
            ele.click();
    }


    public void browserNavigation_FwdORBkd(String enterBackOrForwardNavigation) throws Exception {
        switch (enterBackOrForwardNavigation.toLowerCase()) {
            case "backward":
            case "back":
                driver.navigate().back();
                Thread.sleep(1000);
                log.info("navigated backwards");
                break;
            case "forward":
                driver.navigate().forward();
                Thread.sleep(1000);
                log.info("navigated forward");
                break;
        }
    }

    public void pageRefresh() {
        driver.navigate().refresh();
        log.info("Page refreshed");
    }

    /**
     * Use this method to verify whether the element is clickable
     *
     * @param element The web element of the current page
     * @return A boolean value True, if the element is clickable else False.
     */
    public boolean verifyElementIsClickable(WebElement element) {
        try {
            ExpectedConditions.elementToBeClickable(element);
            log.info("Element is clickable");
        } catch (Exception e) {
            log.info("Element is not clickable");
            return false;
        }
        return true;
    }

    /**
     * Use this method to simulate typing text into an element which may set its
     * value
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @param value   Character sequence to be send to the element
     */
    public void setElementText(String locator, String value) {
        getElement(locator).sendKeys(value);
    }

    public void clear(String locator) {
        getElement(locator).clear();
    }

    public String getTitle() {
        return driver.getCurrentUrl();
    }

    public String getAttribute(String locator, String attribute) {
        return getElement(locator).getAttribute(attribute);
    }

    public String getTextOfElement(String element) {
        return getElement(element).getText();
    }

    public String getAttributeValue(String element) {
        return getElement(element).getAttribute("outerText");
    }

    public boolean elementDisplayed(String locator) {
        try {
            if (getElement(locator).isDisplayed()) {
                log.info("Element " + locator + " is  displayed");
                highlightElement(getElement(locator));

            }

        } catch (Exception nse) {
            log.info("Element " + locator + " is not displayed");
            return false;
        }
        return true;

    }

    public boolean elementEnabled(String locator) {
        if (getElement(locator).isEnabled())
            return true;
        else
            return false;

    }

    public boolean elementDisplayedByJS(String locator) {
        try {
            if (getElementByJS(locator).isDisplayed()) {
                log.info("Element " + locator + " is  displayed");

            }
        } catch (Exception nse) {
            log.info("Element " + locator + " is not displayed");
            return false;
        }
        return true;

    }


    /**
     * Use this method to scroll into view the element on the browser window
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return
     */
    public WebElement scrollToElement(String locator) {
        WebElement ele = getElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", ele);
        if (ele.isDisplayed()) {
            log.info("Element is visible after scrolling down");

            return ele;
        } else {
            log.info("Element is not visible after scrolling down");
        }
        return ele;
    }

    /**
     * This Function Clicks on an element using Javascript
     *
     * @param locator
     */
    public void clickOnElementUsingJS(String locator) {
        WebElement ele = getElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", ele);
    }

    
    private boolean verifyElementToBeClickable(WebElement ele) {
        try {
            ExpectedConditions.elementToBeClickable(ele);
        } catch (Exception e) {
            log.info(ele + " is not clickable");
            return false;
        }
        return true;
    }

    /**
     * This functions double click on an element using action class double click method
     *
     * @param button
     */
    public void doubleClick(String button) {
        WebElement ele = getElement(button);
        Actions actions = new Actions(driver);
        if (verifyElementToBeClickable(ele))
            actions.doubleClick(ele).perform();
        else
            log.info("Element " + ele + " is not clickable");
    }

    /**
     * This method handles unexpected browser popup by clicking on left mouse key for closing the popups
     *
     * @throws AWTException
     */
    public void handelBrowserPopUp() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    /**
     * Use this method to get the element by using Javascript executor
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return
     */
    public WebElement getElementByJS(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String locatorValue = getLocatorValue(locator);
        WebElement eles = (WebElement) js.executeScript("var a = document.evaluate(\"" + locatorValue
                + "\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null); " +
                "if (a.snapshotLength > 0) { " +
                "   return a.snapshotItem(0);" +
                " };");

        log.info("In get element by JS method: " + eles);
        return eles;
    }

    /**
     * Use this method to scroll into view the element and click on that
     * element.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     */
    public void scrollAndClick(String locator) {
        scrollToElement(locator).click();
    }

    /**
     * A method to split the locator string containing locator type and locator
     * value and return the locator value.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return A string containing the locator type like xpath, css, id,
     * linktext as defined in the page's property file.
     */
    private String getLocatorType(String locator) {
        String type = splitString(locator, "]: ", 0);
        return type.substring(1).toUpperCase();
    }

    /*
     * -?- Why have created a repeated function for split string? The index is
     * not being used.
     */
    private String splitString(String locator, String regEx, int index) {
        String[] inputSpilt = locator.split(regEx);
        return inputSpilt[index];
    }

    /**
     * A method to split the locator string containing locator type and locator
     * value and return the locator value.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return A string containing the locator value
     */
    private String getLocatorValue(String locator) {
        return splitString(locator, "]: ", 1);
    }

    /**
     * Find the first WebElement using the given method. This method is affected
     * by the 'explicit wait' times in force at the time of execution. The
     * getElement(..) invocation will return a matching row, or try again
     * repeatedly until the configured timeout is reached. getElement should not
     * be used to look for non-present elements, use getElements(..) and assert
     * zero length response instead.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return The first matching element on the current page.
     */
    public WebElement getElement(String locator) {
        String locatorType = getLocatorType(locator);
        String locatorValue = getLocatorValue(locator);
        WebElement element = null;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch (locatorType) {
            case "XPATH":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
                break;
            case "CSS":
            case "CSSSELECTOR":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
                break;
            case "NAME":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
                break;
            case "CLASSNAME":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
                break;
            case "LINKTEXT":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
                break;
            case "PARTIALLINKTEXT":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
                break;
            case "TAGNAME":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
                break;
            case "ID":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
                break;
        }

        jse.executeScript("arguments[0].style.border='cyan solid 3px' ", element);
        return element;

    }

    public void highlightElement(WebElement ele) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style.border='cyan solid 3px' ", ele);

    }


    public List<WebElement> getListOfElements(String locator) {
        String locatorType = getLocatorType(locator);
        String locatorValue = getLocatorValue(locator);
        List<WebElement> listOfElements = null;
        switch (locatorType) {
            case "XPATH":
                //listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
            	listOfElements = driver.findElements(By.xpath(locatorValue));
                break;
            case "CSS":
            case "CSSSELECTOR":
                listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorValue)));
                break;
            case "NAME":
                listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locatorValue)));
                break;
            case "CLASSNAME":
                listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locatorValue)));
                break;
            case "LINKTEXT":
                listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorValue)));
                break;
            case "PARTIALLINKTEXT":
                listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locatorValue)));
                break;
            case "TAGNAME":
                listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locatorValue)));
                break;
            case "ID":
                listOfElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
                break;
        }

        return listOfElements;
    }

    public static void tearDown() {
        driver.close();
        driver.quit();
    }

    public static String imageToBase64Coversion(String imagePath) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(imagePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }

    /*
     * This method returns the random alphabetic string of length in param
     */

    public boolean imageComparison(File capturedImage, File expectedImage) throws IOException {

        BufferedImage actualBufferedImage = ImageIO.read(capturedImage);
        BufferedImage expectedBufferedImage = ImageIO.read(expectedImage);
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualBufferedImage, expectedBufferedImage);
        return diff.hasDiff();

    }

    /*
     * This method used to do right click
     */
    public void rightClick(String locator) {
        WebElement ele = getElement(locator);
        Actions actions = new Actions(driver);
        actions.contextClick(ele).perform();
    }

    public void select(String locator, String Value) {
        WebElement ele = getElement(locator);
        Select obj = new Select(ele);
        obj.selectByVisibleText(Value);

    }
}

