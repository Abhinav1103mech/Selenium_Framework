package com.pinelabs.RnD.AndroidUI.POM.PaymentsApp;

import com.pinelabs.RnD.AndroidUI.Base.AppiumUtilities;
import com.pinelabs.RnD.CommonUtils.CommonUtils;
import com.pinelabs.RnD.AndroidUI.Constants.FilePaths;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommonHelperPage extends AppiumUtilities {
    static CommonHelperPage commonHelperPage;
    static Properties commonLocatorsProperties;
    static Properties deviceHome;
    static Properties paymentsUPIProperties;

    static Map<String, String> values = new HashMap<>();

    private CommonHelperPage() {

        deviceHome = CommonUtils.readPropertyfile(FilePaths.devicePropertiesPath);
        commonLocatorsProperties = CommonUtils.readPropertyfile(FilePaths.commonLocPropertiesPath);
        paymentsUPIProperties = CommonUtils.readPropertyfile(FilePaths.upiPayPropertiesPath);
    }
    //13.234.97.42

    public static CommonHelperPage getInstance() {
        if (commonHelperPage == null)
            commonHelperPage = new CommonHelperPage();
        return commonHelperPage;
    }

    public void openPaytmFromHome() {
        if (isElementDisplayed(deviceHome.getProperty("imageView")))
            clickOnElement(deviceHome.getProperty("imageView"));
        clickOnElement(deviceHome.getProperty("homeAppPayments"));
    }

    public void clickOnFingerIcon() {
        clickOnElement(commonLocatorsProperties.getProperty("fingerIcon"));
        System.out.println("Clicked on Finger icon");
    }

    public void clickBrowseOtherOptions() {
        clickOnElement(commonLocatorsProperties.getProperty("browseOtherOptions"));
        System.out.println("Clicked on browse other options");
    }


    public void enterAmount(String amount) {

        for (int i = 0; i < amount.length(); i++) {
            clickOnElement(String.format(commonLocatorsProperties.getProperty("number"), Character.getNumericValue(amount.charAt(i))));
        }
        clickOnElement(commonLocatorsProperties.getProperty("00Number"));
        System.out.println("Amount requested : " + amount);
        clickOnElement(commonLocatorsProperties.getProperty("submitButton"));
        System.out.println("Clicked on submit amount");
    }

    public void selectUPIpaymode(String paymode) {
        if (isElementDisplayed(paymentsUPIProperties.getProperty("paymodesOption"))) {

            if (paymode.equalsIgnoreCase("ICICI")) {
                if (isElementDisplayed(paymentsUPIProperties.getProperty("iciciOption")))
                    clickOnElement(paymentsUPIProperties.getProperty("iciciOption"));
                System.out.println("Selected ICICI Bank");
            } else if (paymode.equalsIgnoreCase("Amazon Pay")) {
                if (isElementDisplayed(paymentsUPIProperties.getProperty("amazonPayOption")))
                    clickOnElement(paymentsUPIProperties.getProperty("amazonPayOption"));
                System.out.println("Selected AmazonPay Bank");
            }
        }
    }

    private void selectGetStatus() {

        clickOnElement(commonLocatorsProperties.getProperty("getStatus"));
    }

    public void getLastTransaction() {
        selectGetStatus();
        clickOnElement(commonLocatorsProperties.getProperty("lastTransaction"));
        System.out.println("Clicked on last transaction");
    }

    public void getAnyTransaction(int rocID, int batchNumber) {
        // selectGetStatus();
        clickOnElement(commonLocatorsProperties.getProperty("anyTransaction"));
        //enterInvoiceNumber(CommonUtils.generateRandonNumber(5));
        enterBatchID(batchNumber);
        enterROCID(rocID);
//        if (isElementDisplayed(paymentsReceipt.getProperty("invalidIsoPacketError"))) {
//            clickOnElement(paymentsReceipt.getProperty("dismissError"));
//            throw new NotFoundException("Please check Batch ID in Database, Error msg : Invalid ISO Packet");
//        }
//         validateClientAndMID(paymentsReceipt.getProperty("clientIDMID"));

    }

    public void clickProceedOnReceipt() {
        clickOnElement(commonLocatorsProperties.getProperty("proceedButton"));
    }

    public void clickPrintReceiptCopy() {
        clickOnElement(commonLocatorsProperties.getProperty("printButton"));
    }

    public void enterBatchID(int batchID) {
        if (isElementDisplayed(commonLocatorsProperties.getProperty("enterBatchIDHeading")))
            setElementText(commonLocatorsProperties.getProperty("batchID"), Integer.toString(batchID));
        System.out.println("Entered batch Id " + batchID);
        clickOnElement(commonLocatorsProperties.getProperty("submitButton"));
    }

    public void enterROCID(int rocID) {
        if (isElementDisplayed(commonLocatorsProperties.getProperty("enterROCHeading")))
            setElementText(commonLocatorsProperties.getProperty("rocID"), Integer.toString(rocID));
        System.out.println("Entered ROCID " + rocID);
        clickOnElement(commonLocatorsProperties.getProperty("submitButton"));
    }

    public void selectReprint() {
        clickOnElement(paymentsUPIProperties.getProperty("reprint"));
        System.out.println("Clicked on Reprint option under UPI");
    }

    public void enterInvoiceNumber(int invoiceNumber) {
        if (isElementDisplayed(commonLocatorsProperties.getProperty("invoiceHeader"))) {
            System.out.println("On Invoice page");
            setElementText(commonLocatorsProperties.getProperty("billingInvoiceNumber"), Integer.toString(invoiceNumber));
            System.out.println("Entered invoice number: " + invoiceNumber);
            clickOnElement(commonLocatorsProperties.getProperty("submitButton"));
            System.out.println("Submitted invoice number");
        } else
            System.out.println("Invoice text box not displayed");
    }

//    public String scanQR() {
//
//        System.out.println("QR Code Displayed");
//        return decodeQr(commonLocatorsProperties.getProperty("qRCode"));
//    }

    private Map<String, String> getKeyValueFromQR(String qrString) {
        qrString = qrString.split("\\?")[1];
        String[] arr = qrString.split("&");
        for (String d : arr) {
            String[] keyValue = d.split("=");
            values.put(keyValue[0], keyValue[1]);
        }
//        vals.entrySet().forEach(valss -> {
//            System.out.println(valss.getKey() + " " + valss.getValue());
//        });

        return values;
    }

//    public String getValueFromQR(String key) {
//        values = getKeyValueFromQR(scanQR());
//        String value;
//        if (key.equals("tr")) {
//            value = values.get("tr");
//            value = value.substring(value.lastIndexOf("S") + 1);
//            System.out.println(value);
//        } else
//            value = values.get(key);
//        return value;
//    }

//    public String validateClientAndMID(String key) {
//        //System.out.println(getElementText(commonLocatorsProperties.getProperty("clientIDMID")));
//     //   return fetchReceiptValuesbatchID(getElementText(commonLocatorsProperties.getProperty("clientIDMID"))).get(key);
//
//    }

    public String validateValuesFromChargeslip(String key) {
        String newLoc= String.format(commonLocatorsProperties.getProperty("chargeslipKey"),"'"+key+"'");
      //  System.out.println(getElementText(newLoc));
        return fetchValueFromChargeslip(getElementText(newLoc)).get(key);
    }


    public Map<String, String> fetchValueFromChargeslip(String input_String) {

        String[] str = input_String.split(":");
        String number_output = str[1].replaceAll("[^0-9]", "");
        String string_output = str[1].replaceAll("[^A-Za-z]", "");
        values.put(str[0], number_output);
        values.put(string_output, str[2]);
//        System.out.println("Key 1" + str[0]);
//        System.out.println("Value of key 1 " + number_output);
//        System.out.println("Key 2 " + string_output);
//        System.out.println("Value of Key2" + str[2]);
        return values;
    }

    public String validateCompletionStatus() {
        return getElementText(commonLocatorsProperties.getProperty("upiPaymentComplete"));
    }

    public void printReceipt(String yesOrno) {
        if (yesOrno.equalsIgnoreCase("Yes"))
            clickOnElement(commonLocatorsProperties.getProperty("yesBotton"));
        else if (yesOrno.equalsIgnoreCase("No")) {
            clickOnElement(commonLocatorsProperties.getProperty("noButton"));
        }
    }






    public void validateInvalidTxnInputError() {
        if (isElementDisplayed(commonLocatorsProperties.getProperty("invalidTxnInput"))) {
            clickOnElement(commonLocatorsProperties.getProperty("dismissError"));
            // throw new Exception("Invalid Txn Input Error Message Displayed. Please Check!");

        }

    }

    public void closeDriver() {
        tearDown();
    }
}
