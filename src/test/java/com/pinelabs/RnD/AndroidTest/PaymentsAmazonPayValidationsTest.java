package com.pinelabs.RnD.AndroidTest;

import com.pinelabs.RnD.API.Builders.UpiAmazonPay;
import com.pinelabs.RnD.AndroidUI.Base.AppiumUtilities;
import com.pinelabs.RnD.AndroidUI.POM.DeviceHomePage;
import com.pinelabs.RnD.AndroidUI.POM.PaymentsApp.CommonHelperPage;
import com.pinelabs.RnD.AndroidUI.POM.PaymentsApp.PaymentsAmazonPayValidationsPage;
import com.pinelabs.RnD.AndroidUI.POM.PaymentsApp.PaymentsUPIValidationsPage;
import com.pinelabs.RnD.CommonUtils.CommonUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/*
public class PaymentsAmazonPayValidationsTest extends TestUtils {
    PaymentsAmazonPayValidationsPage paymentsAmazonPayInstance;
    DeviceHomePage deviceHomePageInstance;
    UpiAmazonPay upiAmazonPayInstance;
    CommonHelperPage commonHelperPageInstance;
    PaymentsUPIValidationsPage paymentsUPIValidationInstance;
    String qrValues;

    @BeforeClass
    public void getInstances() {
        AppiumUtilities.setUpConnection();
        paymentsAmazonPayInstance = getPaymentsAmazonPayValidationsPage();
        deviceHomePageInstance = getDeviceHomePageInstance();
        commonHelperPageInstance = getCommonHelperPageInstance();
        paymentsUPIValidationInstance = getPaymentsUPIValidationInstance();
        upiAmazonPayInstance = UpiAmazonPay.getInstance(UpiAmazonPay.defaultrequest);
    }

    @Test(description = "Verify the Amazon Pay Transaction sync mode.")
    public void paymentsAmazonPay_TC001() {
        deviceHomePageInstance.openPaymentsApp();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsAmazonPayInstance.selectPaymentMode("Amazon Pay");
        paymentsAmazonPayInstance.selectAmazonPaySale();
        commonHelperPageInstance.enterAmount("1");
        commonHelperPageInstance.scanQR();
        commonHelperPageInstance.clickProceedOnReceipt();
        qrValues = commonHelperPageInstance.getValueFromQR("tr");
       // upiAmazonPayInstance.getRequest().setSellerOrderId(qrValues);
        //System.out.println("Order ID is " + sellerOrderID);
        qrValues = commonHelperPageInstance.getValueFromQR("mi");
        upiAmazonPayInstance.getRequest().setMerchantID(qrValues);
        // System.out.println("Merchant ID is " + merchantID);
        String totalAmt = commonHelperPageInstance.getValueFromQR("am");
        upiAmazonPayInstance.getRequest().setOrderTotalAmount("1.0");
        // System.out.println("total amount is " + totalAmt);
//        upiAmazonPayInstance.getRequest().setMerchantID(merchantID);
//        upiAmazonPayInstance.getRequest().setOrderTotalAmount("1.0");
//        upiAmazonPayInstance.getRequest().setSellerOrderId(sellerOrderID);
        upiAmazonPayInstance.createAndExecute();
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Validate the status of Last amazonPay transaction")
    public void paymentsAmazonPay_TC002() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsAmazonPayInstance.selectPaymentMode("Amazon Pay");
        commonHelperPageInstance.getLastTransaction();
    }

    @Test(description = "Validate the status of any amazonPay transaction based on provided batch ID and ROC ID")
    public void paymentsAmazonPay_TC003() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsAmazonPayInstance.selectPaymentMode("Amazon Pay ");
        commonHelperPageInstance.getAnyTransaction(233, 9006);

    }

    @Test(description = "Validate the reprint receipt of  Last amazonPay transaction")
    public void paymentsAmazonPay_TC004() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsAmazonPayInstance.selectPaymentMode("Amazon Pay");
        commonHelperPageInstance.selectReprint();
        commonHelperPageInstance.getLastTransaction();

    }

    @Test(description = "Validate the reprint receipt of any amazonPay transaction")
    public void paymentsAmazonPay_TC005() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsAmazonPayInstance.selectPaymentMode("Amazon Pay");
        commonHelperPageInstance.selectReprint();
        commonHelperPageInstance.getAnyTransaction(120, 9009);
    }

    @Test(description = "Validate the amazonPay reorts of current batch summary")
    public void paymentsAmazonPay_TC006() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsAmazonPayInstance.selectPaymentMode("Amazon Pay");
        paymentsAmazonPayInstance.selectUPIReports();
        paymentsAmazonPayInstance.getCurrBatchSummary();
        commonHelperPageInstance.getLastTransaction();

    }

    @Test(description = "Validate the amazonPay reports of Last batch summary")
    public void paymentsAmazonPay_TC007() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsAmazonPayInstance.selectPaymentMode("Amazon Pay");
        paymentsAmazonPayInstance.selectUPIReports();
        paymentsAmazonPayInstance.getLastBatchSummary();
        commonHelperPageInstance.getAnyTransaction(233, 9006);
    }

    @Test(description = "Validate the amazonPay batch Detailed report")
    public void paymentsAmazonPay_TC008() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsAmazonPayInstance.selectPaymentMode("Amazon Pay");
        paymentsAmazonPayInstance.selectUPIReports();
        paymentsAmazonPayInstance.getBatchDetailReport();
        commonHelperPageInstance.getAnyTransaction(233, 9006);
    }

    @Test(description = "Validate the reprint receipt of last void amazonPay transaction  ")
    public void paymentsAmazonPay_TC009() {
        deviceHomePageInstance.openPaymentsApp();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("Amazon Pay");
        paymentsUPIValidationInstance.selectVoid();
        commonHelperPageInstance.enterBatchID(9009);
        commonHelperPageInstance.enterROCID(123);
        commonHelperPageInstance.enterAmount("1");
        commonHelperPageInstance.clickProceedOnReceipt();
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("Amazon Pay");
        commonHelperPageInstance.selectReprint();
        commonHelperPageInstance.getLastTransaction();
        commonHelperPageInstance.enterInvoiceNumber(CommonUtils.generateRandonNumber(5));
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Validate the reprint receipt of last void amazonPay transaction  ")
    public void paymentsAmazonPay_TC010() {
        deviceHomePageInstance.openPaymentsApp();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("Amazon Pay");
        paymentsUPIValidationInstance.selectVoid();
        commonHelperPageInstance.enterBatchID(9009);
        commonHelperPageInstance.enterROCID(123);
        commonHelperPageInstance.enterAmount("1");
        commonHelperPageInstance.clickProceedOnReceipt();
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("Amazon Pay");
        commonHelperPageInstance.selectReprint();
        commonHelperPageInstance.getLastTransaction();
        commonHelperPageInstance.enterInvoiceNumber(CommonUtils.generateRandonNumber(5));
        commonHelperPageInstance.clickProceedOnReceipt();
    }


}

*/
