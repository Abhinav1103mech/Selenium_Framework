package com.pinelabs.RnD.AndroidUI.POM.PaymentsApp;

import com.pinelabs.RnD.AndroidUI.Base.AppiumUtilities;
import com.pinelabs.RnD.CommonUtils.CommonUtils;
import com.pinelabs.RnD.AndroidUI.Constants.FilePaths;

import java.util.Properties;

public class PaymentsAmazonPayValidationsPage extends AppiumUtilities {

    static PaymentsAmazonPayValidationsPage paymentsAmazonPayValidationsPage;
    static Properties commonLocatorsProperties;
    static Properties paymentsAmazonPayProperties;

    private PaymentsAmazonPayValidationsPage() {

        commonLocatorsProperties = CommonUtils.readPropertyfile(FilePaths.commonLocPropertiesPath);
        paymentsAmazonPayProperties = CommonUtils.readPropertyfile(FilePaths.amazonPayPropertiesPath);

    }

    public static PaymentsAmazonPayValidationsPage getInstance() {
        if (paymentsAmazonPayValidationsPage == null)
            paymentsAmazonPayValidationsPage = new PaymentsAmazonPayValidationsPage();
        return paymentsAmazonPayValidationsPage;
    }

    public void selectAmazonPaySale() {

        clickOnElement(paymentsAmazonPayProperties.getProperty("amazonPaySale"));
    }

    public void selectPaymentMode(String payMode) {
        clickOnElement(commonLocatorsProperties.getProperty("searchButton"));
        setElementText(commonLocatorsProperties.getProperty("payModeText"), payMode);
        System.out.println("Selected  paymode:" + payMode);
        clickOnElement(paymentsAmazonPayProperties.getProperty("amazonPayPaymode"));
    }


    public void selectUPIReports() {

        clickOnElement(paymentsAmazonPayProperties.getProperty("upiReports"));
    }

    public void getCurrBatchSummary() {

        clickOnElement(paymentsAmazonPayProperties.getProperty("currentBatch"));
    }

    public void getLastBatchSummary() {
        clickOnElement(paymentsAmazonPayProperties.getProperty("lastBatch"));
    }

    public void getBatchDetailReport() {
        clickOnElement(paymentsAmazonPayProperties.getProperty("batchDetail"));
    }

}
