package com.pinelabs.RnD.AndroidTest;

import com.pinelabs.RnD.API.Builders.UpiCallbackIcici;
import com.pinelabs.RnD.AndroidUI.Base.AppiumUtilities;
import com.pinelabs.RnD.AndroidUI.POM.DeviceHomePage;
import com.pinelabs.RnD.AndroidUI.POM.PaymentsApp.CommonHelperPage;
import com.pinelabs.RnD.AndroidUI.POM.PaymentsApp.PaymentsUPIValidationsPage;
import com.pinelabs.RnD.CommonUtils.CommonUtils;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class PaymentsUPIValidationsTest extends TestUtils {
    PaymentsUPIValidationsPage paymentsUPIValidationInstance;
    DeviceHomePage deviceHomePageInstance;
    UpiCallbackIcici upiCallbackIciciInstance;
    CommonHelperPage commonHelperPageInstance;
    Map<String, String> value = new HashMap<>();

    @BeforeClass
    public void getInstances() {
        AppiumUtilities.setUpConnection();
        paymentsUPIValidationInstance = getPaymentsUPIValidationInstance();
        deviceHomePageInstance = getDeviceHomePageInstance();
        commonHelperPageInstance = getCommonHelperPageInstance();
        upiCallbackIciciInstance = UpiCallbackIcici.getInstance(UpiCallbackIcici.defaultrequest);
      //  ExtentReport.initialiseReport();
    }

//    @AfterMethod
//    public void getResult(ITestResult result) {
//        ExtentReport.generateReport(result);
//    }

    @Test(description = "Validate the E2E UPI transaction through Payments App")
    public void iCICI_UPI_001() {
        deviceHomePageInstance.openPaymentsApp();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        paymentsUPIValidationInstance.selectUPISaleRequest();
        commonHelperPageInstance.enterAmount("1");
        commonHelperPageInstance.enterInvoiceNumber(CommonUtils.generateRandonNumber(6));
        commonHelperPageInstance.selectUPIpaymode("ICICI");

        // commonHelperPageInstance.scanQR();
        commonHelperPageInstance.clickProceedOnReceipt();
//        String merchID = commonHelperPageInstance.getValueFromQR("tr");
//        upiCallbackIciciInstance.getRequest().setMerchantTranId(merchID);
//        upiCallbackIciciInstance.getRequest().setTxnStatus("SUCCESS");
//        upiCallbackIciciInstance.createAndExecute();
        System.out.println( "Client ID is " +commonHelperPageInstance.validateValuesFromChargeslip("CLIENT ID"));
        System.out.println( "MID is " + commonHelperPageInstance.validateValuesFromChargeslip("MID"));
        // String d = commonHelperPageInstance.validateCompletionStatus();
//        System.out.println("completion status is " + d);
        System.out.println("Batch Id is " + commonHelperPageInstance.validateValuesFromChargeslip("BATCH ID"));
        System.out.println("ROC Id is " + commonHelperPageInstance.validateValuesFromChargeslip("ROC"));
        commonHelperPageInstance.clickProceedOnReceipt();

//        Assert.assertEquals(c, "293188");
//        Assert.assertEquals(m, "5651");
        //Assert.assertEquals(d, "UPI SALE COMPLETE");
//        Assert.assertEquals(upiCallbackIciciInstance.getResponse().getErroCode(), "00");
//        Assert.assertEquals(upiCallbackIciciInstance.getResponse().getErrorMsg(), "Success");

    }

    @Test(description = "Verify the ICICI UPI \"Charge\" Transaction.")
    public void iCICI_UPI_002() {

    }

    @Test(description = "Verify the ICICI UPI \"GetStatus- Last\" Transaction.")
    public void iCICI_UPI_003() {
        deviceHomePageInstance.openPaymentsApp();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        commonHelperPageInstance.getLastTransaction();
        commonHelperPageInstance.enterInvoiceNumber(CommonUtils.generateRandonNumber(5));
        System.out.println("CLIENT Id is " + commonHelperPageInstance.validateValuesFromChargeslip("CLIENT ID"));
        System.out.println("MID is " + commonHelperPageInstance.validateValuesFromChargeslip("MID"));
        System.out.println("Batch Id is " + commonHelperPageInstance.validateValuesFromChargeslip("BATCH ID"));
        System.out.println("ROC Id is " + commonHelperPageInstance.validateValuesFromChargeslip("ROC"));
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Verify the ICICI UPI \"GetStatus- Any\" Transaction.")
    public void iCICI_UPI_004() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        commonHelperPageInstance.getAnyTransaction(233, 9006);
        System.out.println("CLIENT Id is " + commonHelperPageInstance.validateValuesFromChargeslip("CLIENT ID"));
        System.out.println("MID is " + commonHelperPageInstance.validateValuesFromChargeslip("MID"));
        System.out.println("Batch Id is " + commonHelperPageInstance.validateValuesFromChargeslip("BATCH ID"));
        System.out.println("ROC Id is " + commonHelperPageInstance.validateValuesFromChargeslip("ROC"));
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Verify the ICICI_UPI \"GetStatus\" Transaction with incorrect Batch ID.")
    public void iCICI_UPI_005() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        commonHelperPageInstance.getAnyTransaction(233, 94812);
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Verify the ICICI_UPI \"GetStatus\" Transaction with incorrect ROC ID.")
    public void iCICI_UPI_005a() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        commonHelperPageInstance.getAnyTransaction(23312, 9006);
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Verify the ICICI_UPI \"GetStatus\" Transaction with incorrect Batch ID and ROC ID.")
    public void iCICI_UPI_005b() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        commonHelperPageInstance.getAnyTransaction(23312, 900612);
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Perform ICICI UPI Transactions (4-Sale, 1-Void and 1-Reversal) and try to do batch settlement.")
    public void iCICI_UPI_010() {

    }

    // add duplicate copy validation
    @Test(description = "Generate \"Reprint-Last\" transaction chargeslip after successful ICICI_UPI transaction.")
    public void iCICI_UPI_011() {
        deviceHomePageInstance.openPaymentsApp();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        commonHelperPageInstance.selectReprint();
        commonHelperPageInstance.getLastTransaction();
        commonHelperPageInstance.enterInvoiceNumber(CommonUtils.generateRandonNumber(5));
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Generate \"Reprint - Any\" transaction chargeslip after successful ICICI_UPI transaction.")
    public void iCICI_UPI_012() {
        deviceHomePageInstance.openPaymentsApp();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        commonHelperPageInstance.selectReprint();
        commonHelperPageInstance.getAnyTransaction(123, 9009);
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Perform ICICI UPI Transaction (4-Sale, 1-Void and 1-Reversal) after that try to Generate Reports - Curr. Batch Summary after ")
    public void iCICI_UPI_013() {

    }

    @Test(description = "Perform ICICI UPI Transaction (4-Sale, 1-Void and 1-Reversal) after that try to Generate  \"Reports - Batch Detail Summary\"")
    public void iCICI_UPI_014() {

    }

    @Test(description = "Perform ICICI UPI Transaction (4-Sale, 1-Void and 1-Reversal) After that Settle Batch, then try to Generate Reports - Last Batch Summary ")
    public void iCICI_UPI_015() {

    }

    @Test(description = "Perform ICICI UPI Sale Transaction with QR code display time out  when successful payment done after time out.")
    public void iCICI_UPI_016() {

    }

    @Test(description = "Perform ICICI UPI Sale Transaction with QR code display time out when payment not done after time out.")
    public void iCICI_UPI_017() {

    }

    @Test(description = "Verify the ICICI UPI Sale Transaction when Host is unavailable/Not responding.")
    public void iCICI_UPI_019() {

    }

    @Test(description = "verify ICICI UPI settlement when only reversal in a batch")
    public void iCICI_UPI_020() {

    }

    @Test(description = "Verify the ICICI UPI Transaction and call back not received in certian time and auto\" Get status\" has been called.")
    public void iCICI_UPI_022() {

    }

    @Test(description = "Verify the ICICI UPI Transaction and Time out occurred and payment done.")
    public void iCICI_UPI_023() {

    }

    @Test(description = "Verify the ICICI UPI Transaction and Time out occurred and payment not done.")
    public void iCICI_UPI_024() {

    }

    @Test(description = "Verify the ICICI UPI settlement when only void in a batch.")
    public void iCICI_UPI_025() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        paymentsUPIValidationInstance.selectVoid();
        commonHelperPageInstance.enterBatchID(9011);
        commonHelperPageInstance.enterROCID(172);
        commonHelperPageInstance.enterAmount("1");
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Verify the ICICI UPI settlement when only void Reversal in a batch.")
    public void iCICI_UPI_026() {

    }

    @Test(description = "Verify the ICICI UPI settlement when ICICI UPI host is not availabale/Not responding")
    public void iCICI_UPI_027() {

        // In sql server auxi db database and table name: upi_hostConfigtable: column : upiHostiporURLenter a dummy value . save.
        // auxiControllerServer pe login
        // and settle the device and do transaction
    }

    // add error assertion
    @Test(description = "Validate the void in UPI transaction for already void ROC ID")
    public void iCICI_UPI_028() {
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        paymentsUPIValidationInstance.selectVoid();
        commonHelperPageInstance.enterBatchID(9009);
        commonHelperPageInstance.enterROCID(123);
        commonHelperPageInstance.enterAmount("1");

    }

    @Test(description = "Validate the reprint receipt of last void UPI transaction  ")
    public void iCICI_UPI_029() {
        deviceHomePageInstance.openPaymentsApp();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        paymentsUPIValidationInstance.selectVoid();
        commonHelperPageInstance.enterBatchID(9009);
        commonHelperPageInstance.enterROCID(123);
        commonHelperPageInstance.enterAmount("1");
        commonHelperPageInstance.clickProceedOnReceipt();
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        commonHelperPageInstance.selectReprint();
        commonHelperPageInstance.getLastTransaction();
        commonHelperPageInstance.enterInvoiceNumber(CommonUtils.generateRandonNumber(5));
        commonHelperPageInstance.clickProceedOnReceipt();
    }

    @Test(description = "Validate the reprint receipt of last void UPI transaction  ")
    public void iCICI_UPI_030() {
        deviceHomePageInstance.openPaymentsApp();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        paymentsUPIValidationInstance.selectVoid();
        commonHelperPageInstance.enterBatchID(9009);
        commonHelperPageInstance.enterROCID(123);
        commonHelperPageInstance.enterAmount("1");
        commonHelperPageInstance.clickProceedOnReceipt();
        commonHelperPageInstance.openPaytmFromHome();
        commonHelperPageInstance.clickOnFingerIcon();
        commonHelperPageInstance.clickBrowseOtherOptions();
        paymentsUPIValidationInstance.searchPaymentMode("UPI");
        commonHelperPageInstance.selectReprint();
        commonHelperPageInstance.getLastTransaction();
        commonHelperPageInstance.enterInvoiceNumber(CommonUtils.generateRandonNumber(5));
        commonHelperPageInstance.clickProceedOnReceipt();
    }


  //  @Test
    public void sqlDbConnection() {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://192.168.101.69:51633;databaseName=PLUTUS_HUBDB;integratedSecurity=true");
            System.out.println("SQL Connection established");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from pl_client_hardware_details_tbl where client_id=292371");
            while (rs.next())
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR occured when connecting to SQL");
        }
    }
}
