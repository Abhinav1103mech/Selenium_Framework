package com.pinelabs.RnD.AndroidUI.Constants;

public class URI {

    public static final String baseURL = "https://14.141.92.54:8202/API/IrisService/V2";
    public static final String simulatorBaseURL = "http://localhost:9891";
    public static final String directSimulatorBaseYRL="http://15.207.64.209:9891";
    public static final String getJwtToken = "/auth/getJWTToken";
    public static final String getParametersFromPineCloud = "/sync/IrisGetParametersFromPineCloud";
    public static final String upiCallback = "/icici/authorize";
    public static final String upiAmazonPay = "/amazon/authorize";
    public static final String irisUploadProductPriceDetails = "/records/IrisUploadProductPriceDetails";
    public static final String irisGetParametersFromPineCloud = "/sync/IrisGetParametersFromPineCloud";
    public static final String irisUploadShiftDetails = "/records/IrisUploadShiftDetails";
    public static final String irisUpdateLubeStockDetails = "/records/IrisUpdateLubeStockDetails";
    public static final String irisUploadLocalAccPaymentDetails = "/records/IrisUploadLocalAccPaymentDetails";
    public static final String irisUploadLubeSaleDetails = "/records/IrisUploadLubeSaleDetails";
    public static final String irisUploadTransactionDetailsToken = "/records/IrisUploadTransactionDetails";
    public static final String insertAndUpdateLocationParams = "/sync/InsertAndUpdateLocationParams";
    public static final String irisBookClaimFuelStatus = "/prebook/IrisBookClaimFuelStatus";
    public static final String irisGetClaimFuelDetails="/prebook/IrisGetClaimFuelDetails";
    public static final String irisGetHardwareDetailsToSync="/IrisGetHardwareDetailsToSync";
    public static final String irisUploadBookFuelDetails="/prebook/IrisUploadBookFuelDetails";

}
