package com.pinelabs.RnD.API.Builders;

import com.pinelabs.RnD.API.Base.BaseUtilsAPI;
import com.pinelabs.RnD.API.PojoFiles.IrisUploadLocalAccPaymentDetailsRequest;
import com.pinelabs.RnD.API.PojoFiles.IrisUploadLocalAccPaymentDetailsResponse;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.pinelabs.RnD.AndroidUI.Constants.URI;
import io.restassured.response.Response;

public class IrisUploadLocalAccPaymentDetails extends BaseUtilsAPI {
    static IrisUploadLocalAccPaymentDetails irisUploadLocalAccPaymentDetails;
    IrisUploadLocalAccPaymentDetailsRequest irisUploadLocalAccPaymentDetailsRequest;
    IrisUploadLocalAccPaymentDetailsResponse irisUploadLocalAccPaymentDetailsResponse;
   public static  String defaultRequest = "{\"HardwareId\":\"0821397407\",\"AccountNumber\":\"1561234567\",\"VoucherId\":\"Pine123456\",\"ROid\":123456,\"PaymentAmount\":1500,\"PayMode\":\"CASH\",\"PaidAmount\":1500,\"OutstandingAmount\":10,\"LocalAccountPaymentDateTime\":\"2021-04-22 20:35:41\"}";
    String request;

    private IrisUploadLocalAccPaymentDetails(String request) {
        this.request = request;
        irisUploadLocalAccPaymentDetailsRequest = stringToJavaObject(this.request, IrisUploadLocalAccPaymentDetailsRequest.class);
    }

    public static IrisUploadLocalAccPaymentDetails getInstance(String request) {
        if (irisUploadLocalAccPaymentDetails == null)
            irisUploadLocalAccPaymentDetails = new IrisUploadLocalAccPaymentDetails(request);
        return irisUploadLocalAccPaymentDetails;
    }

    public IrisUploadLocalAccPaymentDetailsResponse createAndExecute(String token) {
        request = javaObjectToString(irisUploadLocalAccPaymentDetailsRequest);
        setMethod(EnumsRepo.methodName.POST);
        Response response = execute(request, URI.irisUploadLocalAccPaymentDetails, URI.baseURL, token);
        irisUploadLocalAccPaymentDetailsResponse = stringToJavaObject(response.asString(), IrisUploadLocalAccPaymentDetailsResponse.class);
        return irisUploadLocalAccPaymentDetailsResponse;
    }

    public IrisUploadLocalAccPaymentDetailsResponse getResponse() {
        return irisUploadLocalAccPaymentDetailsResponse;
    }

    public IrisUploadLocalAccPaymentDetailsRequest getRequest() {
        return irisUploadLocalAccPaymentDetailsRequest;
    }
}
