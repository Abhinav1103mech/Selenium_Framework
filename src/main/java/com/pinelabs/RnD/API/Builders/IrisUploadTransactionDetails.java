package com.pinelabs.RnD.API.Builders;

import com.pinelabs.RnD.API.Base.BaseUtilsAPI;
import com.pinelabs.RnD.API.PojoFiles.IrisUploadTransactionDetailsRequest;
import com.pinelabs.RnD.API.PojoFiles.IrisUploadTransactionDetailsResponse;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.pinelabs.RnD.AndroidUI.Constants.URI;
import io.restassured.response.Response;

public class IrisUploadTransactionDetails extends BaseUtilsAPI {
    static IrisUploadTransactionDetails irisUploadTransactionDetails;
    IrisUploadTransactionDetailsRequest irisUploadTransactionDetailsRequest;
    IrisUploadTransactionDetailsResponse irisUploadTransactionDetailsResponse;
    public static String defaultRequest = "{\"HardwareId\":\"0820771204\",\"NozzleNum\":1,\"PumpNum\":3,\"ROid\":180664,\"lAmount\":8654,\"lCreditLimit\":0,\"lDiscount\":0,\"lNetAmount\":8654,\"lOutstandingAmount\":0,\"lPresetValue\":100000,\"lProductPrice\":10818,\"lShiftNum\":0,\"lTxnStatus\":0,\"lVolume\":80,\"strAttendant\":\"\",\"strCashMemoNum\":\"\",\"strCommMode\":\"1\",\"strLubeCode\":\"\",\"strMobileNumber\":\"+91\",\"strPLTrnxId\":\"0820771204267723403\",\"strPaymode\":\"Cash\",\"strPresetType\":\"LocalAmount\",\"strProductName\":\"PETROL\",\"strResponseCode\":\"\",\"strTransactionEndDateTime\":\"2021-10-1813:57:55\",\"strTransactionStartDateTime\":\"2021-10-1813:57:05\",\"strUniqueId\":\"1101808362\",\"strVechSeg\":\"TwoWheeler\",\"strVechicleNumber\":\"DL7SBX1397\"}";
    String request;

    private IrisUploadTransactionDetails(String request) {
        this.request = request;
        irisUploadTransactionDetailsRequest = stringToJavaObject(this.defaultRequest, IrisUploadTransactionDetailsRequest.class);

    }

    public static IrisUploadTransactionDetails getInstance(String request) {
        if (irisUploadTransactionDetails == null)
            irisUploadTransactionDetails = new IrisUploadTransactionDetails(request);
        return irisUploadTransactionDetails;
    }


    public IrisUploadTransactionDetailsResponse createAndExecute(String token) {
        request = javaObjectToString(irisUploadTransactionDetailsRequest);
        setMethod(EnumsRepo.methodName.POST);
        Response response = execute(request, URI.irisUploadTransactionDetailsToken, URI.baseURL, token);
        irisUploadTransactionDetailsResponse = stringToJavaObject(response.asString(), IrisUploadTransactionDetailsResponse.class);
        return irisUploadTransactionDetailsResponse;
    }

    public IrisUploadTransactionDetailsResponse getResponse() {
        return irisUploadTransactionDetailsResponse;
    }

    public IrisUploadTransactionDetailsRequest getRequest() {
        return irisUploadTransactionDetailsRequest;
    }
}
